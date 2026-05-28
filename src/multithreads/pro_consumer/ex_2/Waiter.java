package multithreads.pro_consumer.ex_2;

public class Waiter implements Runnable{

	private TaskHandler taskHandler;
	
	public Waiter(TaskHandler taskHandler) {
		this.taskHandler = taskHandler;
	}

	@Override
	public void run() {

		while (!taskHandler.taskCompleted) {
			taskHandler.consume();
		}
	}

}
