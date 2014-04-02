package poolingpeople.commons.entities;

import org.neo4j.graphdb.Node;

public interface ChangeLogAction {
	String getId();

	String getCode();
	
	ChangeLogAction loadChangeLogActionFromNode(Node node);

	void setChangeLogNode(Node node);
	
}
