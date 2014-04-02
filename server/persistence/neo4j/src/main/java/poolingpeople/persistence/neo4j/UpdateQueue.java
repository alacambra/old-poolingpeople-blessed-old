package poolingpeople.persistence.neo4j;

import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;

import poolingpeople.persistence.neo4j.entities.AbstractPersistedModel;

@ApplicationScoped
public class UpdateQueue {

	ArrayList<AbstractPersistedModel<?>> abstractPersistedModels = new ArrayList<>();
	
	public void addModelToUpdate(AbstractPersistedModel<?> abstractPersistedModel){
		if (!abstractPersistedModels.contains(abstractPersistedModel)){
			abstractPersistedModels.add(abstractPersistedModel);
		}
	}
	
	public void executeUpdates(){
		while(!abstractPersistedModels.isEmpty()){
			abstractPersistedModels.get(0).updateAll();
			abstractPersistedModels.remove(0);
		}
	}
}
