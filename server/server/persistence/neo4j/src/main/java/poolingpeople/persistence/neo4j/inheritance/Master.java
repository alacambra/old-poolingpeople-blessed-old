package poolingpeople.persistence.neo4j.inheritance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the given method retrieve a persisted object without which the current object should not exists
 * and therefore the relation must be removed when the current object is deleted
 * 
 * @author albert
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Master {

}
