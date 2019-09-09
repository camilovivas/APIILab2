package model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import exception.ExceptionNoFound;
import exception.ExceptionRegistry;

public class Investor {
	private ArrayList <Club> clubs;

	public Investor() {
		clubs = new ArrayList<Club>();
	}
	
	public void chargeClubs() throws IOException, ParseException {
		File archive = new File("./files/clubs/clubs.txt");
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archive));
			StringBuffer d = new StringBuffer();
			String texto;
			String salida = "";
			while((texto = lector.readLine())!= null) {
				salida += d.toString();
				String [] camposClub = salida.split(",");
				SimpleDateFormat change = new SimpleDateFormat("dd/mm/yyyy");
				Date dateClub = change.parse(camposClub[2]);
				Club e = new Club (camposClub[0], camposClub[1], dateClub, camposClub[3]);
				clubs.add(e);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		
	}
	/**
	 * this method save the attributes from all clubs in a archive txt
	 */
	public void saveClubs() {
		File archive = new File("./files/clubs/clubs.txt");
		String save = "";
		try {
			FileWriter escritor = new FileWriter(archive);
			for (int i = 0; i < clubs.size()-1; i++) {
				//atributos a guardar
				String name = clubs.get(i).getName();
				String id = clubs.get(i).getId();
				Date creationDate = clubs.get(i).getCreationDate();
				String kindOfPet = clubs.get(i).getKindOfPet();
				//guardar
				BufferedWriter s = new BufferedWriter(escritor);
				save += (name+","+id+","+creationDate+","+kindOfPet+"\n");
				if(i == (clubs.size())-2){
					s.write(save);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addClubs(Club a) {
		clubs.add(a);
		saveClubs();
	}
	
	public void organizeClubs(int method) {
		switch(method) {
		case 1:
			organizeClubsName();
		case 2:
			organizeClubsId();
		case 3:
			organizeClubsCreationDate();
		case 4:
			organizeClubskindOfpet();
		case 5:
			organizeClubsWhitMorePeople();
		}
	}
	
	public void organizeClubsName() {
		
	}
	public void organizeClubsId() {
		
	}
	public void organizeClubsCreationDate() {
		
	}
	public void organizeClubskindOfpet() {
		
	}
	public void organizeClubsWhitMorePeople() {
		
	}
	
	public void addPeople(People a, int club) throws ExceptionRegistry {
		if(exist(a) == false){
			clubs.get(club).addPeople(a);
		}
		else {
			throw new ExceptionRegistry(a.getName());
		}
	}
	
	public boolean exist(People a) {
		boolean found = false;
		for (int i = 0; i < clubs.size()-1 && !found; i++) {
			if(clubs.get(i).exist(a.getId())== true) {
				found = true;
			}
		}
		return found;
	}
	
	public String showNameClubs(){
		String names = "";
		for (int i = 0; i < clubs.size()-1; i++) {
			names += "seleccione el numero del club que desea registrarse"+"\n";
			names += i+". "+clubs.get(i).getName()+"\n";
		}
		return names;
	}
	
	public Date configDate(String date) throws ParseException {
		SimpleDateFormat a = new SimpleDateFormat("dd/mm/yyyy");
		Date b = a.parse(date);
		return b;
	}
	
	public void addPet(String idOwner, Pet b) throws ExceptionRegistry, ExceptionNoFound {
		boolean found = false;
		for (int i = 0; i < clubs.size()-1 && !found; i++) {
			People a = clubs.get(i).findPeople(idOwner);
			if(a != null){
				found = true;
				a.addPet(b);
			}
			else if(i == clubs.size()-2){
				throw new ExceptionNoFound(idOwner);
			}
		}	
	}
	// para eliminar una persona de un club 
}
