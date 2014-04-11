package poolingpeople.commons.entities;

public interface Service extends PoolingpeopleEntity {

	void setTitle(String title);
	String getTitle();
	void setDescription(String description);
	String getDescription();
	void setActiveStatus(boolean activeStatus);
	boolean getActiveStatus();
}
