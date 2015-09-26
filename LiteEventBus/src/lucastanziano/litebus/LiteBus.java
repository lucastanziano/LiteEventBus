package lucastanziano.litebus;

public interface LiteBus<T> {
	
	public void subscribe(Object obj);
		
	public void publish(T data);

}
