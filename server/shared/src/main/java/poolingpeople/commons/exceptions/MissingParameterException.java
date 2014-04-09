package poolingpeople.commons.exceptions;

import javax.ws.rs.core.Response;

public class MissingParameterException extends RootApplicationException implements DomainExceptionHTTPResponse {

    private static final long serialVersionUID = -7160582417322246076L;

    public MissingParameterException() {
        super();
    }

    public MissingParameterException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MissingParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingParameterException(String message) {
        super(message);
    }

    public MissingParameterException(Throwable cause) {
        super(cause);
    }

    /**
     * Default HTTP response if a RootApplicationException is thrown
     * @return HTTP 500 if not a specific domain exception provides a response HTTP code
     */
    @Override
    public Response getSpecificWebResponse() {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
