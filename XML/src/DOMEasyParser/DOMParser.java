package DOMEasyParser;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import database.Address;
import database.Contact;
import database.QuickSearch;
import database.Tags;

public class DOMParser {

	private List<Contact> contacts = new ArrayList<Contact>();
	private QuickSearch quickSearch = new QuickSearch();

	public void parse(String fileSourceName) {

		if (isValid(fileSourceName)) {

			NodeList contactList = createDocument(fileSourceName)
					.getElementsByTagName("Contact");

			for (int i = 0; i < contactList.getLength(); i++) {

				Contact contact = new Contact();

				Node currentContact = contactList.item(i);
				NodeList contactChildList = currentContact.getChildNodes();

				for (int j = 0; j < contactChildList.getLength(); j++) {

					Node currentChild = contactChildList.item(j);

					switch (currentChild.getNodeName()) {

					case "Name":
						contact.setName(currentChild.getTextContent());
						break;

					case "Surname":
						contact.setSurname(currentChild.getTextContent());
						break;

					case "Phone":
						contact.setPhone(currentChild.getTextContent());
						break;

					case "DateBirthday":
						contact.setDateBirthday(currentChild.getTextContent());
						break;

					case "Passport":
						fillPasportField(contact, currentChild);
						break;

					case "Citizenship":
						contact.setCitizenship(currentChild.getTextContent());
						break;

					case "Address":
						fillAddressField(contact, currentChild);
						break;

					case "Tags":
						fillTagsField(contact, currentChild);
						break;
					}

				}

				if (contact.getPassportForeign() == null)
					contact.setPassportForeign("NoForeignPssport");

				contacts.add(contact);

			}

		}

		

	}

	public List<Contact> getContactList() {

		return contacts;
	}

	public static Document createDocument(String fName) {

		Document XMLdoc = null;
		try {

			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = f.newDocumentBuilder();
			XMLdoc = builder.parse(new File(fName));

		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}

		catch (SAXException e) {
			System.out.println("in file " + fName + "is syntax error");
		} catch (IllegalArgumentException e) {
			System.out.println(" error file referens=null");
		}

		catch (IOException e) {
			System.out.println("error input output make shure file path");
		}

		return XMLdoc;
	}

	boolean isValid(String fileSourceName) {

		SchemaFactory factory = SchemaFactory
				.newInstance("http://www.w3.org/2001/XMLSchema");

		try {

			Schema schema = factory.newSchema(new File(
					"E:/work/MyContactSchema.xsd"));

			Validator validator = schema.newValidator();

			Source source = new StreamSource(fileSourceName);
			validator.validate(source);

		} catch (SAXException ex) {
			System.out.println(" is not valid because!!!!!! ");
			System.out.println(ex.getMessage());
			return false;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

		return true;

	}

	private void fillTagsField(Contact contact, Node current) {

		NodeList childList = current.getChildNodes();

		Tags tags = new Tags();

		for (int i = 0; i < childList.getLength(); i++) {

			Node currentChild = childList.item(i);

			if (currentChild.getNodeType() == Node.ELEMENT_NODE) {
				tags.addTegs(currentChild.getTextContent());
				quickSearch.setTagsMap(currentChild.getTextContent(), contact);
			}
		}

		contact.setTeg(tags);
	}

	private void fillAddressField(Contact contact, Node current) {

		NodeList childList = current.getChildNodes();

		Address address = new Address();

		for (int i = 0; i < childList.getLength(); i++) {

			Node currentChild = childList.item(i);

			switch (currentChild.getNodeName()) {

			case "Town":
				address.setTown(currentChild.getTextContent());
				break;

			case "Street":
				address.setStreet(currentChild.getTextContent());
				break;

			case "House":
				address.setHouse(currentChild.getTextContent());
				break;

			case "Flat":
				address.setFlat(currentChild.getTextContent());
				break;
			}

		}

		contact.setAddress(address);

	}

	private void fillPasportField(Contact contact, Node current) {

		NamedNodeMap atrList = current.getAttributes();

		if (atrList.getNamedItem("Type").getTextContent().equals("local")) {

			contact.setPassportLocal(atrList.getNamedItem("Id")
					.getTextContent());

		}
		if (atrList.getNamedItem("Type").getTextContent().equals("foreign")) {

			contact.setPassportForeign(atrList.getNamedItem("Id")
					.getTextContent());

		}

	}

	public static void main(String[] args) {

		DOMParser test = new DOMParser();

		test.parse("E:/work/MyContacts.xml");

		System.out.println(test.quickSearch.quickClick("friend"));

		

	}

}
