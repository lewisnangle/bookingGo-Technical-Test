package booking.com.BookingGo;

import java.util.ArrayList;
import java.util.Scanner;

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
    	
    	try {
    		davesTaxis = taxiController.getTaxiOptions(pickup,dropoff,passengers,"dave");
    	} catch (Exception ignore) {
    		davesTaxis=null;
    	}
    	
    	try {
    		ericsTaxis = taxiController.getTaxiOptions(pickup,dropoff,passengers,"eric");
    	} catch (Exception ignore) {
    		ericsTaxis=null;
    	}
    	
    	try {
    		jeffsTaxis = taxiController.getTaxiOptions(pickup,dropoff,passengers,"jeff");    	
    	} catch (Exception ignore) {
    		jeffsTaxis=null;
    	}
    	
    	if(davesTaxis == null && ericsTaxis == null && jeffsTaxis == null) 	{
    		System.out.println("We're sorry there has been an error - please try again.");
    	} else {
    		String davesTaxisOutput= taxiController.getString(davesTaxis,"dave");
        	String ericsTaxisOutput= taxiController.getString(ericsTaxis,"eric");
        	String jeffsTaxisOutput= taxiController.getString(jeffsTaxis,"jeff");
        	
        	ArrayList<Taxi> cheapestTaxis = taxiController.cheapestTaxis(davesTaxis,ericsTaxis,jeffsTaxis);
        	  	
        	System.out.println("From available taxis: \n" + davesTaxisOutput + " " + ericsTaxisOutput + " " + jeffsTaxisOutput + "\n");
        	
        	System.out.println("Best options from all suppliers are: \n");
        	for(Taxi taxi : cheapestTaxis) {
        		System.out.println(taxi.getCarType() + " - " + taxi.getSupplier() + " - " + taxi.getPrice());
        	}
    	}
      	
    }
    
    
}

















