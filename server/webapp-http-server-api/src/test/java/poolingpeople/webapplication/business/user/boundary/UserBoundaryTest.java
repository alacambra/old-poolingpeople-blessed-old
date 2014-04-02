package poolingpeople.webapplication.business.user.boundary;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import poolingpeople.webapplication.business.entity.AbstractBoundaryTest;
import poolingpeople.webapplication.business.utils.helpers.FileLoader;

public class UserBoundaryTest extends AbstractBoundaryTest{

	@Inject
	UserBoundary target;
	
	@Test
	public void testGetUserById() throws JsonGenerationException, JsonMappingException, IOException {
		Map<Object,Object> createdUser = insertUserFromFile(userRequestFile);
		Map<Object,Object> expectedProjec = convertJsonFileToMap(userResponseFile);
		expectedProjec.put("id", createdUser.get("id"));

		Response response = target.getUserById((String) createdUser.get("id"));
		
		assertEquals(Response.Status.OK, response.getStatusInfo());
		Map<Object,Object> receivedUserData = convertJsonToMap((String)response.getEntity());

//		assertTrue(mapsAreEquals(expectedProjec, receivedUserData));
	}

	@Test
	public void testGetAllUsers() throws JsonGenerationException, JsonMappingException, IOException{
		List<Map<Object, Object>> expected = createUserListFromUserFile(userRequestFile, 1);
		Response users = target.getAllUsers();
		assertEquals(Response.Status.OK, users.getStatusInfo());

		@SuppressWarnings("unchecked")
		List<Map<Object, Object>> actual = mapper.readValue((String)users.getEntity(), List.class);
//		assertTrue(mapsListAreEquals(expected, actual));
	}

	@Test
	public void testSaveUser() throws JsonGenerationException, JsonMappingException, IOException{
		Map<String,Object> expectedUser = convertJsonFileToMap(userResponseFile);
		Response response = target.saveUser(FileLoader.getText(jsonModelsPath + userRequestFile));
		assertEquals(Response.Status.OK, response.getStatusInfo());
//		Map<String,Object> actual = convertJsonToMap((String) response.getEntity());
//		expectedUser.put("id", actual.get("id"));
//		assertTrue(mapsAreEquals(expectedUser, actual));
	}

	@Test
	public void testUpdateUser() throws JsonGenerationException, JsonMappingException, IOException{
		Map<Object,Object> expected = insertUserFromFile(userRequestFile);
		String json = convertMapToJson(expected);
		Response r = target.updateUser((String) expected.get("id"), json);
		assertEquals(Status.OK.getStatusCode(), r.getStatus());
		Map<Object,Object> actual = convertJsonToMap((String)r.getEntity());
		assertTrue(mapsAreEquals(expected, actual));
	}

	@Test
	public void testDeleteUser() throws JsonGenerationException, JsonMappingException, IOException{
		Map<Object,Object> createdUser = insertUserFromFile(userRequestFile);
		String json = convertMapToJson(createdUser);
		Response r = target.updateUser((String) createdUser.get("id"), json);
		assertEquals(Status.OK.getStatusCode(), r.getStatus());
		Map<Object,Object> receivedUser = convertJsonToMap((String)r.getEntity());
		assertTrue(mapsAreEquals(createdUser, receivedUser));
	}

}
