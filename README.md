# LiteEventBus
=========
An essential and ultra light event bus library for Java based on the publish subscribe pattern.
Little and middle size projects don't often require complex-featurefull event buses but they do require basic message passing features for event handling.
LiteEventBus is a very small library (currently less than 200 lines of code spread in 4 classes) but will give you: handlers declaration through 

> Generic type for messages
There is no fixed type for messages. Declare `LiteBus<MyType> bus = LiteBusFactory.newWeakReference()` to get the bus to publish MyType messages.

> Easy subscription
Use `liteBus.subscribe(myObjectInstance)` to subscribe the object to the bus.

> Handler declaration through annotations
Use `@EventHandler` to declare which method will handle published messages from a subscribed class

> Strong or weak reference
Use the LiteBusFactory methods `newStrongReferenceBus()` or `newWeakReferenceBus()` to instantiate a bus with the type of reference that best suit your use case.

> Custom error handling
You can implements your error handler that can be added to the bus when necessary.

> Easy to debug and customize
Because the source code is very simple and short it is very easy to debug

> Asynchronous message handling
Fire and forget is currently the only policy for message publication (no synchronous message handling yet).



<h2>Usage</h2>


### Message bus creation

        // Declare as many bus instances as you want (typically inside a Singleton class)
        LiteBus<MyMessage> bus = LiteBusFactory.newWeakReference();
        LiteBus<MyMessage2> bus2 = LiteBusFactory.newStrongReference();
       
        // Add your custom error handler
		LiteBus<MyMessage> bus = LiteBusFactory.newWeakReference(new IPublishErrorHandler() {
		
				@Override
				public void handleException(Exception arg0) {
					// this will catch an exception occurred publishing an event 					
				}
				
				@Override
				public void handleEventOnClearedObject(WeakReference<Object> arg0) {
					// this will catch the tentative to fire an event on a disposed object that was weak referenced 				
				}
			});
        
			 
### Subscription
			 
        public MyClass(){
		   LiteBusContext.INSTANCE.getBus().subscribe(this);		
		}
		
		// every message of type MyMessage or any subtype will be delivered
        // to this handler
        @EventHandler
		public void handleMyMessage(MyMessage message) {
			// do something
		}

		

### Message publication

        MyMessage message = new MyMessage();
		LiteBusContext.INSTANCE.getBus().publish(message);
		


<h2>Installation</h2>
Add LiteEventBus-x.x.jar to your Java build path.




<h2>License</h2>

This project is distributed under the terms of the GNU General Public License. See file "LICENSE" for further reference.





