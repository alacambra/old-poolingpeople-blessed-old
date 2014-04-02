package poolingpeople.webapplication.business.utils.logging.boundary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * Allows Methods, or at class level (all methods included) to be logged
 * LoggerInterceptor contains the logging functionality
 * @see LoggerInterceptor 
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Loggable {
}
