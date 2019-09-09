package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import exception.*;

/**
 * @author camilo
 *
 */
public class People implements Serializable{
	private String name;
	private String lastName;
	private String id;
	private Date dateOfBorn;
	private String petOfPreference;
	private ArrayList <Pet> pets;
	
	/**
	 * @param name
	 * @param lastName
	 * @param id
	 * @param dateOfBorn
	 * @param perOfPreference
	 */
	public People(String name, String lastName, String id, Date dateOfBorn, String petOfPreference) {
		this.name = name;
		this.lastName = lastName;
		this.id = id;
		this.dateOfBorn = dateOfBorn;
		this.petOfPreference = petOfPreference;
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

	public String getPetOfPreference() {
		return petOfPreference;
	}

	public void setPetOfPreference(String petOfPreference) {
		this.petOfPreference = petOfPreference;
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
	
	//COMPARACIONES
	
	public int compareName(People a) {
		int retorno = 0;
		int compare = name.compareToIgnoreCase(a.getName());
		if(compare <0) {
			retorno = -1;
		}
		else if(compare == 0) {
			retorno = 0;
		}
		else {
			retorno = 1;
		}
		return retorno;
		
	}
	public int comparelastName(People a) {
		int retorno = 0;
		int compare = lastName.compareToIgnoreCase(a.getLastName());
		if(compare <0) {
			retorno = -1;
		}
		else if(compare == 0) {
			retorno = 0;
		}
		else {
			retorno = 1;
		}
		return retorno;
		
	}
	public int compareId(People a) {
		int retorno = 0;
		int compare = id.compareToIgnoreCase(a.getId());
		if(compare <0) {
			retorno = -1;
		}
		else if(compare == 0) {
			retorno = 0;
		}
		else {
			retorno = 1;
		}
		return retorno;
	}
	public int compareDate(People a) {
		int retorno = 0;
		int compare = dateOfBorn.compareTo(a.getDateOfBorn());
		if(compare <0) {
			retorno = -1;
		}
		else if(compare == 0) {
			retorno = 0;
		}
		else {
			retorno = 1;
		}
		return retorno;
	}
	public int comparePetOfPreference(People a) {
		int retorno = 0;
		int compare = petOfPreference.compareToIgnoreCase(a.getPetOfPreference());
		if(compare <0) {
			retorno = -1;
		}
		else if(compare == 0) {
			retorno = 0;
		}
		else {
			retorno = 1;
		}
		return retorno;
	}
	
	//ORDENADORES
	/**
	 * this method organize the pets by name using sort order
	 */
	public void organizePetName() {
		for (int i = 0; i < pets.size()-1; i++) {
			Pet menor = pets.get(i);
			int cual = i;
			for (int j = i+1; j < pets.size(); j++) {
				int compare = pets.get(j).compareName(menor);
				if(compare == -1) {
					menor = pets.get(j);
					cual = j;
				}
			}
			Pet tem = pets.get(i);
			pets.set(i, menor);
			pets.set(cual, tem);
		}
	}
	
	

}
