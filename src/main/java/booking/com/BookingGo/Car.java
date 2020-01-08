package booking.com.BookingGo;

public class Car implements Comparable<Car>{
	
	int price;
	int seats;
	private carType carType;
	String supplier_id;
	
	enum carType {
		STANDARD,EXECUTIVE,LUXURY,PEOPLE_CARRIER,LUXURY_PEOPLE_CARRIER,MINIBUS;
	}
	
	public Car(carType type, int price, String supplier_id) {
		this.carType = type;
		this.price = price;
		this.supplier_id = supplier_id;
	}
	
	public int getSeats() {
		switch(this.carType) {
		case STANDARD:
			return 4;
		case EXECUTIVE:
			return 4;
		case LUXURY:
			return 4;
		case PEOPLE_CARRIER:
			return 6;
		case LUXURY_PEOPLE_CARRIER:
			return 6;
		case MINIBUS:
			return 16;
		default:
			break;
		}
		return price;	
	}
	
	public String getSupplier() {
		return supplier_id;
	}
		
	public carType getCarType() {
		return carType;
	}

	public void setCarType(carType carType) {
		this.carType = carType;
	}

	public int compareTo(Car car) {
		return Integer.compare(this.getPrice(), car.getPrice());
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
