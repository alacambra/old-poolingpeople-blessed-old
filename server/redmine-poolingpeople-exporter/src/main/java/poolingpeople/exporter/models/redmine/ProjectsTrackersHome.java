package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ProjectsTrackers.
 * @see poolingpeople.exporter.models.redmine.ProjectsTrackers
 * @author Hibernate Tools
 */
@Stateless
public class ProjectsTrackersHome {

	private static final Log log = LogFactory
			.getLog(ProjectsTrackersHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ProjectsTrackers transientInstance) {
		log.debug("persisting ProjectsTrackers instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ProjectsTrackers persistentInstance) {
		log.debug("removing ProjectsTrackers instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ProjectsTrackers merge(ProjectsTrackers detachedInstance) {
		log.debug("merging ProjectsTrackers instance");
		try {
			ProjectsTrackers result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProjectsTrackers findById(ProjectsTrackersId id) {
		log.debug("getting ProjectsTrackers instance with id: " + id);
		try {
			ProjectsTrackers instance = entityManager.find(
					ProjectsTrackers.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
