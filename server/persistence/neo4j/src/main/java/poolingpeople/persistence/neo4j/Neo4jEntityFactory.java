package poolingpeople.persistence.neo4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Singleton;
import javax.inject.Inject;

import org.neo4j.graphdb.Node;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.ChangeLogAttributeUpdate;
import poolingpeople.commons.entities.Comment;
import poolingpeople.commons.entities.Effort;
import poolingpeople.commons.entities.EntityFactory;
import poolingpeople.commons.entities.PoolingpeopleEntity;
import poolingpeople.commons.entities.Project;
import poolingpeople.commons.entities.Subject;
import poolingpeople.commons.entities.Task;
import poolingpeople.commons.entities.User;
import poolingpeople.commons.helper.Pager;
import poolingpeople.persistence.neo4j.entities.AbstractPersistedModel;
import poolingpeople.persistence.neo4j.entities.PersistedChangeLog;
import poolingpeople.persistence.neo4j.entities.PersistedChangeLogAttributeUpdateAction;
import poolingpeople.persistence.neo4j.entities.PersistedClassResolver;
import poolingpeople.persistence.neo4j.entities.PersistedComment;
import poolingpeople.persistence.neo4j.entities.PersistedEffort;
import poolingpeople.persistence.neo4j.entities.PersistedProject;
import poolingpeople.persistence.neo4j.entities.PersistedTask;
import poolingpeople.persistence.neo4j.entities.PersistedUser;
import poolingpeople.persistence.neo4j.exceptions.NodeNotFoundException;

@Singleton
@UpdateHierarchicalResources
public class Neo4jEntityFactory implements EntityFactory { 

	@Inject
	private NeoManager manager;

	/**
	 * TODO : replaced the declared instance sources with the InstanceProvider
	 * 
	 */
	
	@Inject
	InstanceProvider instanceProvider;

	//	@Inject
	//	Instance<Pager> pagerSource;
	//
	@Inject
	private PersistedClassResolver classResolver;


	@Override
	public void deleteTask(String uuid)  {

		PersistedTask task = getTaskById(uuid);
		task.runDeletePreconditions();
		Set<AbstractPersistedModel<?>> objects = task.loadObjectsToInform();
		manager.removeNode(task.getNode());

		//		for(AbstractPersistedModel<?> model : objects){
		//			model.updateAll();
		//		}
	}

	@Override
	public PersistedTask getTaskById(String uuid)  {
		return instanceProvider.getInstanceForClass(PersistedTask.class).loadExistingNodeById(uuid, PoolingpeopleObjectType.TASK);
	}

	@Override
	public PersistedTask createTask(Task task)  {
		return instanceProvider.getInstanceForClass(PersistedTask.class).createNodeFromDtoModel(PoolingpeopleObjectType.TASK, task);
	}

	@Override
	public List<Task> getAllTask() {
		return manager.getPersistedObjects(
				manager.getNodes(PersistedTask.NODE_TYPE.name(), 
						instanceProvider.getInstanceForClass(Pager.class).getStart(),
						instanceProvider.getInstanceForClass(Pager.class).getSize()), 
						new ArrayList<Task>(), 
						PersistedTask.class, 
						Task.class);
	}

	@Override
	public PersistedProject createProject(Project project) {
		return instanceProvider.getInstanceForClass(PersistedProject.class).createNodeFromDtoModel(PoolingpeopleObjectType.PROJECT, project);
	}

	@Override
	public PersistedProject getProjectById(String uuid) {
		return instanceProvider.getInstanceForClass(PersistedProject.class).loadExistingNodeById(uuid, PoolingpeopleObjectType.PROJECT);
	}

	@Override
	public List<Project> getAllProject() {
		return manager.getPersistedObjects(
				manager.getNodes(PersistedProject.NODE_TYPE.name(), 
						instanceProvider.getInstanceForClass(Pager.class).getStart(),
						instanceProvider.getInstanceForClass(Pager.class).getSize()), 
						new ArrayList<Project>(), 
						PersistedProject.class,
						Project.class);
	}

	@Override
	public void deleteProject(String uuid) {
		manager.removeNode(getProjectById(uuid).getNode());
	}

	@Override
	public User getUserById(String uuid) {
		return instanceProvider.getInstanceForClass(PersistedUser.class).loadExistingNodeById(uuid, PoolingpeopleObjectType.USER);
	}

