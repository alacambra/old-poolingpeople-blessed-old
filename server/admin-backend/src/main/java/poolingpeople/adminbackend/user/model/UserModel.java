package poolingpeople.adminbackend.user.model;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import poolingpeople.adminbackend.user.service.EntityFactoryService;
import poolingpeople.commons.entities.UserDTO;

@Model
public class UserModel {

	private UserDTO user = new UserDTO();
	private @Inject
	EntityFactoryService entityFactoryService;

	public void createUser() {
		entityFactoryService.createUser(user);
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<UserDTO> getAllUser() {
		return entityFactoryService.getAllUser();
	}
}
