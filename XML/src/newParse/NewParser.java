package newParse;

import java.lang.reflect.Field;
import java.util.*;

import javax.swing.text.html.HTML.Tag;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import database.Address;
import database.Contact;
import database.Tags;
import pareser.Parser;

public class NewParser {

	public static List filledObjects(Document doc,
			LinkedList<String> tagsNameForObject, LinkedList<Class> listClass)
			throws InstantiationException, IllegalAccessException {

		List<Object> objectList = new ArrayList<Object>();

		NodeList list = doc.getElementsByTagName(tagsNameForObject.poll());

		for (int i = 0; i < list.getLength(); i++) {

			Node current = list.item(i);
			objectList.add(filledObject(current, listClass, tagsNameForObject,
					listClass.get(0).newInstance()));
		}

		return objectList;

	}

	static <T> T filledObject(Node current, LinkedList<Class> listClass,
			LinkedList<String> tagsNameForObject, T filledObject)
			throws InstantiationException, IllegalAccessException {

		LinkedList<Class> copyListClass = new LinkedList<Class>(listClass);

		NodeList child = current.getChildNodes();

		LinkedList<Node> childList = new LinkedList<Node>();

		for (int i = 0; i < child.getLength(); i++) {

			childList.add(child.item(i));

		}

		Integer fieldIndx = 0;
		for (int i = 0; i < child.getLength(); i++) {

			Node currentChild = childList.poll();// child.item(i);

			if (currentChild.getNodeType() == Node.ELEMENT_NODE) {

				Field field[] = filledObject.getClass().getDeclaredFields();

				if (tagsNameForObject.contains(currentChild.getNodeName())) {

					copyListClass.poll();

					field[fieldIndx].setAccessible(true);
					field[fieldIndx].set(
							filledObject,
							filledObject(currentChild, copyListClass,
									tagsNameForObject, copyListClass.get(0)
											.newInstance()));
					fieldIndx++;
					continue;
				}

				if (isCollection(field[fieldIndx])) {
					
					fieldIndx++;
				} else {

					field[fieldIndx].setAccessible(true);
					field[fieldIndx].set(filledObject,
							currentChild.getTextContent());
					fieldIndx++;
				}
			}

		}

		return filledObject;
	}

	static boolean isCollection(Field field) {

		if (Collection.class.isAssignableFrom(field.getType()))
			return true;

		return false;
	}

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException {

		Parser fill = new Parser();
		NodeList a = fill.createDocument("E:/work/MyContacts.xml")
				.getElementsByTagName("Passport");

		// for(int i=0;i<a.getLength();i++){

		Node b = a.item(0);
		// /System.out.println(b.hasChildNodes());
		NamedNodeMap ba = b.getAttributes();

		// System.out.println(ba.getNamedItem("Type").getTextContent());

		for (int j = 0; j < ba.getLength(); j++) {
			Node c = ba.item(j);

			System.out.println(c.getNodeName());

		}
		// }

		// „тобы работало нужно научить заполн€ть списки
		/*
		 * Parser fill = new Parser();
		 * fill.createDocument("E:/work/MyContacts.xml");
		 * 
		 * @SuppressWarnings("unchecked") List<Contact> contact = filledObjects(
		 * fill.createDocument("E:/work/MyContacts.xml"), new
		 * LinkedList<String>(Arrays.asList("Contact", "Address", "Tags")), new
		 * LinkedList<Class>(Arrays.asList(Contact.class, Address.class,
		 * Tags.class)));
		 * 
		 * for (Contact c : contact) { System.out.println(c); }
		 */

	}
}
