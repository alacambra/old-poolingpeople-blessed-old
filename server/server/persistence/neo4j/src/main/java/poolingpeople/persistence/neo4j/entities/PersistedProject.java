package poolingpeople.persistence.neo4j.entities;

import java.util.Collection;
import java.util.List;

import org.neo4j.graphdb.Direction;

import poolingpeople.commons.entities.ChangeLog;
import poolingpeople.commons.entities.Project;
import poolingpeople.commons.entities.ProjectStatus;
import poolingpeople.commons.entities.Task;
import poolingpeople.commons.entities.User;
import poolingpeople.persistence.neo4j.DefaultValues;
import poolingpeople.persistence.neo4j.NodePropertyName;
import poolingpeople.persistence.neo4j.PoolingpeopleObjectType;
import poolingpeople.persistence.neo4j.Relations;
import poolingpeople.persistence.neo4j.exceptions.RelationAlreadyExistsException;
import poolingpeople.persistence.neo4j.exceptions.RelationNotFoundException;


public class PersistedProject extends AbstractPersistedModel<PersistedProject> implements Project {

	public static final PoolingpeopleObjectType NODE_TYPE = PoolingpeopleObjectType.PROJECT;
	private List<PersistedTask> relatedTasks;

	
	/**************** NON-INHERITABLE ATTRIBUTES *****************/

	@Override
	public String getTitle() {
		return getStringProperty(NodePropertyName.TITLE);
	}

	@Override
	public void setTitle(String title) {
		setProperty(NodePropertyName.TITLE, title);
	}

	@Override
	public String getDescription() {
		return getStringProperty(NodePropertyName.DESCRIPTION);
	}

	@Override
	public void setDescription(String description) {
		setProperty(NodePropertyName.DESCRIPTION, description);
	}

	@Override
	public ProjectStatus getStatus() {
		try {
			return (getStringProperty(NodePropertyName.STATUS).equals("")) ? ProjectStatus.NEW
					: ProjectStatus
					.valueOf(getStringProperty(NodePropertyName.STATUS));
		} catch (NullPointerException e) {
			return ProjectStatus.NEW;
		}
	}

	@Override
	public void setStatus(ProjectStatus status) {
		setProperty(NodePropertyName.STATUS, status.name());		
	}

	/**************** INHERITABLE ATTRIBUTES *****************/

	/*
	 * **** DATE METHODS ****
	 */
	@Override
	public Long getStartDate() {
		Long startDate = getCalculatedStartDate();
		return getStartDateIsDefault() ? getDefaultStartDate() : startDate;
	}

	@Override
	public Long getEndDate() {
		Long endDate = getCalculatedEndDate();
		return getEndDateIsDefault() ? getDefaultEndDate() : endDate;
	}

	private void setCalculatedStartDate(Long startDate) {
		setProperty(NodePropertyName.START_DATE, startDate);
	}

	private void setCalculatedEndDate(Long endDate) {
		setProperty(NodePropertyName.END_DATE, endDate);
	}

	private Long getCalculatedStartDate(){
		return getLongProperty(NodePropertyName.START_DATE);
	}

	private Long getCalculatedEndDate(){
		return getLongProperty(NodePropertyName.END_DATE);
	}


	@Override
	public void setDefaultStartDate(Long startDate) {

		if(getLongProperty(NodePropertyName.START_DATE) == null) {
			setCalculatedStartDate(startDate);
		}

		setProperty(NodePropertyName.DEFAULT_START_DATE, startDate);
	}

	@Override
	public void setDefaultEndDate(Long endDate) {

		if(getLongProperty(NodePropertyName.END_DATE) == null) {
			setCalculatedEndDate(endDate);
		}

		setProperty(NodePropertyName.DEFAULT_END_DATE, endDate);
	}

	private Long getDefaultStartDate() {
		return getLongProperty(NodePropertyName.DEFAULT_START_DATE);
	}

	private Long getDefaultEndDate() {
		return getLongProperty(NodePropertyName.DEFAULT_END_DATE);
	}
	
	/*
	 * **** PROGRESS METHODS ****
	 */
	@Override
	public Float getProgress() {
		Float progress = getCalculatedProgress();
		return getProgressIsDefault() ? getDefaultProgress() : progress;
	}

	private Float getCalculatedProgress() {
		return getFloatProperty(NodePropertyName.PROGRESS);
	}

	private Float getDefaultProgress(){
		return getFloatProperty(NodePropertyName.DEFAULT_PROGRESS);
	}

