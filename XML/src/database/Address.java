package database;

public class Address {



private String town;
private String street;
private String house;
private String flat;

public String toString(){
	
	return "town "+town+" street "+street+" house "+house+" flat "+flat;
	
}

public String getAddress(){
	
	return street;
}
}