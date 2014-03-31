package poolingpeople.persistence.neo4j;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

@Interceptor
@Neo4jTransaction
public class TransactionInterceptor {

	@Inject
	GraphDatabaseService databaseService; 
	
	@Inject
    HttpServletRequest request;

	@AroundInvoke
	public Object logMethod(InvocationContext context) throws Exception {

		if ( request == null )
			throw new Exception("request kaput");
		
		Transaction tx = databaseService.beginTx();

		try {
			Object o = context.proceed();
			tx.success();
			return o;
		} catch (Exception e) {
			tx.failure();
			throw e;
		} finally {
			tx.close();
		}
	}
}
