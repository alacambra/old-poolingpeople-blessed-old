package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class IssueCategories.
 * @see poolingpeople.exporter.models.redmine.IssueCategories
 * @author Hibernate Tools
 */
@Stateless
public class IssueCategoriesHome {

	private static final Log log = LogFactory.getLog(IssueCategoriesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(IssueCategories transientInstance) {
		log.debug("persisting IssueCategories instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(IssueCategories persistentInstance) {
		log.debug("removing IssueCategories instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public IssueCategories merge(IssueCategories detachedInstance) {
		log.debug("merging IssueCategories instance");
		try {
			IssueCategories result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public IssueCategories findById(Integer id) {
		log.debug("getting IssueCategories instance with id: " + id);
		try {
			IssueCategories instance = entityManager.find(
					IssueCategories.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
