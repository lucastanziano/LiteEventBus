package lucastanziano.litebus;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import lucastanziano.litebus.errors.IPublishErrorHandler;
import lucastanziano.litebus.errors.NullErrorHandler;

class WeakReferenceLiteBus<T> implements LiteBus<T> {

	private ConcurrentHashMap<WeakReference<Object>, List<Method>> listenerMethodsMap = new ConcurrentHashMap<WeakReference<Object>, List<Method>>();
	private final IPublishErrorHandler errorHandler;

	WeakReferenceLiteBus() {
		this(NullErrorHandler.INSTANCE);
	}

	WeakReferenceLiteBus(IPublishErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	@Override
	public void subscribe(Object obj) {
		List<Method> handlers = LiteBusUtil.retrieveEventHandlerMethods(obj.getClass().getMethods());
		listenerMethodsMap.put(new WeakReference<Object>(obj), handlers);
	}
	
	

	@Override
	public void publish(T data) {
		Iterator<WeakReference<Object>> it = listenerMethodsMap.keySet().iterator();
		while (it.hasNext()) {
			WeakReference<Object> reference = it.next();
			if(reference.get()!=null){
				try {
					LiteBusUtil.invokeTargetMethods(reference.get(), data,  listenerMethodsMap.get(reference));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					errorHandler.handleException(e);
				}
			}else{
				errorHandler.handleEventOnClearedObject(reference);
			}			

		}
	}

}