package booking.com.BookingGo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonObject;

import booking.com.BookingGo.Taxi.carType;


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
    	
    	TaxiController taxiController = new TaxiController();
    	
    	ArrayList<Taxi> davesTaxis = new ArrayList<Taxi>();
    	ArrayList<Taxi> ericsTaxis = new ArrayList<Taxi>();
    	ArrayList<Taxi> jeffsTaxis = new ArrayList<Taxi>();
    	    	
    	
    	davesTaxis = taxiController.getTaxiOptions(pickup,dropoff,passengers,"dave");
    	ericsTaxis = taxiController.getTaxiOptions(pickup,dropoff,passengers,"eric");
    	jeffsTaxis = taxiController.getTaxiOptions(pickup,dropoff,passengers,"jeff");    	
      	
    	String davesTaxisOutput= taxiController.getString(davesTaxis,"dave");
    	String ericsTaxisOutput= taxiController.getString(ericsTaxis,"eric");
    	String jeffsTaxisOutput= taxiController.getString(jeffsTaxis,"jeff");
    	
    	ArrayList<Taxi> cheapestTaxis = taxiController.cheapestTaxis(davesTaxis,ericsTaxis,jeffsTaxis);
    	  	
    	System.out.println("From available taxis: \n" + davesTaxisOutput + " " + ericsTaxisOutput + " " + jeffsTaxisOutput + "\n");
    	
    	System.out.println("Best options from each supplier are: \n");
    	for(Taxi taxi : cheapestTaxis) {
    		System.out.println(taxi.getCarType() + " - " + taxi.getSupplier() + " - " + taxi.getPrice());
    	}
    	
    }
    
    
}

















