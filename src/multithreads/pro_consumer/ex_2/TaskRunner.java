package multithreads.pro_consumer.ex_2;

public class TaskRunner implements Runnable {

	private TaskHandler handler;
	
	public TaskRunner(TaskHandler handler) {
		super();
		this.handler = handler;
	}

	@Override
	public void run() {
		while(!handler.taskCompleted) {
			handler.produce();
		}
		
	}

}
