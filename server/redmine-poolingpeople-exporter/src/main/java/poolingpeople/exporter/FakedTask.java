package poolingpeople.exporter;

import java.util.Collection;
import java.util.List;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.Comment;
import poolingpeople.commons.entities.Effort;
import poolingpeople.commons.entities.Project;
import poolingpeople.commons.entities.Task;
import poolingpeople.commons.entities.TaskPriority;
import poolingpeople.commons.entities.TaskStatus;
import poolingpeople.commons.entities.User;

public class FakedTask implements Task {

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub

	}

	@Override
	public TaskPriority getPriority() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPriority(TaskPriority priority) {
		// TODO Auto-generated method stub

	}

	@Override
	public TaskStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatus(TaskStatus status) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long getStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float getProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getDuration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultDuration(Integer progress) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEffort(Effort effort) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Effort> getEfforts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEffort(Effort effort) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getEffort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project getProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultStartDate(Long startDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDefaultEndDate(Long endDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDefaultProgress(Float progress) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProgress() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDates() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDuration() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEfforts() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSubtask(Task child) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeSubtask(Task child) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTaskRelation(Task child) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAssignee(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public Task getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParentId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getAssignee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getSubtasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getSubtaskCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void synchronizeWith(Object tplObject) {
		// TODO Auto-generated method stub
		
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
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

}
