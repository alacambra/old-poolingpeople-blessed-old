package poolingpeople.commons.entities;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

public class ServiceDTO implements Service {

	private String id;
	private List<ChangeLog> changeLogList = new ArrayList<>();
	private List<Comment> commentList = new ArrayList<>();
	private String title;
	private String description;
	private boolean activeStatus;

	@IgnoreAttribute
	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public List<ChangeLog> getChangeLogList() {
		return changeLogList;
	}

	@Override
	public List<Comment> getObjectComments() {
		return commentList;
	}

	@Override
	public void addComment(Comment comment) {
		commentList.add(comment);
	}

	@Override
	public void synchronizeWith(Object tplObject) {
		
	}

	@Override
	public void setTitle(String title) {
		this.title= title;
	}

	@Size(min=3)
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setDescription(String description) {
		this.description=description;
	}

	@Size(min=10)
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus=activeStatus;
	}

	@Override
	public boolean getActiveStatus() {
		return activeStatus;
	}

}
