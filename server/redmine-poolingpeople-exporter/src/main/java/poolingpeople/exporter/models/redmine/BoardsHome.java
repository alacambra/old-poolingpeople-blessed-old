package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Boards.
 * @see poolingpeople.exporter.models.redmine.Boards
 * @author Hibernate Tools
 */
@Stateless
public class BoardsHome {

	private static final Log log = LogFactory.getLog(BoardsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Boards transientInstance) {
		log.debug("persisting Boards instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Boards persistentInstance) {
		log.debug("removing Boards instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Boards merge(Boards detachedInstance) {
		log.debug("merging Boards instance");
		try {
			Boards result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Boards findById(Integer id) {
		log.debug("getting Boards instance with id: " + id);
		try {
			Boards instance = entityManager.find(Boards.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
