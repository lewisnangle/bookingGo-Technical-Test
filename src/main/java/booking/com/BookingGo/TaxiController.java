package booking.com.BookingGo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import booking.com.BookingGo.Taxi.carType;

public class TaxiController {
	
	public JsonObject callTaxiAPI(String pickup, String dropoff, int passengers, String supplier) {
		
		JsonObject jsonResponse = null;
		
		try {
			URL url = null;
			
			long start = System.currentTimeMillis();
			long end = start + 2*1000; // 2 * 1000ms = 2s
			while (System.currentTimeMillis() < end){
				url = new URL("https://techtest.rideways.com/" + supplier + "/?pickup="+ pickup + "&dropoff=" + dropoff);
				break;
			}
			
			if(url == null) {
				return null;
			}

			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			String output;
			String outputString = "";    			
			while ((output = reader.readLine()) != null) {
				outputString = outputString + output;
			}
			
			JsonParser parser = new JsonParser();
			jsonResponse = parser.parse(outputString).getAsJsonObject();
		
			connection.disconnect();
			
		  } catch (MalformedURLException e) {
			e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();

		  }
		return jsonResponse;
	}
	
	public ArrayList<Taxi> parseJsonObject(JsonObject jsonResponse,int passengers, String supplier) {
		
		ArrayList<Taxi> cars = new ArrayList<Taxi>();
		
		JsonElement options = jsonResponse.get("options");
		int length = options.getAsJsonArray().size();
	
		for(int i=0;i<length;i++) {
			JsonElement type = options.getAsJsonArray().get(i).getAsJsonObject().get("car_type");
			JsonElement price = options.getAsJsonArray().get(i).getAsJsonObject().get("price");
			Taxi taxi = new Taxi(carType.valueOf(type.getAsString()), price.getAsInt(),supplier);
			if (taxi.getSeats()>=passengers) {
				cars.add(taxi);	
			}		
		}
		return cars;
		
	}
	
	public ArrayList<Taxi> getTaxiOptions(String pickup,String dropoff,int passengers,String supplier){
		JsonObject jsonObject = callTaxiAPI(pickup, dropoff, passengers, supplier);
		if(jsonObject == null) {
			return null;
		}
		
		return parseJsonObject(jsonObject, passengers, supplier);
	}
	
	public String getString(ArrayList<Taxi> taxiList, String supplier) {
		String returnString = "";
		if (taxiList.isEmpty() || taxiList == null) {
    		returnString = "No cars available at this time from " + supplier + " - Please try again later";
    	} else {
    		Collections.sort(taxiList,Collections.reverseOrder());
        	for(int i = 0; i<taxiList.size();i++) {
        		returnString = returnString + "\n" + taxiList.get(i).getCarType().toString()+ " - " + taxiList.get(i).getSupplier() + " - " + taxiList.get(i).getPrice();
        	}
    	}
		return returnString;
		
	}
	
    public ArrayList<Taxi> cheapestTaxis(ArrayList<Taxi> davesTaxis, ArrayList<Taxi> ericsTaxis, ArrayList<Taxi> jeffsTaxis){
    	
    	ArrayList<Taxi> allTaxis = new ArrayList<Taxi>();
    	ArrayList<Taxi> cheapestTaxis = new ArrayList<Taxi>();
    	
    	allTaxis.addAll(davesTaxis);
    	allTaxis.addAll(ericsTaxis);
    	allTaxis.addAll(jeffsTaxis);

    	Taxi cheapestSTANDARD = new Taxi(carType.STANDARD,Integer.MAX_VALUE,"");
    	Taxi cheapestEXECUTIVE = new Taxi(carType.EXECUTIVE,Integer.MAX_VALUE,"");
    	Taxi cheapestLUXURY = new Taxi(carType.LUXURY,Integer.MAX_VALUE,"");
    	Taxi cheapestPEOPLE_CARRIER = new Taxi(carType.PEOPLE_CARRIER,Integer.MAX_VALUE,"");
    	Taxi cheapestLUXURY_PEOPLE_CARRIER = new Taxi(carType.LUXURY_PEOPLE_CARRIER,Integer.MAX_VALUE,"");
    	Taxi cheapestMINIBUS = new Taxi(carType.MINIBUS,Integer.MAX_VALUE,"");
    	
    	for(Taxi taxi : allTaxis) {
    		if(taxi.getCarType() == carType.STANDARD && taxi.getPrice() < cheapestSTANDARD.getPrice()) {
    			cheapestSTANDARD = taxi;
    		}
    		if(taxi.getCarType() == carType.EXECUTIVE && taxi.getPrice() < cheapestEXECUTIVE.getPrice()) {
    			cheapestEXECUTIVE = taxi;
    		}
    		if(taxi.getCarType() == carType.LUXURY && taxi.getPrice() < cheapestLUXURY.getPrice()) {
    			cheapestLUXURY = taxi;
    		}
    		if(taxi.getCarType() == carType.PEOPLE_CARRIER && taxi.getPrice() < cheapestPEOPLE_CARRIER.getPrice()) {
    			cheapestPEOPLE_CARRIER = taxi;
    		}
    		if(taxi.getCarType() == carType.LUXURY_PEOPLE_CARRIER && taxi.getPrice() < cheapestLUXURY_PEOPLE_CARRIER.getPrice()) {
    			cheapestLUXURY_PEOPLE_CARRIER = taxi;
    		}
    		if(taxi.getCarType() == carType.MINIBUS && taxi.getPrice() < cheapestMINIBUS.getPrice()) {
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
