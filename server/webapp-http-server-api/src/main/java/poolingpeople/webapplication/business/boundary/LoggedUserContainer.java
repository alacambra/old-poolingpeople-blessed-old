package poolingpeople.webapplication.business.boundary;

import poolingpeople.commons.entities.EntityFactory;
import poolingpeople.commons.entities.User;

public class LoggedUserContainer implements ILoggedUserContainer {

	private EntityFactory entityFactory;
	private String email;
	private String password;
	private User user;

	public LoggedUserContainer(EntityFactory entityFactory){
		this.entityFactory = entityFactory;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void validateCredentials() {

		user = email == null || password == null ? 
				null : entityFactory.getUserByCredentials(email, password);

	}

	@Override
	public User getUser() {
		return user;
	}
	
	@Override
	public boolean userIsSuccessfullyLogged() {
		return user != null;
	}
}



























































































