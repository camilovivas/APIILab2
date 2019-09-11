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
	
	public ArrayList<Club> getClubs() {
		return clubs;
	}

	public void setClubs(ArrayList<Club> clubs) {
		this.clubs = clubs;
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
	public void saveClubs() {//ERROR AL GUARDAR
		File archive = new File("./files/clubs/clubs.txt");
		String save = "";
		try {
			FileWriter escritor = new FileWriter(archive);
			BufferedWriter s = new BufferedWriter(escritor);
			for (int i = 0; i < clubs.size(); i++) {
				//atributos a guardar
				String name = clubs.get(i).getName();
				String id = clubs.get(i).getId();
				Date creationDate = clubs.get(i).getCreationDate();
				String kindOfPet = clubs.get(i).getKindOfPet();
				//guardar
				save += (name+","+id+","+creationDate+","+kindOfPet+"\n");
			}
			s.write(save);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addClubs(Club a) {
		clubs.add(a);
		saveClubs();
	}
	
//	ORDENADORES
	public void organizeClubs(int method) {
		switch(method) {
		case 1:
			organizeClubsName();
			break;
		case 2:
			organizeClubsId();
			break;
		case 3:
			organizeClubsCreationDate();
			break;
		case 4:
			organizeClubskindOfpet();
			break;
		case 5:
			organizeClubsWhitMorePeople();
			break;
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
	
	/**
	 * this method check if exist a person in all clubs
	 * @param a
	 * @return
	 */
	public boolean exist(People a) {
		boolean found = false;
		for (int i = 0; i < clubs.size() && !found; i++) {
			if(clubs.get(i).exist(a.getId())== true) {
				found = true;
			}
		}
		return found;
	}
	
//	REMOVE
	public void removePeople(String id) throws ExceptionNoFound, FileNotFoundException {
		for (int i = 0; i < clubs.size(); i++) {
			People a = clubs.get(i).findPeople(id);
			if(a != null) {
				a.removeAllPets();
				clubs.get(i).removePeople(id);
			}
			else if(i == clubs.size()-2){
				throw new ExceptionNoFound(id);
			}
		}
	}
	
	public void removeClub(int i) {
		clubs.remove(i);
	}
	
	public String showNameClubs(){
		String names = "";
		for (int i = 0; i < clubs.size(); i++) {
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
		for (int i = 0; i < clubs.size() && !found; i++) {
			People a = clubs.get(i).findPeople(idOwner);
			if(a != null){
				found = true;
				a.addPet(b);
			}
			else if(i == clubs.size()-1){
				throw new ExceptionNoFound(idOwner);
			}
		}	
	}
	
	public People searchPeopleAllClubs(String id) throws ExceptionNoFound{
		People retorno = null;
		boolean found = false;
		for (int i = 0; i < clubs.size() && !found; i++) {
			retorno = clubs.get(i).findPeople(id);
			if(retorno != null) {
				found = true;
			}
			else if(i == clubs.size()-1){
				throw new ExceptionNoFound(id);
			}
		}
		return retorno;
	}
	
//	name archive
	
	public String folderSavePeople(int i) {
		String msj = "";
		switch(i) {
		case 1:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePeople/organizeName.txt";
		case 2:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePeople/organizeLastName.txt";
		case 3:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePeople/organizeId.txt";
		case 4:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePeople/organizeDate.txt";
		case 5:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePeople/organizePet.txt";
		case 6:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePeople/organize_Pets.txt";
		
		}
		return msj;
		
	}
}
