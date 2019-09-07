package ui;
import java.io.Reader;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import exception.ExceptionRegistry;
import model.*;

public class Main {
	
	private Investor relation;
	private Scanner	reader;
	
	public Main() throws ParseException, ExceptionRegistry {
		reader = new Scanner(System.in);
		relation = new Investor();
		int i = menu();
		options(i);
	}
	
	public static void main(String[] args) throws ParseException, ExceptionRegistry {
		Main m = new Main();
		

	}
	
	public int menu() {
		int option = 0;
			System.out.println("ingrese la opcion que desea realizar");
			System.out.println("1. registrar un club");
			System.out.println("2. registrar una mascota");
			System.out.println("3. registrar una persona");
			System.out.println("4. eliminar un club");
			System.out.println("5. eliminar una mascota");
			System.out.println("6. eliminar una persona");
			System.out.println("7. generar listados ordenados");
			option =  reader.nextInt();
		return option;
	}
	
	public void options(int i) throws ParseException, ExceptionRegistry {
		switch(i) {
		case 1:
		case 2:
		case 3:
			int h = namesClubs();
			People a = attributesPeople();
			relation.addPeople(a, h);
		case 4:
		case 5:
		case 6:
			
		
		}
	}
	
	public int namesClubs() {
		System.out.println(relation.showNameClubs());
		int club = reader.nextInt();
		return club;
	}
	
	public People attributesPeople() throws ParseException {
		System.out.println("ingrese el nombre");
		String name = reader.next();
		System.out.println("ingrese el apellido");
		String lastName = reader.next();
		System.out.println("ingrese el id");
		String id = reader.next();
		System.out.println("ingrese la fecha de nacimiento"+"/n"+"EJEMPLO: DD/MM/YYYY");
		String date = reader.next();
		Date a = relation.configDate(date);
		System.out.println("ingrese mascota favorita");
		String pet = reader.next();
		People p = new People(name, lastName, id, a, pet);
		return p;
	}
	
	public void case6() {
		System.out.println("ingrese el numero de la opcion que desea realizar");
		System.out.println("1. generar listados de personas");
		System.out.println("2. generar listados de mascotas");
		System.out.println("3. generar listados de clubes");
		int option = reader.nextInt();
		
		switch(option) {
		case 1:
			System.out.println("1. generar listado por nombre");
			System.out.println("2. generar listado por apellido");
			System.out.println("3. generar listado por identificacion");
			System.out.println("4. generar listado por fecha de nacimiento");
			System.out.println("5. generar listado por mascota de preferencia");
			System.out.println("6. generar listado de personas con mayor numero de mascotas");
			
		case 2:
		case 3:
		case 4:
		}
		
	}

}
