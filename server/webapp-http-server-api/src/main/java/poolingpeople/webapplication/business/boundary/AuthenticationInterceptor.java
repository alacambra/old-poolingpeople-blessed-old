package poolingpeople.webapplication.business.boundary;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import poolingpeople.webapplication.business.utils.configuration.boundary.Configurable;

@Interceptor
@AuthValidator
public class AuthenticationInterceptor {

	@Inject
	private ILoggedUserContainer loggedUserContainer;
	
	@Inject
	@Configurable("debug")
	private boolean isDebugging;

	@AroundInvoke
	public Object checkCallPermission(InvocationContext context) throws Exception {

		if(! isDebugging) {

			loggedUserContainer.validateCredentials();

			if (!loggedUserContainer.userIsSuccessfullyLogged() && context.getMethod().getAnnotation(AuthNotRequired.class) == null) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}

		}
		return context.proceed();

	}
}
