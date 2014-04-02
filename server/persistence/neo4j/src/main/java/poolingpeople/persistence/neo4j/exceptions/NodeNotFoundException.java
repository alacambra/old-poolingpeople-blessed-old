package poolingpeople.persistence.neo4j.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import poolingpeople.commons.exceptions.RootApplicationException;
//import poolingpeople.webapplication.business.utils.configuration.boundary.Configurable;

public class NodeNotFoundException extends RootApplicationException {

//    @Inject
//    @Configurable("doesNotExist")
    private String doesNotExistMsg;
    /**
     *
     */
    private static final long serialVersionUID = -4979887816239892239L;

    public NodeNotFoundException(){
    	super();
    }
    
    public NodeNotFoundException(String msg) {
    	super(msg);
    }
    
    @Override
    public Response getSpecificWebResponse() {
        return Response.status(Status.NOT_FOUND).entity(doesNotExistMsg).build();
    }

}
