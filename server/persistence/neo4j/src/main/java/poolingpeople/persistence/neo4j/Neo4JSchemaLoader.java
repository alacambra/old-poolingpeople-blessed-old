package poolingpeople.persistence.neo4j;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.ConstraintViolationException;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.schema.IndexDefinition;
import org.neo4j.graphdb.schema.Schema;

public class Neo4JSchemaLoader {

	Logger logger  = Logger.getLogger(this.getClass()); 
	
	public void loadSchema(GraphDatabaseService graphDb){
		// START SNIPPET: createIndex

		IndexDefinition indexDefinition = null;

		try ( Transaction tx = graphDb.beginTx() )
		{
			Schema schema = graphDb.schema();
			
			indexDefinition = loadIndexFor(schema, indexDefinition, PoolingpeopleObjectType.USER, NodePropertyName.ID);
			indexDefinition = loadIndexFor(schema, indexDefinition, PoolingpeopleObjectType.USER, NodePropertyName.EMAIL);
			indexDefinition = loadIndexFor(schema, indexDefinition, PoolingpeopleObjectType.POOL, NodePropertyName.ID);
			indexDefinition = loadIndexFor(schema, indexDefinition, PoolingpeopleObjectType.TASK, NodePropertyName.ID);
			indexDefinition = loadIndexFor(schema, indexDefinition, PoolingpeopleObjectType.PROJECT, NodePropertyName.ID);
			indexDefinition = loadIndexFor(schema, indexDefinition, PoolingpeopleObjectType.CHANGELOG, NodePropertyName.ID);
			indexDefinition = loadIndexFor(schema, indexDefinition, PoolingpeopleObjectType.CHANGELOG_ACTION, NodePropertyName.ID);
			
			tx.success();
		}
		// END SNIPPET: createIndex
		// START SNIPPET: wait
		try ( Transaction tx = graphDb.beginTx() )
		{
			Schema schema = graphDb.schema();
			schema.awaitIndexesOnline( 10, TimeUnit.SECONDS );
			tx.success();
		}
//		// END SNIPPET: wait
	}

	private IndexDefinition loadIndexFor(Schema schema, IndexDefinition indexDefinition, 
			PoolingpeopleObjectType objectType, NodePropertyName propertyName){
		
		try{
			
			schema.constraintFor(
					DynamicLabel.label(objectType.name()))
					.assertPropertyIsUnique(propertyName.name())
					.create();
			
			
			logger.debug("Creating index for " + objectType.name() + ":" + propertyName.name()) ;
			
		}catch(ConstraintViolationException e){
			logger.debug("Already created: index for " + objectType.name() + ":" + propertyName.name());
		}
		
		return indexDefinition;
	}

}




































































