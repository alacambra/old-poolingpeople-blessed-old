package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Changesets.
 * @see poolingpeople.exporter.models.redmine.Changesets
 * @author Hibernate Tools
 */
@Stateless
public class ChangesetsHome {

	private static final Log log = LogFactory.getLog(ChangesetsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Changesets transientInstance) {
		log.debug("persisting Changesets instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Changesets persistentInstance) {
		log.debug("removing Changesets instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Changesets merge(Changesets detachedInstance) {
		log.debug("merging Changesets instance");
		try {
			Changesets result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Changesets findById(Integer id) {
		log.debug("getting Changesets instance with id: " + id);
		try {
			Changesets instance = entityManager.find(Changesets.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
