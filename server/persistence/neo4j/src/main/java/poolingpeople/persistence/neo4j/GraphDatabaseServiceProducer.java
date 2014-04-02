package poolingpeople.persistence.neo4j;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

@ApplicationScoped
public class GraphDatabaseServiceProducer{

	private final static String db_uri = "/opt/neo4j";
	private final GraphDatabaseService graphDb;
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public GraphDatabaseServiceProducer() {
		
		GraphDatabaseService tmp= null;
		
		try {
			
			tmp = new GraphDatabaseFactory().newEmbeddedDatabase( db_uri );
			logger.info("using db:" + db_uri);
			
		} catch(Exception e){
			logger.error(db_uri + " not found", e);
			tmp =  new GraphDatabaseFactory().newEmbeddedDatabase("neo4j");
			logger.info("Using default db");
			
		}
		
		graphDb = tmp;
		new Neo4JSchemaLoader().loadSchema(graphDb);
	}
	
	@Produces
	public GraphDatabaseService getGraphDb() {
		return graphDb; 
	}
	
	@PreDestroy
	public void shutdownDb(){
		graphDb.shutdown(); 
	}
}



































