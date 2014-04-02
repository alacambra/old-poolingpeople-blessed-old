package poolingpeople.webapplication.business.boundary.helpers;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;

import poolingpeople.persistence.neo4j.Neo4jTransaction;
import poolingpeople.persistence.neo4j.NeoManager;
import poolingpeople.persistence.neo4j.entities.PersistedProject;
import poolingpeople.persistence.neo4j.inheritance.DummyAsyncBean;


@Path("help")
@Stateless
@Neo4jTransaction
public class RestBoundaryActionsHelper {

	Logger logger = Logger.getLogger("RestBoundryActionsHelper");

	@Inject DummyAsyncBean asyncBean;

	@Inject NeoManager manager;

	@DELETE
	@Path("projects")
	public Response deleteAllProjects() {
		for (Node n : manager.getNodes(PersistedProject.NODE_TYPE.name())){
			n.delete();
		}
		return Response.noContent().build();
	}

	@GET
	@Path("async")
	@Produces(MediaType.TEXT_PLAIN)
	public Response async() throws InterruptedException {
		asyncBean.asyncMethod();
		return Response.ok("a").build();
	}

}
