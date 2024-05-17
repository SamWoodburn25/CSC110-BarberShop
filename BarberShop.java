/*
 * sam woodburn
 * Fall 2023
 * CSC111- Final Project
 * Barber shop- barber shop class
 *     main method, reads the input file and simulates a barber shop with 3 available chairs and a barbers chair
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BarberShop {
	
	//main method
	public static void main (String[] args) {
		//try block
		try {
			//variables
			int arrival = 0;
			int service = 0;
			Boolean barbchairAvailable = false;
			Boolean done = false;
			String name = " ";
			Customer cust;
			Customer current = new Customer();
			Customer inBarberChair = new Customer();;
			Queue customerqueue = new Queue(8);
			CQueue chairqueue = new CQueue(3);
			
			
			//create a file to store the barber input 
			File barberFile = new File("BarberInput.txt");
			Scanner fileScanner = new Scanner(barberFile);

			//loop through the file line by line and make customers to add to customer queue
			while(fileScanner.hasNextLine()) {
				arrival = fileScanner.nextInt();
				service = fileScanner.nextInt();
				name = fileScanner.next();
				cust = new Customer(arrival, service, name);
				customerqueue.enqueue(cust);
			} 
			
			int time = 0;
			int custCount = 0;
			int serviceCount = 0;
			//loop through until all customers have been serviced
			while(!customerqueue.isEmpty()) {
				//print the time
				System.out.println("\n-----------------------------------");
				System.out.println("Time = " + time);
				//if there is 1 or less people waiting the barber takes a break
				if(customerqueue.curr.getArrival() <= 1) {
					System.out.println("\nBarber takes a break.");
				}
				//if there is someone waiting in the chairs and service is currently 
				if(chairqueue.numItems > 0 && serviceCount == 0) {
					barbchairAvailable = true;
				}
				else {
					barbchairAvailable = false;
				}
				
				
				//if the current arrival equals the time then the person sits
				if(customerqueue.curr.getArrival() == time) {
					//dequeue current and set it to current variable
					current = customerqueue.dequeue();
					//if the chairs are not full, ad current to the chair queue
					if(!chairqueue.isFull()) {
						chairqueue.enqueue(current);
						System.out.println("\nA chair is available.");
						System.out.println("    " + current.getName() + " sits.");
					}
					//if they are full print that
					else {
						System.out.println("\nAll chairs are full.");
						System.out.println("    " + current.getName() + " leaves.");
					}
				}
				
				//if the barber chair is available then take first person in chair and set to in barber chair
				if(barbchairAvailable) {
					inBarberChair = chairqueue.dequeue();
					serviceCount = inBarberChair.getService();
				}
				
				//if the chair queue is empty print that
				if(chairqueue.isEmpty()) {
					System.out.println("\nChairs are empty.");
				}
				else {
					//print who's in the chairs
					chairqueue.display();
				}
				//if barber chair is empty print that
				if(inBarberChair.getName() == null) {
					System.out.println("Barber Chair is empty.");
				}
				else {
					//print who is in the barbers chair and what their service time is
					System.out.println("Barber:");
					System.out.println("\t" + inBarberChair.getName() + " in chair- " + serviceCount + " left");
					//decrease service
					serviceCount--;
				}
				
				//print arrival list/ print the customer queue
				customerqueue.display();
				
				//increase variables
				time++;
				custCount++;

			}//end of while- customer queue is empty
			
			//if there are still customers waiting in chairs, finish those
			while(!chairqueue.isEmpty() && !done) {
				System.out.println("\n-----------------------------------");
				System.out.println("Time = " + time);
				if(chairqueue.isFull()) {
					System.out.println("All chairs are full.");
				}
				//if there is someone waiting in the chairs and service is currently 
				if(chairqueue.numItems > 0 && serviceCount == 0) {
					barbchairAvailable = true;
				}
				else {
					barbchairAvailable = false;
				}
				
				//if the barber chair is available then take first person in chair and set to in barber chair
				if(barbchairAvailable) {
					inBarberChair = chairqueue.dequeue();
					serviceCount = inBarberChair.getService();
				}
				
				//if the chair queue is empty print that and done = true
				if(chairqueue.isEmpty() && !barbchairAvailable) {
					System.out.println("\nChairs are empty.");
					done = true;
				}
				else {
					//print who's in the chairs
					chairqueue.display();
				}
				//if barber chair is empty print that
				if(inBarberChair.getName() == null) {
					System.out.println("Barber Chair is empty.");
				}
				else {
					//print who is in the barbers chair and what their service time is
					System.out.println("Barber:");
					System.out.println("\t" + inBarberChair.getName() + " in chair- " + serviceCount + " left");
					//decrease service
					serviceCount--;
				}
				//print arrival list/ print the customer queue
				customerqueue.display();
				//increase variables
				time++;
				custCount++;
			}
			
			//service the last person in the barber chair
			while(barbchairAvailable) {
				serviceCount = inBarberChair.getService();
				for(int i = serviceCount-1; i > 0; i--) {
					//print time
					System.out.println("\n-----------------------------------");
					System.out.println("Time = " + time);
					//print empty chairs
					chairqueue.display();
					//print barbers chair
					System.out.println("Barber:");
					System.out.println("\t" + inBarberChair.getName() + " in chair- " + i + " left");
					time++;
				}
				barbchairAvailable = false;
			}
			
			//print one last time that everything is empty
			//print time
			System.out.println("\n-----------------------------------");
			System.out.println("Time = " + time++);
			//print empty chairs
			chairqueue.display();
			//print barbers chair
			System.out.println("Barber: \n\t - ");
			System.out.println("Process Completed- all customers have left :)");
			
			
			//close file scanner
			fileScanner.close();
		}
		
		
		//catch exception
		catch(FileNotFoundException e) {
			System.out.println("no file");
		}
	}
	
	
}
