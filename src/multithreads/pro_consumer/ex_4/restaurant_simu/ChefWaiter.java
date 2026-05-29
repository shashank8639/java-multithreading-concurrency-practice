package multithreads.pro_consumer.ex_4.restaurant_simu;

import java.util.LinkedList;
import java.util.Queue;

public class ChefWaiter {

	private static final int KITCHEN_CAPACITY = 3;
	private static Queue<String> orderCounter = new LinkedList<>();
	private static volatile boolean isRestaurantOpen = false;
	private static Object lock = new Object();
	private static final String[] dishes = {"Aloo mattar", "Panneer kichdi", "Moon kichdi", "Poha tikka"};
	
	public static void main(String[] args) {

		Thread chef1 = new Thread(new Chef("CHef-A"), "CHEF-A");
		Thread chef2 = new Thread(new Chef("CHef-B"), "CHEF-B");
		
		Thread waiter1 = new Thread(new Waiter("Waiter-A"), "WAITER-A");
		Thread waiter2 = new Thread(new Waiter("Waiter-B"), "WAITER-B");
		
		isRestaurantOpen = true;
		chef1.start();
		chef2.start();
		waiter1.start();
		waiter2.start();
		
		try {
			Thread.sleep(10000);
			synchronized (lock) {
				isRestaurantOpen = false;
				lock.notifyAll();
			}
			
			chef1.join();
			chef2.join();
			waiter1.join();
			waiter2.join();
			
			System.out.println("All the tasks are done Chef and waiters");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static class Chef implements Runnable {

		String name;
	
		public Chef(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			int dishCount =1;
			while(isRestaurantOpen) {
				synchronized (lock) {
					
					while (isRestaurantOpen && orderCounter.size()==KITCHEN_CAPACITY) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					if(!isRestaurantOpen) break;
					
					String dish = "Dish: "+dishes[dishCount%KITCHEN_CAPACITY]+" - "+dishCount++;
					orderCounter.add(dish);
					System.out.println("======="+dish+" cooked by "+name);
					
					lock.notifyAll();
				}
				
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(name+" had left the Kitchen");
		}
		
	}
	
	static class Waiter implements Runnable{
		String name;

		public Waiter(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			//Waiter should keep working until Restaurant is closed and when OrderCounter is Empty
			while(isRestaurantOpen || !orderCounter.isEmpty()) {
				synchronized (lock) {
					while(isRestaurantOpen && orderCounter.isEmpty()) {
						
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					if(orderCounter.isEmpty() && !isRestaurantOpen) break;
					
					String dish = orderCounter.poll();
					System.out.println(dish+" served by "+name);
					
					lock.notifyAll();
				}
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				
			System.out.println(name+" completed their shif");
		}
	}

}
/*
 * volatile keyword: Added to isRestaurantOpen to ensure all threads see the
 * change immediately when the main thread updates it.
 * 
 * Wait Logic: Inside the while(wait) blocks, I added isRestaurantOpen as a
 * condition. This prevents threads from sleeping forever if the notifyAll()
 * happens after the restaurant closes.
 * 
 * The "Drain" Logic: In the Waiter class, I changed the loop to while
 * (isRestaurantOpen || !orderCounter.isEmpty()). This ensures that if there are
 * dishes left on the counter when the restaurant closes, the waiters finish
 * serving them before exiting.
 * 
 * thread.join(): This makes the main thread wait for the chefs and waiters to
 * actually finish their run() methods before printing "Main thread has ended."
 */
