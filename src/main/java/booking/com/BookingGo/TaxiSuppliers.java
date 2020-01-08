package booking.com.BookingGo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import booking.com.BookingGo.Car.carType;

public class TaxiSuppliers extends Thread {
	
	
	public ArrayList<Car> callDavesTaxis(String pickup, String dropoff, int passengers) {
		
		ArrayList<Car> cars = new ArrayList<Car>();
		
		try {

			URL url = new URL("https://techtest.rideways.com/dave/?pickup="+ pickup + "&dropoff=" + dropoff);
			
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
			JsonObject jsonResponse = parser.parse(outputString).getAsJsonObject();
			
			JsonElement options = jsonResponse.get("options");
			JsonElement supplier = jsonResponse.get("supplier_id");
			
			int length = options.getAsJsonArray().size();
		
			for(int i=0;i<length;i++) {
				JsonElement type = options.getAsJsonArray().get(i).getAsJsonObject().get("car_type");
				JsonElement price = options.getAsJsonArray().get(i).getAsJsonObject().get("price");
				Car car = new Car(carType.valueOf(type.getAsString()), price.getAsInt(), supplier.getAsString());
				if (car.getSeats()>=passengers) {
					cars.add(car);	
				}		
			}
			
			connection.disconnect();
			
		  } catch (MalformedURLException e) {
			e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();

		  }
		return cars;
	}
	
	public ArrayList<Car> callEricsTaxis(String pickup, String dropoff, int passengers) {
		
		ArrayList<Car> cars = new ArrayList<Car>();
		
		try {

			URL url = new URL("https://techtest.rideways.com/eric/?pickup="+ pickup + "&dropoff=" + dropoff);
			
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
			JsonObject jsonResponse = parser.parse(outputString).getAsJsonObject();
			
			JsonElement options = jsonResponse.get("options");
			JsonElement supplier = jsonResponse.get("supplier_id");
			
			int length = options.getAsJsonArray().size();
		
			for(int i=0;i<length;i++) {
				JsonElement type = options.getAsJsonArray().get(i).getAsJsonObject().get("car_type");
				JsonElement price = options.getAsJsonArray().get(i).getAsJsonObject().get("price");
				Car car = new Car(carType.valueOf(type.getAsString()), price.getAsInt(), supplier.getAsString());
				if (car.getSeats()>=passengers) {
					cars.add(car);	
				}		
			}
			
			connection.disconnect();
			
		  } catch (MalformedURLException e) {
			e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();

		  }
		return cars;
	}
	
	public ArrayList<Car> callJeffsTaxis(String pickup, String dropoff, int passengers) {
		
		ArrayList<Car> cars = new ArrayList<Car>();
		
		try {

			URL url = new URL("https://techtest.rideways.com/jeff/?pickup="+ pickup + "&dropoff=" + dropoff);
			
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
			JsonObject jsonResponse = parser.parse(outputString).getAsJsonObject();
			
			JsonElement options = jsonResponse.get("options");
			JsonElement supplier = jsonResponse.get("supplier_id");
			
			int length = options.getAsJsonArray().size();
		
			for(int i=0;i<length;i++) {
				JsonElement type = options.getAsJsonArray().get(i).getAsJsonObject().get("car_type");
				JsonElement price = options.getAsJsonArray().get(i).getAsJsonObject().get("price");
				Car car = new Car(carType.valueOf(type.getAsString()), price.getAsInt(), supplier.getAsString());
				if (car.getSeats()>=passengers) {
					cars.add(car);	
				}		
			}
			
			connection.disconnect();
			
		  } catch (MalformedURLException e) {
			e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();

		  }
		return cars;
	}

}
