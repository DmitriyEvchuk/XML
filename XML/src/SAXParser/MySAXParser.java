package SAXParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import database.*;

public class MySAXParser extends DefaultHandler {

	final public QuickSearch quickSearch = new QuickSearch();

	private List<Contact> contacts = new ArrayList<Contact>();

	private Contact contact;
	private Address address;
	private Tags tags;

	private String data = "";

	public List<Contact> getContactList() {
		return contacts;
	}

	public void startElement(String uri, String name, String qName,
			Attributes atts) {
		switch (name) {
		case "Contact":
			contact = new Contact();
			break;

		case "Address":
			address = new Address();
			break;

		case "Tags":
			tags = new Tags();
			break;

		case "Passport": {

			String id = atts.getValue("Id");

			if (atts.getValue("Type").equals("local"))
				contact.setPassportLocal(id);
			else
				contact.setPassportForeign(id);

			break;
		}

		}
		data = "";
	}

	public void endElement(String uri, String name, String qName) {

		switch (name) {
		// fill Contact
		case "Name":
			contact.setName(data);
			break;

		case "Surname":
			contact.setSurname(data);
			break;

		case "Phone":
			contact.setPhone(data);
			break;

		case "DateBirthday":
			contact.setDateBirthday(data);
			break;

		case "Citizenship":
			contact.setCitizenship(data);
			break;

		// fill Address
		case "Town":
			address.setTown(data);
			break;

		case "Street":
			address.setStreet(data);
			break;

		case "House":
			address.setHouse(data);
			break;

		case "Flat":
			address.setFlat(data);
			break;

		case "Address":
			contact.setAddress(address);
			break;
		// ///////////////////////////////////
		// fill Tags
		case "Tag": {
			tags.addTegs(data);

			quickSearch.setTagsMap(data, contact);
		}
			break;

		case "Tags":
			contact.setTeg(tags);
			break;
		// /////////////////////////////
		case "Contact":
			if (contact.getPassportForeign() == null)
				contact.setPassportForeign("NoPassport");

			contacts.add(contact);
			break;
		}

		data = "";

	}

	public void characters(char ch[], int start, int length) {

		for (int i = start; i < start + length; i++) {

			data = data + Character.toString(ch[i]);

		}

	}

	public static void main(String args[]) throws Exception {

		// XMLReader xr = XMLReaderFactory.createXMLReader();
		MySAXParser test = new MySAXParser();
		// xr.setContentHandler(test);
		FileReader r = new FileReader("E:/work/MyContacts.xml");
		// xr.parse(new InputSource(r));
		// System.out.println(test.quickSearch.quickClick("friend"));
		// //////////////////

		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		SAXParser saxParser = spf.newSAXParser();
		saxParser.parse(new InputSource(r), test);
		System.out.println(test.quickSearch.quickClick("friend"));
	}

}
