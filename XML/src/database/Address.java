package database;

public class Address {

	private String town;
	private String street;
	private String house;
	private String flat;

	public String toString() {

		return "town " + town + " street " + street + " house " + house
				+ " flat " + flat;

	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

}