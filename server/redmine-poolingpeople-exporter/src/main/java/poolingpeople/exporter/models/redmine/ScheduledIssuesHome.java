package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ScheduledIssues.
 * @see poolingpeople.exporter.models.redmine.ScheduledIssues
 * @author Hibernate Tools
 */
@Stateless
public class ScheduledIssuesHome {

	private static final Log log = LogFactory.getLog(ScheduledIssuesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ScheduledIssues transientInstance) {
		log.debug("persisting ScheduledIssues instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ScheduledIssues persistentInstance) {
		log.debug("removing ScheduledIssues instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ScheduledIssues merge(ScheduledIssues detachedInstance) {
		log.debug("merging ScheduledIssues instance");
		try {
			ScheduledIssues result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ScheduledIssues findById(Integer id) {
		log.debug("getting ScheduledIssues instance with id: " + id);
		try {
			ScheduledIssues instance = entityManager.find(
					ScheduledIssues.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
