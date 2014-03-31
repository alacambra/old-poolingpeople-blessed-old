package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Changes.
 * @see poolingpeople.exporter.models.redmine.Changes
 * @author Hibernate Tools
 */
@Stateless
public class ChangesHome {

	private static final Log log = LogFactory.getLog(ChangesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Changes transientInstance) {
		log.debug("persisting Changes instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Changes persistentInstance) {
		log.debug("removing Changes instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Changes merge(Changes detachedInstance) {
		log.debug("merging Changes instance");
		try {
			Changes result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Changes findById(Integer id) {
		log.debug("getting Changes instance with id: " + id);
		try {
			Changes instance = entityManager.find(Changes.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
