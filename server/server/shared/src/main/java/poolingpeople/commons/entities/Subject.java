package poolingpeople.commons.entities;

public interface Subject {
	String getFirstName();

	String getLastName();

	void setFirstName(String firstName);

	void setLastName(String lastName);
	
	void writeComment(Comment comment);
}
