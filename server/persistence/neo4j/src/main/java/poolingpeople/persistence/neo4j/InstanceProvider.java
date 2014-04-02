package poolingpeople.persistence.neo4j;

import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import poolingpeople.commons.exceptions.RootApplicationException;
import poolingpeople.commons.helper.Pager;
import poolingpeople.persistence.neo4j.entities.PersistedChangeLog;
import poolingpeople.persistence.neo4j.entities.PersistedChangeLogAttributeUpdateAction;
import poolingpeople.persistence.neo4j.entities.PersistedComment;
import poolingpeople.persistence.neo4j.entities.PersistedEffort;
import poolingpeople.persistence.neo4j.entities.PersistedProject;
import poolingpeople.persistence.neo4j.entities.PersistedTask;
import poolingpeople.persistence.neo4j.entities.PersistedUser;

@ApplicationScoped
public class InstanceProvider {
	
	@Inject
	Instance<Pager> pagerSource;

	@Inject
	private Instance<PersistedTask> persistedTaskSource;
	
	@Inject
	private Instance<PersistedUser> persistedUserSource;
	
	@Inject
	private Instance<PersistedProject> persistedProjectSource;
	
	@Inject
	private Instance<PersistedComment> persistedCommentSource;
	
	@Inject
	private Instance<PersistedEffort> persistedEffortSource;
	
	@Inject
	private Instance<PersistedChangeLogAttributeUpdateAction> persistedChangeLogAttributeUpdateActionSource;
	
	@Inject
	private Instance<PersistedChangeLog> persistedChangeLogSource;
	 
	private HashMap<Class<?>, Instance<?>> instancesSources;
	
	public HashMap<Class<?>, Instance<?>> getInstancesSources() {
		
		return instancesSources == null ? loadInstancesSources() : instancesSources;
	}
	
	private HashMap<Class<?>, Instance<?>> loadInstancesSources(){
		instancesSources = new HashMap<>();
		instancesSources.put(PersistedTask.class, persistedTaskSource);
		instancesSources.put(Pager.class, pagerSource);
		instancesSources.put(PersistedUser.class, persistedUserSource);
		instancesSources.put(PersistedComment.class, persistedCommentSource);
		instancesSources.put(PersistedEffort.class, persistedEffortSource);
		instancesSources.put(PersistedProject.class, persistedProjectSource);
		instancesSources.put(PersistedChangeLogAttributeUpdateAction.class, persistedChangeLogAttributeUpdateActionSource);
		instancesSources.put(PersistedChangeLog.class, persistedChangeLogSource);
		return instancesSources; 
				
	}
	
	public <T> T getInstanceForClass(Class<T> clazz){
		
		if (getInstancesSources().containsKey(clazz)) {
			return (T) getInstancesSources().get(clazz).get();	
		}
		
		throw new RootApplicationException("No Instance provider for class " + clazz.getCanonicalName() + " found");
	}
}
