package lucastanziano.litebus;

import lucastanziano.litebus.errors.IPublishErrorHandler;

public class LiteBusFactory {

	private LiteBusFactory() {

	}

	public static <T> LiteBus<T> newWeakReferenceBus() {
		return new WeakReferenceLiteBus<T>();
	}
	
	
	public static <T> LiteBus<T> newWeakReferenceBus(IPublishErrorHandler errorHandler) {
		return new WeakReferenceLiteBus<T>(errorHandler);
	}
	
	public static <T> LiteBus<T> newStrongReferenceBus() {
		return new StrongReferenceLiteBus<T>();
	}

	public static <T> LiteBus<T> newStrongReferenceBus(IPublishErrorHandler errorHandler) {
		return new StrongReferenceLiteBus<T>(errorHandler);
	}

	
	



	
}
