package poolingpeople.adminbackend.user.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import poolingpeople.commons.entities.EntityFactory;
import poolingpeople.commons.entities.UserDTO;
import poolingpeople.persistence.neo4j.Neo4jTransaction;

@RequestScoped
@Neo4jTransaction
public class EntityFactoryService {

	private @Inject EntityFactory entityFactory;
	
	public void createUser(UserDTO userDTO) {
		entityFactory.createUser(userDTO);
	}

	public List<UserDTO> getAllUser() {
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("Hans");
		return Arrays.asList(userDTO);
	}
	
}
