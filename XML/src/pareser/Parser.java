package pareser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import database.Address;
import database.Contact;
import database.Tags;

public class Parser {

	private Integer indxClass = 0;

	private LinkedHashSet filledObj = new LinkedHashSet();

	public Document createDocument(String fName) {

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

	public <T> void filledObjects(Node parrent, ArrayList<Class> listClass)
			throws Exception {

		Field[] currentFields = null;

		// Integer indxClass = 0;
		Integer indxFields = 0;
		Class cur = null;

		if (indxClass >= listClass.size())
			indxClass = 0;

		NodeList childs = parrent.getChildNodes();

		Object object = null;

		outer: for (int i = 0; i < childs.getLength(); i++) {// for0

			Node current = childs.item(i);

			if (current.getNodeType() == Node.ELEMENT_NODE) {// if0

				NodeList childsCurrent = current.getChildNodes();

				for (int j = 0; j < childsCurrent.getLength(); j++) {// for1

					Node currentChild = childsCurrent.item(j);

					if (currentChild.getNodeType() == Node.ELEMENT_NODE) {// if1

						filledObjects(current, listClass);
						indxFields++;
						continue outer;

					}// if1

				}// for1

				if (object == null) {
					cur = listClass.get(indxClass);
					object = cur.newInstance();
					indxClass++;
				}
				if (object != null) {
					currentFields = cur.getDeclaredFields();

					Field currentField = currentFields[indxFields];
					currentField.setAccessible(true);
					currentField.set(object, current.getTextContent());
					filledObj.add(object);
					indxFields++;

				}

			}// if0
		}// for 0

	}

	public static void main(String[] Args) throws Exception {

		Parser fill = new Parser();
		Document doc = fill.createDocument("E:/work/MyContacts.xml");
		 //System.out.println(doc.getDocumentElement().getNodeName());
		fill.filledObjects(doc.getDocumentElement(), new ArrayList<Class>(
				Arrays.asList(Contact.class, Address.class, Tags.class)));
		// System.out.println(fill.filledObj);

		List<Contact> myContact = new ArrayList<Contact>();
		Contact testContact = null;

		/*for (int i = 0; i < fill.filledObj.size(); i++) {

			if (fill.filledObj.toArray()[i] instanceof Contact) {
				testContact = (Contact) fill.filledObj.toArray()[i];
			}

			if (fill.filledObj.toArray()[i] instanceof Address) {
				testContact.setAddress((Address) fill.filledObj.toArray()[i]);
			}
			if (fill.filledObj.toArray()[i] instanceof Tags) {
				testContact.setTegs((Tags) fill.filledObj.toArray()[i]);
			}
			if (testContact.getTegs() != null)
				myContact.add(testContact);
		}*/

		System.out.println(myContact);

	}

}
