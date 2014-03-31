package poolingpeople.webapplication.business.utils.cdi;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.inject.Inject;
import org.apache.deltaspike.core.util.metadata.AnnotationInstanceProvider;
import org.apache.deltaspike.core.util.metadata.builder.AnnotatedTypeBuilder;

public class CDITestExtension implements Extension {

    public <X> void processInjectionTarget(@Observes ProcessAnnotatedType pat) {
        if (pat.getAnnotatedType().isAnnotationPresent(Stateless.class)) {
            createEJBWrapper(pat, pat.getAnnotatedType());
        }
    }

    private <X> void createEJBWrapper(ProcessAnnotatedType<X> pat, final AnnotatedType<X> annotatedType) {
        RequestScoped requestScopedAnnotation = AnnotationInstanceProvider.of(RequestScoped.class);
        Inject injectAnnotation = AnnotationInstanceProvider.of(Inject.class);
        
        AnnotatedTypeBuilder<X> builder = new AnnotatedTypeBuilder<X>().readFromType(annotatedType);
        builder.addToClass(requestScopedAnnotation);
        
        for(AnnotatedField<? super X> field : annotatedType.getFields()){
            if (field.isAnnotationPresent(EJB.class)) {
                builder.addToField(field, injectAnnotation);
            }
        }
        
        pat.setAnnotatedType(builder.create());
    }

}
