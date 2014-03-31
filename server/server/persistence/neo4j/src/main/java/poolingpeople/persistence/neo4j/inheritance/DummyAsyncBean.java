package poolingpeople.persistence.neo4j.inheritance;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

@Stateless
public class DummyAsyncBean {
	
	Logger logger = Logger.getLogger("DummyAsyncBean");
	
	@Asynchronous
	public void asyncMethod() throws InterruptedException{

		for(int i = 0; i<20; i++){
			Thread.sleep(1000);
			logger.info(i);
		}
	}
}
