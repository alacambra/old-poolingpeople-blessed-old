package poolingpeople.persistence.neo4j.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import poolingpeople.commons.exceptions.RootApplicationException;

public class NodeExistsException extends RootApplicationException {

//    @Inject
//    @Configurable("doesNotExist")
    private String doesNotExist;

    /**
     *
     */
    private static final long serialVersionUID = -2956832836211447594L;

    public NodeExistsException() {
        super();
    }

    public NodeExistsException(String text) {
        super(text);
    }

    @Override
    public Response getSpecificWebResponse() {
        return Response.status(Status.NOT_FOUND).entity(doesNotExist).build();
    }

}
