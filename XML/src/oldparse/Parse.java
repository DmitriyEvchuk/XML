package oldparse;



import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Parse{

	public Parse(String fName) {
		XMLfile = (new File(fName));
	}

	static private TreeMap<String, String> phoneBook = new TreeMap<String, String>();

	private File XMLfile;

	public Map<String, String> getBook() {
		return phoneBook;
	}

	public Document parseFile()  {
		Document XMLdoc=null;
		try {

			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = f.newDocumentBuilder();
			 XMLdoc = builder.parse(XMLfile);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}

		catch (SAXException e) {
			System.out.println("in file " + XMLfile + "is syntax error");
		} 
		catch (IllegalArgumentException e) {
			System.out.println(" make sure that path file is right");
		}

		catch (IOException e) {
			System.out.println("error input output");
		}

		return XMLdoc;
	}

	public void fiilBook(Document doc) {

		NodeList name = doc.getElementsByTagName("name");
		NodeList telephone = doc.getElementsByTagName("phone");

		for (int i = 0; i < name.getLength(); i++) {

			Node nodName = name.item(i);
			Node nodPhone = telephone.item(i);

			Parse.phoneBook.put(nodName.getTextContent(),
					nodPhone.getTextContent());

		}

	}

	public static void main(String[] args) throws Exception {

		Parse parse = new Parse("E:/work/phone.xml");

		parse.fiilBook(parse.parseFile());

		for (String d : Parse.phoneBook.keySet())
			System.out.println("Name " + d + " Phone "
					+ Parse.phoneBook.get(d));

	}

}

