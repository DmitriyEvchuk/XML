package parse;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import phonebook.Contact;
import phonebook.PhoneBook;

public class Parser {

	public Parser(String fName) {
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

	}

	private Document XMLdoc;

	public void fiilBook(PhoneBook book) {

		Contact contact = null;
		// node with data name and talephone
		Node data = null;

		// lower level tags <name> <phone>
		NodeList lastChild = null;

		// medium level tags< <contact>>
		NodeList child = XMLdoc.getFirstChild().getChildNodes();

		for (int i = 0; i < child.getLength(); i++) {

			Node current = child.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {

				if (current.hasChildNodes()) {

					lastChild = current.getChildNodes();

					for (int j = 0; j < lastChild.getLength(); j++) {

						// tag with name or teleohone
						data = lastChild.item(j);

						if (data.getNodeType() == Node.ELEMENT_NODE) {

							if (data.getNodeName().equals("name")) {
								contact = new Contact();
								contact.setAbonName(data.getTextContent());

							}

							if (data.getNodeName().equals("phone"))
								contact.setAbonPhone(data.getTextContent());

						}

						if ((lastChild.getLength() - 1) == j) {
							book.add(contact);

						}

					}
				}
			}

		}

	}

	public static void main(String[] args) throws Exception {

		Parser parse = new Parser("E:/work/phone.xml");
		PhoneBook book = new PhoneBook();

		parse.fiilBook(book);

		// System.out.println(book);
		book.viewContactPhone("John Daae");
	}

}
