package poolingpeople.exporter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.neo4j.graphdb.Transaction;

import poolingpeople.commons.entities.Effort;
import poolingpeople.commons.entities.ProjectStatus;
import poolingpeople.commons.entities.TaskStatus;
import poolingpeople.exporter.models.neo4j.R2NPersistedEffort;
import poolingpeople.exporter.models.neo4j.R2NPersistedProject;
import poolingpeople.exporter.models.neo4j.R2NPersistedTask;
import poolingpeople.exporter.models.neo4j.R2NPersistedUser;
import poolingpeople.exporter.models.redmine.*;
import poolingpeople.persistence.neo4j.GraphDatabaseServiceProducer;
import poolingpeople.persistence.neo4j.NeoManager;

public class Ingestor {
	
	Logger logger = Logger.getLogger(Ingestor.class);

	Map<Integer, Users> users;
	Map<Integer, Projects> projects;
	Map<Integer, Issues> issues;
	Map<Integer, TimeEntries> entries;

	Map<Integer, String> userIds;
	Map<Integer, String> projectsIds;
	Map<Integer, String> tasksIds;
	Map<Integer, String> effortsIds;
	
	Map<String, R2NPersistedUser> R2NPersistedUsers;
	Map<String, R2NPersistedProject> persistedProjects;
	Map<String, R2NPersistedTask> persistedTasks;
	Map<String, R2NPersistedEffort> persistedEfforts;
	
	GraphDatabaseServiceProducer producer;
	NeoManager manager;
	
	public static void main(String[] args){
		new Ingestor().load();
	}
	
	public Ingestor() {
		producer = new GraphDatabaseServiceProducer();
		manager = new NeoManager(producer.getGraphDb());
	}
	
	public void load(){

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		users = getItems(session, Users.class);
		projects = getItems(session, Projects.class);
		issues = getItems(session, Issues.class);
		entries = getItems(session, TimeEntries.class);

		R2NPersistedUsers = new HashMap<String, R2NPersistedUser>();
		userIds = new HashMap<Integer, String>();
		projectsIds = new HashMap<Integer, String>();
		tasksIds = new HashMap<Integer, String>();
		effortsIds = new HashMap<Integer, String>();
		persistedProjects = new HashMap<String, R2NPersistedProject>();
		persistedTasks = new HashMap<String, R2NPersistedTask>();
		persistedEfforts = new HashMap<String, R2NPersistedEffort>();

		try ( Transaction tx = manager.getGraphDbService().beginTx() )
		{
			logger.error("Users.......................");
			loadDefaultUser(manager);

			for( Entry<Integer, Users> u : users.entrySet() ){

				duplicateUser(u.getValue());

			}

			logger.error("Projects.......................");
			int i = 0;
			for( Entry<Integer, Projects> p : projects.entrySet() ){

				duplicateProject(p.getValue());

			}

			logger.error("Tasks.......................");
			i = 0;
			for( Entry<Integer, Issues> t : issues.entrySet() ){

				duplicateTask(t.getValue());
				
				if ( i%100==0 ){
					System.out.println(issues.size() + " : adding task: " + i);
				}
				i++;

			}

			logger.error("Efforts.......................");
			i = 0;
			for( Entry<Integer, TimeEntries> te : entries.entrySet() ){

				R2NPersistedTask task = persistedTasks.get(getUUIDForId(te.getValue().getIssueId(), tasksIds)); 

				if (task == null){

					logger.error("Skiping entry without task");
					continue;

				}

				R2NPersistedEffort effort = new R2NPersistedEffort(manager, new FakedEffort());
				task.addEffort(effort);

				effort.setComment(te.getValue().getComments());
				effort.setDate(te.getValue().getCreatedOn().getTime());
				effort.setTime(Math.round(te.getValue().getHours()));
				persistedEfforts.put(effort.getId(), effort);
				effortsIds.put(te.getKey(), effort.getId());
				if ( i%100==0 ){
					System.out.println(entries.size() + " : adding effort: " + i);
				}
				i++;
				//				R2NPersistedUser user = R2NPersistedUsers.get(userIds.get(te.getValue().getUserId()));


			}

			//			for( Entry<Integer, Projects> p : projects.entrySet() ){
			//				persistedProjects.get(projectsIds.get(p.getKey())).addTask(persistedTasks.get(tasksIds.get(key));
			//			}

			i = 0;
			for(Entry<Integer, Issues> t : issues.entrySet()){

				if ( i%100==0 ){
					System.out.println(issues.size() + " : adding subtask: " + i);
				}
				i++;
				
				String taskUUID = tasksIds.get(t.getKey());
				
				R2NPersistedProject pp = persistedProjects.get(projectsIds.get(t.getValue().getProjectId()));
				
				R2NPersistedTask task = persistedTasks.get(taskUUID);
				
				if (pp != null){
					pp.addTask(task);
				}

				R2NPersistedTask parent = persistedTasks.get(getUUIDForId(t.getValue().getParentId(), tasksIds));
				if (parent != null){
					parent.addSubtask(task);
				}
				
				R2NPersistedUser user = R2NPersistedUsers.get(getUUIDForId(t.getValue().getAssignedToId(), userIds));
				if( user != null ){
					task.setAssignee(user);
				}
				
			}

			tx.success();
			session.getTransaction().commit();
		}
	}
	
