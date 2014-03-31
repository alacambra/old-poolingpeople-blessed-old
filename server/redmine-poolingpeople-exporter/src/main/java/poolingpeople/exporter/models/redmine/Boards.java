package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:16 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Boards generated by hbm2java
 */
@Entity
@Table(name = "boards", catalog = "redmine")
public class Boards implements java.io.Serializable {

	private Integer id;
	private int projectId;
	private String name;
	private String description;
	private Integer position;
	private int topicsCount;
	private int messagesCount;
	private Integer lastMessageId;
	private Integer parentId;

	public Boards() {
	}

	public Boards(int projectId, String name, int topicsCount, int messagesCount) {
		this.projectId = projectId;
		this.name = name;
		this.topicsCount = topicsCount;
		this.messagesCount = messagesCount;
	}

	public Boards(int projectId, String name, String description,
			Integer position, int topicsCount, int messagesCount,
			Integer lastMessageId, Integer parentId) {
		this.projectId = projectId;
		this.name = name;
		this.description = description;
		this.position = position;
		this.topicsCount = topicsCount;
		this.messagesCount = messagesCount;
		this.lastMessageId = lastMessageId;
		this.parentId = parentId;
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

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "position")
	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Column(name = "topics_count", nullable = false)
	public int getTopicsCount() {
		return this.topicsCount;
	}

	public void setTopicsCount(int topicsCount) {
		this.topicsCount = topicsCount;
	}

	@Column(name = "messages_count", nullable = false)
	public int getMessagesCount() {
		return this.messagesCount;
	}

	public void setMessagesCount(int messagesCount) {
		this.messagesCount = messagesCount;
	}

	@Column(name = "last_message_id")
	public Integer getLastMessageId() {
		return this.lastMessageId;
	}

	public void setLastMessageId(Integer lastMessageId) {
		this.lastMessageId = lastMessageId;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}