	private void setCalculatedProgress(Float progress) {
		setProperty(NodePropertyName.PROGRESS, progress);
	}

	public void setDefaultProgress(Float progress) {
		setProperty(NodePropertyName.DEFAULT_PROGRESS, progress);
	}
	
	@Override
	public void setDefaultEffort(Integer effort) {
		setProperty(NodePropertyName.DEFAULT_EFFORT, effort);
	}
	
	private Integer getCalculatedEffort(){
		 return getIntegerProperty(NodePropertyName.EFFORT);
	}
	
	@Override
	public Integer getEffort() {

		Integer effort = getCalculatedEffort();
		return effort == null ? getDefaultEffort() : effort;
		
	}

	private void setEffort(int effort) {
		setProperty(NodePropertyName.EFFORT, effort);
	}
	
	private Integer getDefaultEffort(){
		return getIntegerProperty(NodePropertyName.DEFAULT_EFFORT);
	}

	private void setCalculatedDuration(Integer duration) {
		setProperty(NodePropertyName.DURATION, duration);
	}

	@Override
	public Integer getDuration() {
		Integer duration = getCalculatedDuration();
		return duration == null ? getDefaultDuration() : duration;
	}
	
	private Integer getCalculatedDuration(){
		 return getIntegerProperty(NodePropertyName.DURATION);
	}

	@Override
	public void setDefaultDuration(Integer duration) {
		if(getCalculatedDuration() == null) {
			setCalculatedDuration(duration);
		}

		setProperty(NodePropertyName.DEFAULT_DURATION, duration);
	}
	
	private Integer getDefaultDuration(){
		return getIntegerProperty(NodePropertyName.DEFAULT_DURATION);
	}


	/**************** FLAGS FOR INHERITABLE ATTRIBUTES *****************/

	/**
	 * @todo: which are the default values? How to know if values child are valid?
	 * Each attribute must have defaults....
	 * @return
	 */
	public boolean getProgressIsDefault() {
		return getRelatedTasks().size() == 0 || getCalculatedProgress() == null || getCalculatedProgress() == DefaultValues.defaultProgress;
	}


	public boolean getStartDateIsDefault() {
		return getRelatedTasks().size() == 0 || (getCalculatedStartDate() == null) || getCalculatedEndDate() == DefaultValues.invalidEndDate;
	}

	public boolean getEndDateIsDefault() {
		return getRelatedTasks().size() == 0 || (getCalculatedEndDate() == null || getCalculatedStartDate() == DefaultValues.invalidStartDate);
	}

	public boolean getStatusIsDefault() {
		return true;
	}
	
	public boolean getDurationIsDefault() {
		return getRelatedTasks().size() == 0 || getCalculatedDuration() == null;
	}
	
	public boolean getEffortIsDefault() {
		return getRelatedTasks().size() == 0 || getCalculatedEffort() == null;
	}

	/**************** RELATIONAL METHODS *****************/

	public void addSubproject(Project child) {
		Relations.IS_SUBPROJECT_OF.relationIsPossibleOrException(NODE_TYPE,
				((PersistedProject) child).getNodeType());
		createRelationshipTo((PersistedProject) child, Relations.IS_SUBPROJECT_OF);
	}

	@Override
	public void addTask(Task task) {

		if (relationExistsTo((AbstractPersistedModel<?>) task, Relations.PROJECT_HAS_TASK)) {
			throw new RelationAlreadyExistsException();
		}

		createRelationshipTo((AbstractPersistedModel<?>) task, Relations.PROJECT_HAS_TASK);

		//		Long startDate = task.getStartDate();
		//		Long endDate = task.getEndDate();
		//
		//		updateDates(startDate, endDate);
		//		updateDates();
		//		setEffort(getEffort() + task.getEffort());
		//		calculateProgress();

		updateAll();

	}

	/*
	 * @todo: separate remove only relation or remove task included (required for move task from P1 to P2)
	 */
	@Override
	public void removeTask(Task task) {
		if (!relationExistsTo((AbstractPersistedModel<?>) task, Relations.PROJECT_HAS_TASK)) {
			throw new RelationNotFoundException();
		}

		manager.removeNode(((AbstractPersistedModel<?>) task).getNode());
	}

	@Override
	public void removeTaskRelation(Task task) {
		if (!relationExistsTo((AbstractPersistedModel<?>) task, Relations.PROJECT_HAS_TASK)) {
			throw new RelationNotFoundException();
		}

		removeRelationTo((AbstractPersistedModel<?>) task, Relations.PROJECT_HAS_TASK);
		updateAll();
	}

