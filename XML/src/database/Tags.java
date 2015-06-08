package database;


import java.util.HashSet;
import java.util.Set;

public class Tags {

	private Set<String> listTegs = new HashSet<String>();

	public String toString() {

		return "" + listTegs;
	}

	public Set<String> getListTegs() {
		return listTegs;
	}

	public void addTegs(String tagsName) {
		listTegs.add(tagsName);
	}

}
