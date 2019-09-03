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

public class Investor {
	private ArrayList <Club> clubs;

	public Investor() {
		clubs = new ArrayList<Club>();
	}
	
	public void chargeClubs() throws IOException, ParseException {
		File archive = new File("C:");
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
//	como es una prueba de esto?
	public void saveClubs() {
		File archive = new File("C:");
		try {
			FileWriter escritor = new FileWriter(archive);
			for (int i = 0; i < clubs.size(); i++) {
				//atributos a guardar
				String name = clubs.get(i).getName();
				String id = clubs.get(i).getId();
				Date creationDate = clubs.get(i).getCreationDate();
				String kindOfPet = clubs.get(i).getKindOfPet();
				//guardar
				BufferedWriter s = new BufferedWriter(escritor);
				s.write(name+","+id+","+creationDate+","+kindOfPet);//presiento que se va mantener reescribiendo y al final va a quedar una sola linea
//				escritor.write(name+","+id+","+creationDate+","+kindOfPet);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
