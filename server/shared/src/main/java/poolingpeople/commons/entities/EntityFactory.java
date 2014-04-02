package poolingpeople.commons.entities;

import java.util.List;


public interface EntityFactory {

	PoolingpeopleEntity getPoolingpeopleEntity(String uuid);
	
	void deleteTask(String uuid);

	Task getTaskById(String uuid);

	Task createTask(Task task);

	List<Task> getAllTask();

	Project createProject(Project project);

	Project getProjectById(String uuid);

	List<Project> getAllProject();

	void deleteProject(String uuid);

	User getUserById(String uuid);

	User getUserByCredentials(String email, String password);

	void deleteUser(String uuid);

	List<User> getAllUsers();

	User createUser(User user);

	Effort getEffortById(String uuid);

	/*
	 * Creating a persisted effort from a transient effort
	 */
	Effort createEffort(Effort effort);

	List<Comment> getObjectComments(PoolingpeopleEntity entity);
	Comment createCommentOnObject(Comment comment, PoolingpeopleEntity entity, Subject author);
	void deleteComment(String commentId);
	Comment getComment(String commentId);

	Comment createCommentOnObject(Comment comment, String uuid, Subject author);

	List<ChangeLog> getChangelogOfObject(String id);

	ChangeLog createChangeLog();

	ChangeLogAttributeUpdate createChangeLogAttributeUpdate();
}
