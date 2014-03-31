package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class WikiContentVersions.
 * @see poolingpeople.exporter.models.redmine.WikiContentVersions
 * @author Hibernate Tools
 */
@Stateless
public class WikiContentVersionsHome {

	private static final Log log = LogFactory
			.getLog(WikiContentVersionsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(WikiContentVersions transientInstance) {
		log.debug("persisting WikiContentVersions instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(WikiContentVersions persistentInstance) {
		log.debug("removing WikiContentVersions instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public WikiContentVersions merge(WikiContentVersions detachedInstance) {
		log.debug("merging WikiContentVersions instance");
		try {
			WikiContentVersions result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public WikiContentVersions findById(Integer id) {
		log.debug("getting WikiContentVersions instance with id: " + id);
		try {
			WikiContentVersions instance = entityManager.find(
					WikiContentVersions.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
