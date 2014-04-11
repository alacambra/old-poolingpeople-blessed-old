package poolingpeople.adminbackend.user.model;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import poolingpeople.commons.entities.EntityFactory;
import poolingpeople.commons.entities.User;
import poolingpeople.commons.entities.UserDTO;
import poolingpeople.persistence.neo4j.Neo4jTransaction;

@Model
@Neo4jTransaction
public class UserModel {

	private @Inject	EntityFactory entityFactory;
	private UserDTO user = new UserDTO();

	public void createUser() {
		entityFactory.createUser(user);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"User created", ""));
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<UserDTO> getAllUser() {
		List<UserDTO> users = new ArrayList<>();

		for (final User user : entityFactory.getAllUsers()) {
			users.add(new UserDTO() {
				{
					setId(user.getId());
					setFirstName(user.getFirstName());
					setLastName(user.getLastName());
					setEmail(user.getEmail());
					setBirthDate(user.getBirthDate());
					setActivation(user.getActivation());
				}
			});
		}
		return users;
	}

	public void getUserById() {
		final User userById = entityFactory.getUserById(user.getId());
		user = new UserDTO() {
			{
				setId(userById.getId());
				setBirthDate(userById.getBirthDate());
				setEmail(userById.getEmail());
				setFirstName(userById.getFirstName());
				setLastName(userById.getLastName());
				setActivation(userById.getActivation());
			}
		};
	}

	public void updateUser() {
		synchronizeWithPersistedUser();
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"User updated", ""));
	}

	private void synchronizeWithPersistedUser() {
		User userById = entityFactory.getUserById(user.getId());
		userById.setFirstName(user.getFirstName());
		userById.setLastName(user.getLastName());
		userById.setPassword(user.getPassword());
		userById.setBirthDate(user.getBirthDate());
		userById.setActivation(user.getActivation());
	}
}
