package poolingpeople.webapplication.business.task.entity;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import poolingpeople.commons.entities.Effort;
import poolingpeople.commons.entities.EffortDto;
import poolingpeople.commons.entities.Project;
import poolingpeople.commons.entities.Task;
import poolingpeople.persistence.neo4j.AbstractPersitenceTest;
import poolingpeople.persistence.neo4j.PoolingpeopleObjectType;
import poolingpeople.persistence.neo4j.entities.PersistedEffort;
import poolingpeople.persistence.neo4j.entities.PersistedProject;
import poolingpeople.persistence.neo4j.entities.PersistedTask;

public class PersistedTaskTest extends AbstractPersitenceTest{
	
	String relatedStructure = "project-task-task-effort-related.cy";
	String unrelatedStructure = "project-task-task-effort-unrelated.cy";

	
	private void loadRelatedStructure() {
		addCypherStructure(relatedStructure);
	}
	
	private void loadUnrelatedStructure() {
		addCypherStructure(unrelatedStructure);
	}
	
	
	@Test
	@Ignore
	public void testRunDeletePreconditions() {
	}

//	@Test
//	public void testPersistedTaskConstructors() {
//		TaskDTO dto = new TaskDTO();
//		dto.setTitle("title");
//
//		PersistedTask target = new PersistedTask(manager, dto);
//
//		assertEquals("title", target.getTitle());
//		assertEquals(target, new PersistedTask(manager, target.getId()));
//		assertEquals(target, new PersistedTask(manager, target.getNode()));
//
//	}

	@Test
	@Ignore
	public void testGetDuration() {
	}

	@Test
	@Ignore
	public void testSetDefaultDuration() {
	}

	@Test
	@Ignore
	public void testGetEffort() {
	}

	@Test
	public void datesAreCorrect() {
		
		loadUnrelatedStructure();
		Task t1 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T1", PoolingpeopleObjectType.TASK);
		assertEquals(new Long(1), t1.getStartDate());
		assertEquals(new Long(2), t1.getEndDate());
		
		Task t11 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T11", PoolingpeopleObjectType.TASK);
		t1.equals(t11);
		t1.addSubtask(t11);
		updateQueue.executeUpdates();
		assertEquals(new Long(34), t1.getStartDate());
		assertEquals(new Long(51), t1.getEndDate());
		assertEquals(new Long(34), t11.getStartDate());
		assertEquals(new Long(51), t11.getEndDate());
		
		Task t111 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T111", PoolingpeopleObjectType.TASK);
		
		t11.addSubtask(t111);
		updateQueue.executeUpdates();
		
		assertEquals(new Long(4), t1.getStartDate());
		assertEquals(new Long(510), t1.getEndDate());
		assertEquals(new Long(4), t11.getStartDate());
		assertEquals(new Long(510), t11.getEndDate());
		assertEquals(new Long(4), t111.getStartDate());
		assertEquals(new Long(510), t111.getEndDate());
		
		Task t112 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T112", PoolingpeopleObjectType.TASK);
		t11.addSubtask(t112);
		updateQueue.executeUpdates();
		
		assertEquals(new Long(4), t1.getStartDate());
		assertEquals(new Long(510), t1.getEndDate());
		assertEquals(new Long(4), t11.getStartDate());
		assertEquals(new Long(510), t11.getEndDate());
		assertEquals(new Long(4), t111.getStartDate());
		assertEquals(new Long(510), t111.getEndDate());
		assertEquals(new Long(24), t112.getStartDate());
		assertEquals(new Long(51), t112.getEndDate());
		
		Project p = instanceProvider.getInstanceForClass(PersistedProject.class) .loadExistingNodeById("P1", PoolingpeopleObjectType.PROJECT);
		p.addTask(t1);
		updateQueue.executeUpdates();
		
		assertEquals(new Long(4), p.getStartDate());
		assertEquals(new Long(510), p.getEndDate());
		
		t1.setDefaultStartDate(23L);
		Task t12 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T12", PoolingpeopleObjectType.TASK);
		t1.addSubtask(t12);
		updateQueue.executeUpdates();
		assertEquals(new Long(3), t1.getStartDate());
		assertEquals(new Long(510), t1.getEndDate());
		
		
	}

