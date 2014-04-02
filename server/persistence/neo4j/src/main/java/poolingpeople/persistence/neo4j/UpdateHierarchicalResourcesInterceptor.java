package poolingpeople.persistence.neo4j;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

@Interceptor
@UpdateHierarchicalResources
public class UpdateHierarchicalResourcesInterceptor {

	@Inject
	UpdateQueue updateQueue; 
	
	@AroundInvoke
	public Object doUpdate(InvocationContext context) throws Exception {

			Object o = context.proceed();
			updateQueue.executeUpdates();
			return o;
	}
}
