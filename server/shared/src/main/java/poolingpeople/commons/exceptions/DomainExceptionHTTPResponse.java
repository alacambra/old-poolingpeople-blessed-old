
package poolingpeople.commons.exceptions;

import javax.ws.rs.core.Response;

public interface DomainExceptionHTTPResponse {
    Response getSpecificWebResponse();
}
