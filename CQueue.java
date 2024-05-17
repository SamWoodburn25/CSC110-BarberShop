/*
 * sam woodburn
 * Fall 2023
 * CSC111- Final Project
 * Barber shop- CQueue class
 * 		array based circular queue
 */

public class CQueue {
	//variables
	private int capacity;
	private Customer[] arr;
	protected int numItems;
	private int frontIndex;
	private int backIndex;

	//constructor
	public CQueue(int capacity) {
		this.capacity = capacity;
		arr = new Customer[capacity];
		numItems = 0;
		frontIndex = -1;
		backIndex = -1;
	}

	//checks if empty/ is full
	public boolean isEmpty() {
		return numItems == 0;
	}
	public boolean isFull() {
		return numItems == capacity;
	}
	
	//display method
	public void display() {
		System.out.print("\nChairs:\n");
		if (!isEmpty()) {
			int temp = -1;
			if(backIndex >= frontIndex) {
				temp = frontIndex;
			}
			else {
				temp = backIndex;
			}
			for (int i = 0; i < numItems; i++) {
				System.out.println("\t" + arr[temp].getName() + ": arrival = " + arr[temp].getArrival() + ":  service = " + arr[temp].getService());
				temp++;
			}
			System.out.println();
		}
		if (isEmpty()) {
			System.out.println(" - ");
		}
	}

	//add method
	public void enqueue(Customer cust) {
		if (!isFull()) {
			if (numItems == 0) {
				frontIndex = 0;
			}
			backIndex = (backIndex + 1) % capacity;
			arr[backIndex] = cust;
			numItems++;
		}
		if(isFull()) {
			System.out.println("queue is full");
		}
	}

	//remove method
	public Customer dequeue() {
		if (!isEmpty()) {
			Customer value = arr[frontIndex];
			frontIndex = (frontIndex + 1) % capacity;
			numItems--;
			return value;
		} else {
			return null;
		}
	}

	//remove all
	public void dequeueAll() {
		numItems = 0;
		frontIndex = -1;
		backIndex = -1;
	}
}
