package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class AuthSources.
 * @see poolingpeople.exporter.models.redmine.AuthSources
 * @author Hibernate Tools
 */
@Stateless
public class AuthSourcesHome {

	private static final Log log = LogFactory.getLog(AuthSourcesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AuthSources transientInstance) {
		log.debug("persisting AuthSources instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AuthSources persistentInstance) {
		log.debug("removing AuthSources instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AuthSources merge(AuthSources detachedInstance) {
		log.debug("merging AuthSources instance");
		try {
			AuthSources result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AuthSources findById(Integer id) {
		log.debug("getting AuthSources instance with id: " + id);
		try {
			AuthSources instance = entityManager.find(AuthSources.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
