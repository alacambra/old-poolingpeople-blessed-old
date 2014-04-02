package poolingpeople.persistence.neo4j.entities;

import java.lang.reflect.Method;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.NotFoundException;
import org.neo4j.graphdb.index.IndexHits;

import poolingpeople.commons.entities.Comment;
import poolingpeople.commons.entities.IgnoreAttribute;
import poolingpeople.commons.exceptions.RootApplicationException;
import poolingpeople.persistence.neo4j.InstanceProvider;
import poolingpeople.persistence.neo4j.Neo4jProfiler;
import poolingpeople.persistence.neo4j.NeoManager;
import poolingpeople.persistence.neo4j.NodePropertyName;
import poolingpeople.persistence.neo4j.PoolingpeopleObjectType;
import poolingpeople.persistence.neo4j.Relations;
import poolingpeople.persistence.neo4j.UpdateQueue;
import poolingpeople.persistence.neo4j.container.IndexContainer;
import poolingpeople.persistence.neo4j.container.UUIDIndexContainer;
import poolingpeople.persistence.neo4j.exceptions.ConsistenceException;
import poolingpeople.persistence.neo4j.exceptions.NodeNotFoundException;
import poolingpeople.persistence.neo4j.exceptions.NotUniqueException;
import poolingpeople.persistence.neo4j.exceptions.RelationAlreadyExistsException;

@Neo4jProfiler
public abstract class AbstractPersistedModel<T extends AbstractPersistedModel<T>> {

	protected Node underlyingNode;

	@Inject 
	protected NeoManager manager;

	@Inject
	private PersistedClassResolver persistedClassResolver;
	
	@Inject
	private InstanceProvider instanceProvider;
	
	@Inject
	UpdateQueue updateQueue;

	protected Logger logger = Logger.getLogger(this.getClass());
	protected boolean isCreated = true;
	protected Set<IndexContainer> indexContainers;

	private PoolingpeopleObjectType NODE_TYPE;

	public AbstractPersistedModel() {
	}

	public T loadExistingNodeById(String id, PoolingpeopleObjectType objectType) {
		underlyingNode = manager.getUniqueNode(objectType.name(), NodePropertyName.ID.name(), id);
		NODE_TYPE = objectType;
		
		validateNodeTypeOrException();

		return (T) this;
	}

	public T createNodeFromDtoModel(PoolingpeopleObjectType objectType, Object dtoModel) {
		isCreated = false;
		HashMap<String, Object> props = new HashMap<String, Object>();
		underlyingNode = manager.createNode(props, new UUIDIndexContainer(UUID.randomUUID().toString()), objectType);
		NODE_TYPE = objectType;
		synchronizeWith(dtoModel);
		initializeVariables();
		isCreated = true;

		return (T) this;
	}

	public T createNodePriorLoadingAttributes(PoolingpeopleObjectType poolingpeopleObjectType) {
		isCreated = false;
		HashMap<String, Object> props = new HashMap<String, Object>();
		underlyingNode = manager.createNode(props, new UUIDIndexContainer(UUID.randomUUID().toString()), poolingpeopleObjectType);
		NODE_TYPE = poolingpeopleObjectType;
		isCreated = true;
		return (T) this;
	}
	
	public T loadModelFromExistentNode(Node node, PoolingpeopleObjectType objectType) {

		NODE_TYPE = objectType;
		underlyingNode = node;
		
		validateNodeTypeOrException();
		
		return (T) this;
	}
	
	public T loadModelFromExistentNode(Node node) {

		underlyingNode = node;
		NODE_TYPE = PoolingpeopleObjectType.valueOf(getStringProperty(NodePropertyName.TYPE));
		
		return (T) this;
	}

	private void validateNodeTypeOrException() {
		if ( !NODE_TYPE.equals(PoolingpeopleObjectType.valueOf(getStringProperty(NodePropertyName.TYPE)))){
			underlyingNode = null;
			NODE_TYPE = null;
			throw new ConsistenceException("The node has not the type " + NODE_TYPE);
		}
	}

	public PoolingpeopleObjectType getNodeType() {
		return NODE_TYPE;
	}

	public Node getNode() {
		return underlyingNode;
	}

	public String getId() {
		return manager.getStringProperty(underlyingNode,
				NodePropertyName.ID.name());
	}

