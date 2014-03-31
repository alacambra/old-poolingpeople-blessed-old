package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Deliverables.
 * @see poolingpeople.exporter.models.redmine.Deliverables
 * @author Hibernate Tools
 */
@Stateless
public class DeliverablesHome {

	private static final Log log = LogFactory.getLog(DeliverablesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Deliverables transientInstance) {
		log.debug("persisting Deliverables instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Deliverables persistentInstance) {
		log.debug("removing Deliverables instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Deliverables merge(Deliverables detachedInstance) {
		log.debug("merging Deliverables instance");
		try {
			Deliverables result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Deliverables findById(Integer id) {
		log.debug("getting Deliverables instance with id: " + id);
		try {
			Deliverables instance = entityManager.find(Deliverables.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
