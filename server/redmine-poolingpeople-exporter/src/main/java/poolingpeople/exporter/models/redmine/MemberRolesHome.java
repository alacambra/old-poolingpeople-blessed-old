package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class MemberRoles.
 * @see poolingpeople.exporter.models.redmine.MemberRoles
 * @author Hibernate Tools
 */
@Stateless
public class MemberRolesHome {

	private static final Log log = LogFactory.getLog(MemberRolesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(MemberRoles transientInstance) {
		log.debug("persisting MemberRoles instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MemberRoles persistentInstance) {
		log.debug("removing MemberRoles instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MemberRoles merge(MemberRoles detachedInstance) {
		log.debug("merging MemberRoles instance");
		try {
			MemberRoles result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MemberRoles findById(Integer id) {
		log.debug("getting MemberRoles instance with id: " + id);
		try {
			MemberRoles instance = entityManager.find(MemberRoles.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
