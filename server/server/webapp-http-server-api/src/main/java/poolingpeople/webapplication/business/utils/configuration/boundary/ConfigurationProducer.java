package poolingpeople.webapplication.business.utils.configuration.boundary;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;


public class ConfigurationProducer {
	@Inject
	Configurator configurator;
	
	@Produces 
	public String injectStringValue(InjectionPoint point) {
		return (hasFieldAnnotation(point)) ? configurator.getStringValue(getValueFromAnnotation(point)) : "";
	}

	@Produces 
	public long injectLongValue(InjectionPoint point) {
		return (hasFieldAnnotation(point)) ? configurator.getLongValue(getValueFromAnnotation(point)) : 0;
	}
	
	@Produces 
	public int injectIntValue(InjectionPoint point) {
		return (hasFieldAnnotation(point)) ? configurator.getIntValue(getValueFromAnnotation(point)) : 0;
	}
	
	@Produces 
	public boolean injectBooleanValue(InjectionPoint point) {
		return (hasFieldAnnotation(point)) ? configurator.getBooleanValue(getValueFromAnnotation(point)) : false;
	}
	
	private String getValueFromAnnotation(InjectionPoint point) {
		@SuppressWarnings("unchecked")
		AnnotatedField<Configurable> field = (AnnotatedField<Configurable>) point.getAnnotated();
		Configurable configuration = field.getAnnotation(Configurable.class);
		return (configuration != null) ? configuration.value() : "";
	}

	private boolean hasFieldAnnotation(InjectionPoint point) {
		return (point.getAnnotated() == null || !(point.getAnnotated() instanceof AnnotatedField)) ? false : true; 
	}
}
