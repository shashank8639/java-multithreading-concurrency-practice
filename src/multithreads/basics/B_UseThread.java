package multithreads.basics;

class MyThread extends Thread {

	@Override
	public void run() {
		
		for(int i=0; i < 4; i++)
			System.out.println("Hello...");
	}
}

/*
 * This is a simple example of Thread creation styles and applying with
 * Lambda expression just to differentiate between them and also use of join
 * method.
 */
public class B_UseThread {

	public static void main(String[] args) throws InterruptedException {

		
		MyThread m1 = new MyThread();
		
		
		Thread emp = new Thread(() -> {
			System.out.println("Emp thread");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(" emp stop");
		});
		
		Thread manager = new Thread(() -> {
			System.out.println("Manager thread");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("manager stop");
		});
		
		Thread ceo = new Thread(() -> {
			System.out.println("CEO thread");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("CEO stop");
		});
		
		
		m1.start();
		emp.start();
		
		/*
		 * join(): Belongs to Thread class. Makes the current thread (main or m1) wait 
		 * until the specified thread (emp) completes its execution.
		 */
		emp.join();
		manager.start();
		manager.join();
		ceo.start();
		ceo.join();
		
		System.out.println("main thread completed");
		
	}

}

