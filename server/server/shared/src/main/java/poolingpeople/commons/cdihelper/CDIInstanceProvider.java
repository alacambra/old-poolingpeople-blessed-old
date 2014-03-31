package poolingpeople.commons.cdihelper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import poolingpeople.commons.exceptions.RootApplicationException;

@ApplicationScoped
public class CDIInstanceProvider implements InstanceProvider{

	@Inject
	BeanManager manager;
	
	@Override
	public <T> T getInstance(Class<T> clazz) {
		Bean<?> bean = manager.resolve(manager.getBeans(clazz));
		
		if(bean == null) throw new RootApplicationException("Bean could not be resolved for Class: " + clazz.toString());
		
		CreationalContext<?> cc = manager.createCreationalContext(bean);
		return clazz.cast(manager.getReference(bean, clazz, cc));
	}

}
