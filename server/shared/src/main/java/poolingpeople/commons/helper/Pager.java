package poolingpeople.commons.helper;

public interface Pager {

	Pager setStart(Integer start);

	Pager setSize(Integer size);

	Integer getStart();

	Integer getSize();

}