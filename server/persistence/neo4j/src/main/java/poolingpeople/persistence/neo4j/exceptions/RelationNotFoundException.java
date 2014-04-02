package poolingpeople.persistence.neo4j.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
//import poolingpeople.webapplication.business.utils.configuration.boundary.Configurable;

import poolingpeople.commons.exceptions.RootApplicationException;

public class RelationNotFoundException extends RootApplicationException {

//    @Inject
//    @Configurable("relationNotFound")
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
