package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class JournalDetails.
 * @see poolingpeople.exporter.models.redmine.JournalDetails
 * @author Hibernate Tools
 */
@Stateless
public class JournalDetailsHome {

	private static final Log log = LogFactory.getLog(JournalDetailsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(JournalDetails transientInstance) {
		log.debug("persisting JournalDetails instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(JournalDetails persistentInstance) {
		log.debug("removing JournalDetails instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public JournalDetails merge(JournalDetails detachedInstance) {
		log.debug("merging JournalDetails instance");
		try {
			JournalDetails result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public JournalDetails findById(Integer id) {
		log.debug("getting JournalDetails instance with id: " + id);
		try {
			JournalDetails instance = entityManager.find(JournalDetails.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
