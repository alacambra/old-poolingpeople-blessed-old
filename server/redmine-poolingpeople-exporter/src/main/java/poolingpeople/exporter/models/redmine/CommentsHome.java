package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Comments.
 * @see poolingpeople.exporter.models.redmine.Comments
 * @author Hibernate Tools
 */
@Stateless
public class CommentsHome {

	private static final Log log = LogFactory.getLog(CommentsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Comments transientInstance) {
		log.debug("persisting Comments instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Comments persistentInstance) {
		log.debug("removing Comments instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Comments merge(Comments detachedInstance) {
		log.debug("merging Comments instance");
		try {
			Comments result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Comments findById(Integer id) {
		log.debug("getting Comments instance with id: " + id);
		try {
			Comments instance = entityManager.find(Comments.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
