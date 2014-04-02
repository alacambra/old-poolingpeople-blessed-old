package poolingpeople.persistence.neo4j.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import poolingpeople.commons.exceptions.RootApplicationException;

public class ConsistenceException extends RootApplicationException {

    private static final long serialVersionUID = -2956832836211447594L;

    public ConsistenceException(String text) {
        super(text);
    }

    @Override
    public Response getSpecificWebResponse() {
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getMessage()).build();
    }

}
