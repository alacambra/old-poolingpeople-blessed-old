package poolingpeople.webapplication.business.user.boundary;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.Comment;
import poolingpeople.commons.entities.IgnoreAttribute;
import poolingpeople.commons.entities.Task;
import poolingpeople.commons.entities.User;
import poolingpeople.webapplication.business.utils.validation.EmailValidation;

public class UserDTO implements User {

	private String id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private Long birthDate;

	@IgnoreAttribute
	@Override
	public String getId() {
		return id;
	}

	@EmailValidation
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Long birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	@JsonIgnore
	public List<Task> getTasks() {
		return null;
	}

	@Override
	public void synchronizeWith(Object tplObject) {
		// TODO Auto-generated method stub
		
	}

	@JsonIgnore
	@Override
	public List<ChangeLog> getChangeLogList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@JsonIgnore
	public List<Comment> getObjectComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}
}
