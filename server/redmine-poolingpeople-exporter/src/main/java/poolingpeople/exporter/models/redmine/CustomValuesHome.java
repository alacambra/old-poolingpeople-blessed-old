package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CustomValues.
 * @see poolingpeople.exporter.models.redmine.CustomValues
 * @author Hibernate Tools
 */
@Stateless
public class CustomValuesHome {

	private static final Log log = LogFactory.getLog(CustomValuesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CustomValues transientInstance) {
		log.debug("persisting CustomValues instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CustomValues persistentInstance) {
		log.debug("removing CustomValues instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CustomValues merge(CustomValues detachedInstance) {
		log.debug("merging CustomValues instance");
		try {
			CustomValues result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CustomValues findById(Integer id) {
		log.debug("getting CustomValues instance with id: " + id);
		try {
			CustomValues instance = entityManager.find(CustomValues.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
