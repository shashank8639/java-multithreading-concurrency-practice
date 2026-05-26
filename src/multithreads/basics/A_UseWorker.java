package multithreads.basics;

class Worker extends Thread {
	@Override
	public void run() {
		
		for(int i=0; i < 4; i++)
			System.out.println("Hello...");
	}
}
class Owner extends Thread {
	@Override
	public void run() {
		
		for(int i=0; i < 4; i++)
			System.out.println("owner runs...");
	}
}
public class A_UseWorker {

	public static void main(String[] args) {

		System.out.println("=========");
		System.out.println("===Main Thread====");
		/*
		 * Simple Worker and Owner classes extends Thread class for demonstration of
		 * multithreading and how Threads are executed by ThreadScheduler
		 */
		Worker w1 = new Worker();
		
		w1.start();
		
		
		Owner o1 = new Owner();
		o1.start();
	}

}

