package poolingpeople.persistence.neo4j.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import poolingpeople.commons.exceptions.RootApplicationException;

//import poolingpeople.webapplication.business.utils.configuration.boundary.Configurable;

public class NotUniqueException extends RootApplicationException {

//    @Inject
//    @Configurable("doesNotExist")
    private String doesNotExist;

    public NotUniqueException() {
		super();
	}

	public NotUniqueException(String message) {
		super(message);
	}
	
	public NotUniqueException(String message, Throwable e) {
		super(message, e);
	}

	/**
     *
     */
    private static final long serialVersionUID = -293381679679028746L;

    @Override
    public Response getSpecificWebResponse() {
        return Response.status(Status.BAD_REQUEST).entity(doesNotExist).build();
    }

}
