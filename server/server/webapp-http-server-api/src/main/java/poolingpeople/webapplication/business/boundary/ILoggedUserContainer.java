package poolingpeople.webapplication.business.boundary;

import poolingpeople.commons.entities.User;

public interface ILoggedUserContainer {

	String getPassword();

	String getEmail();

	void setPassword(String password);

	void setEmail(String email);

	void validateCredentials();

	User getUser();

	boolean userIsSuccessfullyLogged();

}