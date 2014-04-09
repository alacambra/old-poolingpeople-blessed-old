package poolingpeople.adminbackend.user.model;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import poolingpeople.commons.entities.EntityFactory;
import poolingpeople.commons.entities.UserDTO;
import poolingpeople.persistence.neo4j.Neo4jTransaction;

@Model
@Neo4jTransaction
public class UserModel {

	private @Inject EntityFactory entityFactory;
	private UserDTO userDTO = new UserDTO();
	
	public void createUser() {
		entityFactory.createUser(userDTO);
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	
}
