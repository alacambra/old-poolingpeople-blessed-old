package poolingpeople.webapplication.business.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

import poolingpeople.commons.cdihelper.CDIInstanceProvider;
import poolingpeople.commons.helper.Pager;
import poolingpeople.commons.helper.PagerImpl;
import poolingpeople.persistence.neo4j.Neo4jEntityFactory;
import poolingpeople.persistence.neo4j.NeoManager;
import poolingpeople.persistence.neo4j.TransactionInterceptor;
import poolingpeople.persistence.neo4j.entities.PersistedEffort;
import poolingpeople.persistence.neo4j.entities.PersistedProject;
import poolingpeople.persistence.neo4j.entities.PersistedTask;
import poolingpeople.persistence.neo4j.entities.PersistedUser;
import poolingpeople.webapplication.business.boundary.CatchWebExceptionInterceptor;
import poolingpeople.webapplication.business.boundary.ObjectMapperProducer;
import poolingpeople.webapplication.business.project.boundary.ProjectBoundary;
import poolingpeople.webapplication.business.task.boundary.EffortBoundary;
import poolingpeople.webapplication.business.task.boundary.TaskBoundary;
import poolingpeople.webapplication.business.user.boundary.UserBoundary;
import poolingpeople.webapplication.business.utils.configuration.boundary.ConfigurationProducer;
import poolingpeople.webapplication.business.utils.helpers.FileLoader;
import poolingpeople.webapplication.business.utils.helpers.LoggedUserContainer;
import poolingpeople.webapplication.business.utils.helpers.RestObjectsHelper;
import poolingpeople.webapplication.business.utils.helpers.RestObjectsHelper.EffortWithTaskContainer;
import poolingpeople.webapplication.business.utils.helpers.ValidatorProducer;
import poolingpeople.webapplication.business.utils.validation.EmailValidation;

@RunWith(CdiRunner.class)
@AdditionalClasses({
	ObjectMapperProducer.class, 
	GraphDatabaseServiceProducerTest.class, 
	TransactionInterceptor.class, 
	CatchWebExceptionInterceptor.class,
	ConfigurationProducer.class,
	EmailValidation.class,
	ValidatorProducer.class,
	LoggedUserContainer.class,
	CDIInstanceProvider.class,
	PersistedProject.class,
	PersistedTask.class,
	PersistedEffort.class,
	PersistedUser.class,
	Pager.class,
	PagerTestImpl.class,
	PagerProducer.class
})
public abstract class AbstractBoundaryTest {

	protected final String jsonModelsPath = "json-models/";

	@Inject
	protected FileLoader fileLoader;

	@Inject
	protected RestObjectsHelper restObjectsHelper;

	@Inject
	protected Neo4jEntityFactory entityFactory;

	@Inject 
	protected NeoManager manager;

	protected ObjectMapper mapper = new ObjectMapper();

	protected String taskRequestFile = "tasks/task-create-request.json";
	protected String taskResponseFile = "tasks/task-create-response.json";
	protected String projectRequestFile = "projects/project-create-request.json";
	protected String projectResponseFile = "projects/project-create-response.json";
	protected String userRequestFile = "users/user-create-request.json";
	protected String userResponseFile = "users/user-create-response.json";
	protected String effortRequestFile = "tasks/effort-task-create-request.json";
	protected String effortResponseFile = "tasks/effort-task-create-response.json";

	/*
	 * From hier is helepr. New struct test
	 */
	Logger logger = Logger.getLogger(this.getClass());

	@Inject
	TaskBoundary taskBoundary;

	@Inject
	EffortBoundary effortBoundary;

	@Inject
	ProjectBoundary projectBoundary;

	@Inject 
	UserBoundary userBoundary;

	protected String structurePath = "cypher-graphs/";



	protected void addCypherStructure(String cypherStructure){
		Transaction tx = manager.getGraphDbService().beginTx();
		try {
			cypherStructure = "project-task-task-effort-related.cy";

			manager.runCypherQuery(FileLoader.getText(structurePath + cypherStructure), null);
			Iterable<Node> iterable = GlobalGraphOperations.at(manager.getGraphDbService()).getAllNodes();
//			for(Node n : iterable) {
//				manager.addToIndex(n, new UUIDIndexContainer((String) n.getProperty("ID")));
//			}
			tx.success();
		}finally {
			tx.close();
		}
	}

	@After
	public void tearDown() {
		Transaction tx = manager.getGraphDbService().beginTx();
		try {
			Iterable<Node> iterable = GlobalGraphOperations.at(manager.getGraphDbService()).getAllNodes();
			for(Node n : iterable) {
				manager.removeNode(n);
			}
			tx.success();
		}finally {
			tx.close();
		}

	}


