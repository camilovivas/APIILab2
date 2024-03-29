package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
		File archive = new File("./files/PERSONAS.csv");
		try {
			BufferedReader c = new BufferedReader(new FileReader(archive));
			String texto;
			while((texto = c.readLine()) != null){
				persona = texto;
				String [] camposPersonas = persona.split(",");
				SimpleDateFormat change = new SimpleDateFormat("mm/dd/yyyy");
				Date fechaDate = change.parse(camposPersonas[3]);
				People ensayo = new People(camposPersonas[1], camposPersonas[2], camposPersonas[0], fechaDate, camposPersonas[4] );
				owners.add(ensayo);
			}
			c.close();
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
			file = new FileOutputStream("./files/personas/personas"+name+".arc");
			ObjectOutputStream object = new ObjectOutputStream(file);
			object.writeObject(owners);
			object.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method import the object from archive serializado
	 * @throws ClassNotFoundException
	 */
	public void importPeople() throws ClassNotFoundException {
		File archiveSerializado = new File("./files/personas/personas"+name+".arc");
		try {
			ObjectInputStream object = new ObjectInputStream(new FileInputStream(archiveSerializado));
			owners = (ArrayList<People>) object.readObject();
			object.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//this method only used 1 times
	public void addPetToPeople() throws IOException, ParseException, ExceptionRegistry {
		String pet ="";
		File archive = new File("./files/MASCOTAS.csv");
		try {
			BufferedReader d = new BufferedReader(new FileReader(archive));
			String tex;
			for (int i = 0; i < owners.size(); i++) {
				int contable = 0;
				boolean ya = false;
				int numberRandom = (int) (Math.random()*3);
				if(numberRandom == 0) {
					numberRandom = 1;
				}
				while((tex = d.readLine())!= null && !ya) {
					pet = tex;
					String [] camposPet = pet.split(",");
					SimpleDateFormat change =  new SimpleDateFormat("dd/mm/yyyy");
					Date fechaDate = change.parse(camposPet[2]);
					Pet e = new Pet(camposPet[0],camposPet[1],fechaDate,camposPet[3],camposPet[4]);
					owners.get(i).addPet(e);
					contable++;
					if(contable == numberRandom) {
						ya = true;
					}
				}
			}
			d.close();
			savePeople();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * this method find a people using find binaria
	 * @param id
	 * @return
	 */
	public People findPeople(String id) {
		int inicio = 0;
		int fin = owners.size();
		People retorno = null;
		boolean found = false;
		organizePeopleId();
		while(inicio< fin && !found) {
			int medio = (inicio+fin)/2;
			if(owners.get(medio).getId().equals(id)) {
				found = true;
				retorno = owners.get(medio);
			}
			else if(owners.get(medio).getId().compareTo(id)>0) {
				fin = medio-1; 
			}
			else {
				inicio = medio+1;
			}
		}
		return retorno;
	}
	
	
	/**
	 * this method delete people by club
	 * @param id id by people to delete
	 * @throws FileNotFoundException
	 */
	public void removePeople(String id) throws FileNotFoundException {
		int inicio = 0;
		int fin = owners.size();
		organizePeopleId();
		boolean found = false;
		while(inicio<= fin && !found) {
			int medio = (inicio+fin)/2;
			if(owners.get(medio).getId().equals(id)) {
				found = true;
				owners.remove(medio);
				savePeople();
			}
			else if(owners.get(medio).getId().compareTo(id)>0) {
				fin = medio-1; 
			}
			else {
				inicio = medio+1;
			}
		}
	}
	
	/**
	 * this method check if a person exist in the club
	 * @param id
	 * @return
	 */
	public boolean exist(String id) {
		boolean exist = false;
		for(int i = 0; i<owners.size() && !exist; i++) {
			if(owners.get(i).getId().equals(id)) {
				exist = true;
			}
		}
		return exist;
	}
	
	/**
	 * this method add People to the Club 
	 * @param a
	 * @throws ExceptionRegistry
	 */
	public void addPeople(People a){
		owners.add(a);	
	}
	
	/**
	 * this method look quantity of people from the club 
	 * @return
	 */
	public int quantityPeople() {
		return owners.size();
	}
	
	/**
	 * this method save listings by People organize
	 * @param nameMethod
	 * @throws IOException
	 */
	public void savePeopleOrganize(String nameMethod) throws IOException {
		File archive = new File("./files/ordenamientos/organizePeople/organize"+nameMethod+".txt");
		String save = "";
		BufferedWriter a = new BufferedWriter(new FileWriter(archive));
		for (int i = 0; i < owners.size(); i++) {
			String name = owners.get(i).getName();
			String lastName = owners.get(i).getLastName();
			String id = owners.get(i).getId();
			Date dateOfBorn = owners.get(i).getDateOfBorn();
			String pet = owners.get(i).getPetOfPreference();
			save += (name+" "+ lastName+" " + id+" " + dateOfBorn+" " + pet +"\n");
		}
		a.write(save);
		a.close();
	}
	
	//	ORDENADORES
	/**
	 * this method organize and save people
	 * @param method
	 * @throws IOException
	 */
	public void organizePeople(int method) throws IOException {
		switch(method) {
		case 1:
			organizePeopleName();
			savePeopleOrganize("Name");
			break;
		case 2:
			organizePeopleLastName();
			savePeopleOrganize("LastName");
			break;
		case 3:
			organizePeopleId();
			savePeopleOrganize("Id");
			break;
		case 4:
			organizeDateOfBorn();
			savePeopleOrganize("Date");
			break;
		case 5:
			organizePetOfpreference();
			savePeopleOrganize("TypePet");
			break;
		case 6:
			organizePeopleWhitMorePets();
			savePeopleOrganize("Quantity_Pets");
			break;
		}
	}
	
	/**
	 * This method organize the people by name using insertion sort
	 */
	public void organizePeopleName() {
		for (int i = 1; i < owners.size(); i++) {
			for (int j = i; j > 0; j--) {
				int compare = owners.get(j-1).compareName(owners.get(j));
				if(compare == 1) {
					People tem = owners.get(j);
					owners.set(j, owners.get(j-1));
					owners.set(j-1, tem);
				}
			}
		}
		
	}
	
	/**
	 * This method organize the people by  last name using insertion sort
	 */
	public void organizePeopleLastName() {
		for (int i = 1; i < owners.size(); i++) {
			for (int j = i; j > 0; j--) {
				int compare = owners.get(j-1).comparelastName(owners.get(j));
				if(compare == 1) {
					People tem = owners.get(j);
					owners.set(j, owners.get(j-1));
					owners.set(j-1, tem);
				}
			}
		}
	}
	
	
	/**
	 * This method organize the people by name using insertion sort
	 */
	public void organizePeopleId() {
		for (int i = 1; i < owners.size(); i++) {
			for (int j = i; j > 0; j--) {
				int compare = owners.get(j-1).compareId(owners.get(j));
				if(compare == 1) {
					People tem = owners.get(j);
					owners.set(j, owners.get(j-1));
					owners.set(j-1, tem);
				}
			}
		}
	}
	
	/**
	 * This method organize the people by Date using insertion sort
	 */
	public void organizeDateOfBorn() {
		for (int i = 1; i < owners.size(); i++) {
			for (int j = i; j > 0; j--) {
				int compare = owners.get(j-1).compareDate(owners.get(j));
				if(compare == 1) {
					People tem = owners.get(j);
					owners.set(j, owners.get(j-1));
					owners.set(j-1, tem);
				}
			}
		}
	}
	
	/**
	 * This method organize the people by pet of preference using insertion sort
	 */
	public void organizePetOfpreference() {
		for (int i = 1; i < owners.size(); i++) {
			for (int j = i; j > 0; j--) {
				int compare = owners.get(j-1).comparePetOfPreference(owners.get(j));
				if(compare == 1) {
					People tem = owners.get(j);
					owners.set(j, owners.get(j-1));
					owners.set(j-1, tem);
				}
			}
		}
	}
	
	/**
	 * This method organize the people by quantity of pets using insertion sort
	 */
	public void organizePeopleWhitMorePets() {
		for (int i = 1; i < owners.size(); i++) {
			for (int j = i; j > 0; j--) {
				int compare = owners.get(j-1).compareQuantityPets(owners.get(j));
				if(compare == 1) {
					People tem = owners.get(j);
					owners.set(j, owners.get(j-1));
					owners.set(j-1, tem);
				}
			}
		}
	}
	
	
	//COMPARACIONES
	public int compareName(Club a) {
		int retorno = 0;
		int compare = name.compareToIgnoreCase(a.getName());
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
	public int compareId(Club a) {
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
	public int compareDate(Club a) {
		int retorno = 0;
		int compare =creationDate.compareTo(a.getCreationDate());
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
	public int compareKindOfPet(Club a) {
		int retorno = 0;
		int compare = kindOfPet.compareToIgnoreCase(a.getKindOfPet());
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
	
	public int compareQuantityPeople(Club a) {
		int retorno = 0;
		int compare1 = quantityPeople();
		int compare2 = a.quantityPeople();
		if(compare1 == compare2) {
			retorno = 0;
		}
		else if (compare1 < compare2) {
			retorno = -1;
		}
		else {
			retorno = 1;
		}
		return retorno;
	}
	

	
}
