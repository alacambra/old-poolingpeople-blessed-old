package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Settings.
 * @see poolingpeople.exporter.models.redmine.Settings
 * @author Hibernate Tools
 */
@Stateless
public class SettingsHome {

	private static final Log log = LogFactory.getLog(SettingsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Settings transientInstance) {
		log.debug("persisting Settings instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Settings persistentInstance) {
		log.debug("removing Settings instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Settings merge(Settings detachedInstance) {
		log.debug("merging Settings instance");
		try {
			Settings result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Settings findById(Integer id) {
		log.debug("getting Settings instance with id: " + id);
		try {
			Settings instance = entityManager.find(Settings.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
