package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ScheduleDefaults.
 * @see poolingpeople.exporter.models.redmine.ScheduleDefaults
 * @author Hibernate Tools
 */
@Stateless
public class ScheduleDefaultsHome {

	private static final Log log = LogFactory
			.getLog(ScheduleDefaultsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ScheduleDefaults transientInstance) {
		log.debug("persisting ScheduleDefaults instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ScheduleDefaults persistentInstance) {
		log.debug("removing ScheduleDefaults instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ScheduleDefaults merge(ScheduleDefaults detachedInstance) {
		log.debug("merging ScheduleDefaults instance");
		try {
			ScheduleDefaults result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ScheduleDefaults findById(Integer id) {
		log.debug("getting ScheduleDefaults instance with id: " + id);
		try {
			ScheduleDefaults instance = entityManager.find(
					ScheduleDefaults.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
