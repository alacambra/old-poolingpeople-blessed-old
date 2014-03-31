package poolingpeople.webapplication.business.utils.logging.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PP Logger is effectively a simple delegating object to an real Logger instance created by LoggerProducer
 * @see LoggerProducer
 */
public interface PPLogger {
	public void log(Level logLevel, String message, Object[] args);
	public Logger getLogger();
}
