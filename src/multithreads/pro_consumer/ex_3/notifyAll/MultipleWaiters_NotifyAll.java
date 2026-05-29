package multithreads.pro_consumer.ex_3.notifyAll;

public class MultipleWaiters_NotifyAll {

	private static Object lock = new Object();
	private static boolean eventOccured = false;
	
	public static void main(String[] args) {

		for(int i=1; i <= 5; i++) {
			final int nThread = i;
			Thread waiter = new Thread(() -> {
				synchronized (lock) {
					System.out.println("Thread no: "+nThread+" is waiting...");
					
					while(!eventOccured) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("Thread no: "+nThread+" received notification");
				}
				
			});
			
			waiter.start();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread notifier = new Thread(() -> {
			synchronized (lock) {
				System.out.println("\nNotifier event to be happened....");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				eventOccured = true;
				lock.notifyAll();
				System.out.println("Notify to All waiting threads");
			}
		});
		
		notifier.start();
	}

}