	@Test
	public void testProgressIsCorrect() {
		loadUnrelatedStructure();
		Task t1 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T1", PoolingpeopleObjectType.TASK);
		Task t11 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T11", PoolingpeopleObjectType.TASK);
		Task t111 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T111", PoolingpeopleObjectType.TASK);
		Task t112 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T112", PoolingpeopleObjectType.TASK);
		Task t12 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T12", PoolingpeopleObjectType.TASK);
		Task t121 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T121", PoolingpeopleObjectType.TASK);
		Task t122= instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T122", PoolingpeopleObjectType.TASK);
		
		t1.addSubtask(t11);
		t1.addSubtask(t12);
		t11.addSubtask(t111);
		t11.addSubtask(t112);
		t12.addSubtask(t121);
		t12.addSubtask(t122);
		
		String q = "MATCH (n:TASK)-->()-->(child:TASK) return sum(child.DEFAULT_PROGRESS * child.DEFAULT_DURATION) / sum(child.DEFAULT_DURATION) as total ";
		Double expectedProgress = (Double) manager.runCypherQuery(q, null).columnAs("total").next();
		
		Float f = Float.parseFloat(expectedProgress.toString());
		updateQueue.executeUpdates();
		assertEquals(f, t1.getProgress());

	}
	
	@Test
	public void effortIsCorrect() {
		loadUnrelatedStructure();
		
		EffortDto dto = new EffortDto();
		String q = "MATCH (task:TASK)-[:HAS_EFFORT]->(effort) RETURN sum(effort.TIME) as total";
		dto.setTime(10);
		Effort effort = instanceProvider.getInstanceForClass(PersistedEffort.class)
				.createNodeFromDtoModel(PoolingpeopleObjectType.EFFORT, dto);
		
		Task t1 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T1", PoolingpeopleObjectType.TASK);
		Task t11 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T11", PoolingpeopleObjectType.TASK);
		Task t111 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T111", PoolingpeopleObjectType.TASK);
		Task t112 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T112", PoolingpeopleObjectType.TASK);
		Task t12 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T12", PoolingpeopleObjectType.TASK);
		Task t121 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T121", PoolingpeopleObjectType.TASK);
		Task t122= instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T122", PoolingpeopleObjectType.TASK);
		
		t1.addSubtask(t11);
		t11.addSubtask(t111);
		t11.addSubtask(t112);
		t1.addSubtask(t12);
		t12.addSubtask(t121);
		t12.addSubtask(t122);
		
		t111.addEffort(effort);
		updateQueue.executeUpdates();
		
		Integer totalEffort = Integer.parseInt(manager.runCypherQuery(q, null).columnAs("total").next().toString());
		assertEquals(totalEffort, t1.getEffort());
	}
	
	@Test
	public void durationIsCorrect() {
		loadUnrelatedStructure();
		Task t1 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T1", PoolingpeopleObjectType.TASK);
		Task t11 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T11", PoolingpeopleObjectType.TASK);
		Task t111 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T111", PoolingpeopleObjectType.TASK);
		Task t112 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T112", PoolingpeopleObjectType.TASK);
		Task t12 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T12", PoolingpeopleObjectType.TASK);
		Task t121 = instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T121", PoolingpeopleObjectType.TASK);
		Task t122= instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById("T122", PoolingpeopleObjectType.TASK);
		
		t1.addSubtask(t11);
		t1.addSubtask(t12);
		t11.addSubtask(t111);
		t11.addSubtask(t112);
		t12.addSubtask(t121);
		t12.addSubtask(t122);
		
		updateQueue.executeUpdates();
		
		String q = "MATCH (n:TASK)-->()-->(child:TASK) return sum(child.DEFAULT_DURATION) as total ";
		Long expectedProgress = (Long) manager.runCypherQuery(q, null).columnAs("total").next();
		
		Integer f = Integer.parseInt(expectedProgress.toString());
		assertEquals(f, t1.getDuration());
	}

}	
