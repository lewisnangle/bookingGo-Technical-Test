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
    	
    	TaxiController suppliers = new TaxiController();
    	
    	ArrayList<Taxi> davesTaxis = new ArrayList<Taxi>();
    	ArrayList<Taxi> ericsTaxis = new ArrayList<Taxi>();
    	ArrayList<Taxi> jeffsTaxis = new ArrayList<Taxi>();
    	    	
    	
    	davesTaxis = suppliers.getTaxiOptions(pickup,dropoff,passengers,"dave");
    	ericsTaxis = suppliers.getTaxiOptions(pickup,dropoff,passengers,"eric");
    	jeffsTaxis = suppliers.getTaxiOptions(pickup,dropoff,passengers,"jeff");    	
      	
    	String davesTaxisOutput= suppliers.getString(davesTaxis,"dave");
    	String ericsTaxisOutput= suppliers.getString(ericsTaxis,"eric");
    	String jeffsTaxisOutput= suppliers.getString(jeffsTaxis,"jeff");
    	
    	System.out.println(davesTaxisOutput + " " + ericsTaxisOutput + " " + jeffsTaxisOutput);
    	
    	
    }
}
