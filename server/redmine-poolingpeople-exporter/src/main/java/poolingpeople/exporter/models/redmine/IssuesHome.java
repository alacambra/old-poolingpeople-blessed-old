package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Issues.
 * @see poolingpeople.exporter.models.redmine.Issues
 * @author Hibernate Tools
 */
@Stateless
public class IssuesHome {

	private static final Log log = LogFactory.getLog(IssuesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Issues transientInstance) {
		log.debug("persisting Issues instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Issues persistentInstance) {
		log.debug("removing Issues instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Issues merge(Issues detachedInstance) {
		log.debug("merging Issues instance");
		try {
			Issues result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Issues findById(Integer id) {
		log.debug("getting Issues instance with id: " + id);
		try {
			Issues instance = entityManager.find(Issues.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
