package poolingpeople.webapplication.business.utils.helpers;

import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorProducer {
    
    @Produces
    public Validator produce(){
        ValidatorFactory buildDefaultValidatorFactory = Validation.buildDefaultValidatorFactory();
        return buildDefaultValidatorFactory.getValidator();
    }
}