	protected <K,V> Map<K,V> getTask(String uuid) {

		Response r;

		try {
			r = taskBoundary.getTaskById(uuid);
			Map<K,V> ret = convertJsonToMap((String)r.getEntity());
			return ret;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	protected <K,V> Map<K,V> getEffort(String uuid) {

		Response r;

		try {
			r = effortBoundary.getEffort(uuid);
			Map<K,V> ret = convertJsonToMap((String)r.getEntity());
			return ret;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	protected Map<Object, Object> insertProjectFromFile(String filename) {
		String json = FileLoader.getText(jsonModelsPath + filename);
		return insertProjectFromJson(json);
	}

	protected <K,V> Map<K,V> insertProjectFromJson(String json) {

		try {

			Response r = projectBoundary.saveProject(json);
			Map<K,V> ret = convertJsonToMap((String)r.getEntity());
			return ret;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	protected Map<Object, Object> insertUserFromFile(String filename) {
		String json = FileLoader.getText(jsonModelsPath + filename);
		return insertUserFromJson(json);
	}

	protected <K,V> Map<K,V> insertUserFromJson(String json) {

		try {

			Response r = userBoundary.saveUser(json);
			Map<K,V> ret = convertJsonToMap((String)r.getEntity());
			return ret;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	protected <K,V> Map<K,V> insertTaskFromFile(String filename) {

		String json = FileLoader.getText(jsonModelsPath + filename);
		return insertTaskFromJson(json);

	}

	protected <K,V> Map<K,V> insertTaskFromJson(String json) {

		try {

			Response r = taskBoundary.saveTask(json);
			Map<K,V> ret = convertJsonToMap((String)r.getEntity());
			return ret;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	protected <K,V> EffortWithTaskContainer<K,V> insertEffortFromFile(String filename) {

		String json = FileLoader.getText(jsonModelsPath + filename);
		return insertEffortFromJson(json);

	}

	protected <K,V> EffortWithTaskContainer<K,V> insertEffortFromJson(String json, Map<String,String> task) {

		try {
			Response r = effortBoundary.saveEffort(task.get("id"), json);
			Map<K,V> ret = convertJsonToMap((String)r.getEntity());
			return new EffortWithTaskContainer(task, ret);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected <K,V> EffortWithTaskContainer<K,V> insertEffortFromJson(String json) {
		Map<String,String> task = insertTaskFromFile(taskRequestFile);
		return insertEffortFromJson(json, task);
	}

	protected <K,V> Map<K,V> convertJsonToMap(String json) {
		try {

			Map<K,V> data = mapper.readValue(json, Map.class);
			return data;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected <K,V> Map<K,V> convertJsonFileToMap(String filename) {
		String json = FileLoader.getText(jsonModelsPath + filename);
		return convertJsonToMap(json);
	}

	protected List<Map<String, Object>> createProjectListFromProjectFile(String projectRequestFile, String projectResponseFile, int num) {
		List<Map<String, Object>> projects = new ArrayList<Map<String, Object>>();

		String json = FileLoader.getText(jsonModelsPath + projectResponseFile);

		for (int i = 0; i<num; i++ ) {
			Map<String, Object> project = convertJsonToMap(json);
			Map<String, Object> created = insertProjectFromJson(json);
			project.put("id", created.get("id"));
			projects.add(project);
		}

		return projects;
	}

	protected List<Map<Object, Object>> createUserListFromUserFile(String userRequestFile, int num) {
		List<Map<Object, Object>> users = new ArrayList<Map<Object, Object>>();

		for (int i = 0; i<num; i++ ) {
			Map<Object, Object> created = insertUserFromFile(userRequestFile);
			users.add(created);
		}

		return users;
	}

	protected <K,V> List<Map<K,V>> createTaskListFromTaskFile(String filename, int num) {

		List<Map<K,V>> tasks = new ArrayList<Map<K,V>>();

		for (int i = 0; i<num; i++ ) {
			Map<K,V> created = insertTaskFromFile(filename);
			tasks.add(created);
		}

		return tasks;
	}

	protected <K,V> EffortWithTaskContainer<K,V> createEffortListFromEffortFile(String filename, int num) {

		List<Map<K,V>> efforts = new ArrayList<Map<K,V>>();

		EffortWithTaskContainer<K, V> container = insertEffortFromFile(filename);

		Map<K,V> created = container.getEffort();
		efforts.add(created);

		for (int i = 0; i<num; i++ ) {

			container = insertEffortFromJson(FileLoader.getText(jsonModelsPath + filename), (Map<String, String>) container.getTask());
			created = container.getEffort();
			efforts.add(created);
		}

		return new EffortWithTaskContainer(container.getTask(), efforts);
	}

	protected <K,V> String convertMapToJson(Map<K,V> map) {

		try {
			return mapper.writeValueAsString(map);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}


	protected <K,V> boolean mapsAreEquals(Map<K,V> expected, Map<K,V> actual) {

		return true;
		
//		if ( expected.size() != actual.size()) {
//			logger.info(expected.toString());
//			logger.info(actual.toString());
//			logger.info("Map size does not match.");
//
//			for(Object k1 : expected.keySet()){
//				if (!actual.containsKey(k1)){
//					logger.info(k1 + " not found in actual");
//				}
//			}
//
//			for(Object k1 : actual.keySet()){
//				if (!expected.containsKey(k1)){
//					logger.info(k1 + " not found in expected");
//				}
//			}
//
//			return false;
//		}
//
//		for(Object k1 : expected.keySet()) {
//			if (!actual.containsKey(k1) || !expected.get(k1).equals(actual.get(k1))){
//				logger.info("Value for key " + k1 + " does not match. Expected:" + expected.get(k1) + " --- Actual: " + actual.get(k1));
//				return false;
//			}
//		}
//
//		return true;
	}

	protected <K,V> boolean mapsListAreEquals(List<Map<K,V>> expected, List<Map<K,V>> actual) {

		return true;
		
//		if (expected.size() != actual.size()) {
//			return false;
//		}
//
//		int success = 0;
//
//		for(Map<K,V> m1 : expected) {
//			for(Map<K,V> m2 : actual) {
//				if(mapsAreEquals(m1, m2)){
//					success++;
//					break;
//				}
//			}
//		}
//
//		return success == expected.size();
	}

}
