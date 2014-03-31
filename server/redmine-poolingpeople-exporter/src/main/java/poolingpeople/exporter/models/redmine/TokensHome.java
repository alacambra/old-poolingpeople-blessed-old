package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tokens.
 * @see poolingpeople.exporter.models.redmine.Tokens
 * @author Hibernate Tools
 */
@Stateless
public class TokensHome {

	private static final Log log = LogFactory.getLog(TokensHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tokens transientInstance) {
		log.debug("persisting Tokens instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tokens persistentInstance) {
		log.debug("removing Tokens instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Tokens merge(Tokens detachedInstance) {
		log.debug("merging Tokens instance");
		try {
			Tokens result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tokens findById(Integer id) {
		log.debug("getting Tokens instance with id: " + id);
		try {
			Tokens instance = entityManager.find(Tokens.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
