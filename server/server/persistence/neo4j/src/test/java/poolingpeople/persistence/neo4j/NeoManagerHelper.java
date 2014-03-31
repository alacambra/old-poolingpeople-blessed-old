package poolingpeople.persistence.neo4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

import poolingpeople.persistence.neo4j.NodePropertyName;
import poolingpeople.persistence.neo4j.container.IndexContainer;

public class NeoManagerHelper {

	private GraphDatabaseService graphDb;

	public NeoManagerHelper(GraphDatabaseService graphDb) {
		this.graphDb = graphDb;
	}

	public Node addNode(IndexContainer... indexContainer) {

		return addNode(new HashMap<String, Object>(), indexContainer);
	}

	public Node addNode(Map<String, Object> properties, IndexContainer... indexContainer) {

		Node node = null;
		node = graphDb.createNode();

		for(int i = 0; i<indexContainer.length; i++) {
			node.setProperty(NodePropertyName.ID.name(), indexContainer[i].getValue());
			graphDb.index().forNodes(indexContainer[i].getType()).add(node, indexContainer[i].getKey(), indexContainer[i].getValue());
		}
		
		for (Entry<String, Object> prop : properties.entrySet()) {
			node.setProperty(prop.getKey(), prop.getValue());
		}
		
		return node;
	}
	
}
