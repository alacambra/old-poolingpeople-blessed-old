package poolingpeople.commons.cdihelper;

public interface InstanceProvider {
	<T> T getInstance(Class<T> clazz);
}
