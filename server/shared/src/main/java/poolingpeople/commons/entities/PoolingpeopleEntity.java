package poolingpeople.commons.entities;

import java.util.List;

public interface PoolingpeopleEntity extends Synchronizable{
	String getId();
	List<ChangeLog> getChangeLogList();
	List<Comment> getObjectComments();
	void addComment(Comment comment);
}
