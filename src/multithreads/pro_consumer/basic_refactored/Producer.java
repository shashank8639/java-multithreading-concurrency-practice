package multithreads.pro_consumer.basic_refactored;

public class Producer implements Runnable{

	private final SharedBuffer buffer;
	
	public Producer(SharedBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		int value = 0;
		try {
			while(true) {
				buffer.produce(value++);
                Thread.sleep(500); // Simulate work
			}
		} catch(InterruptedException e) {
			
			Thread.currentThread().interrupt();
		}
	}

}

