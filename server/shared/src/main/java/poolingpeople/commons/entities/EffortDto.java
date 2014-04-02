package poolingpeople.commons.entities;

import poolingpeople.commons.entities.Effort;
import poolingpeople.commons.entities.IgnoreAttribute;

public class EffortDto implements Effort{
	
	private String id;
	private Long date;
	private String comment;
	private Integer time;
	
	@IgnoreAttribute
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	@Override
	public String getTaskId() {
		return null;
	}
	@Override
	public void synchronizeWith(Object tplObject) {
		// TODO Auto-generated method stub
		
	}
	
	
}
