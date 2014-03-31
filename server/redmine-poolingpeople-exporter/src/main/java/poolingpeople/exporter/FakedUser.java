package poolingpeople.exporter;

import java.util.List;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.Comment;
import poolingpeople.commons.entities.Task;
import poolingpeople.commons.entities.User;

public class FakedUser implements User{

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastName(String lastname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getBirthDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBirthDate(Long birthDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Task> getTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void synchronizeWith(Object tplObject) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<ChangeLog> getChangeLogList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getObjectComments() {
		// TODO Auto-generated method stub
		return null;
	}

}
