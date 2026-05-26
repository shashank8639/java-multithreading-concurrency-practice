package multithreads.basics;

class Nums {
	private int n = 10;
	private int count = 1;

	/*
	 * synchronized: It is a keyword If it is used for method then that method
	 * requires lock of that current object and make other threads wait until the
	 * current task is completed.
	 */
	public synchronized void printEven() {
		while(count <= n) {
			if(count%2==1) {
				try {
					/*
					 * wait(): It belongs to object class can be accessed outside of Thread class
					 * and has ability to release the acquired lock.
					 */
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("even: "+count);
				count++;
				/*
				 * notify(): It belongs to Object class can be accessed outside of Thread and it
				 * just notifies only when current thread finished the task.
				 */
				this.notify();
			}
		}
	}
	
	public synchronized void printOdd() {
		while(count <= n) {
			if(count%2==0) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("odd: "+count);
				count++;
				this.notify();
			}
		}
	}
}
public class C_PrintNumsThread_synch { 

	public static void main(String[] args) {
		/*
		 * This class object is used as Shared monitor lock which consists of
		 * synchronized methods and simulate as basic producer consumer example. It acts
		 * as Shared Resource when its passed as argument to Thread class constructor.
		 */
		Nums obj = new Nums();
		
		Thread t1 = new Thread(()->obj.printOdd());
		Thread t2 = new Thread(()-> obj.printEven());
		
		t1.start();
		t2.start();
	}

}
