package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Club {
	private String name;
	private String id;
	private Date creationDate;
	private String kindOfPet;
	private ArrayList <People> owners;
	
	public Club(String name, String id, Date creationDate, String kindOfPet) {
		this.name = name;
		this.id = id;
		this.creationDate = creationDate;
		this.kindOfPet = kindOfPet;
		owners = new ArrayList<People>();
	}
	
	public void chargePeople() {
		File archive = new File("C:");
		
		
		
	}
	
}
