package poolingpeople.webapplication.business.service.boundary;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.neo4j.graphdb.Node;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.Comment;
import poolingpeople.commons.entities.Service;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceMixin implements Service {

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@JsonIgnore
	public Node getNode() {
		return null;
	}

	@JsonIgnore
	@Override
	public List<ChangeLog> getChangeLogList() {
		// TODO Auto-generated method stub
		return null;
	}

	@JsonIgnore
	@Override
	public List<Comment> getObjectComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void synchronizeWith(Object tplObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActiveStatus(boolean activeStatus) {
		// TODO Auto-generated method stub
		
	}

	@JsonIgnore
	@Override
	public boolean getActiveStatus() {
		// TODO Auto-generated method stub
		return false;
	}

}
