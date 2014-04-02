package poolingpeople.webapplication.business.changelog.boundary;

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

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.persistence.neo4j.Neo4jTransaction;
import poolingpeople.webapplication.business.boundary.AbstractBoundary;
import poolingpeople.webapplication.business.boundary.AuthValidator;
import poolingpeople.webapplication.business.boundary.CatchWebAppException;
import poolingpeople.webapplication.business.boundary.JsonViews;

@Path("changelog")
@Stateless
@Neo4jTransaction
@CatchWebAppException
@AuthValidator
public class ChangelogBoundary extends AbstractBoundary{

	@GET
	@Path("/of/object/" + idPattern)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getChangelogOfObject(@PathParam("id") String id)
			throws JsonGenerationException, JsonMappingException, IOException {
		List<ChangeLog> changelogOfObject = entityFactory.getChangelogOfObject(id);
		String response = mapper.writerWithView(JsonViews.BasicChangelog.class).writeValueAsString(changelogOfObject);
		return Response.ok().entity(response).build();
		
	}
	
}


































































