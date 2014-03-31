package poolingpeople.webapplication.business.comments.boundary;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonView;
import org.neo4j.graphdb.Node;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.Comment;
import poolingpeople.commons.entities.PoolingpeopleEntity;
import poolingpeople.commons.entities.User;
import poolingpeople.webapplication.business.boundary.JsonViews;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentsMixin implements Comment{

	@Override
	@JsonView(JsonViews.Shared.class)
	public String getId() {
		return null;
	}
	

	@JsonIgnore
	public Node getNode() {
		return null;
	}

	@Override
	@JsonIgnore
	public List<ChangeLog> getChangeLogList() {
		return null;
	}

	@Override
	@JsonIgnore
	public List<Comment> getObjectComments() {
		return null;
	}

	@Override
	public void addComment(Comment comment) {
		
	}

	@Override
	public void synchronizeWith(Object tplObject) {
		
	}

	@Override
	@JsonView(JsonViews.Shared.class)
	public String getComment() {
		return null;
	}

	@Override
	@JsonView(JsonViews.Shared.class)
	public Long getDate() {
		return null;
	}

	@Override
	public void setComment(String text) {
		
	}

	@Override
	public void setDate(Long date) {
		
	}

	@Override
	@JsonView(JsonViews.CommentsWithSubject.class)
	public User getOwner() {
		return null;
	}

	@Override
	@JsonView(JsonViews.CommentsWithObject.class)
	public PoolingpeopleEntity getCommentedEntity() {
		return null;
	}


}
