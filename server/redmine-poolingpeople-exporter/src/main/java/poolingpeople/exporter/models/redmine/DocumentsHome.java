package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Documents.
 * @see poolingpeople.exporter.models.redmine.Documents
 * @author Hibernate Tools
 */
@Stateless
public class DocumentsHome {

	private static final Log log = LogFactory.getLog(DocumentsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Documents transientInstance) {
		log.debug("persisting Documents instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Documents persistentInstance) {
		log.debug("removing Documents instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Documents merge(Documents detachedInstance) {
		log.debug("merging Documents instance");
		try {
			Documents result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Documents findById(Integer id) {
		log.debug("getting Documents instance with id: " + id);
		try {
			Documents instance = entityManager.find(Documents.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
