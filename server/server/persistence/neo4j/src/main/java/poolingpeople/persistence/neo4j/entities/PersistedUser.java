package poolingpeople.persistence.neo4j.entities;

import java.util.List;

import javax.ejb.Stateless;

import org.neo4j.graphdb.Direction;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.Comment;
import poolingpeople.commons.entities.Task;
import poolingpeople.commons.entities.User;
import poolingpeople.commons.exceptions.RootApplicationException;
import poolingpeople.persistence.neo4j.NodePropertyName;
import poolingpeople.persistence.neo4j.PoolingpeopleObjectType;
import poolingpeople.persistence.neo4j.Relations;
import poolingpeople.persistence.neo4j.container.UserIndexContainer;
import poolingpeople.persistence.neo4j.exceptions.NotUniqueException;

public class PersistedUser extends AbstractPersistedModel<PersistedUser> implements User {

	public static final PoolingpeopleObjectType NODE_TYPE = PoolingpeopleObjectType.USER;

	public PersistedUser(){
	}

	public PersistedUser loadExistingUserWithCredentials(String email, String password){
		loadByCredentials(email, password);
		return this;
	}
	
	public PersistedUser createUserWithCredentials(String email, String password, User user){
		super.createNodeFromDtoModel(NODE_TYPE, user);
		manager.addToIndex(underlyingNode, new UserIndexContainer(email, password));
		setLoginEmail(email);
		setPassword(password);
		return this;
	}
	
	private void loadByCredentials(String email, String password) {

		if (underlyingNode != null) {
			throw new RootApplicationException("Node already loaded");
		} 
		
		try {
			underlyingNode = getPersistedObject(new UserIndexContainer(email, password), this.getClass()).getNode();
		} catch (NotUniqueException e){
			throw new NotUniqueException("Two many user with the same email/psw", e);
		}
		
		
	}
	
	@Override
	public String getId() {
		return getStringProperty(NodePropertyName.ID);
	}

	@Override
	public String getFirstName() {
		return getStringProperty(NodePropertyName.FIRSTNAME);
	}

	@Override
	public void setFirstName(String firstName) {
		setProperty(NodePropertyName.FIRSTNAME, firstName);

	}

	@Override
	public String getEmail() {
		return getStringProperty(NodePropertyName.EMAIL);
	}

	private void setLoginEmail(String email) {
		setProperty(NodePropertyName.EMAIL, email);
	}

	@Override
	public String getLastName() {
		return getStringProperty(NodePropertyName.LASTNAME);
	}

	@Override
	public void setLastName(String description) {
		setProperty(NodePropertyName.LASTNAME, description);
	}

	@Override
	public String getPassword() {
		return getStringProperty(NodePropertyName.PASSWORD);
	}

	@Override
	public void setPassword(String password) {
		setProperty(NodePropertyName.PASSWORD, password);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof PersistedUser
				&& ((PersistedUser) obj).getNode().equals(underlyingNode);
	}

	public void addSubtask(User child) {
		Relations.IS_SUBPROJECT_OF.relationIsPossibleOrException(NODE_TYPE, ((PersistedUser) child).getNodeType());
		createRelationshipTo((PersistedUser) child, Relations.IS_SUBPROJECT_OF);
	}
	
	@Override
	public Long getBirthDate() {
		return getLongProperty(NodePropertyName.BIRTHDATE);
	}

	@Override
	public void setBirthDate(Long birthDate) {
		setProperty(NodePropertyName.BIRTHDATE, birthDate);
	}
	
	@Override
	public List<Task> getTasks() {
		return getRelatedNodes(Relations.DOES, PersistedTask.class, Task.class, Direction.OUTGOING);
	}

	@Override
	protected void initializeVariables() {
		
	}

	@Override
	public List<ChangeLog> getChangeLogList() {
		return getRelatedNodes(Relations.HAS_CHANGE_LOG, PersistedChangeLog.class, ChangeLog.class, Direction.OUTGOING);
	}

	@Override
	public void writeComment(Comment comment) {
		createRelationshipTo((PersistedComment)comment, Relations.COMMENTED);
	}

}























































