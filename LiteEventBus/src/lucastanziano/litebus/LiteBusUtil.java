package lucastanziano.litebus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import lucastanziano.litebus.annotations.EventHandler;

class LiteBusUtil {

	static <T> void invokeTargetMethods(Object obj, T data, List<Method> methods)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (Method target : methods) {
			for (Parameter paramType : target.getParameters()) {
				Class<?> parClass = paramType.getType();
				if (parClass.isAssignableFrom(data.getClass())) {
					target.invoke(obj, data);
				}

			}
		}
	}

	static List<Method> retrieveEventHandlerMethods(Method[] methods) {
		List<Method> handlers = new ArrayList<Method>();
		for (Method m : methods) {
			if (m.isAnnotationPresent(EventHandler.class)) {
				handlers.add(m);
			}
		}
		return handlers;
	}
}
