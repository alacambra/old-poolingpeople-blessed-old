package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CustomFieldsProjects.
 * @see poolingpeople.exporter.models.redmine.CustomFieldsProjects
 * @author Hibernate Tools
 */
@Stateless
public class CustomFieldsProjectsHome {

	private static final Log log = LogFactory
			.getLog(CustomFieldsProjectsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CustomFieldsProjects transientInstance) {
		log.debug("persisting CustomFieldsProjects instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CustomFieldsProjects persistentInstance) {
		log.debug("removing CustomFieldsProjects instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CustomFieldsProjects merge(CustomFieldsProjects detachedInstance) {
		log.debug("merging CustomFieldsProjects instance");
		try {
			CustomFieldsProjects result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CustomFieldsProjects findById(CustomFieldsProjectsId id) {
		log.debug("getting CustomFieldsProjects instance with id: " + id);
		try {
			CustomFieldsProjects instance = entityManager.find(
					CustomFieldsProjects.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
