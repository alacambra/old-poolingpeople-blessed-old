package poolingpeople.persistence.neo4j.entities;

import javax.inject.Inject;

import org.neo4j.graphdb.Node;

import poolingpeople.commons.entities.ChangeLogAction;
import poolingpeople.commons.entities.ChangeLogAttributeUpdate;
import poolingpeople.persistence.neo4j.NeoManager;
import poolingpeople.persistence.neo4j.NodePropertyName;

public class PersistedChangeLogAttributeUpdateAction implements ChangeLogAttributeUpdate {
	private Node changeLogNode;
	@Inject NeoManager manager; 
	private String changedAtributeName;
	private String code;
	private String oldValue;
	private String newValue;
	private String id;
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getChangedAttributeName() {
		return changedAtributeName;
	}

	@Override
	public String getOldValue() {
		return oldValue;
	}

	@Override
	public String getNewValue() {
		return newValue;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public ChangeLogAction loadChangeLogActionFromNode(Node node) {
		changeLogNode = node;
		changedAtributeName = manager.getStringProperty(changeLogNode, NodePropertyName.CHANGED_PROPERTY.name());
		code = manager.getStringProperty(changeLogNode, NodePropertyName.CHANGELOG_ACTION_CODE.name());
		oldValue = manager.getStringProperty(changeLogNode, NodePropertyName.OLD_VALUE.name());
		newValue = manager.getStringProperty(changeLogNode, NodePropertyName.NEW_VALUE.name());
		id = manager.getStringProperty(changeLogNode, NodePropertyName.ID.name());
		return this;
	}

	@Override
	public void setChangedAttributeName(String name) {
		manager.setProperty(changeLogNode, NodePropertyName.CHANGED_PROPERTY.name(), name);
	}

	@Override
	public void setOldValue(String oldValue) {
		manager.setProperty(changeLogNode, NodePropertyName.OLD_VALUE.name(), oldValue);
	}

	@Override
	public void setNewValue(String newValue) {
		manager.setProperty(changeLogNode, NodePropertyName.NEW_VALUE.name(), newValue);
	}

	@Override
	public void setChangeLogNode(Node node) {
		this.changeLogNode = node;
	}

}
