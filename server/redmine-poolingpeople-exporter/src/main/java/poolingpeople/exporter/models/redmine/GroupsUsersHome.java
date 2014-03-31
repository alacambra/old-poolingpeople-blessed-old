package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class GroupsUsers.
 * @see poolingpeople.exporter.models.redmine.GroupsUsers
 * @author Hibernate Tools
 */
@Stateless
public class GroupsUsersHome {

	private static final Log log = LogFactory.getLog(GroupsUsersHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(GroupsUsers transientInstance) {
		log.debug("persisting GroupsUsers instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(GroupsUsers persistentInstance) {
		log.debug("removing GroupsUsers instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public GroupsUsers merge(GroupsUsers detachedInstance) {
		log.debug("merging GroupsUsers instance");
		try {
			GroupsUsers result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public GroupsUsers findById(GroupsUsersId id) {
		log.debug("getting GroupsUsers instance with id: " + id);
		try {
			GroupsUsers instance = entityManager.find(GroupsUsers.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
