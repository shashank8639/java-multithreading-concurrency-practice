package multithreads.pro_consumer.basic_refactored;

import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer {

	private final Queue<Integer> queue = new LinkedList<>();
	private final int capacity;
	
	public SharedBuffer(int capacity) {
		this.capacity = capacity;
	}

	//sychronized block which is used to fill the data
	public synchronized void produce(int value) throws InterruptedException {
		
		//while loop to check until que is full and check spurious wakeups
		while(queue.size()>=capacity) {
			System.out.println("Queue is full, Producer is waiting");
			wait();
		}
		
		queue.add(value);
		System.out.println("Produced: "+value);
		
		notify(); //notifies to waiting thread
	}
	
	//synchronized block : consumer consumes the data
	public synchronized int consume() throws InterruptedException {
		
		//to check if que is empty call wait
		while(queue.isEmpty()) {
			System.out.println("Consumer looking for data, waiting....");
			wait();
		}
		
		int data = queue.poll();
		System.out.println("Consumer: "+data);
		
		//Notify the producer...
		notify();
		return data;
	}
}

