package poolingpeople.persistence.neo4j.entities;

import javax.inject.Inject;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.ChangeLogAction;
import poolingpeople.commons.entities.ChangeLogAttributeUpdate;
import poolingpeople.commons.entities.Subject;
import poolingpeople.persistence.neo4j.InstanceProvider;
import poolingpeople.persistence.neo4j.NodePropertyName;
import poolingpeople.persistence.neo4j.PoolingpeopleObjectType;
import poolingpeople.persistence.neo4j.Relations;
import poolingpeople.persistence.neo4j.exceptions.RelationAlreadyExistsException;

public class PersistedChangeLog extends
		AbstractPersistedModel<PersistedChangeLog> implements ChangeLog {

	public static final PoolingpeopleObjectType NODE_TYPE = PoolingpeopleObjectType.CHANGELOG;

	@Inject
	private InstanceProvider instanceProvider;

	@Inject
	private ChangeLogActionResolver resolver;

	@Override
	protected void initializeVariables() {
	}

	@Override
	public Subject getSubject() {
		return getEndNode(Relations.HAS_SUBJECT, PersistedUser.class);
	}

	@Override
	public ChangeLogAction getAction() {
		ChangeLogAction actualChangeLogActionObject;
		String changeLogActionPropertyType = getStringProperty(NodePropertyName.CHANGELOG_ACTION);
		Class<? extends ChangeLogAction> changeLogActionType = resolver
				.resolveChangeLogActionByName(ChangeLogActionType
						.valueOf(changeLogActionPropertyType));
		actualChangeLogActionObject = instanceProvider.getInstanceForClass(
				changeLogActionType)
				.loadChangeLogActionFromNode(underlyingNode);
		return actualChangeLogActionObject;
	}

	@Override
	public Long getDate() {
		return getLongProperty(NodePropertyName.TIME);
	}

	@Override
	public void setSubject(Subject subject) {
		if (relationExistsTo((AbstractPersistedModel<?>) subject,
				Relations.HAS_SUBJECT)) {
			throw new RelationAlreadyExistsException();
		}
		createRelationshipTo((AbstractPersistedModel<?>) subject,
				Relations.HAS_SUBJECT);

		// TODO: UPDATE ALL ?
	}

	// instead of creating relationship between the action and the changelog,
	// set the corresponding values on the underlying node as properties
	@Override
	public void setAction(ChangeLogAction action) {
//		if (relationExistsTo((AbstractPersistedModel<?>) action, Relations.HAS_SUBJECT)) {
//			throw new RelationAlreadyExistsException();
//		}
//		createRelationshipTo((AbstractPersistedModel<?>) action, Relations.HAS_SUBJECT);
	}

	@Override
	public void setDate(Long date) {
		setProperty(NodePropertyName.TIME, date);
	}

	@Override
	public ChangeLog load(ChangeLogAttributeUpdate changeLogAttributeUpdate,
			Subject retrieveSubject, long currentTimeMillis) {
		this.setAction(changeLogAttributeUpdate);
		this.setSubject(retrieveSubject);
		this.setDate(currentTimeMillis);
		return this;
	}

}
