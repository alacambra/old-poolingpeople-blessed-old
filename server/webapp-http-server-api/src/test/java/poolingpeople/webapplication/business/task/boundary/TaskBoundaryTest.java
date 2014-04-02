package poolingpeople.webapplication.business.task.boundary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.graphdb.Transaction;

import static org.junit.Assert.*;
import poolingpeople.commons.entities.Project;
import poolingpeople.commons.entities.Task;
import poolingpeople.commons.entities.User;
import poolingpeople.webapplication.business.entity.AbstractBoundaryTest;
import poolingpeople.webapplication.business.utils.helpers.FileLoader;

public class TaskBoundaryTest extends AbstractBoundaryTest{

	@Inject
	TaskBoundary target;

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetTaskById() throws Exception {

		Map<String,String> createdTaskdata = insertTaskFromFile(taskRequestFile);
		Map<String,String> expectedTaskdata = convertJsonFileToMap(taskResponseFile);
		expectedTaskdata.put("id", createdTaskdata.get("id"));

		Response response = target.getTaskById(createdTaskdata.get("id"));
		assertEquals(Response.Status.OK, response.getStatusInfo());
		Map<String,String> receivedTaskdata = convertJsonToMap((String)response.getEntity());

//		assertTrue(mapsAreEquals(expectedTaskdata, receivedTaskdata));

	}

	@Test
	public void testGetAllTask() throws Exception {

		List<Map<Object, Object>> expected = createTaskListFromTaskFile(taskRequestFile, 3);
		Response allTask = target.getAllTask();
		assertEquals(Response.Status.OK, allTask.getStatusInfo());

		@SuppressWarnings("unchecked")
		List<Map<Object, Object>> actual = mapper.readValue((String)allTask.getEntity(), List.class);
//		assertTrue(mapsListAreEquals(expected, actual));

	}

	@Test
	public void testSaveTask() throws Exception {

		Map<String,String> expected = convertJsonFileToMap(taskResponseFile);
		Response response = target.saveTask(FileLoader.getText(jsonModelsPath +taskRequestFile));
		assertEquals(Response.Status.OK, response.getStatusInfo());
		Map<String,String> actual = convertJsonToMap((String) response.getEntity());
		expected.put("id", actual.get("id"));
//		assertTrue(mapsAreEquals(expected, actual));

	}

	@Test
	@Ignore
	public void testUpdateTask() throws Exception {

		String title = "title under test";
		Map<String,String> expected = insertTaskFromFile(taskRequestFile);
		expected.put("title", title);
		String json = convertMapToJson(expected);
		Response r = target.updateTask(expected.get("id"), json);
		assertEquals(Status.OK.getStatusCode(), r.getStatus());
		Map<String,String> actual = convertJsonToMap((String)r.getEntity());
//		assertTrue(mapsAreEquals(expected, actual));

	}

	@Test
	public void testDeleteTask() throws Exception {
		Map<String,String> createdTaskdata = insertTaskFromFile(taskRequestFile);
		Response r = target.deleteTask(createdTaskdata.get("id"));
		assertEquals(Status.NO_CONTENT.getStatusCode(), r.getStatus());
		try{
			target.getTaskById(createdTaskdata.get("id"));
		}catch(WebApplicationException e){
			assertEquals(Status.NOT_FOUND.getStatusCode(), e.getResponse().getStatus());
		}
	}

	@Test
	public void testCreateSubtaskInTask() throws JsonParseException, JsonMappingException, IOException{
		Map<Object, Object> parent = insertTaskFromFile(taskRequestFile);
		Response r = target.createSubtaskInTask((String) parent.get("id"), FileLoader.getText(jsonModelsPath + taskRequestFile));
		assertEquals(Status.OK.getStatusCode(), r.getStatus());
		Transaction tx = manager.getGraphDbService().beginTx();
		try{
			Task parentTask = entityFactory.getTaskById((String) parent.get("id"));
			Task child = entityFactory.getTaskById((String) convertJsonToMap((String) r.getEntity()).get("id"));
			assertEquals(child.getParent(), parentTask);
			tx.success();
		} finally {
			tx.close();
		}

	}

	@Test
	public void testAddSubtaskToTask(){

		Map<Object, Object> parent = insertTaskFromFile(taskRequestFile);
		Map<Object, Object> child = insertTaskFromFile(taskRequestFile);
		Response r = target.addSubtaskToTask((String) child.get("id"), (String) parent.get("id"));
		assertEquals(Status.NO_CONTENT.getStatusCode(), r.getStatus());

		Transaction tx = manager.getGraphDbService().beginTx();

		try{
			Task parentTask = entityFactory.getTaskById((String) parent.get("id"));
			Task childTask = entityFactory.getTaskById((String) child.get("id"));
			assertNotNull(childTask.getParent());
			assertEquals(childTask.getParent(), parentTask);
			tx.success();
		} finally {
			tx.close();
		}
	}

