package poolingpeople.webapplication.business.task.boundary;

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

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import poolingpeople.commons.entities.Effort;
import poolingpeople.commons.entities.EntityFactory;
import poolingpeople.commons.entities.Task;
import poolingpeople.persistence.neo4j.Neo4jTransaction;
import poolingpeople.webapplication.business.boundary.CatchWebAppException;
import poolingpeople.webapplication.business.boundary.IdWrapper;
import poolingpeople.webapplication.business.task.entity.EffortDto;
import poolingpeople.webapplication.business.task.entity.EffortMixin;

@Path("tasks/{taskId:[\\w\\d-]+}/efforts/")
@Stateless
@Neo4jTransaction
@CatchWebAppException
public class EffortBoundary {

	@Inject
	ObjectMapper mapper;

	@Inject
	EntityFactory entityFactory;

	@GET
	@Path("{id:[\\w\\d-]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEffort(@PathParam("id") String uuid) 
			throws JsonGenerationException, JsonMappingException, IOException{
		String r = mapper.writerWithView(EffortMixin.class).writeValueAsString(entityFactory.getEffortById(uuid));
		return Response.ok().entity(r).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEfforts(@PathParam("taskId") String taskId) throws JsonGenerationException, JsonMappingException, IOException {

		Task task = entityFactory.getTaskById(taskId);
		String r = mapper.writeValueAsString(task.getEfforts());
		return Response.ok().entity(r).build();
	}

	@PUT
	@Path("{id:[\\w\\d-]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) 
	public Response updateEffort(@PathParam("taskId") String taskId, @PathParam("id") String uuid, String json)
			throws JsonParseException, JsonMappingException, IOException{

		Effort dtoEffort = mapper.readValue(json, EffortDto.class);
		Effort effort =  entityFactory.getEffortById(uuid);
		effort.synchronizeWith(dtoEffort);
		String r = mapper.writeValueAsString(effort);
		return Response.noContent().build();

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveEffort(@PathParam("taskId") String taskId, String json) 
			throws JsonParseException, JsonMappingException, IOException {

		Effort dtoEffort = mapper.readValue(json, EffortDto.class);
		Task task = entityFactory.getTaskById(taskId);
		Effort effort = entityFactory.createEffort(dtoEffort);
		task.addEffort(effort);

		return Response.ok().entity(mapper.writeValueAsString(new IdWrapper(effort.getId()))).build();
	}
	
	@DELETE
	@Path("{id:[\\w\\d-]+}")
	public Response deleteEffort(@PathParam("taskId") String taskId, @PathParam("id") String uuid) {
		
		Task task = entityFactory.getTaskById(taskId);
		task.deleteEffort(entityFactory.getEffortById(uuid));
		
		return Response.noContent().build();
	}

}










































































