package lucastanziano.litebus.example;

public class Main {
	
	private final static int REPEAT_EVENTS = 5;

	public static void main(String[] args) {
		Dog snoopy = new Dog("Snoopy");
		Dog pluto = new Dog("Pluto");
		MailMan evilMailMan = new MailMan();
		
		
		for(int counter = 0; counter<REPEAT_EVENTS ; counter++){
			try {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						evilMailMan.deliverMail();
						
					}
				}).start();
				
				Thread.sleep(2000);
			} catch (InterruptedException e) {
                  System.out.println("Program interrupted!");
			}
		}
		

	}

}
