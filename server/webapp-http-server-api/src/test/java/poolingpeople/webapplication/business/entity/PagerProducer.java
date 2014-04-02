package poolingpeople.webapplication.business.entity;

import javax.enterprise.inject.Produces;

import poolingpeople.commons.helper.Pager;

public class PagerProducer {
	
	@Produces
	public Pager getPager(){
		return new PagerTestImpl();
	}
}
