package poolingpeople.persistence.neo4j.container;

public class IndexContainer {
	private String type;
	private String key;
	private Object value;

	public IndexContainer(String type, String key, Object value) {
		super();
		this.type = type;
		this.key = key;
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return type + ": " + key + ", " + value;
	}
}





























