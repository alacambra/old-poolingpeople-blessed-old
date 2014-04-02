package poolingpeople.persistence.neo4j.inheritance;

import java.util.Collection;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;

import poolingpeople.commons.entities.Project;
import poolingpeople.persistence.neo4j.NeoManager;
import poolingpeople.persistence.neo4j.NodePropertyName;
import poolingpeople.persistence.neo4j.PoolingpeopleObjectType;

@Stateless
@Asynchronous
public class InehritanceConcistencyUpdater {

	@Inject NeoManager manager;
	
	public void updateProjectTree(Project project){
		
	}
	
	public void updateProjects(){
		String q = "MATCH(n:" + PoolingpeopleObjectType.PROJECT.name() + ") return count(n) as total";
		ResourceIterator<Integer> res = manager.runCypherQuery(q).columnAs("total");
		int total = res.next();
		res.close();
		int step = 100;
		for (int i = 0; i < total+step-1; i+=step){
			Collection<Node> nodes = manager.getNodes(PoolingpeopleObjectType.PROJECT.name(), i, i+step);
			for(Node n:nodes){
				
			}
		}
		
	}
	
}
