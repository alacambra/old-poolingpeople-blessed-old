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
import javax.persistence.UniqueConstraint;

/**
 * Members generated by hbm2java
 */
@Entity
@Table(name = "members", catalog = "redmine", uniqueConstraints = @UniqueConstraint(columnNames = {
		"user_id", "project_id" }))
public class Members implements java.io.Serializable {

	private Integer id;
	private int userId;
	private int projectId;
	private Date createdOn;
	private boolean mailNotification;

	public Members() {
	}

	public Members(int userId, int projectId, boolean mailNotification) {
		this.userId = userId;
		this.projectId = projectId;
		this.mailNotification = mailNotification;
	}

	public Members(int userId, int projectId, Date createdOn,
			boolean mailNotification) {
		this.userId = userId;
		this.projectId = projectId;
		this.createdOn = createdOn;
		this.mailNotification = mailNotification;
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

	@Column(name = "user_id", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "project_id", nullable = false)
	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", length = 19)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "mail_notification", nullable = false)
	public boolean isMailNotification() {
		return this.mailNotification;
	}

	public void setMailNotification(boolean mailNotification) {
		this.mailNotification = mailNotification;
	}

}
