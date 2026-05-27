package multithreads.pro_consumer.basic;

public class A_ProducerConsumer {

//	The object acts as a monitor lock where threads need lock to run sync block
	static final Object lock = new Object();
	static boolean dataReady = false;
	
	public static void main(String[] args) {

		Thread producer = new Thread(() -> {
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
			}
			/*
			 * The producer thread after receiving the lock it updates the flag and load the
			 * data so consumer pulls the data and then notifies to waiting thread
			 */
			synchronized (lock) {
				dataReady = true;
				System.out.println("Producer loaded the data");
				lock.notify(); //The thread notifies to waiting thread
			}
		});
		
		Thread consumer = new Thread(() -> {
			synchronized (lock) {
				
				//consumer waits for data to be loaded by producer
				while(!dataReady) {
					/*
					 * The flag dataReady becomes true when producer thread is started Producer
					 * updates the flag by loading the data here for wait is used inside while loop
					 * because a sleep thread can be awaken spuriously so while is recommended if
					 * shouldn't be used at all
					 */	 
					System.out.println("Consumer is waiting for data");
					
					try {
						lock.wait(); //The thread releases the lock
					} catch (InterruptedException e) {
					}
				}
				
				System.out.println("Consumer received the data");
			}
		});
		
		producer.start();
		consumer.start();
		
		try {
			consumer.join();
		} catch (InterruptedException e) {
		}
		
		System.out.println("Main is ended");
	}

}