	private R2NPersistedUser duplicateUser(Users user){
		R2NPersistedUser pu = new R2NPersistedUser(manager, getEmail(user), "a", new FakedUser());
		pu.setFirstName(user.getFirstname());
		pu.setRedmineId(user.getId());
		R2NPersistedUsers.put(pu.getId(), pu);
		
		userIds.put(user.getId(), pu.getId());
		
		return pu;
	}
	
	private R2NPersistedTask duplicateTask(Issues issue){
		R2NPersistedTask pt = new R2NPersistedTask(manager, new FakedTask());
		pt.setDefaultStartDate(issue.getCreatedOn().getTime());
		pt.setDescription(issue.getDescription());
		pt.setStatus(getTaskStatus(issue.getStatusId()));
		pt.setTitle(issue.getSubject());
		persistedTasks.put(pt.getId(), pt);
		tasksIds.put(issue.getId(), pt.getId());
		
		return pt;
	}
	
	private R2NPersistedProject duplicateProject(Projects project){
		R2NPersistedProject pp = new R2NPersistedProject(manager, new FakedProject());
		pp.setDefaultStartDate(project.getCreatedOn().getTime());
		pp.setDescription(project.getDescription());
		pp.setStatus(getProjectStatus(project.getStatus()));
		pp.setTitle(project.getName());
		pp.setRedmineId(project.getId());
		persistedProjects.put(pp.getId(), pp);
		projectsIds.put(project.getId(), pp.getId());
		
		return pp;
	}
	
	private Effort duplicateEffort(TimeEntries timeEntry){
		
		R2NPersistedEffort effort = new R2NPersistedEffort(manager, new FakedEffort());
		effort.setComment(timeEntry.getComments());
		effort.setDate(timeEntry.getCreatedOn().getTime());
		effort.setTime(Math.round(timeEntry.getHours()));
		effort.setRedmineId(timeEntry.getId());
		persistedEfforts.put(effort.getId(), effort);
		effortsIds.put(timeEntry.getId(), effort.getId());
		
		return effort;
	}
	
	private String getUUIDForId(Integer rmId, Map<Integer, String> container){
		return container.get(rmId);
	}

	private R2NPersistedUser loadDefaultUser(NeoManager manager){
		R2NPersistedUser pu = new R2NPersistedUser(manager, "a@a.com", "a", new FakedUser());
		pu.setFirstName("DefaultUser");
		pu.setPassword("a");
		return pu;
	}

	private <T extends HasId> Map<Integer, T> getItems(Session session, Class<T> clazz) {

		Criteria criteria = session.createCriteria(clazz);
		List<T> resource = criteria.list();

		HashMap<Integer, T> set = new HashMap<>();

		for(T r : resource){
			set.put(r.getId(), r);
		}

		return set;
	}

	private ProjectStatus getProjectStatus(int status){
		switch(status){
		case 1:
			return ProjectStatus.NEW;
		case 5:
			return ProjectStatus.COMPLETED;
		case 9:
			return ProjectStatus.ARCHIVED;

		default:
			return ProjectStatus.NEW;
		}
	}

	private String getEmail(Users redmineUser) {
		return redmineUser.getMail() == null || "".equals(redmineUser.getMail()) ? String.valueOf(new Date().getTime()) + "@s.com" : redmineUser.getMail();
	}

	private TaskStatus getTaskStatus(int status){
		switch(status){
		case 1:
			return TaskStatus.NEW;
		case 5:
			return TaskStatus.COMPLETED;
		case 9:
			return TaskStatus.ARCHIVED;

		default:
			return TaskStatus.NEW;
		}
	}
}


































