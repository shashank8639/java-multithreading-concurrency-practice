package multithreads.pro_consumer.basic_refactored;


public class Queued_Producer_Consumer {

	public static void main(String[] args) {
		
		SharedBuffer shared = new SharedBuffer(5);
		
		Thread producerThread = new Thread(new Producer(shared), "Producer-Thread");
		Thread consumerThread = new Thread(new Consumer(shared), "Consumer-Thread");
		
		producerThread.start();
		consumerThread.start();
		
		//let it run for 10s
		try {
			Thread.sleep(6000);
			System.out.println("Main thread waits then exits...");
			System.exit(0);
			
		}catch(InterruptedException e) {
			
		}
	}

}