package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Versions.
 * @see poolingpeople.exporter.models.redmine.Versions
 * @author Hibernate Tools
 */
@Stateless
public class VersionsHome {

	private static final Log log = LogFactory.getLog(VersionsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Versions transientInstance) {
		log.debug("persisting Versions instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Versions persistentInstance) {
		log.debug("removing Versions instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Versions merge(Versions detachedInstance) {
		log.debug("merging Versions instance");
		try {
			Versions result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Versions findById(Integer id) {
		log.debug("getting Versions instance with id: " + id);
		try {
			Versions instance = entityManager.find(Versions.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
