package poolingpeople.webapplication.business.service.boundary;

import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import poolingpeople.commons.entities.Service;
import poolingpeople.persistence.neo4j.Neo4jTransaction;
import poolingpeople.webapplication.business.boundary.AbstractBoundary;
import poolingpeople.webapplication.business.boundary.AuthValidator;
import poolingpeople.webapplication.business.boundary.CatchWebAppException;

@Path("services")
@Stateless
@Neo4jTransaction
@CatchWebAppException
@AuthValidator
public class ServiceBoundary extends AbstractBoundary{

	@GET
	@Path("/of/task/" + idPattern)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getServiceOfTask(@PathParam("id") String taskId)
			throws JsonGenerationException, JsonMappingException, IOException {
		List<Service> servicesOfTask = entityFactory.getTaskById(taskId).getServiceList();
		String response = mapper.writeValueAsString(servicesOfTask);
		return Response.ok().entity(response).build();
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllServices()
			throws JsonGenerationException, JsonMappingException, IOException {
		List<Service> services = entityFactory.getAllServices(); 
		String response = mapper.writeValueAsString(services);
		return Response.ok().entity(response).build();
		
	}
	
}