	@Test
	public void testMoveSubtaskFromTaskToTask(){
		Map<Object, Object> source = insertTaskFromFile(taskRequestFile);
		Map<Object, Object> dest = insertTaskFromFile(taskRequestFile);
		Map<Object, Object> child = insertTaskFromFile(taskRequestFile);
		
		Response r = target.addSubtaskToTask((String) child.get("id"), (String) source.get("id"));
		
		Transaction tx = manager.getGraphDbService().beginTx();
		try{
			Task parentTask = entityFactory.getTaskById((String) source.get("id"));
			Task childTask = entityFactory.getTaskById((String) child.get("id"));
			assertNotNull(childTask.getParent());
			assertEquals(childTask.getParent(), parentTask);
			tx.success();
		} finally {
			tx.close();
		}
		
		r = target.moveSubtaskFromTaskToTask((String) child.get("id"), (String) source.get("id"), (String) dest.get("id"));
		
		assertEquals(Status.NO_CONTENT.getStatusCode(), r.getStatus());

		tx = manager.getGraphDbService().beginTx();
		try{
			Task destTask = entityFactory.getTaskById((String) dest.get("id"));
			Task childTask = entityFactory.getTaskById((String) child.get("id"));
			assertNotNull(childTask.getParent());
			assertEquals(childTask.getParent(), destTask);
			tx.success();
		} finally {
			tx.close();
		}
	}

	@Test
	public void testCreateTaskInProject() throws JsonParseException, JsonMappingException, IOException{
		Map<Object, Object> project = insertProjectFromFile(projectRequestFile);
		Response r = target.createTaskInProject((String) project.get("id"), FileLoader.getText(jsonModelsPath + taskRequestFile));
		assertEquals(Status.OK.getStatusCode(), r.getStatus());
		
		Transaction tx = manager.getGraphDbService().beginTx();
		try{
			Project parentProject = entityFactory.getProjectById((String) project.get("id"));
			Task childTask = entityFactory.getTaskById((String) (convertJsonToMap((String) r.getEntity()).get("id")));
			assertNotNull(childTask.getProject());
			assertEquals(childTask.getProject(), parentProject);
			tx.success();
		} finally {
			tx.close();
		}
	}

	@Test
	public void testAddTaskToProject() throws JsonParseException, JsonMappingException, IOException{

		Map<Object, Object> project = insertProjectFromFile(projectRequestFile);
		Map<Object, Object> task = insertTaskFromFile(taskRequestFile);
		Response r = target.addTaskToProject((String) task.get("id"), (String) project.get("id"));
		assertEquals(Status.NO_CONTENT.getStatusCode(), r.getStatus());
		
		Transaction tx = manager.getGraphDbService().beginTx();
		
		try{
			Project parentProject = entityFactory.getProjectById((String) project.get("id"));
			Task childTask = entityFactory.getTaskById((String) task.get("id"));
			assertNotNull(childTask.getProject());
			assertEquals(childTask.getProject(), parentProject);
			tx.success();
		} finally {
			tx.close();
		}
		
	}

	@Test
	public void testMoveTaskFromProjectToProject() throws JsonParseException, JsonMappingException, IOException{
		
		Map<Object, Object> source = insertProjectFromFile(projectRequestFile);
		Map<Object, Object> dest = insertProjectFromFile(projectRequestFile);
		Map<Object, Object> child = insertTaskFromFile(taskRequestFile);
		
		Response r = target.addTaskToProject((String) child.get("id"), (String) source.get("id"));
		
		Transaction tx = manager.getGraphDbService().beginTx();
		try{
			Project sourceProject = entityFactory.getProjectById((String) source.get("id"));
			Task childTask = entityFactory.getTaskById((String) child.get("id"));
			assertNotNull(childTask.getProject());
			assertEquals(childTask.getProject(), sourceProject);
			tx.success();
		} finally {
			tx.close();
		}
		
		r = target.moveTaskFromProjectToProject((String) child.get("id"), (String) source.get("id"), (String) dest.get("id"));
		
		assertEquals(Status.NO_CONTENT.getStatusCode(), r.getStatus());

		tx = manager.getGraphDbService().beginTx();
		try{
			Project destProject = entityFactory.getProjectById((String) dest.get("id"));
			Task childTask = entityFactory.getTaskById((String) child.get("id"));
			assertNotNull(childTask.getProject());
			assertEquals(childTask.getProject(), destProject);
			tx.success();
		} finally {
			tx.close();
		}

	}

	@Test
	public void testAssignTaskToUser(){

		Map<Object, Object>  user = insertUserFromFile(userRequestFile);
		Map<Object, Object> task = insertTaskFromFile(taskRequestFile);
		
		Response r = target.assignTaskToUser((String)task.get("id"), (String) user.get("id"));
		assertEquals(Status.NO_CONTENT.getStatusCode(), r.getStatus());
		
		Transaction tx = manager.getGraphDbService().beginTx();
		try{
			User assignee = entityFactory.getUserById((String) user.get("id"));
			Task assignedTask= entityFactory.getTaskById((String) task.get("id"));
			assertNotNull(assignedTask.getAssignee());
			assertEquals(assignedTask.getAssignee(), assignee);
			tx.success();
		} finally {
			tx.close();
		}
	}
	
	
}
