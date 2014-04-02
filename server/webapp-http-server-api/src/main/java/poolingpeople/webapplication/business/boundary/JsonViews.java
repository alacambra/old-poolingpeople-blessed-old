package poolingpeople.webapplication.business.boundary;

public class JsonViews {
	
	/************************************** GENERAL VIEWS ***********************************************/
	
	/**
	 * Attributes that must NEVER be sent
	 */
	public static interface NeverUsed{}
	
	/**
	 * Attributes that can be send in a subentity
	 */
	public static interface Shared{}
	

	/************************************** PROJECT's VIEWS ***********************************************/
	
	/**
	 * Attributes for an elementary (visitor or non contact...) view but only in the main entity
	 */
	public static interface BasicProject extends Shared{}
	/**
	 * Basic attributes from the main entity and shared attributes of subentity 
	 */
	public static interface BasicProjectWithElements extends BasicProject{}
	
	/**
	 * All attributes of an entity without subentities
	 */
	public static interface FullProject extends BasicProject{}
	
	/**
	 * All attributes of an entity with subentities
	 */
	public static interface FullProjectWithElements extends BasicProjectWithElements, FullProject{}
	
	/************************************** TASK's VIEWS ***********************************************/
	/**
	 * Attributes for an elementary (visitor or non contact...) view but only in the main entity
	 */
	public static interface BasicTask extends Shared{}
	/**
	 * Basic attributes from the main entity and shared attributes of subentity 
	 */
	public static interface BasicTaskWithElements extends BasicTask{}
	
	/**
	 * All attributes of an entity without subentities
	 */
	public static interface FullTask extends BasicTask{}
	
	/**
	 * All attributes of an entity with subentities
	 */
	public static interface FullTaskWithElements extends BasicTaskWithElements, FullTask{}
	
	
	/************************************** EFFORT's VIEWS ***********************************************/
	/**
	 * Attributes for an elementary (visitor or non contact...) view but only in the main entity
	 */
	public static interface BasicEffort extends Shared{}
	/**
	 * Basic attributes from the main entity and shared attributes of subentity 
	 */
	public static interface BasicEffortWithElements extends BasicEffort{}
	
	/**
	 * All attributes of an entity without subentities
	 */
	public static interface FullEffort extends BasicEffort{}
	
	/**
	 * All attributes of an entity with subentities
	 */
	public static interface FullEffortWithElements extends BasicEffortWithElements, FullEffort{}
	
	/************************************** COMMENTS's VIEWS ***********************************************/
	public static interface BasicComments extends Shared{}
	public static interface CommentsWithObject extends BasicComments{}
	public static interface CommentsWithSubject extends BasicComments{}
	public static interface CommentsFull extends CommentsWithObject, CommentsWithSubject{}
	
	/************************************Changelog VIEW***************************************************/
	public interface BasicChangelog extends Shared{} 
	
}
