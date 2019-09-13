package model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author camilo
 *
 */
public class Pet implements Serializable{
	private String id;
	private String name;
	private Date dateBorn;
	private String gender;
	private String kindOfPet;
	
	public Pet(String id, String name, Date dateBorn, String gender, String kindOfPet) {
		this.id = id;
		this.name = name;
		this.dateBorn = dateBorn;
		this.gender = gender;
		this.kindOfPet = kindOfPet;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateBorn() {
		return dateBorn;
	}

	public void setDateBorn(Date dateBorn) {
		this.dateBorn = dateBorn;
	}

	public String getGender() {
		return gender;
	}

	public void setType(String gender) {
		this.gender = gender;
	}

	public String getKindOfPet() {
		return kindOfPet;
	}

	public void setKindOfPet(String kindOfPet) {
		this.kindOfPet = kindOfPet;
	}
	
	//COMPARACIONES
	
	/**
	 * this method compare the name whit other pet
	 * @param a pet to compare
	 * @return 1 if is higher, 0 if is equals or -1 if is less
	 */
	public int compareName(Pet a ) {
		int retorno = 0;
		int compare = name.compareToIgnoreCase(a.getName());
		if(compare<0) {
			retorno = -1;
		}
		else if(compare ==0) {
			retorno = 0;
		}
		else {
			retorno = 1;
		}
		
		return retorno;
	}
	
	/**
	 * this method compare the id whit other pet
	 * @param a pet to compare
	 * @return 1 if is higher, 0 if is equals or -1 if is less
	 */
	public int compareId(Pet a ) {
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
	
	/**
	 * this method compare the Date whit other pet
	 * @param a pet to compare
	 * @return 1 if is higher, 0 if is equals or -1 if is less
	 */
	public int compareDate(Pet a) {
		int retorno = 0;
		int compare = dateBorn.compareTo(a.getDateBorn());
		if(compare <0) {
			retorno = -1;
		}
		else if (compare == 0) {
			retorno = 0;
		}
		else {
			retorno = 1;
		}
		return retorno;
	}
	
	/**
	 * this method compare the gender whit other pet
	 * @param a pet to compare
	 * @return 1 if is higher, 0 if is equals or -1 if is less
	 */
	public int compareGen(Pet a) {
		int retorno = 0;
		int compare = gender.compareToIgnoreCase(a.getGender());
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
	 * this method compare the kind of pet whit other pet
	 * @param a pet to compare
	 * @return 1 if is higher, 0 if is equals or -1 if is less
	 */
	public int compareKindOfPet(Pet a) {
		int retorno = 0;
		int compare = kindOfPet.compareToIgnoreCase(a.kindOfPet);
		if(compare <0) {
			retorno = -1;
		}
		else if (compare == 0) {
			retorno = 0;
		}
		else {
			retorno = 1;
		}
		return retorno;
	}
	
}
