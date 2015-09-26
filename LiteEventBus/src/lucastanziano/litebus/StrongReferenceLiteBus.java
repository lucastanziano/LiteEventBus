package lucastanziano.litebus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import lucastanziano.litebus.errors.IPublishErrorHandler;
import lucastanziano.litebus.errors.NullErrorHandler;

class StrongReferenceLiteBus<T> implements LiteBus<T> {

	private ConcurrentHashMap<Object, List<Method>> listenerMethodsMap = new ConcurrentHashMap<Object, List<Method>>();
	private final IPublishErrorHandler errorHandler;

	StrongReferenceLiteBus() {
		this(NullErrorHandler.INSTANCE);
	}

	StrongReferenceLiteBus(IPublishErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	@Override
	public void subscribe(Object obj) {
		List<Method> handlers = LiteBusUtil.retrieveEventHandlerMethods(obj.getClass().getMethods());
		listenerMethodsMap.put(obj, handlers);
	}

	@Override
	public void publish(T data) {
		Iterator<Object> it = listenerMethodsMap.keySet().iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			try {
				LiteBusUtil.invokeTargetMethods(obj, data,  listenerMethodsMap.get(obj));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				errorHandler.handleException(e);
			}

		}
	}

}