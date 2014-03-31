package poolingpeople.webapplication.business.utils.logging.boundary;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import poolingpeople.webapplication.business.utils.configuration.boundary.Configurable;

/**
 * LoggerProduces creates depending on the isLogging flag a WhateverLogger for an injected instance 
 * Also it creates a standard Logger for the LoggerInterceptor
 */
public class LoggerProducer {
	@Inject @Configurable("isLogging")
	private boolean isLogging;
	
	@Produces 
	public PPLogger produceLoggerInstance(InjectionPoint point) {
		String className = point.getMember().getDeclaringClass().getName();
		return (isLogging) ? new SimpleLogger(className) : new NullableLogger(); 
	}
	
	@Produces
	public Logger produceStandardLogger(InjectionPoint point) {
		String className = point.getMember().getDeclaringClass().getName();
		return Logger.getLogger(className);
	}
}