	protected void createRelationTo(Relations relation, AbstractPersistedModel<?> to, boolean mustBeUnique) {
		if(mustBeUnique && manager.relationExists(underlyingNode, to.getNode(), relation)) {
			throw new RelationAlreadyExistsException();
		}

		relation.relationIsPossibleOrException(NODE_TYPE, to.getNodeType());
		manager.createRelationshipTo(underlyingNode, to.getNode(), relation);
	}

	public void runDeletePreconditions(){

	}

	private Method getSetterMethod(String getterName, Class<?> param, Object target) {

		String setterName = getterName.replaceAll("^get([A-Z][\\w\\d]+)$", "set$1");
		try {
			return setterName.equals(getterName) ? null : target.getClass().getMethod(setterName, param);
		} catch (NoSuchMethodException e) {
			return null;
		} catch (SecurityException e) {
			return null;
		}
	}

	protected void setProperty(NodePropertyName property, Object value) {
		manager.setProperty(underlyingNode, property.name(), value);
	}

	protected Integer getIntegerProperty(NodePropertyName property) {
		return manager.getIntegerProperty(underlyingNode, property.name());
	}

	protected Float getFloatProperty(NodePropertyName property) {
		return manager.getFloatProperty(underlyingNode, property.name());
	}

	protected String getStringProperty(NodePropertyName property) {
		return manager.getStringProperty(underlyingNode, property.name());
	}

	protected Long getLongProperty(NodePropertyName property) {
		return manager.getLongProperty(underlyingNode, property.name());
	}

	protected void createRelationshipTo(AbstractPersistedModel<?> to, Relations relation) {
		manager.createRelationshipTo(underlyingNode, to.getNode(), relation);
	}

	protected void createRelationshipFrom(AbstractPersistedModel<?> from, Relations relation) {
		manager.createRelationshipTo(from.getNode(), underlyingNode, relation);
	}

	protected void onCreate() {

	}

	protected void onDelete() {

	}

	protected void finalDelete() {
		onDelete();
	}

	protected  AbstractPersistedModel<?> getEndNode(Relations relation) {

		Node n = manager.getEndNode(underlyingNode, relation);
		Class<? extends AbstractPersistedModel<?>> clazz = persistedClassResolver.getPersistedEntityClassForNode(n);

		if ( n != null ) {
			return getPersistedObject(n, clazz);
		}

		return null;

	}

	protected <P extends AbstractPersistedModel<?>> P getEndNode(Relations relation, Class<P> clazz) {

		Node n = manager.getEndNode(underlyingNode, relation);

		if ( n != null ) {
			return getPersistedObject(n, clazz);
		}

		return null;

	}
	
	protected <P extends AbstractPersistedModel<?>> P getStartNode(Relations relation, Class<P> clazz) {

		Node n = manager.getStartNode(underlyingNode, relation);

		if ( n != null ) {
			return getPersistedObject(n, clazz);
		}

		return null;

	}

	protected <P extends AbstractPersistedModel<?>> P getRelatedNode(Relations relation, Class<P> clazz, Direction direction) {

		Node n = manager.getRelatedNode(underlyingNode, relation, direction);

		if(n != null)
			return getPersistedObject(n, clazz);

		return null;

	}

	protected <P extends AbstractPersistedModel<P>> List<P> getRelatedNodes(Relations relation, Class<P> clazz, Direction direction){
		ArrayList<P> list = new ArrayList<>();
		return (List<P>) getPersistedObjects(manager.getRelatedNodes(underlyingNode, relation, direction), list, clazz);
	}

	protected <IN, IM extends AbstractPersistedModel<IM>> List<IN> getRelatedEndNodes(Relations relation, Class<IM> implementationClass, Class<IN> interfaceClass){
		return getRelatedNodes(relation, implementationClass, interfaceClass, Direction.OUTGOING);
	}

	protected <IN, IM extends AbstractPersistedModel<IM>> List<IN> getRelatedStartNodes(Relations relation, Class<IM> implementationClass, Class<IN> interfaceClass){
		return getRelatedNodes(relation, implementationClass, interfaceClass, Direction.INCOMING);
	}

	protected <IN, IM extends AbstractPersistedModel<IM>> List<IN> getRelatedNodes(Relations relation, Class<IM> implementationClass,  Class<IN> interfaceClass, Direction direction){
		return (List<IN>) getPersistedObjects(
				manager.getRelatedNodes(underlyingNode, relation, direction), 
				new ArrayList<IN>(), 
				implementationClass, 
				interfaceClass);
	}

	protected boolean relationExistsTo(AbstractPersistedModel<?> to,  Relations relation) {
		return manager.relationExists(underlyingNode, to.getNode(), relation);
	}

