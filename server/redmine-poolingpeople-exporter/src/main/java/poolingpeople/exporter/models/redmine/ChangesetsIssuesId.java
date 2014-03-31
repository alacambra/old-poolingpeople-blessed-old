package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:16 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ChangesetsIssuesId generated by hbm2java
 */
@Embeddable
public class ChangesetsIssuesId implements java.io.Serializable {

	private int changesetId;
	private int issueId;

	public ChangesetsIssuesId() {
	}

	public ChangesetsIssuesId(int changesetId, int issueId) {
		this.changesetId = changesetId;
		this.issueId = issueId;
	}

	@Column(name = "changeset_id", nullable = false)
	public int getChangesetId() {
		return this.changesetId;
	}

	public void setChangesetId(int changesetId) {
		this.changesetId = changesetId;
	}

	@Column(name = "issue_id", nullable = false)
	public int getIssueId() {
		return this.issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ChangesetsIssuesId))
			return false;
		ChangesetsIssuesId castOther = (ChangesetsIssuesId) other;

		return (this.getChangesetId() == castOther.getChangesetId())
				&& (this.getIssueId() == castOther.getIssueId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getChangesetId();
		result = 37 * result + this.getIssueId();
		return result;
	}

}
