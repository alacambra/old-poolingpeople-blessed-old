package poolingpeople.webapplication.business.boundary;

import javax.enterprise.inject.Produces;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.Comment;
import poolingpeople.commons.entities.Effort;
import poolingpeople.commons.entities.Project;
import poolingpeople.commons.entities.Task;
import poolingpeople.commons.entities.User;
import poolingpeople.webapplication.business.changelog.boundary.ChangelogMixin;
import poolingpeople.webapplication.business.comments.boundary.CommentsMixin;
import poolingpeople.webapplication.business.project.boundary.ProjectMixin;
import poolingpeople.webapplication.business.task.entity.EffortMixin;
import poolingpeople.webapplication.business.task.entity.TaskMixin;
import poolingpeople.webapplication.business.user.boundary.UserMixin;

public class ObjectMapperProducer {

    @Produces
    public ObjectMapper produceObjectMapper() {
        return createObjectMapperWithMixinConfiguration();
    }

    private ObjectMapper createObjectMapperWithMixinConfiguration() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(
                new MixinModule()
                .addMixin(Task.class, TaskMixin.class)
                .addMixin(Effort.class, EffortMixin.class)
                .addMixin(Project.class, ProjectMixin.class)
                .addMixin(User.class, UserMixin.class)
                .addMixin(Comment.class, CommentsMixin.class)
                .addMixin(ChangeLog.class, ChangelogMixin.class)
        );
        return mapper;
    }

    private static class MixinModule extends SimpleModule {

        public MixinModule() {
            super("MixinModule", new Version(0, 0, 1, null));
        }

        public MixinModule addMixin(Class<?> entityClazz, Class<?> mixinClazz) {
            setMixInAnnotation(entityClazz, mixinClazz);
            return this;
        }
    }

}
