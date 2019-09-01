package model;

import java.io.Serializable;
import java.util.Date;

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
	
	
	
}
