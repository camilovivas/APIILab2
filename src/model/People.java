package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
			throw new ExceptionRegistry(a.getId());//TODO
		}
	}
	
	public int cantidadMascotas() {
		return pets.size();
	}
	
	public void removeAllPets() {
		int index = 0;
		while(pets.isEmpty() == true) {	
			pets.remove(index);
			index++;
		}
			
	}
	
	public void removePet(int i) {
		pets.remove(i);
	}
	
	public String ShowNamesPets() {
		String names = "";
		for (int i = 0; i < pets.size(); i++) {
			names += i+pets.get(i).getName()+"\n";
		}
		return names;
	}
	
	
	public void savePetsOrganize(String methodName) throws IOException {
		File archive = new File("./files/ordenamientos/organizePet/organize"+methodName+".txt");
		String save = "";
		BufferedWriter a = new BufferedWriter(new FileWriter(archive));
		for (int i = 0; i < pets.size(); i++) {
			String id = pets.get(i).getId();
			String name = pets.get(i).getName();
			Date date = pets.get(i).getDateBorn();
			String gender = pets.get(i).getGender();
			String kindOfPet = pets.get(i).getKindOfPet();
			save += (id+" "+ name+" "+ date+" "+ gender+" "+ kindOfPet+"\n");
		}
		a.write(save);
		a.close();
	}
	
	//ORDENADORES
	public void organizePets(int method) throws IOException {
		switch(method) {
		case 1:
			organizePetName();
			savePetsOrganize("Name");
			break;
		case 2:
			organizePetId();
			savePetsOrganize("Id");
			break;
		case 3:
			organizePetDate();
			savePetsOrganize("Date");
			break;
		case 4:
			organizePetGender();
			savePetsOrganize("Gender");
			break;
		case 5:
			organizePetKindOfPet();
			savePetsOrganize("KindOfPet");
			break;
		}
	}
	
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
	
	public void organizePetId() {
		for (int i = 0; i < pets.size()-1; i++) {
			Pet menor = pets.get(i);
			int cual = i;
			for (int j = i+1; j < pets.size(); j++) {
				int compare = pets.get(j).compareId(menor);
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
	public void organizePetDate() {
		for (int i = 0; i < pets.size()-1; i++) {
			Pet menor = pets.get(i);
			int cual = i;
			for (int j = i+1; j < pets.size(); j++) {
				int compare = pets.get(j).compareDate(menor);
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
	public void organizePetGender() {
		for (int i = 0; i < pets.size()-1; i++) {
			Pet menor = pets.get(i);
			int cual = i;
			for (int j = i+1; j < pets.size(); j++) {
				int compare = pets.get(j).compareGen(menor);
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
	public void organizePetKindOfPet() {
		for (int i = 0; i < pets.size()-1; i++) {
			Pet menor = pets.get(i);
			int cual = i;
			for (int j = i+1; j < pets.size(); j++) {
				int compare = pets.get(j).compareKindOfPet(menor);
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
	/**
	 * this method compare the id by this person whit other 
	 * @param a
	 * @return
	 */
	public int compareId(People a) {
		int retorno = 0;
		int compare = id.compareTo(a.getId());
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
	
	public int compareQuantityPets(People a) {
		int retorno = 0;
		int compare1 = cantidadMascotas();
		int compare2 = a.cantidadMascotas();
		if(compare1 == compare2) {
			retorno = 0;
		}
		else if(compare1 < compare2) {
			retorno = -1;
		}
		else {
			retorno = 1;
		}
		return retorno;
	}
}
