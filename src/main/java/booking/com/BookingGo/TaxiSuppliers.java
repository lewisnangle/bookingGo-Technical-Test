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

public class TaxiSuppliers {
	
	public JsonObject callTaxiAPI(String pickup, String dropoff, int passengers, String supplier) {
		
		JsonObject jsonResponse = null;
		
		try {

			URL url = new URL("https://techtest.rideways.com/" + supplier + "/?pickup="+ pickup + "&dropoff=" + dropoff);
			
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
	
	public ArrayList<Car> parseJsonObject(JsonObject jsonResponse,int passengers, String supplier) {
		
		ArrayList<Car> cars = new ArrayList<Car>();
		
		JsonElement options = jsonResponse.get("options");
		int length = options.getAsJsonArray().size();
	
		for(int i=0;i<length;i++) {
			JsonElement type = options.getAsJsonArray().get(i).getAsJsonObject().get("car_type");
			JsonElement price = options.getAsJsonArray().get(i).getAsJsonObject().get("price");
			Car car = new Car(carType.valueOf(type.getAsString()), price.getAsInt(),supplier);
			if (car.getSeats()>=passengers) {
				cars.add(car);	
			}		
		}
		return cars;
		
	}
	
	public ArrayList<Car> getTaxiOptions(String pickup,String dropoff,int passengers,String supplier){
		JsonObject jsonObject = callTaxiAPI(pickup, dropoff, passengers, supplier);
		
		return parseJsonObject(jsonObject, passengers, supplier);
		
	}
	
}
