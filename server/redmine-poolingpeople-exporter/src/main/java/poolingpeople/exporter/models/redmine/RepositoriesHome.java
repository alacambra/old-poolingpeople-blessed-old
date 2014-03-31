package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Repositories.
 * @see poolingpeople.exporter.models.redmine.Repositories
 * @author Hibernate Tools
 */
@Stateless
public class RepositoriesHome {

	private static final Log log = LogFactory.getLog(RepositoriesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Repositories transientInstance) {
		log.debug("persisting Repositories instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Repositories persistentInstance) {
		log.debug("removing Repositories instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Repositories merge(Repositories detachedInstance) {
		log.debug("merging Repositories instance");
		try {
			Repositories result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Repositories findById(Integer id) {
		log.debug("getting Repositories instance with id: " + id);
		try {
			Repositories instance = entityManager.find(Repositories.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
