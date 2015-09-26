package lucastanziano.litebus.errors;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;

public interface IPublishErrorHandler {
	
	public void handleException(Exception e);

	public void handleEventOnClearedObject(WeakReference<Object> reference);
	

	


}
