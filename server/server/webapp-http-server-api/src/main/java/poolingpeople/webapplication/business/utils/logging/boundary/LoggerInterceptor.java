package poolingpeople.webapplication.business.utils.logging.boundary;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import poolingpeople.webapplication.business.utils.configuration.boundary.Configurable;

@Interceptor
@Loggable
public class LoggerInterceptor {
	@Inject
	private Logger logger;
	@Inject @Configurable("isLogging")
	private boolean isLogginOn;

	@AroundInvoke
	public Object logMethod(InvocationContext context) throws Exception {
		return (isLogginOn) ? logMethodInvocation(context) : noLogging(context) ;
	}

	private Object noLogging(InvocationContext context) throws Exception {
		return context.proceed();
	}

	private Object logMethodInvocation(InvocationContext context)
			throws Exception {
		
		logger.info("Entering: " +
				context.getMethod().getDeclaringClass().getCanonicalName()+"#"+ 
				context.getMethod().getName() + stringifyParameters(context.getParameters()));
		try {
			return context.proceed();
		} finally {
			logger.info("Exiting: "+context.getMethod().getDeclaringClass().getCanonicalName()+"#"+ context.getMethod().getName());
		}
	}

	private String stringifyParameters(Object[] params) {

		String stringifiedParams = "";

		if (params != null) {
			for(Object param : params) {
				if(param == null) continue;
				stringifiedParams += ":" + param.toString();
			}
		}
		return stringifiedParams; 
	}
}
