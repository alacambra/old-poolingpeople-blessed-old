package poolingpeople.webapplication.business.boundary;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.core.Context;

import org.codehaus.jackson.map.ObjectMapper;

import poolingpeople.commons.entities.EntityFactory;
import poolingpeople.commons.helper.Pager;

public class AbstractBoundary {

	@Inject 
	protected ILoggedUserContainer loggedUserContainer;
	
	@Inject
	protected ObjectMapper mapper;

	@Inject
	protected EntityFactory entityFactory;
	
	@Inject
	protected Pager pager;
	
	@Inject
	protected Event<UpdateTask> updateTaskEvent;
	
	protected static final String uuidRegexPattern = "[\\w\\d-]+";
	protected static final String idPattern = "{id:" + uuidRegexPattern + "}";
}
