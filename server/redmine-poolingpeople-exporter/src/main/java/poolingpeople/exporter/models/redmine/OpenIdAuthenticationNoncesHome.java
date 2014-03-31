package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class OpenIdAuthenticationNonces.
 * @see poolingpeople.exporter.models.redmine.OpenIdAuthenticationNonces
 * @author Hibernate Tools
 */
@Stateless
public class OpenIdAuthenticationNoncesHome {

	private static final Log log = LogFactory
			.getLog(OpenIdAuthenticationNoncesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(OpenIdAuthenticationNonces transientInstance) {
		log.debug("persisting OpenIdAuthenticationNonces instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(OpenIdAuthenticationNonces persistentInstance) {
		log.debug("removing OpenIdAuthenticationNonces instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public OpenIdAuthenticationNonces merge(
			OpenIdAuthenticationNonces detachedInstance) {
		log.debug("merging OpenIdAuthenticationNonces instance");
		try {
			OpenIdAuthenticationNonces result = entityManager
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OpenIdAuthenticationNonces findById(Integer id) {
		log.debug("getting OpenIdAuthenticationNonces instance with id: " + id);
		try {
			OpenIdAuthenticationNonces instance = entityManager.find(
					OpenIdAuthenticationNonces.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
