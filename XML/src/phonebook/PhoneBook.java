package phonebook;

import java.util.ArrayList;
import java.util.List;



//also we can add here complex find or somthink else 
@SuppressWarnings("serial")
public class PhoneBook extends ArrayList<Contact>{

	public List<Contact> find(String name) {

		List<Contact> list = new ArrayList<Contact>();
		for (Contact current : this) {
			if (current.getAbonName().equals(name))
				list.add(current);

		}

		return list;
	}

	public void viewContactPhone(String name) {

		for (Contact current : find(name)) {
			System.out.println("Name " + current.getAbonName() + "Phone"
					+ current.getAbonPhone());

		}

	}

}
