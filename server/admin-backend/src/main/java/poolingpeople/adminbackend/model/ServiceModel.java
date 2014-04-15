package poolingpeople.adminbackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import poolingpeople.commons.entities.EntityFactory;
import poolingpeople.commons.entities.Service;
import poolingpeople.commons.entities.ServiceDTO;
import poolingpeople.persistence.neo4j.Neo4jTransaction;

@Model
@Neo4jTransaction
public class ServiceModel {

	@Inject
	private EntityFactory entityFactory;
	private ServiceDTO service = new ServiceDTO();

	public ServiceDTO getService() {
		return service;
	}

	public void setService(ServiceDTO service) {
		this.service = service;
	}

	public void createService() {
		entityFactory.createService(service);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Service created",
						""));
	}

	public List<ServiceDTO> getAllServices() {
		List<ServiceDTO> services = new ArrayList<>();

		int	pageSize = 50;
		for (final Service service : entityFactory.getAllServices(pageSize)) {
			services.add(new ServiceDTO() {
				{
					setId(service.getId());
					setTitle(service.getTitle());
					setActiveStatus(service.getActiveStatus());
					setDescription(service.getDescription());
				}
			});
		}

		return services;
	}

	public void getServiceById() {
		final Service serviceById = entityFactory.getServiceById(service
				.getId());
		service = new ServiceDTO() {
			{
				setId(serviceById.getId());
				setTitle(serviceById.getTitle());
				setDescription(serviceById.getDescription());
				setActiveStatus(serviceById.getActiveStatus());
			}
		};
	}
	
	public void updateService() {
		synchronizeWithPersistedService();
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Service updated", ""));
	}

	private void synchronizeWithPersistedService() {
		Service serviceById = entityFactory.getServiceById(service.getId());
		serviceById.setActiveStatus(service.getActiveStatus());
		serviceById.setDescription(service.getDescription());
		serviceById.setTitle(service.getTitle());
	}

}
