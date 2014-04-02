package poolingpeople.persistence.neo4j.container;

import org.neo4j.graphdb.Label;

import poolingpeople.persistence.neo4j.NodePropertyName;
import poolingpeople.persistence.neo4j.PoolingpeopleObjectType;

/**
 * use {@link Label}s instead
 * @see org.neo4j.graphdb.Label
 * @author alacambra
 *
 */
@Deprecated
public class TypeIndexContainer extends IndexContainer{
	public TypeIndexContainer(PoolingpeopleObjectType type) {
		super("ALL", NodePropertyName.TYPE.name(), type.name());
	}
}





























