package poolingpeople.webapplication.business.project.boundary;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Ignore;
import org.junit.Test;

import poolingpeople.webapplication.business.entity.AbstractBoundaryTest;
import poolingpeople.webapplication.business.utils.helpers.FileLoader;

public class ProjectBoundaryTest extends AbstractBoundaryTest {
	
	@Inject
	ProjectBoundary target;
	
	@Test
	public void testGetProjectById() throws JsonGenerationException, JsonMappingException, IOException {
		Map<Object,Object> createdProjectData = insertProjectFromFile(projectRequestFile);
		Map<Object,Object> expected = convertJsonFileToMap(projectResponseFile);
		expected.put("id", createdProjectData.get("id"));

		Response response = target.getProjectById((String) createdProjectData.get("id"));
		assertEquals(Response.Status.OK, response.getStatusInfo());
		Map<Object,Object> actual = convertJsonToMap((String)response.getEntity());

//		assertTrue(mapsAreEquals(expected, actual));
	}

	@Test
	public void testGetAllProjectResponse() throws JsonGenerationException, JsonMappingException, IOException {
		List<Map<String, Object>> expected = createProjectListFromProjectFile(projectRequestFile, projectResponseFile ,3);
		System.out.println(expected);
		Response projects = target.getAllProjects();
		assertEquals(Response.Status.OK, projects.getStatusInfo());

	}
	
	@Test
	@Ignore
	public void testGetAllProjectJson() throws JsonGenerationException, JsonMappingException, IOException {
		
		List<Map<String, Object>> expected = createProjectListFromProjectFile(projectRequestFile, projectResponseFile ,3);
		System.out.println(expected);
		Response projects = target.getAllProjects();

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> actual = mapper.readValue((String)projects.getEntity(), List.class);
		assertTrue(mapsListAreEquals(expected, actual));
	}

	@Test
	public void testSaveProject() throws JsonParseException, JsonMappingException, IOException {
		
		Map<String,Object> expected = convertJsonFileToMap(projectResponseFile);
		Response response = target.saveProject(FileLoader.getText(jsonModelsPath + projectRequestFile));
		assertEquals(Response.Status.OK, response.getStatusInfo());
		Map<String,Object> actual = convertJsonToMap((String) response.getEntity());
		expected.put("id", actual.get("id"));
//		assertTrue(mapsAreEquals(expected, actual));
		
	}

	@Test
	public void testUpdateProject() throws JsonParseException, JsonMappingException, IOException {
		String title = "title under test";
		Map<Object,Object> inserted = insertProjectFromFile(projectRequestFile);
		Map<Object,Object> expected = convertJsonFileToMap(projectResponseFile);
		expected.put("id", inserted.get("id"));
		expected.put("title", title);
		String json = convertMapToJson(expected);
		Response r = target.updateProject((String) expected.get("id"), json);
		assertEquals(Status.OK.getStatusCode(), r.getStatus());
		Map<Object,Object> actual = convertJsonToMap((String)target.getProjectById((String) inserted.get("id")).getEntity());
//		assertTrue(mapsAreEquals(expected, actual));
	}

	@Test
	public void testDeleteProject() throws JsonGenerationException, JsonMappingException, IOException {
		
		Map<Object,Object> createdProject = insertProjectFromFile(projectRequestFile);
		Response r = target.deleteProject((String)createdProject.get("id"));
		assertEquals(Status.NO_CONTENT.getStatusCode(), r.getStatus());
		try{
			target.getProjectById((String)createdProject.get("id"));
		}catch(WebApplicationException e){
			assertEquals(Status.NOT_FOUND.getStatusCode(), e.getResponse().getStatus());
		}
	}

}






















































