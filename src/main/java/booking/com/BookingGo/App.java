package booking.com.BookingGo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.google.gson.JsonObject;


public class App {
	
	
    public static void main( String[] args ) {
    	
    	System.out.println("Welcome to BookingGo! \n" 
    	+ "Enter pickup location, followed by dropoff location in the form {latitude, longitude}:");
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	String pickup;
    	String dropoff = null;
    	int passengers;
    	
    	String currentLine = scanner.nextLine();

		pickup = currentLine;
		
		currentLine = scanner.nextLine();
		
		dropoff = currentLine;
		
		System.out.println("Enter number of passengers:");
		
		currentLine = scanner.nextLine();
		
		passengers = Integer.parseInt(currentLine);
		
		scanner.close();
    	
    	System.out.println("Finding you a ride.. pickup: " + pickup + ", dropoff: " + dropoff + ", Number of passengers: " + passengers);
    	
    	TaxiSuppliers suppliers = new TaxiSuppliers();
    	
    	ArrayList<Car> davesCars = new ArrayList<Car>();
    	ArrayList<Car> ericsCars = new ArrayList<Car>();
    	ArrayList<Car> jeffsCars = new ArrayList<Car>();
    	    	
    	
    	davesCars = suppliers.getTaxiOptions(pickup,dropoff,passengers,"dave");
    	ericsCars = suppliers.getTaxiOptions(pickup,dropoff,passengers,"eric");
    	jeffsCars = suppliers.getTaxiOptions(pickup,dropoff,passengers,"jeff");    	
      	
    	if (davesCars.isEmpty()) {
    		System.out.println("No cars available at this time - Please try again later");
    	} else {
    		Collections.sort(davesCars,Collections.reverseOrder());
    		System.out.println("");
        	for(int i = 0; i<davesCars.size();i++) {
        		System.out.println(davesCars.get(i).getCarType().toString()+ " - " + davesCars.get(i).getSupplier() + " - " + davesCars.get(i).getPrice());
        	}
    	}
    	
    	if (ericsCars.isEmpty()) {
    		System.out.println("No cars available at this time - Please try again later");
    	} else {
    		Collections.sort(ericsCars,Collections.reverseOrder());
    		System.out.println("");
        	for(int i = 0; i<ericsCars.size();i++) {
        		System.out.println(ericsCars.get(i).getCarType().toString()+ " - " + ericsCars.get(i).getSupplier() + " - " + ericsCars.get(i).getPrice());
        	}
    	}
    	
    	if (jeffsCars.isEmpty()) {
    		System.out.println("No cars available at this time - Please try again later");
    	} else {
    		Collections.sort(jeffsCars,Collections.reverseOrder());
    		System.out.println("");
        	for(int i = 0; i<jeffsCars.size();i++) {
        		System.out.println(jeffsCars.get(i).getCarType().toString()+ " - " + jeffsCars.get(i).getSupplier() + " - " + jeffsCars.get(i).getPrice());
        	}
    	}
    }
}
