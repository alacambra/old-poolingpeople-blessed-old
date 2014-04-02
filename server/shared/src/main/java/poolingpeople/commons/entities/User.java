package poolingpeople.commons.entities;

import java.util.List;

public interface User extends PoolingpeopleEntity, Synchronizable, Subject {
	
	String getId();

	String getEmail();

	String getFirstName();
	
	void setFirstName(String firstName);
	
	String getLastName();

	void setLastName(String lastname);

	String getPassword();
	void setPassword(String password);
	
	Long getBirthDate();
	void setBirthDate(Long birthDate);

	List<Task> getTasks();

}




























































