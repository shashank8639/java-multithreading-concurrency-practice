package multithreads.pro_consumer.basic_refactored;

public class Consumer implements Runnable{

	private final SharedBuffer buffer;
	
	public Consumer(SharedBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		try {
			while(true)  {
				buffer.consume();
				Thread.sleep(1000);
			}
		} catch(InterruptedException i) {
			
			Thread.currentThread().interrupt();
		}
	}

}

