package poolingpeople.commons.entities;

public interface Creator {
	String getId();
	
	String getEmail();
	
	String getFirstName();

	String getLastName();

	void setFirstName(String firstName);

	void setLastName(String lastName);

}
