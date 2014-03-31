package poolingpeople.exporter;

import java.util.Collection;
import java.util.List;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.Comment;
import poolingpeople.commons.entities.Project;
import poolingpeople.commons.entities.ProjectStatus;
import poolingpeople.commons.entities.Task;
import poolingpeople.commons.entities.User;

public class FakedProject implements Project{

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
	public ProjectStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatus(ProjectStatus status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultStartDate(Long startDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultEndDate(Long endDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTask(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Task> getTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTask(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getEffort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEffort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProgress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Float getProgress() {
		// TODO Auto-generated method stub
		return null;
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
	public void setDefaultProgress(Float progress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getTaskCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeTaskRelation(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOwner(User owner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultEffort(Integer effort) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getDuration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultDuration(Integer duration) {
		// TODO Auto-generated method stub
		
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

}
