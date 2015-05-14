package parse;

import java.util.*;


public class Contact {

	private String name;

	private ArrayList<String> phone = new ArrayList<String>();

	public void setAbonName(String name) {
		this.name = name;
	}

	public void setAbonPhone(String phoneNum) {
		phone.add(phoneNum);
	}

	public String getAbonName() {
		return name;
	}

	public List<String> getAbonPhone() {
		return phone;
	}
	
	public String toString(){
		
		return name+""+phone;
		
	}
	
}
