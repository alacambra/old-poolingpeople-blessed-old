package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Watchers.
 * @see poolingpeople.exporter.models.redmine.Watchers
 * @author Hibernate Tools
 */
@Stateless
public class WatchersHome {

	private static final Log log = LogFactory.getLog(WatchersHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Watchers transientInstance) {
		log.debug("persisting Watchers instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Watchers persistentInstance) {
		log.debug("removing Watchers instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Watchers merge(Watchers detachedInstance) {
		log.debug("merging Watchers instance");
		try {
			Watchers result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Watchers findById(Integer id) {
		log.debug("getting Watchers instance with id: " + id);
		try {
			Watchers instance = entityManager.find(Watchers.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
