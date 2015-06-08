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
	private Tags teg;

	public String toString() {

		return " name " + name + " surname " + surname + " citizenship "
				+ citizenship + " passportLocal " + passportLocal
				+ " passportForeign " + passportForeign + "  address "
				+ address + "\n" + " tegs " + teg + "\n";

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDateBirthday() {
		return dateBirthday;
	}

	public void setDateBirthday(String dateBirthday) {
		this.dateBirthday = dateBirthday;
	}

	public String getPassportLocal() {
		return passportLocal;
	}

	public void setPassportLocal(String passportLocal) {
		this.passportLocal = passportLocal;
	}

	public String getPassportForeign() {
		return passportForeign;
	}

	public void setPassportForeign(String passportForeign) {
		this.passportForeign = passportForeign;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Tags getTeg() {
		return teg;
	}

	public void setTeg(Tags teg) {
		this.teg = teg;
	}

	
}