	@Override
	public User getUserByCredentials(String email, String password) {
		try{
			return instanceProvider.getInstanceForClass(PersistedUser.class).loadExistingUserWithCredentials(email, password);
		} catch(Exception e) {
			/*
			 * @todo Exception is catched here because if catched in interceptor or directly in the LiggedUSerContainer
			 * provoque unexpected exceptions behavior
			 */
			if (!isNodeNotFoundException(e)){
				throw e;
			}

			return null;
		}
	}

	private boolean isNodeNotFoundException(Throwable e){

		if (e instanceof NodeNotFoundException){
			return true;
		}

		Throwable ex = e.getCause();

		while(ex != null) {

			if(ex instanceof NodeNotFoundException) {
				return true;
			}

			ex = ex.getCause();
		}

		return false;

	}

	@Override
	public void deleteUser(String uuid) {
		manager.removeNode(getTaskById(uuid).getNode());

	}

	@Override
	public List<User> getAllUsers() {

		return manager.getPersistedObjects(
				manager.getNodes(PersistedUser.NODE_TYPE.name(), instanceProvider.getInstanceForClass(Pager.class).getStart(),
						instanceProvider.getInstanceForClass(Pager.class).getSize()), 
						new ArrayList<User>(), 
						PersistedUser.class,
						User.class);
	}

	@Override
	public User createUser(User user) {
		return instanceProvider.getInstanceForClass(PersistedUser.class).createUserWithCredentials(user.getEmail(), user.getPassword(), user);
	}

	@Override
	public Effort getEffortById(String uuid) {
		return instanceProvider.getInstanceForClass(PersistedEffort.class).loadExistingNodeById(uuid, PoolingpeopleObjectType.EFFORT); 
	}

	/*
	 * Creating a persisted effort from a transient effort
	 */
	@Override
	public Effort createEffort(Effort effort) {
		return instanceProvider.getInstanceForClass(PersistedEffort.class).createNodeFromDtoModel(PoolingpeopleObjectType.EFFORT, effort);
	}

	public NeoManager getManager() {
		return manager;
	}

	@Override
	public List<Comment> getObjectComments(PoolingpeopleEntity entity) {
		return entity.getObjectComments();
	}

	@Override
	public Comment createCommentOnObject(Comment comment, PoolingpeopleEntity entity, Subject author) {
		Comment c = instanceProvider.getInstanceForClass(PersistedComment.class)
				.createNodeFromDtoModel(PoolingpeopleObjectType.COMMENT, comment);
		
		entity.addComment(c);
		author.writeComment(c);
		
		return c;
	}

	@Override
	public void deleteComment(String commentId) {
		manager.removeNode(instanceProvider.getInstanceForClass(PersistedComment.class).loadExistingNodeById(commentId, PoolingpeopleObjectType.COMMENT).getNode());
	}

	@Override
	public Comment getComment(String commentId) {
		return instanceProvider.getInstanceForClass(PersistedComment.class).loadExistingNodeById(commentId, PoolingpeopleObjectType.COMMENT);
	}

	@Override
	public PoolingpeopleEntity getPoolingpeopleEntity(String uuid) {
		return classResolver.getPoolingpeopleEntityFromNode(
				manager.getUniqueNode(NodePropertyName.ID.name(), NodePropertyName.ID.name(), uuid));
	}

	@Override
	public Comment createCommentOnObject(Comment comment, String objectUuid, Subject author) {
		return createCommentOnObject(comment,
				classResolver.getPoolingpeopleEntityFromNode(
						manager.getUniqueNode(NodePropertyName.ID.name(), NodePropertyName.ID.name(), objectUuid)), author);
	}

	@Override
	public List<ChangeLog> getChangelogOfObject(String id) {
		PoolingpeopleEntity poolingpeopleEntity = getPoolingpeopleEntity(id);
		Node node = ((AbstractPersistedModel<?>) poolingpeopleEntity).getNode();
		Class<? extends AbstractPersistedModel<?>> persistedEntityClassForNode = classResolver.getPersistedEntityClassForNode(node);
		AbstractPersistedModel<?> instanceForClass = instanceProvider.getInstanceForClass(persistedEntityClassForNode).loadModelFromExistentNode(node);
		List<ChangeLog> changeLogList = ((PoolingpeopleEntity)instanceForClass).getChangeLogList();
		return changeLogList;
	}
	
	@Override
	public ChangeLog createChangeLog() {
		return instanceProvider.getInstanceForClass(PersistedChangeLog.class).createNodePriorLoadingAttributes(PoolingpeopleObjectType.CHANGELOG);
	}

	@Override
	public ChangeLogAttributeUpdate createChangeLogAttributeUpdate() {
		return instanceProvider.getInstanceForClass(PersistedChangeLogAttributeUpdateAction.class);
	}
}
