package poolingpeople.persistence.neo4j;

import org.jglue.cdiunit.CdiRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

/**
 * Using decorator pattern in order to combine the CdiRunner services all along with the required
 * Neo4J Transaction and services: NeoManager instance with a test GraphDatabaseService
 */
public class Neo4JCdiRunner extends CdiRunner{
	private GraphDatabaseService graphDbService;

	public Neo4JCdiRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}
	
	@Override
	protected Object createTest() throws Exception {
		
		//Using the implementation of the CDI Runner
		Object classOfTheTest = super.createTest();

		if(!(classOfTheTest instanceof AbstractPersitenceTest))
			throw new ClassCastException("This Junit Runner implementation is only applicable on a object of type AbstractPersistenceTest");

		this.graphDbService = ((AbstractPersitenceTest) classOfTheTest).getManager().getGraphDbService();
		
		return classOfTheTest;
	}
	
	@Override
	protected Statement methodBlock(final FrameworkMethod method) {
		final Statement defaultStatement = super.methodBlock(method);
		
		//wrapping method execution with an active Neo4J Transaction
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				Transaction transaction = graphDbService.beginTx(); 
				try{
					defaultStatement.evaluate();
					transaction.success();
				}finally{
					transaction.close();
				}
			}
		};
	}

}
