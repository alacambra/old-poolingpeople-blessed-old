package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class OpenIdAuthenticationAssociations.
 * @see poolingpeople.exporter.models.redmine.OpenIdAuthenticationAssociations
 * @author Hibernate Tools
 */
@Stateless
public class OpenIdAuthenticationAssociationsHome {

	private static final Log log = LogFactory
			.getLog(OpenIdAuthenticationAssociationsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(OpenIdAuthenticationAssociations transientInstance) {
		log.debug("persisting OpenIdAuthenticationAssociations instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(OpenIdAuthenticationAssociations persistentInstance) {
		log.debug("removing OpenIdAuthenticationAssociations instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public OpenIdAuthenticationAssociations merge(
			OpenIdAuthenticationAssociations detachedInstance) {
		log.debug("merging OpenIdAuthenticationAssociations instance");
		try {
			OpenIdAuthenticationAssociations result = entityManager
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OpenIdAuthenticationAssociations findById(Integer id) {
		log.debug("getting OpenIdAuthenticationAssociations instance with id: "
				+ id);
		try {
			OpenIdAuthenticationAssociations instance = entityManager.find(
					OpenIdAuthenticationAssociations.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
