package multithreads.pro_consumer.ex_2;

public class WaiterTask_Main {

	public static void main(String[] args) {

		TaskHandler taskHandler = new TaskHandler();
		
		Thread waiter = new Thread(new Waiter(taskHandler));
		Thread manager = new Thread(new TaskRunner(taskHandler));
		
		waiter.start();
		manager.start();
		
		try {
			Thread.sleep(8000);
			System.exit(0);
			System.out.println("Completed the tasks");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
