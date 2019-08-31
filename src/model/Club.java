package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author camilo
 *
 */
public class Club {
	private String name;
	private String id;
	private Date creationDate;
	private String kindOfPet;
	private ArrayList <People> owners;
	
	/**
	 * @param name
	 * @param id
	 * @param creationDate
	 * @param kindOfPet
	 */
	public Club(String name, String id, Date creationDate, String kindOfPet) {
		this.name = name;
		this.id = id;
		this.creationDate = creationDate;
		this.kindOfPet = kindOfPet;
		owners = new ArrayList<People>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getKindOfPet() {
		return kindOfPet;
	}

	public void setKindOfPet(String kindOfPet) {
		this.kindOfPet = kindOfPet;
	}

	public ArrayList<People> getOwners() {
		return owners;
	}

	public void setOwners(ArrayList<People> owners) {
		this.owners = owners;
	}

	public void chargePeople() throws FileNotFoundException, ParseException {
		String persona = "";
		File archive = new File("C:");
		try {
			BufferedReader c = new BufferedReader(new FileReader(archive));
			StringBuffer d = new StringBuffer();
			String texto;
			while((texto = c.readLine()) != null){
				persona += c.toString();
				String [] camposPersonas = persona.split(",");
				SimpleDateFormat change = new SimpleDateFormat("dd/mm/yyyy");
				Date fechaDate = change.parse(camposPersonas[3]);
				People ensayo = new People(camposPersonas[0], camposPersonas[1], camposPersonas[2], fechaDate, camposPersonas[4] );
				owners.add(ensayo);
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void savePeople() throws FileNotFoundException {
		FileOutputStream file;
		try {
			file = new FileOutputStream("./files/personas.arc");
			ObjectOutputStream object = new ObjectOutputStream(file);
			object.writeObject(owners);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param id
	 * @return
	 */
	public boolean exist(String id) {
		boolean exist = false;
		for(int i = 0; i>owners.size(); i++) {
			if(owners.get(i).getId().equals(id)) {
				exist = true;
			}
		}
		return exist;
	}
	
	/**
	 * @param a
	 * @throws ExceptionRegistry
	 */
	public void addPeople(People a) throws ExceptionRegistry{
		
		if(exist(a.getId()) == false) {
			owners.add(a);
		}
		else {
			throw new ExceptionRegistry(a.getName());
		}
	
	}
	
	
	
}
