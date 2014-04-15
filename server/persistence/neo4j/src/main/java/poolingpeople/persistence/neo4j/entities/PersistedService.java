package poolingpeople.persistence.neo4j.entities;

import java.util.List;

import org.neo4j.graphdb.Direction;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.Service;
import poolingpeople.persistence.neo4j.Neo4jProfiler;
import poolingpeople.persistence.neo4j.NodePropertyName;
import poolingpeople.persistence.neo4j.PoolingpeopleObjectType;
import poolingpeople.persistence.neo4j.Relations;

@Neo4jProfiler
public class PersistedService extends AbstractPersistedModel<PersistedService> implements Service{
	public static final PoolingpeopleObjectType NODE_TYPE = PoolingpeopleObjectType.SERVICE;

	@Override
	public List<ChangeLog> getChangeLogList() {
		return getRelatedNodes(Relations.HAS_CHANGE_LOG, PersistedService.class, ChangeLog.class, Direction.OUTGOING);
	}

	@Override
	public void setTitle(String name) {
		setProperty(NodePropertyName.TITLE, name);
	}

	@Override
	public String getTitle() {
		return getStringProperty(NodePropertyName.TITLE);
	}

	@Override
	public void setDescription(String description) {
		setProperty(NodePropertyName.DESCRIPTION, description);
	}

	@Override
	public String getDescription() {
		return getStringProperty(NodePropertyName.DESCRIPTION);
	}

	@Override
	public void setActiveStatus(boolean activeStatus) {
		setProperty(NodePropertyName.ACTIVE, Boolean.toString(activeStatus));
	}

	@Override
	public boolean getActiveStatus() {
		return Boolean.parseBoolean(getStringProperty(NodePropertyName.ACTIVE));
	}

	@Override
	protected void initializeVariables() {
	}
	
}
