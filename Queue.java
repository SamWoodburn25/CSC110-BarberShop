/*
 * sam woodburn
 * Fall 2023
 * CSC111- Final Project
 * Barber shop- queue class
 * 		array based queue of customers
 */

public class Queue {
	private int cap;
	private Customer[] arr;
	private int numItems;
	protected int front;
	protected Customer curr;
	protected int back;

	public Queue(int cap) {
		this.cap = cap;
		arr = new Customer[cap];
		numItems = 0;
		front = -1;
		back = -1;
		curr = new Customer();
	}
	
	public int size() {
		return cap;
	}

	public boolean isEmpty() {
		return numItems == 0;
	}

	public boolean isFull() {
		return numItems == cap;
	}

	public void display() {
		System.out.print("\nArrival List:\n");
		if (!isEmpty()) {
			int temp = front;
			for (int i = 0; i < numItems; i++) {
				System.out.println("\t" + arr[temp].getName() + ": arrival = " + arr[temp].getArrival() + ":  service = " + arr[temp].getService());
				temp++;
			}
			System.out.println();
		}
		if (isEmpty()) {
			System.out.println("\t - ");
		}
	}

	public void enqueue(Customer i) {
		if (!isFull()) {
			if (numItems == 0) {
				front = 0;
			}
			back = back + 1;
			arr[back] = i;
			curr = arr[front];
			numItems++;
		}
		if(isFull()) {
			
		}
	}

	public Customer dequeue() {
		if (!isEmpty()) {
			Customer value = arr[front];
			front = (front + 1) % cap;
			curr = arr[front];
			numItems--;
			return value;
		} else {
			return null;
		}
	}

	public void dequeueAll() {
		numItems = 0;
		front = -1;
		back = -1;
	}
}