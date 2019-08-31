package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author camilo
 *
 */
public class People implements Serializable{
	private String name;
	private String lastName;
	private String id;
	private Date dateOfBorn;
	private String perOfPreference;
	private ArrayList <Pet> pets;
	
	/**
	 * @param name
	 * @param lastName
	 * @param id
	 * @param dateOfBorn
	 * @param perOfPreference
	 */
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
	
	/**
	 * @param name
	 * @return
	 */
	public boolean exist(String name) {
		boolean exist = false;
		for (int i = 0; i < pets.size(); i++) {
			if(pets.get(i).getName().equals(name)) {
				exist = true;
			}
		}
		return exist;
	}
	
	/**
	 * @param a
	 * @throws ExceptionRegistry
	 */
	public void addPet(Pet a)throws ExceptionRegistry {
		if(exist(a.getName()) == false) {
			pets.add(a);
		}
		else {
			throw new ExceptionRegistry(a.getName());
		}
	}
	
	

}
