package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

	/**
	 * this method import the clubs from archive plane
	 * @throws IOException
	 * @throws ParseException
	 */
	public void chargeClubs() throws IOException, ParseException {
		File archive = new File("./files/clubs/clubs.txt");
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archive));
			String texto;
			String salida = "";
			while((texto = lector.readLine())!= null) {
				salida = texto;
				String [] camposClub = salida.split(",");
				SimpleDateFormat change = new SimpleDateFormat("dd/MM/yyyy");
				Date dateClub = change.parse(camposClub[2]);
				Club e = new Club (camposClub[0], camposClub[1], dateClub, camposClub[3]);
				clubs.add(e);
			}
			lector.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * this method import the people of all clubs from archive serializable
	 */
	public void chargePeopleAndPet() {
		for (int i = 0; i < clubs.size(); i++) {
			try {
				clubs.get(i).importPeople();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
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
			BufferedWriter s = new BufferedWriter(escritor);
			for (int i = 0; i < clubs.size(); i++) {
				//atributos a guardar
				String name = clubs.get(i).getName();
				String id = clubs.get(i).getId();
				Date creationDate = clubs.get(i).getCreationDate();
				SimpleDateFormat a = new SimpleDateFormat("dd/MM/yyyy");
				String date = a.format(creationDate);
				String kindOfPet = clubs.get(i).getKindOfPet();
				//guardar
				save += (name+","+id+","+date+","+kindOfPet+"\n");
			}
			s.write(save);
			s.close();
			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * this method save the clubs organized
	 * @param nameMethod name by archive
	 * @throws IOException
	 */
	public void saveClubsOrganize(String nameMethod) throws IOException {
		File archive = new File("./files/ordenamientos/organizeClub/organize"+nameMethod+".txt");
		String save = "";
		BufferedWriter a = new BufferedWriter(new FileWriter(archive));
		for (int i = 0; i < clubs.size(); i++) {
			String name = clubs.get(i).getName();
			String id = clubs.get(i).getId();
			Date dateOfBorn = clubs.get(i).getCreationDate();
			String pet = clubs.get(i).getKindOfPet();
			save += (name+" "+  id+" " + dateOfBorn+" " + pet +"\n");
		}
		a.write(save);
		a.close();
	}
	
	
	/**
	 * this method save an add a club
	 * @param a club to add
	 */
	public void addClubs(Club a) {
		clubs.add(a);
		saveClubs();
	}
	
	
	/**
	 * this method add people to a club 
	 * @param a people to add
	 * @param club club has been add
	 * @throws ExceptionRegistry
	 */
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
	 * @param a people to find
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
	
	/**
	 * this method show names by all clubs
	 * @return
	 */
	public String showNameClubs(){
		String names = "";
		for (int i = 0; i < clubs.size(); i++) {
			names += i+". "+clubs.get(i).getName()+"\n";
		}
		return names;
	}
	
	/**
	 * this method convert String to Date
	 * @param date String to convert
	 * @return String converted
	 * @throws ParseException
	 */
	public Date configDate(String date) throws ParseException {
		SimpleDateFormat a = new SimpleDateFormat("dd/mm/yyyy");
		Date b = a.parse(date);
		return b;
	}
	
	/**
	 * this method add pets by the id of its owner
	 * @param idOwner identification by owner
	 * @param b pet to add
	 * @throws ExceptionRegistry
	 * @throws ExceptionNoFound
	 */
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
	
	/**
	 * this method search a person in all clubs
	 * @param id
	 * @return
	 * @throws ExceptionNoFound
	 */
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
	
//	NAME ARCHIVE
	
	/**
	 * this method show where are the archive.txt whit the people organized
	 * @param i type of sort
	 * @return a String whit the direction 
	 */
	public String folderSavePeople(int i) {
		String msj = "";
		switch(i) {
		case 1:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePeople/organizeName.txt";
			break;
		case 2:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePeople/organizeLastName.txt";
			break;
		case 3:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePeople/organizeId.txt";
			break;
		case 4:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePeople/organizeDate.txt";
			break;
		case 5:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePeople/organizeTypePet.txt";
			break;
		case 6:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePeople/organizeQuantity_Pets.txt";
			break;
		}
		return msj;
	}
	
	/**
	 * this method show where are the archive.txt whit the pets organized
	 * @param i type of sort
	 * @return a String whit the direction 
	 */
	public String folderSavePets(int i) {
		String msj = "";
		switch(i) {
		case 1:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePet/organizeName.txt";
			break;
		case 2:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePet/organizeId.txt";
			break;
		case 3:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePet/organizeDate.txt";
			break;
		case 4:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePet/organizeGender.txt";
			break;
		case 5:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizePet/organizeKindOfPet.txt";
			break;
		}
		return msj;
	}
	
	/**
	 * this method show where are the archive.txt whit the clubs organized
	 * @param i type of sort
	 * @return a String whit the direction 
	 */
	public String folderSaveClubs(int i) {
		String msj = "";
		switch(i) {
		case 1:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizeClub/organizeName.txt";
			break;
		case 2:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizeClub/organizeId.txt";
			break;
		case 3:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizeClub/organizeDate.txt";
			break;
		case 4:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizeClub/organizePet.txt";
			break;
		case 5:
			msj += "se ha generado el listado ordenado... lo puedes encontrar en la carpeta ordenamientos/organizeClub/organizeQuantity_People.txt";
			break;
		}
		return msj;
	}
//	ORDENADORES
	
	/**
	 * -this method organize and save clubs
	 * @param method criterio a ordenar
	 * @throws IOException
	 */
	public void organizeClubs(int method) throws IOException {
		switch(method) {
		case 1:
			organizeClubsName();
			saveClubsOrganize("Name");
			break;
		case 2:
			organizeClubsId();
			saveClubsOrganize("Id");
			break;
		case 3:
			organizeClubsCreationDate();
			saveClubsOrganize("Date");
			break;
		case 4:
			organizeClubskindOfpet();
			saveClubsOrganize("Pet");
			break;
		case 5:
			organizeClubsWhitMorePeople();
			saveClubsOrganize("Quantity_People");
			break;
		}
	}
	
	
	/**
	 * This method organize the clubs by name using the method bubble sort
	 */
	public void organizeClubsName() {
		for (int i = clubs.size(); i > 0 ; i--) {
			for (int j = 0; j  < i-1; j++) {
				int compare = clubs.get(j).compareName(clubs.get(j+1));
				if (compare == 1) {
					Club temp = clubs.get(j);
					clubs.set(j, clubs.get(j+1));
					clubs.set(j+1, temp);
				}
			}
		}
	}
	
	/**
	 * This method organize the clubs by identification using the method bubble sort
	 */
	public void organizeClubsId() {
		for (int i = clubs.size(); i > 0 ; i--) {
			for (int j = 0; j  < i-1; j++) {
				int compare = clubs.get(j).compareId(clubs.get(j+1));
				if (compare == 1) {
					Club temp = clubs.get(j);
					clubs.set(j, clubs.get(j+1));
					clubs.set(j+1, temp);
				}
			}
		}
	}
	
	/**
	 * This method organize the clubs by date using the method bubble sort
	 */
	public void organizeClubsCreationDate() {
		for (int i = clubs.size(); i > 0 ; i--) {
			for (int j = 0; j  < i-1; j++) {
				int compare = clubs.get(j).compareDate(clubs.get(j+1));
				if (compare == 1) {
					Club temp = clubs.get(j);
					clubs.set(j, clubs.get(j+1));
					clubs.set(j+1, temp);
				}
			}
		}
	}
	
	/**
	 * This method organize the clubs by kind of pet using the method bubble sort
	 */
	public void organizeClubskindOfpet() {
		for (int i = clubs.size(); i > 0 ; i--) {
			for (int j = 0; j  < i-1; j++) {
				int compare = clubs.get(j).compareKindOfPet(clubs.get(j+1));
				if (compare == 1) {
					Club temp = clubs.get(j);
					clubs.set(j, clubs.get(j+1));
					clubs.set(j+1, temp);
				}
			}
		}
	}
	
	/**
	 * This method organize the clubs by quantity of pets using the method bubble sort
	 */
	public void organizeClubsWhitMorePeople() {
		for (int i = clubs.size(); i > 0 ; i--) {
			for (int j = 0; j  < i-1; j++) {
				int compare = clubs.get(j).compareQuantityPeople(clubs.get(j+1));
				if (compare == 1) {
					Club temp = clubs.get(j);
					clubs.set(j, clubs.get(j+1));
					clubs.set(j+1, temp);
				}
			}
		}
	}
}

