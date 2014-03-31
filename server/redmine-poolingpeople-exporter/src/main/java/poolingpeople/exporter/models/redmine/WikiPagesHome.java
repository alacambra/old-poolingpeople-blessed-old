package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class WikiPages.
 * @see poolingpeople.exporter.models.redmine.WikiPages
 * @author Hibernate Tools
 */
@Stateless
public class WikiPagesHome {

	private static final Log log = LogFactory.getLog(WikiPagesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(WikiPages transientInstance) {
		log.debug("persisting WikiPages instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(WikiPages persistentInstance) {
		log.debug("removing WikiPages instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public WikiPages merge(WikiPages detachedInstance) {
		log.debug("merging WikiPages instance");
		try {
			WikiPages result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public WikiPages findById(Integer id) {
		log.debug("getting WikiPages instance with id: " + id);
		try {
			WikiPages instance = entityManager.find(WikiPages.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
