package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class SchemaMigrations.
 * @see poolingpeople.exporter.models.redmine.SchemaMigrations
 * @author Hibernate Tools
 */
@Stateless
public class SchemaMigrationsHome {

	private static final Log log = LogFactory
			.getLog(SchemaMigrationsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(SchemaMigrations transientInstance) {
		log.debug("persisting SchemaMigrations instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(SchemaMigrations persistentInstance) {
		log.debug("removing SchemaMigrations instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SchemaMigrations merge(SchemaMigrations detachedInstance) {
		log.debug("merging SchemaMigrations instance");
		try {
			SchemaMigrations result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SchemaMigrations findById(String id) {
		log.debug("getting SchemaMigrations instance with id: " + id);
		try {
			SchemaMigrations instance = entityManager.find(
					SchemaMigrations.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
