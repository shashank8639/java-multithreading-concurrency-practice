package multithreads.pro_consumer.ex_2;

public class TaskHandler {

	boolean taskCompleted = false;
	
	public void produce() {
		try {
			System.out.println("Task is started");
			Thread.sleep(1500);
			
			synchronized (this) {
				System.out.println("lock is received to TaskManager");
				taskCompleted = true;
				this.notify();
			}
		} catch (InterruptedException e) {
		}
	}
	
	public synchronized void consume() {
		long starttime = System.currentTimeMillis();
		while(!taskCompleted) {
			
			System.out.println("waiter is waiting for task to be completed");
			try {
				wait(2000); //waits for 3 secs get awaken 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long totalTime = System.currentTimeMillis()-starttime;
		
		System.out.println("waiter received the lock");
		if(taskCompleted)
			System.out.println("The total waiting time: "+totalTime+" done..");
		else
			System.out.println("The task not completed in time.");
	}
}

