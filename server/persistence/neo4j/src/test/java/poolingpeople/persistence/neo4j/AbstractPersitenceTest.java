package poolingpeople.persistence.neo4j;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.junit.After;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.Node;
import org.neo4j.tooling.GlobalGraphOperations;

import poolingpeople.persistence.neo4j.NeoManager;
import poolingpeople.persistence.neo4j.entities.PersistedEffort;
import poolingpeople.persistence.neo4j.entities.PersistedProject;
import poolingpeople.persistence.neo4j.entities.PersistedTask;

@RunWith(SuperRunner.class)
@AdditionalClasses({
	PersistedProject.class,
	PersistedTask.class,
	PersistedEffort.class,
	GraphDatabaseServiceProducerTest.class, 
	TransactionInterceptor.class, 
	ProfilerInterceptor.class,
	UpdateQueue.class
})
public abstract class AbstractPersitenceTest {
	protected String structurePath = "cypher-graphs/";
	
	@Inject
	protected NeoManager manager;
	
	@Inject
	protected InstanceProvider instanceProvider;
	
	@Inject
	protected UpdateQueue updateQueue;
	
	public void setManager(NeoManager manager) {
		this.manager = manager;
	}

	protected void addCypherStructure(String cypherStructure){
		manager.runCypherQuery(FileLoader.getText(structurePath + cypherStructure), null);
		Iterable<Node> iterable = GlobalGraphOperations.at(manager.getGraphDbService()).getAllNodes();
//		for(Node n : iterable) {
//			manager.addToIndex(n, new UUIDIndexContainer((String) n.getProperty("ID")));
//		}
	}
	
	public NeoManager getManager() {
		return manager;
	}
	
	@After
	public void tearDown() {
//		Iterable<Node> iterable = GlobalGraphOperations.at(manager.getGraphDbService()).getAllNodes();
		String q = "MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n, r";
//		manager.runCypherQuery(q, null);
		
//		for(Node n : iterable) {
//			try {
//				manager.removeNode(n);
//			} catch (Exception e ){
//			}
//		}
	}
}
