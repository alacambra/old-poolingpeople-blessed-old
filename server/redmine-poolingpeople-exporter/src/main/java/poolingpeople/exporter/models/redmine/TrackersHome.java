package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Trackers.
 * @see poolingpeople.exporter.models.redmine.Trackers
 * @author Hibernate Tools
 */
@Stateless
public class TrackersHome {

	private static final Log log = LogFactory.getLog(TrackersHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Trackers transientInstance) {
		log.debug("persisting Trackers instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Trackers persistentInstance) {
		log.debug("removing Trackers instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Trackers merge(Trackers detachedInstance) {
		log.debug("merging Trackers instance");
		try {
			Trackers result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Trackers findById(Integer id) {
		log.debug("getting Trackers instance with id: " + id);
		try {
			Trackers instance = entityManager.find(Trackers.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
