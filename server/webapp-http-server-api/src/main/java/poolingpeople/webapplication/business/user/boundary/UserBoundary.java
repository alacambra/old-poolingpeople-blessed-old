package poolingpeople.webapplication.business.user.boundary;

import java.io.IOException;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import poolingpeople.commons.entities.EntityFactory;
import poolingpeople.commons.entities.User;
import poolingpeople.persistence.neo4j.Neo4jTransaction;
import poolingpeople.webapplication.business.boundary.AuthNotRequired;
import poolingpeople.webapplication.business.boundary.AuthValidator;
import poolingpeople.webapplication.business.boundary.CatchWebAppException;
import poolingpeople.webapplication.business.boundary.IdWrapper;

@Path("users")
@Stateless
@Neo4jTransaction
@CatchWebAppException
@AuthValidator
public class UserBoundary {

	Logger logger = Logger.getLogger(this.getClass());

	@Inject
	ObjectMapper mapper;

	@Inject
	EntityFactory entityFactory;

	@Inject
	Validator validator;

	@GET
	@Path("{id:[\\w\\d-]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserById(@PathParam("id") String id)
			throws JsonGenerationException, JsonMappingException, IOException {
		
		String r = mapper.writeValueAsString(entityFactory.getUserById(id));
		return Response.ok().entity(r).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() throws JsonGenerationException,
	JsonMappingException, IOException {
		String r = mapper.writeValueAsString(entityFactory.getAllUsers());
		return Response.ok().entity(r).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@AuthNotRequired
	public Response saveUser(String json) throws JsonParseException,
	JsonMappingException, IOException {

		User dtoUser = deserializeAndValidate(json, UserDTO.class);
		User user = entityFactory.createUser(dtoUser);
		return Response.ok().entity(mapper.writeValueAsString(new IdWrapper(user.getId()))).build();
	}

	@PUT
	@Path("{id:[\\w\\d-]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") String uuid, String json)
			throws JsonParseException, JsonMappingException, IOException {

		User dtoUser = deserializeAndValidate(json, UserDTO.class);
		User user = entityFactory.getUserById(uuid);
		user.synchronizeWith(dtoUser);
		String r = mapper.writeValueAsString(user);
		return Response.ok().entity(r).build();
	}

	@DELETE
	@Path("{id:[\\w\\d-]+}")
	public Response deleteUser(@PathParam("id") String uuid) {
		entityFactory.deleteUser(uuid);
		return Response.noContent().build();
	}

//	@GET
//	@Path("fakeit")
//	@Produces(MediaType.APPLICATION_JSON)
//	@AuthNotRequired
//	public Response fakeUser() throws JsonGenerationException, JsonMappingException, IOException {
//
//		User persistedUser = entityFactory.createUser("al@al.com" + new Date().getTime(), "a", new PersistedUser(new UserDTO()));
//
////		persistedUser.setEmail("al@al.com" + new Date().getTime());
//		persistedUser.setFirstName("al");
//		persistedUser.setLastName("ipsum");
//
//		String r = mapper.writeValueAsString(persistedUser);
//		return Response.ok().entity(r).build();
//	}

	private  <T>  T deserializeAndValidate(String json, Class<T> clazz) 
			throws JsonParseException, JsonMappingException, IOException {

		T bean = mapper.readValue(json, clazz);

		StringBuilder sb = new StringBuilder();
		Set<ConstraintViolation<T>> violations = validator.validate(bean);

		if (violations.size() > 0) {
			for (ConstraintViolation<T> constraintViolation : violations) {
				System.out.println(constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage());
				sb.append(constraintViolation.getMessage());
			}
			logger.info("{\"Validation failed:" + sb.toString() + "\"");
			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST).entity("{\"Validation failed:" + sb.toString() + "\"").build());
		}

		return bean;
	}
}




















































