package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:16 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Enumerations generated by hbm2java
 */
@Entity
@Table(name = "enumerations", catalog = "redmine")
public class Enumerations implements java.io.Serializable {

	private Integer id;
	private String name;
	private Integer position;
	private boolean isDefault;
	private String type;
	private boolean active;
	private Integer projectId;
	private Integer parentId;
	private String positionName;

	public Enumerations() {
	}

	public Enumerations(String name, boolean isDefault, boolean active) {
		this.name = name;
		this.isDefault = isDefault;
		this.active = active;
	}

	public Enumerations(String name, Integer position, boolean isDefault,
			String type, boolean active, Integer projectId, Integer parentId,
			String positionName) {
		this.name = name;
		this.position = position;
		this.isDefault = isDefault;
		this.type = type;
		this.active = active;
		this.projectId = projectId;
		this.parentId = parentId;
		this.positionName = positionName;
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

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "position")
	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Column(name = "is_default", nullable = false)
	public boolean isIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "project_id")
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "position_name", length = 30)
	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
