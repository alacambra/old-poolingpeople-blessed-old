package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TimeEntries.
 * @see poolingpeople.exporter.models.redmine.TimeEntries
 * @author Hibernate Tools
 */
@Stateless
public class TimeEntriesHome {

	private static final Log log = LogFactory.getLog(TimeEntriesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TimeEntries transientInstance) {
		log.debug("persisting TimeEntries instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TimeEntries persistentInstance) {
		log.debug("removing TimeEntries instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TimeEntries merge(TimeEntries detachedInstance) {
		log.debug("merging TimeEntries instance");
		try {
			TimeEntries result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TimeEntries findById(Integer id) {
		log.debug("getting TimeEntries instance with id: " + id);
		try {
			TimeEntries instance = entityManager.find(TimeEntries.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
