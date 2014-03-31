package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Attachments.
 * @see poolingpeople.exporter.models.redmine.Attachments
 * @author Hibernate Tools
 */
@Stateless
public class AttachmentsHome {

	private static final Log log = LogFactory.getLog(AttachmentsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Attachments transientInstance) {
		log.debug("persisting Attachments instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Attachments persistentInstance) {
		log.debug("removing Attachments instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Attachments merge(Attachments detachedInstance) {
		log.debug("merging Attachments instance");
		try {
			Attachments result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Attachments findById(Integer id) {
		log.debug("getting Attachments instance with id: " + id);
		try {
			Attachments instance = entityManager.find(Attachments.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
