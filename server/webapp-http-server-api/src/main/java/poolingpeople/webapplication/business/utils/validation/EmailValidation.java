package poolingpeople.webapplication.business.utils.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

/**
 * http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
 */
@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {

    String message() default "Validation error: does not match email format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
