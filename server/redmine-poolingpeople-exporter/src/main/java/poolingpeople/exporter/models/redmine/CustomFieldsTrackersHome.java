package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CustomFieldsTrackers.
 * @see poolingpeople.exporter.models.redmine.CustomFieldsTrackers
 * @author Hibernate Tools
 */
@Stateless
public class CustomFieldsTrackersHome {

	private static final Log log = LogFactory
			.getLog(CustomFieldsTrackersHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CustomFieldsTrackers transientInstance) {
		log.debug("persisting CustomFieldsTrackers instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CustomFieldsTrackers persistentInstance) {
		log.debug("removing CustomFieldsTrackers instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CustomFieldsTrackers merge(CustomFieldsTrackers detachedInstance) {
		log.debug("merging CustomFieldsTrackers instance");
		try {
			CustomFieldsTrackers result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CustomFieldsTrackers findById(CustomFieldsTrackersId id) {
		log.debug("getting CustomFieldsTrackers instance with id: " + id);
		try {
			CustomFieldsTrackers instance = entityManager.find(
					CustomFieldsTrackers.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
