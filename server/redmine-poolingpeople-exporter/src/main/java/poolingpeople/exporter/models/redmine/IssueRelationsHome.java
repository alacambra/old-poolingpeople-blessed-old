package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class IssueRelations.
 * @see poolingpeople.exporter.models.redmine.IssueRelations
 * @author Hibernate Tools
 */
@Stateless
public class IssueRelationsHome {

	private static final Log log = LogFactory.getLog(IssueRelationsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(IssueRelations transientInstance) {
		log.debug("persisting IssueRelations instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(IssueRelations persistentInstance) {
		log.debug("removing IssueRelations instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public IssueRelations merge(IssueRelations detachedInstance) {
		log.debug("merging IssueRelations instance");
		try {
			IssueRelations result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public IssueRelations findById(Integer id) {
		log.debug("getting IssueRelations instance with id: " + id);
		try {
			IssueRelations instance = entityManager.find(IssueRelations.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
