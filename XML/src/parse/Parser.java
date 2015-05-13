package parse;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class Parser {

	public Parser(String fName) {
		XMLfile = (new File(fName));
	}

	static private TreeMap<String, String> phoneBook = new TreeMap<String, String>();

	private File XMLfile;

	public Map<String, String> getBook() {
		return phoneBook;
	}

	public Document parseFile() throws Exception {

		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = f.newDocumentBuilder();
		return builder.parse(XMLfile);

	}

	public void fiilBook(Document doc) {

		NodeList name = doc.getElementsByTagName("name");
		NodeList telephone = doc.getElementsByTagName("phone");

		for (int i = 0; i < name.getLength(); i++) {

			Node nodName = name.item(i);
			Node nodPhone = telephone.item(i);

			Parser.phoneBook.put(nodName.getTextContent(),
					nodPhone.getTextContent());

		}

	}

	public static void main(String[] args) throws Exception {

		Parser parse = new Parser("E:/work/phone.xml");

		parse.fiilBook(parse.parseFile());

		for (String d : Parser.phoneBook.keySet())
			System.out.println("Name " + d + " Phone "
					+ Parser.phoneBook.get(d));

	}

}
