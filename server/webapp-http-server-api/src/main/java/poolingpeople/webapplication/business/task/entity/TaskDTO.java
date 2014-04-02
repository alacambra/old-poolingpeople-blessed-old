package poolingpeople.webapplication.business.task.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.Comment;
import poolingpeople.commons.entities.Effort;
import poolingpeople.commons.entities.IgnoreAttribute;
import poolingpeople.commons.entities.Project;
import poolingpeople.commons.entities.Task;
import poolingpeople.commons.entities.TaskPriority;
import poolingpeople.commons.entities.TaskStatus;
import poolingpeople.commons.entities.User;
import poolingpeople.commons.exceptions.RootApplicationException;

public class TaskDTO implements Task {

	private String id;
	private String title;
	private String description;
	private Long startDate;
	private Long endDate;
	private Float progress;
	private Integer duration;
	private TaskStatus taskStatus;
	private TaskPriority taskPriority;
	private Integer effort;
	private Project project;
	private User assignee;
	private Task parent;
	private List<Task> subTasks = new ArrayList<>();

	@IgnoreAttribute
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public TaskPriority getPriority() {
		return taskPriority;
	}

	@Override
	public TaskStatus getStatus() {
		return taskStatus;
	}

	@Override
	public Long getStartDate() {
		return startDate;
	}
	
	public Long getDefaultStartDate() {
		return startDate;
	}

	@Override
	public Long getEndDate() {
		return endDate;
	}
	
	public Long getDefaultEndDate() {
		return endDate;
	}

	@Override
	public Float getProgress() {
		return progress;
	}

	public Float getDefaultProgress() {
		return progress;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setPriority(TaskPriority priority) {
		this.taskPriority = priority;
	}

	@Override
	public void setStatus(TaskStatus status) {
		this.taskStatus = status;
	}

	@Override
	public Integer getDuration() {
		return duration;
	}
	
	public Integer getDefaultDuration() {
		return duration;
	}

	@Override
	public void setDefaultDuration(Integer duration) {
		this.duration = duration;
	}

	@Override
	@JsonIgnore
	public void addEffort(Effort effort) {
		throw new RootApplicationException("Inaccessible method");
		
	}

	@Override
	@JsonIgnore
	public Collection<Effort> getEfforts() {
		throw new RootApplicationException("Inaccessible method");
	}

	@Override
	public void deleteEffort(Effort effort) {
		throw new RootApplicationException("Inaccessible method");		
	}

	@Override
	public Integer getEffort() {
		return effort;
	}

	@Override
	public Project getProject() {
		return project;
	}

	@Override
	public void setDefaultStartDate(Long startDate) {
		this.startDate = startDate;
		
	}

	@Override
	public void setDefaultEndDate(Long endDate) {
		this.endDate = endDate;
		
	}

	@Override
	public void setDefaultProgress(Float progress) {
		this.progress = progress;
		
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
		subTasks.add(child);
	}

	@Override
	public void removeSubtask(Task child) {
		subTasks.remove(child);
	}

	@Override
	public void removeTaskRelation(Task child) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAssignee(User u) {
		assignee = u;
	}

	@Override
	public Task getParent() {
		return parent;
	}

	@Override
	public User getAssignee() {
		return assignee;
	}

	@Override
	public String getParentId() {
		return parent.getId();
	}

	@Override
	public List<Task> getSubtasks() {
		return subTasks; 
	}

	@Override
	public Integer getSubtaskCount() {
		return subTasks.size();
	}

	@Override
	public void synchronizeWith(Object tplObject) {
		// TODO Auto-generated method stub
		
	}

	@JsonIgnore
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
