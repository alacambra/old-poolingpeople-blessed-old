package poolingpeople.commons.exceptions;

import javax.ws.rs.core.Response;

public class RootApplicationException extends RuntimeException implements DomainExceptionHTTPResponse {

    private static final long serialVersionUID = -7160582417322246076L;

    public RootApplicationException() {
        super();
    }

    public RootApplicationException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RootApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RootApplicationException(String message) {
        super(message);
    }

    public RootApplicationException(Throwable cause) {
        super(cause);
    }

    /**
     * Default HTTP response if a RootApplicationException is thrown
     * @return HTTP 500 if not a specific domain exception provides a response HTTP code
     */
    @Override
    public Response getSpecificWebResponse() {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
