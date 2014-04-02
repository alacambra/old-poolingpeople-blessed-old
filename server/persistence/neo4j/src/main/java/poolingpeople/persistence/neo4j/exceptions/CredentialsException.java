package poolingpeople.persistence.neo4j.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import poolingpeople.commons.exceptions.RootApplicationException;

public class CredentialsException extends RootApplicationException {

//    @Inject
    private String msg = "Invalid credentials";
    /**
     *
     */
    private static final long serialVersionUID = -4979887816239892239L;

    public CredentialsException(){
    	super();
    }
    
    public CredentialsException(String msg) {
    	super(msg);
    }
    
    @Override
    public Response getSpecificWebResponse() {
        return Response.status(Status.UNAUTHORIZED).entity(msg).build();
    }

}
