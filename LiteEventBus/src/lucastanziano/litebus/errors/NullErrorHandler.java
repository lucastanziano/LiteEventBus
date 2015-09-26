package lucastanziano.litebus.errors;

import java.lang.ref.WeakReference;

public enum NullErrorHandler implements IPublishErrorHandler {

	INSTANCE;

	@Override
	public void handleException(Exception e) {
		// do nothing

	}

	@Override
	public void handleEventOnClearedObject(WeakReference<Object> reference) {
		// do nothing
		
	}

}
