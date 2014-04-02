package poolingpeople.commons.entities;

import java.util.Collection;
import java.util.List;

public interface Task extends PoolingpeopleEntity{
	
	/**
	 * UUID of a node. 
	 * @return
	 */
	String getId();

	String getTitle();

	void setTitle(String title);

	String getDescription();

	void setDescription(String description);

	/*
	 * @todo: Intercept nullables and return a defaulted injected value... if possible 
	 */
	TaskPriority getPriority();
	
	void setPriority(TaskPriority priority);

	TaskStatus getStatus();
	
	void setStatus(TaskStatus status);

	Long getStartDate();

	Long getEndDate();

	Float getProgress();

	/**
	 * Estimated duration of the task
	 * @return
	 */
	Integer getDuration();
	void setDefaultDuration(Integer progress); 
	
	/**
	 * Number of ours used to develop some aspect of the task
	 * @param effort
	 */
	void addEffort(Effort effort);
	Collection<Effort> getEfforts();

	void deleteEffort(Effort effort);
	
	Integer getEffort();
	
	Project getProject();

	void setDefaultStartDate(Long startDate);

	void setDefaultEndDate(Long endDate);

	void setDefaultProgress(Float progress);

	void updateProgress();

	void updateDates();

	void updateAll();

	void updateDuration();

	void updateEfforts();

	void addSubtask(Task child);

	void removeSubtask(Task child);

	void removeTaskRelation(Task child);

	void setAssignee(User u);

	Task getParent();
	String getParentId();

	User getAssignee();

	List<Task> getSubtasks();
	
	Integer getSubtaskCount();
	
}

















