package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Wikis.
 * @see poolingpeople.exporter.models.redmine.Wikis
 * @author Hibernate Tools
 */
@Stateless
public class WikisHome {

	private static final Log log = LogFactory.getLog(WikisHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Wikis transientInstance) {
		log.debug("persisting Wikis instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Wikis persistentInstance) {
		log.debug("removing Wikis instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Wikis merge(Wikis detachedInstance) {
		log.debug("merging Wikis instance");
		try {
			Wikis result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Wikis findById(Integer id) {
		log.debug("getting Wikis instance with id: " + id);
		try {
			Wikis instance = entityManager.find(Wikis.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
