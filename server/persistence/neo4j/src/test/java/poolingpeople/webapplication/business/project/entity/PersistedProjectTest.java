package poolingpeople.webapplication.business.project.entity;

import static org.junit.Assert.*;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.validation.constraints.AssertTrue;

import org.junit.Ignore;
import org.junit.Test;

import poolingpeople.persistence.neo4j.AbstractPersitenceTest;
import poolingpeople.persistence.neo4j.PoolingpeopleObjectType;
import poolingpeople.persistence.neo4j.entities.PersistedProject;

public class PersistedProjectTest extends AbstractPersitenceTest{

	@Inject Instance<PersistedProject> injectedProject;
	
	String unrelatedStructureFileName =  "project-task-effort-unrelated.cy";
	String relatedStructureFileName =  "project-task-effort-related.cy";

	private void addUnrelatedStructure() {
		addCypherStructure(unrelatedStructureFileName);
	}

	private void addRelatedStructure() {
		addCypherStructure(relatedStructureFileName);
	}

	
	@Test
	public void testAddTaskDatesAreCorrect() {

		addUnrelatedStructure();

		PersistedProject persistedProject = injectedProject.get().loadExistingNodeById("1", PoolingpeopleObjectType.PROJECT);
		assertEquals(new Long(10), persistedProject.getStartDate());
		assertEquals(new Long(20), persistedProject.getEndDate());
		
//		Task t2 = new PersistedTask(manager, "3");
//		target.addTask(t2);
//		assertEquals(new Long(34), target.getStartDate());
//		assertEquals(new Long(51), target.getEndDate());
//
//		Task t1 = new PersistedTask(manager, "2");
//		target.addTask(t1);
//		assertEquals(new Long(1), target.getStartDate());
//		assertEquals(new Long(51), target.getEndDate());

	}
//
//	@Test
//	public void testAddTaskProgressIsCorrect() {
//
//		addUnrelatedStructure();
//		target = new PersistedProject(manager, "1");
//		Task t2 = new PersistedTask(manager, "3");
//		target.addTask(t2);
//		Task t1 = new PersistedTask(manager, "2");
//		target.addTask(t1);
//
//		String q = "MATCH (n:TASK) return sum(n.DEFAULT_PROGRESS * n.DEFAULT_DURATION) / sum(n.DEFAULT_DURATION) as total ";
//		Double expectedProgress = (Double) manager.runCypherQuery(q, null).columnAs("total").next();
//
//		Float f = Float.parseFloat(expectedProgress.toString());
//		assertEquals(f, target.getProgress());
//
//	}
//
//	@Test
//	public void testAddTaskEffortIsCorrect() {
//
//		addUnrelatedStructure();
//		target = new PersistedProject(manager, "1");
//		Task t2 = new PersistedTask(manager, "3");
//		target.addTask(t2);
//		Task t1 = new PersistedTask(manager, "2");
//		target.addTask(t1);
//
//		String q = "MATCH (n:EFFORT) RETURN sum(n.time) as total";
//		Integer totalEffort = (Integer) manager.runCypherQuery(q, null).columnAs("total").next();
//		assertEquals(totalEffort, target.getEffort());
//	}
//
//	/*
//	 * @ASK: Shpuld the projects date also compute?
//	 */
//	@Test
//	public void testRemoveTaskDatesAreCorrect() {
//
//		addRelatedStructure();
//		target = new PersistedProject(manager, "1");
//
//		target.updateAll();
//
//		Task t2 = new PersistedTask(manager, "3");
//		Task t1 = new PersistedTask(manager, "2");
//
//		assertEquals(new Long(1), target.getStartDate());
//		assertEquals(new Long(51), target.getEndDate());
//
//		target.removeTask(t1);
////		assertEquals(new Long(34), target.getStartDate());
//		assertEquals(new Long(51), target.getEndDate());
//
//		target.removeTask(t2);
//		assertEquals(new Long(10), target.getStartDate());
//		assertEquals(new Long(20), target.getEndDate());
//
//	}
//
//	@Test
//	public void testRemoveTaskProgressIsCorrect() {
//		addRelatedStructure();
//
//		target = new PersistedProject(manager, "1");
//		target.updateAll();
//		
//		String q = "MATCH (n:TASK) return sum(n.DEFAULT_PROGRESS * n.DEFAULT_DURATION) / sum(n.DEFAULT_DURATION) as total ";
//		Double expectedProgress = (Double) manager.runCypherQuery(q, null).columnAs("total").next();
//		Float f = Float.parseFloat(expectedProgress.toString());
//		assertEquals(f, target.getProgress());
//		
//		Task t1 = new PersistedTask(manager, "2");
//		target.removeTask(t1);
//		expectedProgress = (Double) manager.runCypherQuery(q, null).columnAs("total").next();
//		f = Float.parseFloat(expectedProgress.toString());
////		assertEquals(f, target.getProgress());
//		
//		Task t2 = new PersistedTask(manager, "3");
//		target.removeTask(t2);
//		f = new Float(2.5);
//		assertEquals(f, target.getProgress());
//	}
//
//	@Test
//	public void testRemoveTaskEffortIsCorrect() {
//		addRelatedStructure();
//		target = new PersistedProject(manager, "1");
//		
//		Task t1 = new PersistedTask(manager, "2");
//		target.removeTask(t1);
//		String q = "MATCH (n:EFFORT) RETURN sum(n.time) as total";
//		Integer totalEffort = (Integer) manager.runCypherQuery(q, null).columnAs("total").next();
//		assertEquals(totalEffort, target.getEffort());
//		
//		Task t2 = new PersistedTask(manager, "3");
//		q = "MATCH (n:EFFORT) RETURN sum(n.time) as total";
//		totalEffort = (Integer) manager.runCypherQuery(q, null).columnAs("total").next();
//		target.removeTask(t2);
//		assertEquals(totalEffort, target.getEffort());
//		
//		
//	}
//
//	@Test
//	public void testGetTasks() {
//		addRelatedStructure();
//		target = new PersistedProject(manager, "1");
//		assertEquals(2, target.getTasks().size());
//	}
}
