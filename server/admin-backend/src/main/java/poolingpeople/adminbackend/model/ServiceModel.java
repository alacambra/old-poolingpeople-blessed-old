package poolingpeople.adminbackend.model;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import poolingpeople.commons.entities.EntityFactory;
import poolingpeople.commons.entities.Service;
import poolingpeople.commons.entities.ServiceDTO;
import poolingpeople.persistence.neo4j.Neo4jTransaction;

@Model
@Neo4jTransaction
public class ServiceModel {

	@Inject private EntityFactory entityFactory;
	private ServiceDTO service = new ServiceDTO();
	public ServiceDTO getService() {
		return service;
	}
	public void setService(ServiceDTO service) {
		this.service = service;
	}
	
	
}
