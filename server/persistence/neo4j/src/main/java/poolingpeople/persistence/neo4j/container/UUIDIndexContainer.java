package poolingpeople.persistence.neo4j.container;

import java.util.UUID;

import poolingpeople.persistence.neo4j.NodePropertyName;

public class UUIDIndexContainer extends IndexContainer{
	public UUIDIndexContainer(String uuid) {
		super("ALL", NodePropertyName.ID.name(), uuid);
	}
	
	public UUIDIndexContainer() {
		this(UUID.randomUUID().toString());
	}
}





























