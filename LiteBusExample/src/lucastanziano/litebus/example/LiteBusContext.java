package lucastanziano.litebus.example;

import lucastanziano.litebus.LiteBus;
import lucastanziano.litebus.LiteBusFactory;

public enum LiteBusContext {
	
	INSTANCE;
	
	private  LiteBus<String> bus = LiteBusFactory.newWeakReferenceBus();
	  
	  public LiteBus<String> getBus(){

		  return bus;
	  }
	  
	  

}
