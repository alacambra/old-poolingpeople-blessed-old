package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Enumerations.
 * @see poolingpeople.exporter.models.redmine.Enumerations
 * @author Hibernate Tools
 */
@Stateless
public class EnumerationsHome {

	private static final Log log = LogFactory.getLog(EnumerationsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Enumerations transientInstance) {
		log.debug("persisting Enumerations instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Enumerations persistentInstance) {
		log.debug("removing Enumerations instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Enumerations merge(Enumerations detachedInstance) {
		log.debug("merging Enumerations instance");
		try {
			Enumerations result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Enumerations findById(Integer id) {
		log.debug("getting Enumerations instance with id: " + id);
		try {
			Enumerations instance = entityManager.find(Enumerations.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
