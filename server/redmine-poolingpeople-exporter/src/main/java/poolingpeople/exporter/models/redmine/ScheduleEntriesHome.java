package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ScheduleEntries.
 * @see poolingpeople.exporter.models.redmine.ScheduleEntries
 * @author Hibernate Tools
 */
@Stateless
public class ScheduleEntriesHome {

	private static final Log log = LogFactory.getLog(ScheduleEntriesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ScheduleEntries transientInstance) {
		log.debug("persisting ScheduleEntries instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ScheduleEntries persistentInstance) {
		log.debug("removing ScheduleEntries instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ScheduleEntries merge(ScheduleEntries detachedInstance) {
		log.debug("merging ScheduleEntries instance");
		try {
			ScheduleEntries result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ScheduleEntries findById(Integer id) {
		log.debug("getting ScheduleEntries instance with id: " + id);
		try {
			ScheduleEntries instance = entityManager.find(
					ScheduleEntries.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
