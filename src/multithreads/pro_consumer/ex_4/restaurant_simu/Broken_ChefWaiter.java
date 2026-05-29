package multithreads.pro_consumer.ex_4.restaurant_simu;

import java.util.LinkedList;
import java.util.Queue;

public class Broken_ChefWaiter {

	private static final int KITCHEN_CAPACITY = 3;
	private static Queue<String> orderCounter = new LinkedList<>();
	private static Object lock = new Object();
	private static boolean isRestaurantOpen = false;
	private static String[] dishes = { "Aloo mattar", "Panneer kichdi", "Moon kichdi", "Poha tikka" };

	public static void main(String[] args) {

		Thread chef1 = new Thread(new Chef("Chef-A"), "Chef-A");
		Thread chef2 = new Thread(new Chef("Chef-B"), "Chef-B");

		Thread waiter1 = new Thread(new Waiter("Waiter-A"), "Waiter-A");
		Thread waiter2 = new Thread(new Waiter("Waiter-B"), "Waiter-B");

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
			System.out.println("Restaurant is closed and Waiter finished all orders");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Main thread has ended");
	}

	static class Chef implements Runnable {
		String name;

		public Chef(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			int dishCount = 1;
			while (isRestaurantOpen) {
				synchronized (lock) {

					while (orderCounter.size() == KITCHEN_CAPACITY) {
						System.out.println("Chef done the work");
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					String dish = dishes[dishCount % KITCHEN_CAPACITY] + "" + dishCount++;
					orderCounter.add(dish);
					System.out.println(name + " cooked " + dish);

					// NOtifies all waiting Worker threads
					lock.notifyAll();
				}
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
				}
			}
		}

	}

	static class Waiter implements Runnable {

		String name;

		public Waiter(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			while (isRestaurantOpen) {
				synchronized (lock) {

					while (orderCounter.isEmpty()) {
						System.out.println(name + " Waiter is waiting for dish");

						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					String dish = orderCounter.poll();
					System.out.println("Dish: " + dish + " ---- by " + name);

					lock.notifyAll();
				}

				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
				}
			}
		}

	}

}
