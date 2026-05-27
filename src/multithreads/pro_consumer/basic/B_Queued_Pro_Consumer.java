package multithreads.pro_consumer.basic;

import java.util.LinkedList;
import java.util.Queue;

public class B_Queued_Pro_Consumer {

	public static final Queue<Integer> queue = new LinkedList<Integer>();
	public static final int capacity = 5;
	public static final Object lock = new Object();
	
	public static void main(String[] args) {

		Thread producer = new Thread(() -> {
			int value = 0;
			while(true) {
				synchronized (lock) {
					//if queue is full reminding the consumer
					while(queue.size()==capacity) {
						System.out.println("Producer is wasting queue is full");
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("===producer left==");
					}
					
					System.out.println("Producer value: "+value);
					queue.add(value++);
					lock.notify(); //notifying the consumer about updated value
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("===producer continues====");
				}
			}
		});
		
		Thread consumer = new Thread(() -> {
			while(true) {
				synchronized (lock) {
					while(queue.isEmpty()) {
						System.out.println("Queue is empty, consumer is waiting...");
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					int value = queue.poll();
					System.out.println("consumed value: "+value);
					lock.notify(); //notifying the producer thread data is consumed
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("=consumer started===========");
				}
			}
		});
		
		producer.start();
		consumer.start();
		
		try {
			Thread.sleep(10000);
			System.exit(0);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
	}

}

