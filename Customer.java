/*
 * sam woodburn
 * Fall 2023
 * CSC111- Final Project
 * Barber shop- customer class
 *     creates a new customer with arrival time, service time, and name
 */


public class Customer {
	//variables
	public int arrival;
	public int service;
	public String name;
	
	//constructor
	public Customer() {
		this.arrival = -1;
		this.service = -1;
		this.name = null;
	}
	//parameterized constructor
	public Customer(int arrival, int service, String name) {
		this.arrival = arrival;
		this.service = service;
		this.name = name;
	}
	
	//getters and setters- arrival
	public void setArrival(int arrival) {
		this.arrival = arrival;
	}
	public int getArrival() {
		return arrival;
	}
	
	//getters and setters- service
	public void setService(int service) {
		this.service = service;
	}
	public int getService() {
		return service;
	}
	
	//getters and setters- name
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
