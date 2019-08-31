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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateOfBorn() {
		return dateOfBorn;
	}

	public void setDateOfBorn(Date dateOfBorn) {
		this.dateOfBorn = dateOfBorn;
	}

	public String getPerOfPreference() {
		return perOfPreference;
	}

	public void setPerOfPreference(String perOfPreference) {
		this.perOfPreference = perOfPreference;
	}

	public ArrayList<Pet> getPets() {
		return pets;
	}

	public void setPets(ArrayList<Pet> pets) {
		this.pets = pets;
	}
	
	
	

}
