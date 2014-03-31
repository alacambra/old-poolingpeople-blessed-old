package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:16 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MemberRoles generated by hbm2java
 */
@Entity
@Table(name = "member_roles", catalog = "redmine")
public class MemberRoles implements java.io.Serializable {

	private Integer id;
	private int memberId;
	private int roleId;
	private Integer inheritedFrom;

	public MemberRoles() {
	}

	public MemberRoles(int memberId, int roleId) {
		this.memberId = memberId;
		this.roleId = roleId;
	}

	public MemberRoles(int memberId, int roleId, Integer inheritedFrom) {
		this.memberId = memberId;
		this.roleId = roleId;
		this.inheritedFrom = inheritedFrom;
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

	@Column(name = "member_id", nullable = false)
	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Column(name = "role_id", nullable = false)
	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Column(name = "inherited_from")
	public Integer getInheritedFrom() {
		return this.inheritedFrom;
	}

	public void setInheritedFrom(Integer inheritedFrom) {
		this.inheritedFrom = inheritedFrom;
	}

}