	@Override
	public Collection<Task> getTasks() {
		return getRelatedEndNodes(Relations.PROJECT_HAS_TASK, PersistedTask.class, Task.class);
	}

	/**************** UPDATE METHODS *****************/

	private void calculateProgress() {
		Float totalProgress = (float) 0;
		Integer totalEstimation = 0;

		for(Task t : getTasks()) {
			totalEstimation += t.getDuration();
			totalProgress += t.getDuration() * t.getProgress();
		}

		setCalculatedProgress(totalEstimation > 0 ? totalProgress / totalEstimation : DefaultValues.defaultProgress);
	}

	@Override
	public void updateEffort() {

		int effort = 0;

		for (Task t : getRelatedTasks()) {
			effort += t.getEffort();
		}

		setEffort(effort);
	}

	@Override
	public void updateProgress() {
		calculateProgress();
	}

	@Override
	public void updateDates() {

		Long startDate = DefaultValues.invalidStartDate;
		Long endDate = DefaultValues.invalidEndDate;
		Long currentStartDate = getStartDate();
		Long currentEndDate = getEndDate();

		for (Task t : getRelatedTasks()) {

			Long sd = t.getStartDate();
			Long ed = t.getEndDate();
			startDate = startDate > sd ? sd : startDate;
			endDate = endDate < ed ? ed : endDate;

		};

		if ( currentStartDate != startDate ) {
			setCalculatedStartDate(startDate);
			startDateChanged(startDate);
		}

		if ( currentEndDate != endDate ) {
			setCalculatedEndDate(endDate);
			endDateChanged(endDate);
		}

	}
	
//	@Override
	public void updateDuration() {

		int duration = 0;

		for (Task t : getRelatedTasks()) {
			duration += t.getDuration();
		}

		setCalculatedDuration(duration);
	}


	@Override
	public void updateAll() {
		updateEffort();
		updateDates();
		updateProgress();
		updateDuration();
	}

	/**************** PROPAGATION METHODS *****************/

	private void startDateChanged(Long startDate) {

	}

	private void endDateChanged(Long endDate) {

	}

	private void durationChanged(Integer duration) {

	}

	private void progressChanged(Float progress){

	}

	private void effortChanged(Integer effort) {

	}


	/**************** HELPER METHODS *****************/

	private List<PersistedTask> getRelatedTasks(){
		relatedTasks = getRelatedNodes(Relations.PROJECT_HAS_TASK, PersistedTask.class, Direction.OUTGOING);
		return relatedTasks;
	}


	@Override
	public boolean equals(Object obj) {
		return obj instanceof PersistedProject
				&& ((PersistedProject) obj).getNode().equals(underlyingNode);
	}

	@Override
	public Integer getTaskCount() {
		return getTasks().size();
	}

	@Override
	protected void initializeVariables() {
		
		if ( getDefaultStartDate() == null )
			setDefaultStartDate(DefaultValues.invalidStartDate);

		if ( getDefaultEndDate() == null )
			setDefaultEndDate(DefaultValues.invalidEndDate);

		if ( getDefaultProgress() == null )
			setDefaultProgress(DefaultValues.defaultProgress);
		
		if ( getDefaultEffort() == null )
			setDefaultEffort(DefaultValues.defaultEffort);
		
		if ( getDefaultDuration() == null )
			setDefaultEffort(DefaultValues.defaultDuration);
		
	}

	@Override
	public void setOwner(User owner) {
		
		if (relationExistsFrom((AbstractPersistedModel<?>) owner, Relations.IS_PROJECT_OWNER)) {
//			throw new RelationAlreadyExistsException();
			return;
		}

		removeRelationsTo(Relations.IS_PROJECT_OWNER);
		createRelationshipFrom((AbstractPersistedModel<?>) owner, Relations.IS_PROJECT_OWNER);
	}

	@Override
	public User getOwner() {
		return getRelatedNode(Relations.IS_PROJECT_OWNER, PersistedUser.class, Direction.INCOMING);
	}

	@Override
	public List<ChangeLog> getChangeLogList() {
		return getRelatedNodes(Relations.HAS_CHANGE_LOG, PersistedChangeLog.class, ChangeLog.class, Direction.OUTGOING);
	}

	@Override
	public void synchronizeWith(Object tplObject) {
		// TODO Auto-generated method stub
		
	}


}





































































