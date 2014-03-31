package poolingpeople.exporter.models.neo4j;

import poolingpeople.persistence.neo4j.NodePropertyName;
import poolingpeople.persistence.neo4j.entities.PersistedEffort;

public class R2NPersistedEffort extends PersistedEffort implements IsRedmineEntity{

	@Override
	public void setRedmineId(Integer id) {
		setProperty(NodePropertyName.REDMINE_ID, id);
	}

	@Override
	public Integer getRedmineId() {
		return getIntegerProperty(NodePropertyName.REDMINE_ID);
	}
	
	
	
}
