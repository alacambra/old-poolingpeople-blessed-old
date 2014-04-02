package poolingpeople.webapplication.business.entity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import poolingpeople.commons.helper.Pager;

@ApplicationScoped
@Alternative
public class PagerTestImpl implements Pager {

	private Integer start = 0;
	private Integer size = 15;
	
	public PagerTestImpl() {
	}

	@Override
	public Pager setStart(Integer start) {
		this.start = start;
		return this;
	}
	
	@Override
	public Pager setSize(Integer size) {
		this.size = size;
		return this;
	}
	
	@Override
	public Integer getStart() {
		return start;
	}
	
	@Override
	public Integer getSize() {
		return size;
	}
	
	@Override
	public String toString() {
		return "start:" + start + ", size:" + size;
	}
}
