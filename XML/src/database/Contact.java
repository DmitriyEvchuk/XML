package database;

public class Contact {

	private String name;
	private String surname;
	private String phone;
	private String dateBirthday;
	private String passportLocal;
	private String passportForeign;
	private String citizenship;
	private Address address;
	private Tags teg;;

	
	public String toString(){
		
		return " name "+name+" surname "+surname+" address "+address+" tegs "+teg;
		
	}
	
	public void setAddress(Address address) {
		this.address = address;

	}
	
	
	public void setTegs( Tags teg) {
		this.teg = teg;

	}

	public String getPhone() {
		// System.out.println("dfghjk");
		return phone;
	}
	
	public Tags getTegs() {
		// System.out.println("dfghjk");
		return teg;
	}

}