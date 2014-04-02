package poolingpeople.persistence.neo4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;

@ApplicationScoped
public class GraphDatabaseServiceProducerTest{

	private GraphDatabaseService graphDbService = new TestGraphDatabaseFactory().newImpermanentDatabase();
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public GraphDatabaseServiceProducerTest() {
		loadSchema();
	}
	
	@PostConstruct
	public void initializeGraphDbService() {
		loadSchema();
	}

	private void loadSchema() {
		logger.info("Using on memory db");
		Neo4JSchemaLoader schemaLoader = new Neo4JSchemaLoader();
		schemaLoader.loadSchema(graphDbService);
	}
	
	@Produces
	public GraphDatabaseService getGraphDb() {
		return graphDbService; 
	}

	@PreDestroy
	public void shutdownDb(){
		graphDbService.shutdown(); 
	}
}
