package poolingpeople.commons.entities;

public interface ChangeLogAttributeUpdate extends ChangeLogAction {
	String getChangedAttributeName();

	String getOldValue();

	String getNewValue();

	void setChangedAttributeName(String name);
	
	void setOldValue(String oldValue);
	
	void setNewValue(String newValue);

}
