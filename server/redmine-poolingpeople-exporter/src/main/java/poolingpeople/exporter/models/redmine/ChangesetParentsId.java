package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:16 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ChangesetParentsId generated by hbm2java
 */
@Embeddable
public class ChangesetParentsId implements java.io.Serializable {

	private int changesetId;
	private int parentId;

	public ChangesetParentsId() {
	}

	public ChangesetParentsId(int changesetId, int parentId) {
		this.changesetId = changesetId;
		this.parentId = parentId;
	}

	@Column(name = "changeset_id", nullable = false)
	public int getChangesetId() {
		return this.changesetId;
	}

	public void setChangesetId(int changesetId) {
		this.changesetId = changesetId;
	}

	@Column(name = "parent_id", nullable = false)
	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ChangesetParentsId))
			return false;
		ChangesetParentsId castOther = (ChangesetParentsId) other;

		return (this.getChangesetId() == castOther.getChangesetId())
				&& (this.getParentId() == castOther.getParentId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getChangesetId();
		result = 37 * result + this.getParentId();
		return result;
	}

}