	protected boolean relationExistsFrom(AbstractPersistedModel<?> from,  Relations relation) {
		return manager.relationExists(from.getNode(), underlyingNode, relation);
	}

	@Override
	public int hashCode() {
		return underlyingNode.hashCode();
	}

	protected abstract void initializeVariables();

	/**
	 * called when an attribute update is required. 
	 */
	public void updateAll(){

	}

	/**
	 * Returns the objects that must execute an update all.
	 * Should be overriden
	 * @return
	 */
	public Set<AbstractPersistedModel<?>> loadObjectsToInform() {
		return new HashSet<AbstractPersistedModel<?>>();
	}

	public <E extends AbstractPersistedModel<?>> E getPersistedObject(Node n, Class<E> clazz) {
		try {

			return (E) instanceProvider.getInstanceForClass(clazz).loadModelFromExistentNode(n);
//			Constructor<T> c = clazz.getConstructor(NeoManager.class, Node.class);
//			return (c.newInstance(manager, n));

		} catch (Exception e) {
			throw new RootApplicationException(e);
		}
	}


	public <T extends AbstractPersistedModel<T>, C extends AbstractCollection<T>> C getPersistedObjects( Collection<Node> nodes, C objects, Class<T> clazz ) {
		return manager.getPersistedObjects(nodes, objects, clazz);
	}

	public <IM extends AbstractPersistedModel<IM>, IN> AbstractCollection<IN> getPersistedObjects( 
			Collection<Node> nodes, AbstractCollection<IN> objects, Class<IM> implementationClass,  Class<IN> interfaceClass ) {

		return manager.getPersistedObjects(nodes, objects, implementationClass, interfaceClass);
	}

	public <T extends AbstractPersistedModel<?>> T getPersistedObject(IndexContainer indexContainer, Class<T> clazz) {
		IndexHits<Node> indexHits = manager.getNodes(indexContainer);

		if ( indexHits.size() == 0 ) {
			throw new NodeNotFoundException();
		}

		if ( indexHits.size() > 1 ) {
			throw new NotUniqueException("Too many nodes with the same index:" + indexContainer);
		}

		Node n = indexHits.getSingle();

		if (n == null){
			throw new ConsistenceException("Index found but not its entity."); 
		}

		return getPersistedObject(n, clazz);
	}


	@Override
	public String toString() {

		String r = "\n";

		for ( NodePropertyName name : NodePropertyName.values()) {
			try {
				r += name + ":" + underlyingNode.getProperty(name.name()).toString() + "\n";
			} catch(NotFoundException e) {

			}
		}

		return super.toString() + " | " + r;
	}

	protected void removeRelationTo(AbstractPersistedModel<?> relatedObject, Relations relation) {
		manager.removeRelation(underlyingNode, relatedObject.getNode(), relation);
	}

	protected void removeRelationFrom(AbstractPersistedModel<?> relatedObject, Relations relation) {
		manager.removeRelation(relatedObject.getNode(), underlyingNode, relation);
	}

	protected void removeRelationsTo(Relations relation) {
		manager.removeRelationsTo(underlyingNode, relation);
	}
	
	public void synchronizeWith(Object tplObject) {

		Method[] methods = tplObject.getClass().getMethods();

		for(int i = 0; i < methods.length; i++) {
			Method dtoMethod = methods[i];
			if(dtoMethod.isAnnotationPresent(IgnoreAttribute.class)) {
				continue;
			}

			Method beanMethod = getSetterMethod(dtoMethod.getName(), dtoMethod.getReturnType(), this);

			if(beanMethod == null){
				continue;
			}

			try {

				if ( dtoMethod.invoke(tplObject) == null )
					continue;

				beanMethod.invoke(this, dtoMethod.invoke(tplObject));

			} catch (Exception e) {
				throw new RootApplicationException(
						"error for method " + tplObject.getClass().getCanonicalName() + "." + dtoMethod.getName() + "|" 
								+ this.getClass().getCanonicalName() + "." + beanMethod.getName() + ":" + e.getMessage(), e);
			}
		}
	}

	public List<Comment> getObjectComments(){
		return getRelatedStartNodes(Relations.ABOUT_OBJECT, PersistedComment.class, Comment.class);
	}
	
	public void addComment(Comment comment){
		createRelationshipFrom((PersistedComment)comment, Relations.ABOUT_OBJECT);
	}
	
//	@Override
//	public List<ChangeLog> getChangeLogList() {
//		throw new RootApplicationException("Not implemented");
//	}
}
