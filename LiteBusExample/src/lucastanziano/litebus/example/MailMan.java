package lucastanziano.litebus.example;

import lucastanziano.litebus.annotations.EventHandler;

public class MailMan {

	public final static String DELIVERY_EVENT = "delivery_event";
	
	public MailMan(){
		LiteBusContext.INSTANCE.getBus().subscribe(this);
	}
	
	public void deliverMail(){
		System.out.println("MAILMAN : Delivering mail");
		LiteBusContext.INSTANCE.getBus().publish(DELIVERY_EVENT);
	}
	
	@EventHandler
	public void runAway(String event){
		if(event.equals(Dog.BARK_EVENT)){
			System.out.println("MAILMAN : AAAAAHHHHHHH....!!!");
		}
		
	}
}
