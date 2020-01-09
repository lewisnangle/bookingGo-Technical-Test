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
    
    public ArrayList<Taxi> cheapestTaxis(ArrayList<Taxi> davesTaxis, ArrayList<Taxi> ericsTaxis, ArrayList<Taxi> jeffsTaxis){
    	
    	ArrayList<Taxi> allTaxis = new ArrayList<Taxi>();
    	ArrayList<Taxi> cheapestTaxis = new ArrayList<Taxi>();
    	
    	allTaxis.addAll(davesTaxis);
    	allTaxis.addAll(ericsTaxis);
    	allTaxis.addAll(jeffsTaxis);

    	Taxi cheapestSTANDARD = new Taxi(carType.STANDARD,0,"");
    	Taxi cheapestEXECUTIVE = new Taxi(carType.EXECUTIVE,0,"");
    	Taxi cheapestLUXURY = new Taxi(carType.LUXURY,0,"");
    	Taxi cheapestPEOPLE_CARRIER = new Taxi(carType.PEOPLE_CARRIER,0,"");
    	Taxi cheapestLUXURY_PEOPLE_CARRIER = new Taxi(carType.LUXURY_PEOPLE_CARRIER,0,"");
    	Taxi cheapestMINIBUS = new Taxi(carType.MINIBUS,0,"");
    	
    	for(Taxi taxi : allTaxis) {
    		if(taxi.getCarType() == carType.STANDARD && taxi.getPrice() > cheapestSTANDARD.getPrice()) {
    			cheapestSTANDARD = taxi;
    		}
    		if(taxi.getCarType() == carType.STANDARD && taxi.getPrice() > cheapestEXECUTIVE.getPrice()) {
    			cheapestEXECUTIVE = taxi;
    		}
    		if(taxi.getCarType() == carType.STANDARD && taxi.getPrice() > cheapestLUXURY.getPrice()) {
    			cheapestLUXURY = taxi;
    		}
    		if(taxi.getCarType() == carType.STANDARD && taxi.getPrice() > cheapestPEOPLE_CARRIER.getPrice()) {
    			cheapestPEOPLE_CARRIER = taxi;
    		}
    		if(taxi.getCarType() == carType.STANDARD && taxi.getPrice() > cheapestLUXURY_PEOPLE_CARRIER.getPrice()) {
    			cheapestLUXURY_PEOPLE_CARRIER = taxi;
    		}
    		if(taxi.getCarType() == carType.STANDARD && taxi.getPrice() > cheapestMINIBUS.getPrice()) {
    			cheapestMINIBUS = taxi;
    		}
    	}
    	
    	if(cheapestSTANDARD.getPrice()!=0) {
    		cheapestTaxis.add(cheapestSTANDARD);
    	}
    	if(cheapestEXECUTIVE.getPrice()!=0) {
    		cheapestTaxis.add(cheapestEXECUTIVE);
    	}
    	if(cheapestLUXURY.getPrice()!=0) {
    		cheapestTaxis.add(cheapestLUXURY);
    	}
    	if(cheapestPEOPLE_CARRIER.getPrice()!=0) {
    		cheapestTaxis.add(cheapestPEOPLE_CARRIER);
    	}
    	if(cheapestSTANDARD.getPrice()!=0) {
    		cheapestTaxis.add(cheapestLUXURY_PEOPLE_CARRIER);
    	}
    	if(cheapestSTANDARD.getPrice()!=0) {
    		cheapestTaxis.add(cheapestMINIBUS);
    	}
    	
		return cheapestTaxis;

    }
    
}

















