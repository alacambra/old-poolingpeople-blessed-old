package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Rates.
 * @see poolingpeople.exporter.models.redmine.Rates
 * @author Hibernate Tools
 */
@Stateless
public class RatesHome {

	private static final Log log = LogFactory.getLog(RatesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Rates transientInstance) {
		log.debug("persisting Rates instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Rates persistentInstance) {
		log.debug("removing Rates instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Rates merge(Rates detachedInstance) {
		log.debug("merging Rates instance");
		try {
			Rates result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Rates findById(Integer id) {
		log.debug("getting Rates instance with id: " + id);
		try {
			Rates instance = entityManager.find(Rates.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
