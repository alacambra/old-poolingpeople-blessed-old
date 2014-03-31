package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:16 PM by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import poolingpeople.exporter.HasId;


/**
 * TimeEntries generated by hbm2java
 */
@Entity
@Table(name = "time_entries", catalog = "redmine")
public class TimeEntries implements java.io.Serializable, HasId {

	private Integer id;
	private int projectId;
	private int userId;
	private Integer issueId;
	private float hours;
	private String comments;
	private int activityId;
	private Date spentOn;
	private int tyear;
	private int tmonth;
	private int tweek;
	private Date createdOn;
	private Date updatedOn;
	private Integer rateId;

	public TimeEntries() {
	}

	public TimeEntries(int projectId, int userId, float hours, int activityId,
			Date spentOn, int tyear, int tmonth, int tweek, Date createdOn,
			Date updatedOn) {
		this.projectId = projectId;
		this.userId = userId;
		this.hours = hours;
		this.activityId = activityId;
		this.spentOn = spentOn;
		this.tyear = tyear;
		this.tmonth = tmonth;
		this.tweek = tweek;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	public TimeEntries(int projectId, int userId, Integer issueId, float hours,
			String comments, int activityId, Date spentOn, int tyear,
			int tmonth, int tweek, Date createdOn, Date updatedOn,
			Integer rateId) {
		this.projectId = projectId;
		this.userId = userId;
		this.issueId = issueId;
		this.hours = hours;
		this.comments = comments;
		this.activityId = activityId;
		this.spentOn = spentOn;
		this.tyear = tyear;
		this.tmonth = tmonth;
		this.tweek = tweek;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.rateId = rateId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "project_id", nullable = false)
	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Column(name = "user_id", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "issue_id")
	public Integer getIssueId() {
		return this.issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	@Column(name = "hours", nullable = false, precision = 12, scale = 0)
	public float getHours() {
		return this.hours;
	}

	public void setHours(float hours) {
		this.hours = hours;
	}

	@Column(name = "comments")
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "activity_id", nullable = false)
	public int getActivityId() {
		return this.activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "spent_on", nullable = false, length = 10)
	public Date getSpentOn() {
		return this.spentOn;
	}

	public void setSpentOn(Date spentOn) {
		this.spentOn = spentOn;
	}

	@Column(name = "tyear", nullable = false)
	public int getTyear() {
		return this.tyear;
	}

	public void setTyear(int tyear) {
		this.tyear = tyear;
	}

	@Column(name = "tmonth", nullable = false)
	public int getTmonth() {
		return this.tmonth;
	}

	public void setTmonth(int tmonth) {
		this.tmonth = tmonth;
	}

	@Column(name = "tweek", nullable = false)
	public int getTweek() {
		return this.tweek;
	}

	public void setTweek(int tweek) {
		this.tweek = tweek;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", nullable = false, length = 19)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on", nullable = false, length = 19)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Column(name = "rate_id")
	public Integer getRateId() {
		return this.rateId;
	}

	public void setRateId(Integer rateId) {
		this.rateId = rateId;
	}

}
