package poolingpeople.adminbackend.user.model;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import poolingpeople.commons.entities.User;
import poolingpeople.commons.entities.UserDTO;
import poolingpeople.adminbackend.user.service.EntityFactoryService;

@Model
public class UserModel {

	private UserDTO user = new UserDTO();
	private @Inject EntityFactoryService entityFactory;
	
	public void createUser() {
		entityFactory.createUser(user);
	}


	public UserDTO getUser() {
		return user;
	}


	public void setUser(UserDTO user) {
		this.user = user;
	}


	public List<UserDTO> getAllUser() {
		return entityFactory.getAllUser();
	}
}
