package poolingpeople.persistence.neo4j.inheritance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated method retrieve or set an object that must be propagated. 
 * An inheritable object should have a default value and a calculated value. 
 * Update method to allow the manager object to update it should be supplied.
 * How the update is performed is decided by the updated object
 * @author albert
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Inheritable {
	PropagationType propagationType();
}
