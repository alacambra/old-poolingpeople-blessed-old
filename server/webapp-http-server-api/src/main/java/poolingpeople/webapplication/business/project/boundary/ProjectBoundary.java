package poolingpeople.webapplication.business.project.boundary;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import poolingpeople.commons.entities.EntityFactory;
import poolingpeople.commons.entities.Project;
import poolingpeople.commons.entities.User;
import poolingpeople.persistence.neo4j.Neo4jTransaction;
import poolingpeople.webapplication.business.boundary.AbstractBoundary;
import poolingpeople.webapplication.business.boundary.AuthValidator;
import poolingpeople.webapplication.business.boundary.CatchWebAppException;
import poolingpeople.webapplication.business.boundary.JsonViews;

@Path("projects")
@Stateless
@Neo4jTransaction
@CatchWebAppException
@AuthValidator
public class ProjectBoundary extends AbstractBoundary{

	@GET
	@Path(idPattern)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectById(@PathParam("id") String id)
			throws JsonGenerationException, JsonMappingException, IOException {
		
		String r = mapper.writerWithView(JsonViews.FullProject.class).writeValueAsString(
				entityFactory.getProjectById(id));
		
		return Response.ok().entity(r).build();
	}
	
	@GET
	@Path(idPattern + "/tasks/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectTasks(@PathParam("id") String projectId)
			throws JsonGenerationException, JsonMappingException, IOException {
//		
//		String r = mapper.writerWithView(JsonViews.BasicTask.class).writeValueAsString(
//				entityFactory.getProjectById(projectId).getTasks());
//		
//		return Response.ok().entity(r).build();
		return Response.status(Status.METHOD_NOT_ALLOWED).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProjects() throws JsonGenerationException,
			JsonMappingException, IOException {
		
		String r = mapper.writerWithView(JsonViews.BasicProject.class)
				.writeValueAsString(entityFactory.getAllProject());
		
		return Response.ok().entity(r).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveProject(String json) throws JsonParseException,
			JsonMappingException, IOException {
		
		Project dtoProject = mapper.readValue(json, ProjectDTO.class);
		Project project = entityFactory.createProject(dtoProject);
		
		return Response.ok().entity(mapper.writerWithView(JsonViews.FullProject.class).writeValueAsString(project)).build();
	}

	@PUT
	@Path(idPattern)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProject(@PathParam("id") String uuid, String json)
			throws JsonParseException, JsonMappingException, IOException {
		
		Project dtoProject = mapper.readValue(json, ProjectDTO.class);
		Project persistedProject = entityFactory.getProjectById(uuid);
		persistedProject.synchronizeWith(dtoProject);
		String serializedProject = mapper.writerWithView(JsonViews.FullProject.class).writeValueAsString(persistedProject);
		return Response.ok().entity(serializedProject).build();
	}

	@DELETE
	@Path(idPattern)
	public Response deleteProject(@PathParam("id") String uuid) {
		entityFactory.deleteProject(uuid);
		return Response.noContent().build();
	}
	
	/************************************* USER - TASK *************************************/
	@PUT 
	@Path(idPattern + "/to/user/{userId:" + uuidRegexPattern + "}")
	public Response assignProjectToUser(@PathParam("id") String projectId, @PathParam("userId") String userId){

		User user = entityFactory.getUserById(userId);
		Project project = entityFactory.getProjectById(projectId);
		project.setOwner(user);
		
		return Response.noContent().build();
	}
}


































































