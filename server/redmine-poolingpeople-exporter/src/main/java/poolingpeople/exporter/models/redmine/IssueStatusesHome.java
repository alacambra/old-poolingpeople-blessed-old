package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class IssueStatuses.
 * @see poolingpeople.exporter.models.redmine.IssueStatuses
 * @author Hibernate Tools
 */
@Stateless
public class IssueStatusesHome {

	private static final Log log = LogFactory.getLog(IssueStatusesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(IssueStatuses transientInstance) {
		log.debug("persisting IssueStatuses instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(IssueStatuses persistentInstance) {
		log.debug("removing IssueStatuses instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public IssueStatuses merge(IssueStatuses detachedInstance) {
		log.debug("merging IssueStatuses instance");
		try {
			IssueStatuses result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public IssueStatuses findById(Integer id) {
		log.debug("getting IssueStatuses instance with id: " + id);
		try {
			IssueStatuses instance = entityManager
					.find(IssueStatuses.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
