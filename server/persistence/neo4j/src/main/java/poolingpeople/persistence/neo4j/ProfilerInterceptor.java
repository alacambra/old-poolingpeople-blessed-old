package poolingpeople.persistence.neo4j; 

import java.lang.annotation.Target;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import poolingpeople.persistence.neo4j.entities.AbstractPersistedModel;

@Interceptor
@Neo4jProfiler
public class ProfilerInterceptor {

	static String layer = "";
	Logger logger = Logger.getLogger(this.getClass());

	@AroundInvoke
	public Object profileMethod(InvocationContext context) throws Exception {
		
		String id = "loading...";
		
		if (AbstractPersistedModel.class.isAssignableFrom(context.getTarget().getClass())){
			AbstractPersistedModel<?> target = (AbstractPersistedModel<?>)context.getTarget();

			if(target.getNode() != null){
				id = ((AbstractPersistedModel<?>)context.getTarget()).getId();
			} 
		} else {
			id = context.getTarget().getClass().getSimpleName();
		}

		layer = layer + "--";
		logger.debug(layer + id + ":" + context.getMethod().getName() + " starting --------" );
		Long start = System.nanoTime();

		Object o = context.proceed();

		Long total = System.nanoTime() - start;
		logger.debug(layer + id + ":" +  context.getMethod().getName() + " : " + total + " : ns");
		layer = layer.substring(0, layer.length()-2);
		return o;
	}
}
