package poolingpeople.exporter.models.redmine;

// Generated Feb 17, 2014 1:20:17 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Workflows.
 * @see poolingpeople.exporter.models.redmine.Workflows
 * @author Hibernate Tools
 */
@Stateless
public class WorkflowsHome {

	private static final Log log = LogFactory.getLog(WorkflowsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Workflows transientInstance) {
		log.debug("persisting Workflows instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Workflows persistentInstance) {
		log.debug("removing Workflows instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Workflows merge(Workflows detachedInstance) {
		log.debug("merging Workflows instance");
		try {
			Workflows result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Workflows findById(Integer id) {
		log.debug("getting Workflows instance with id: " + id);
		try {
			Workflows instance = entityManager.find(Workflows.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
