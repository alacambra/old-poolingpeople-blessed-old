package poolingpeople.webapplication.business.task.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.neo4j.graphdb.Node;

import poolingpeople.commons.entities.Effort;
import poolingpeople.persistence.neo4j.PoolingpeopleObjectType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EffortMixin implements Effort{

	@Override
	public String getId() {
		return null;
	}

	@Override
	public Long getDate() {
		return null;
	}

	@Override
	public void setDate(Long date) {
		
	}

	@Override
	public String getComment() {
		return null;
	}

	@Override
	public void setComment(String comment) {
		
	}

	@Override
	public Integer getTime() {
		return null;
	}

	@Override
	public void setTime(Integer time) {
		
	}
	
	@JsonIgnore
	public Node getNode() {
		return null;
	}
	
	@JsonIgnore
	public PoolingpeopleObjectType getNodeType() {
		return null;
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





































