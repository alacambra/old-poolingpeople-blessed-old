package poolingpeople.webapplication.business.comments.boundary;

import java.util.List;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.Comment;
import poolingpeople.commons.entities.PoolingpeopleEntity;
import poolingpeople.commons.entities.User;

public class CommentsDTO implements Comment {

	private String comment;
	private Long date;

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public Long getDate() {
		return date;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	@Override
	public User getOwner() {
		return null;
	}

	@Override
	public PoolingpeopleEntity getCommentedEntity() {
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChangeLog> getChangeLogList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getObjectComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void synchronizeWith(Object tplObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

}







































