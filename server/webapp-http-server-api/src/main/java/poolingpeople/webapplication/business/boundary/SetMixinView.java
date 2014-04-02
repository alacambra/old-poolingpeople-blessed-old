package poolingpeople.webapplication.business.boundary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
/*
 * @Todo support for array pairs to handle several mixins
 */
public @interface SetMixinView {

	Class<?> entity();
	Class<?> mixin();

}
