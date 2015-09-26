package lucastanziano.litebus.example;

import lucastanziano.litebus.annotations.EventHandler;

public class Dog {
	
	public static final String BARK_EVENT = "bark_event";
	
	private final String name;
	
	public Dog(String name){
		this.name = name;
		LiteBusContext.INSTANCE.getBus().subscribe(this);
	}
	
	public String getName(){
		return this.name;
	}

	@EventHandler
	public void bark(String event) {
		if (event.equals(MailMan.DELIVERY_EVENT)) {
			System.out.println(getName() + ": bow-wow");
			LiteBusContext.INSTANCE.getBus().publish(BARK_EVENT);
		}
	}

}
