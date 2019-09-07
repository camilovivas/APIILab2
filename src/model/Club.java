package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import exception.*;

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
	
	//this method only used 1 times
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
	
	/**
	 * this method save the ArrayList of peoples whit yours pets in a archive serializable
	 * @throws FileNotFoundException
	 */
	public void savePeople() throws FileNotFoundException {
		FileOutputStream file;
		try {
			file = new FileOutputStream("./files/personas"+name+".arc");
			ObjectOutputStream object = new ObjectOutputStream(file);
			object.writeObject(owners);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//this method only used 1 times
	public void addPetToPeople() throws IOException, ParseException, ExceptionRegistry {
		String pet ="";
		File archive = new File("C:");
		try {
			BufferedReader d = new BufferedReader(new FileReader(archive));
			String tex;
			for (int i = 0; i < owners.size(); i++) {
				int numberRandom = (int) (Math.random()*3);
				while((tex = d.readLine())!= null && numberRandom>0) {
					pet += d.toString();
					String [] camposPet = pet.split(",");
					SimpleDateFormat change =  new SimpleDateFormat("dd/mm/yyyy");
					Date fechaDate = change.parse(camposPet[2]);
					Pet e = new Pet(camposPet[0],camposPet[1],fechaDate,camposPet[3],camposPet[4]);
					owners.get(i).addPet(e);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method import the object from archive serializado
	 * @throws ClassNotFoundException
	 */
	public void importPeople() throws ClassNotFoundException {
		File archiveSerializado = new File("C:");
		try {
			ObjectInputStream object = new ObjectInputStream(new FileInputStream(archiveSerializado));
			owners = (ArrayList<People>) object.readObject();
			object.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	
	public void organizePeople(int method) {
		switch(method) {
		case 1:
			organizePeopleName();
		case 2:
			organizePeopleLastName();
		case 3:
			organizePeopleId();
		case 4:
			organizeDateOfBorn();
		case 5:
			organizePetOfpreference();
		case 6:
			organizePeopleWhitMorePets();
		}
	}
	
	public void organizePeopleName() {
		
		
	}
	
	public void organizePeopleLastName() {
		
	}
	
	public void organizePeopleId() {
		
	}
	
	public void organizeDateOfBorn() {
		
	}
	
	public void organizePetOfpreference() {
		
	}
	
	public void organizePeopleWhitMorePets() {
		
	}
	
	//remove
	
}
