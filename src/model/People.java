package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class People implements Serializable{
	private String name;
	private String lastName;
	private String id;
	private Date dateOfBorn;
	private String perOfPreference;
	private ArrayList <Pet> pets;
	
	public People(String name, String lastName, String id, Date dateOfBorn, String perOfPreference) {
		this.name = name;
		this.lastName = lastName;
		this.id = id;
		this.dateOfBorn = dateOfBorn;
		this.perOfPreference = perOfPreference;
		pets = new ArrayList<Pet>();
	}
	
	

}
