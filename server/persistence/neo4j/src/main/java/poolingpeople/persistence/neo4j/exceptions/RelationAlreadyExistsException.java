package poolingpeople.persistence.neo4j.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import poolingpeople.commons.exceptions.RootApplicationException;
//import poolingpeople.webapplication.business.utils.configuration.boundary.Configurable;

public class RelationAlreadyExistsException extends RootApplicationException {

//    @Inject
//    @Configurable("alreadyExists")
    private String text;

    /**
     *
     */
    private static final long serialVersionUID = -293381679679028746L;

    @Override
    public Response getSpecificWebResponse() {
        return Response.status(Status.BAD_REQUEST).entity(text).build();
    }

}
