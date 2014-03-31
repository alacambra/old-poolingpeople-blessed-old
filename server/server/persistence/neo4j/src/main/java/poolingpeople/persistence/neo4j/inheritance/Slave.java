package poolingpeople.persistence.neo4j.inheritance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the given method retrieve a persisted object that needs the current object to exists
 * and therefore the <i>slave</i> object must be also be deleted when the <i>master</i> object is deleted
 * 
 * @author albert
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Slave {

}
