package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ChangesetParents.
 * @see poolingpeople.exporter.models.redmine.ChangesetParents
 * @author Hibernate Tools
 */
@Stateless
public class ChangesetParentsHome {

	private static final Log log = LogFactory
			.getLog(ChangesetParentsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ChangesetParents transientInstance) {
		log.debug("persisting ChangesetParents instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ChangesetParents persistentInstance) {
		log.debug("removing ChangesetParents instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ChangesetParents merge(ChangesetParents detachedInstance) {
		log.debug("merging ChangesetParents instance");
		try {
			ChangesetParents result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ChangesetParents findById(ChangesetParentsId id) {
		log.debug("getting ChangesetParents instance with id: " + id);
		try {
			ChangesetParents instance = entityManager.find(
					ChangesetParents.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
