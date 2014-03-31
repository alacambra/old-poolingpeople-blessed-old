package poolingpeople.webapplication.business.utils.logging.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Alternative;

@Alternative
public class SimpleLogger implements PPLogger {
	private Logger logger;

	public SimpleLogger() {
	}

	public SimpleLogger(String name) {
		logger = Logger.getLogger(name);
	}

	@Override
	public void log(Level logLevel, String message, Object[] args) {
		logger.log(logLevel, message, args);
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

}
