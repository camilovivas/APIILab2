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
				persona += c.toString();
				String [] camposPersonas = persona.split(",");
				SimpleDateFormat change = new SimpleDateFormat("mm/dd/yyyy");
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
			file = new FileOutputStream("./files/personas/personas"+name+".arc");
			ObjectOutputStream object = new ObjectOutputStream(file);
			object.writeObject(owners);
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
					if(i == owners.size()-1) {
						savePeople();
					}
				}
			}
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
		while(inicio<= fin && !found) {
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
	public void removePeople(String id) throws FileNotFoundException {
		int inicio = 0;
		int fin = owners.size();
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
		for(int i = 0; i>owners.size() && !exist; i++) {
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
	public void savePeopleOrganize(String nameMethod) throws IOException {//problemas al guardar
		File archive = new File("./files/ordenamientos/organizePeople/organize"+nameMethod+".txt");
		String save = "";
		for (int i = 0; i < owners.size(); i++) {
			BufferedWriter a = new BufferedWriter(new FileWriter(archive));
			String name = owners.get(i).getName();
			String lastName = owners.get(i).getLastName();
			String id = owners.get(i).getId();
			Date dateOfBorn = owners.get(i).getDateOfBorn();
			String pet = owners.get(i).getPetOfPreference();
			save += (name+ lastName + id + dateOfBorn + pet +"\n");
			if(i == owners.size()-1) {
				a.write(save);//aqui
			}
		}
	}
	
	//	ORDENAMIENTO
	public void organizePeople(int method) throws IOException {
		switch(method) {
		case 1:
			organizePeopleName();
			savePeopleOrganize("Name");
		case 2:
			organizePeopleLastName();
			savePeopleOrganize("LastName");
		case 3:
			organizePeopleId();
			savePeopleOrganize("Id");
		case 4:
			organizeDateOfBorn();
			savePeopleOrganize("Date");
		case 5:
			organizePetOfpreference();
			savePeopleOrganize("Pet");
		case 6:
			organizePeopleWhitMorePets();
			savePeopleOrganize("More_Pets");
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
