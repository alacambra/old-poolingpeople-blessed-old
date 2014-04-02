package poolingpeople.persistence.neo4j.entities;

import java.util.List;

import org.neo4j.graphdb.Direction;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.Effort;
import poolingpeople.persistence.neo4j.NodePropertyName;
import poolingpeople.persistence.neo4j.PoolingpeopleObjectType;
import poolingpeople.persistence.neo4j.Relations;
import poolingpeople.persistence.neo4j.exceptions.ConsistenceException;

public class PersistedEffort extends AbstractPersistedModel<PersistedEffort> implements Effort{

	public static final PoolingpeopleObjectType NODE_TYPE = PoolingpeopleObjectType.EFFORT;
	
	@Override
	public Long getDate() {
		return getLongProperty(NodePropertyName.DATE);
	}

	@Override
	public void setDate(Long date) {
		setProperty(NodePropertyName.DATE, date);

	}

	@Override
	public String getComment() {
		return getStringProperty(NodePropertyName.COMMENT);
	}

	@Override
	public void setComment(String comment) {
		setProperty(NodePropertyName.COMMENT, comment);
	}

	@Override
	public Integer getTime() {
		return getIntegerProperty(NodePropertyName.TIME);
	}

	@Override
	public void setTime(Integer time) {

		setProperty(NodePropertyName.TIME, time);
		PersistedTask task = getRelatedNode(Relations.HAS_EFFORT, PersistedTask.class, Direction.INCOMING);

		if ( !isCreated ) {
			return;
		}
		
		if ( task == null ) {
			throw new ConsistenceException("Effort " + getId() + " has no task");
		}

		/*
		 * @todo that requires some kind o update manager 
		 */
		
		task.updateEfforts();

	}

	@Override
	protected void initializeVariables() {
		if ( getTime() == null )
			setTime(0);
	}

	@Override
	public String getTaskId() {
		return getRelatedNode(Relations.HAS_EFFORT, PersistedTask.class, Direction.INCOMING).getId();
	}
}
