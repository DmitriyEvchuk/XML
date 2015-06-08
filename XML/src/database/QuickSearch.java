package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class QuickSearch {

	private HashMap<String, List<Contact>> tagsMap = new HashMap<String, List<Contact>>();

	

	public void setTagsMap(String tagName, Contact obj) {

		tagsMap.get(tagName);
		if (tagsMap.get(tagName) == null)
			tagsMap.put(tagName, new ArrayList<Contact>(Arrays.asList(obj)));
		else
			tagsMap.get(tagName).add(obj);
	}

	public List<Contact> quickClick(String tagsName) {

		return tagsMap.get(tagsName);

	}

}
