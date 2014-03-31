package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Messages.
 * @see poolingpeople.exporter.models.redmine.Messages
 * @author Hibernate Tools
 */
@Stateless
public class MessagesHome {

	private static final Log log = LogFactory.getLog(MessagesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Messages transientInstance) {
		log.debug("persisting Messages instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Messages persistentInstance) {
		log.debug("removing Messages instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Messages merge(Messages detachedInstance) {
		log.debug("merging Messages instance");
		try {
			Messages result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Messages findById(Integer id) {
		log.debug("getting Messages instance with id: " + id);
		try {
			Messages instance = entityManager.find(Messages.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
