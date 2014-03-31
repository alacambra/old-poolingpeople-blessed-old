package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ChangesetsIssues.
 * @see poolingpeople.exporter.models.redmine.ChangesetsIssues
 * @author Hibernate Tools
 */
@Stateless
public class ChangesetsIssuesHome {

	private static final Log log = LogFactory
			.getLog(ChangesetsIssuesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ChangesetsIssues transientInstance) {
		log.debug("persisting ChangesetsIssues instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ChangesetsIssues persistentInstance) {
		log.debug("removing ChangesetsIssues instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ChangesetsIssues merge(ChangesetsIssues detachedInstance) {
		log.debug("merging ChangesetsIssues instance");
		try {
			ChangesetsIssues result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ChangesetsIssues findById(ChangesetsIssuesId id) {
		log.debug("getting ChangesetsIssues instance with id: " + id);
		try {
			ChangesetsIssues instance = entityManager.find(
					ChangesetsIssues.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
