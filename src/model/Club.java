package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	public void chargePeople() throws FileNotFoundException {
			String persona = "";
		File archive = new File("C:");
		try {
			BufferedReader c = new BufferedReader(new FileReader(archive));
			StringBuffer d = new StringBuffer();
			String texto;
			while((texto = c.readLine()) != null){
				persona += c.toString();
				String [] camposPersonas = persona.split(",");
				People ensayo = new People(camposPersonas[0], camposPersonas[1], camposPersonas[2], camposPersonas[3] );
				owners.add(ensayo);
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
