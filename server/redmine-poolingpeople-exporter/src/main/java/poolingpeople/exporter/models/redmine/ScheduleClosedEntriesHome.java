package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ScheduleClosedEntries.
 * @see poolingpeople.exporter.models.redmine.ScheduleClosedEntries
 * @author Hibernate Tools
 */
@Stateless
public class ScheduleClosedEntriesHome {

	private static final Log log = LogFactory
			.getLog(ScheduleClosedEntriesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ScheduleClosedEntries transientInstance) {
		log.debug("persisting ScheduleClosedEntries instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ScheduleClosedEntries persistentInstance) {
		log.debug("removing ScheduleClosedEntries instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ScheduleClosedEntries merge(ScheduleClosedEntries detachedInstance) {
		log.debug("merging ScheduleClosedEntries instance");
		try {
			ScheduleClosedEntries result = entityManager
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ScheduleClosedEntries findById(Integer id) {
		log.debug("getting ScheduleClosedEntries instance with id: " + id);
		try {
			ScheduleClosedEntries instance = entityManager.find(
					ScheduleClosedEntries.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
