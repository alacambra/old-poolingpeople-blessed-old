package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Members.
 * @see poolingpeople.exporter.models.redmine.Members
 * @author Hibernate Tools
 */
@Stateless
public class MembersHome {

	private static final Log log = LogFactory.getLog(MembersHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Members transientInstance) {
		log.debug("persisting Members instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Members persistentInstance) {
		log.debug("removing Members instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Members merge(Members detachedInstance) {
		log.debug("merging Members instance");
		try {
			Members result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Members findById(Integer id) {
		log.debug("getting Members instance with id: " + id);
		try {
			Members instance = entityManager.find(Members.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
