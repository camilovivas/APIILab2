package model;

import java.io.Serializable;
import java.util.Date;

public class Pet implements Serializable{
	private String id;
	private String name;
	private Date dateBorn;
	private String type;
	private String kindOfPet;
	
	public Pet(String id, String name, Date dateBorn, String type, String kindOfPet) {
		this.id = id;
		this.name = name;
		this.dateBorn = dateBorn;
		this.type = type;
		this.kindOfPet = kindOfPet;
	}
	
	
	
}
