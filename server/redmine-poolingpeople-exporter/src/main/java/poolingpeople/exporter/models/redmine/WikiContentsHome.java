package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class WikiContents.
 * @see poolingpeople.exporter.models.redmine.WikiContents
 * @author Hibernate Tools
 */
@Stateless
public class WikiContentsHome {

	private static final Log log = LogFactory.getLog(WikiContentsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(WikiContents transientInstance) {
		log.debug("persisting WikiContents instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(WikiContents persistentInstance) {
		log.debug("removing WikiContents instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public WikiContents merge(WikiContents detachedInstance) {
		log.debug("merging WikiContents instance");
		try {
			WikiContents result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public WikiContents findById(Integer id) {
		log.debug("getting WikiContents instance with id: " + id);
		try {
			WikiContents instance = entityManager.find(WikiContents.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
