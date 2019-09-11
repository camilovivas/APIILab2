package ui;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import exception.ExceptionNoFound;
import exception.ExceptionRegistry;
import model.*;

public class Main {
	
	private Investor relation;
	private Scanner	reader;
	
	public Main() throws ParseException, ExceptionRegistry, ExceptionNoFound {
		reader = new Scanner(System.in);
		relation = new Investor();
		int i = menu();
		options(i);
	}
	
	public static void main(String[] args) throws ParseException, ExceptionRegistry, ExceptionNoFound {
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
	
	public void options(int i) throws ParseException, ExceptionRegistry, ExceptionNoFound {
		switch(i) {
		case 1:
			case1();//problemas
			break;
		case 2:
			case2();
			break;
		case 3:
			int h = namesClubs();
			People a = attributesPeople();
			relation.addPeople(a, h);
			break;
		case 4:
			case4();
			break;
		case 5:
			case5();
			break;
		case 6:
			case6();
			break;
		case 7:
		
		}
	}
	
	public void case1() throws ParseException{
		System.out.println("ingrese el nombre del club");
		String name = reader.next();
		System.out.println("ingrese el numero de identificacion del club");
		String id = reader.next();
		String a;
		Date date = null;
		try {//problema:no repite
			System.out.println("ingrese la fecha de creacion del club"+"\n"+"EJEMPLO: DD/MM/YYYY");
			a = reader.next();
			date =relation.configDate(a);
		} catch (ParseException e) {
			System.out.println("ingrese bien la fecha");
		}
		System.out.println("ingrese el tipo de mascotas");
		String type = reader.next();
		Club c = new Club(name, id, date, type);
		relation.addClubs(c);
	}
	
	public void case4() {
		System.out.println("seleccione el numero del club que desea eliminar"+"\n"+relation.showNameClubs());
		int club = reader.nextInt();
		relation.removeClub(club);
		
	}
	
	public void case5() {
		System.out.println("ingrese el id del dueño de la mascota");
		String idOwner = reader.next();
		try {
			People e = relation.searchPeopleAllClubs(idOwner);
			System.out.println("seleccione el numero de la mascota a eliminar"+e.ShowNamesPets());
			int index = reader.nextInt();
			e.removePet(index);
			System.out.println("se ha eliminado...");
		} catch (ExceptionNoFound e) {
			e.printStackTrace();
		}
		
	}
	
	public void case6() {
		try {
			System.out.println("ingresar el id de la persona a eliminar");
			String id = reader.next();
			relation.removePeople(id);
		} catch (ExceptionNoFound | FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public void  case2() throws ParseException, ExceptionRegistry, ExceptionNoFound {
		System.out.println("ingrese el id de la persona a registrar la mascota");
		String idOwner = reader.next();
		System.out.println("ingrese la identificacion");
		String id = reader.next();
		System.out.println("ingrese el nombre de la mascota");
		String name = reader.next();
		System.out.println("ingrese la fecha de nacimiento"+"\n"+"EJEMPLO: DD/MM/YYYY");
		String dateBorn = reader.next();
		Date date = relation.configDate(dateBorn);
		System.out.println("ingrese el genero"+"\n"+"FEMALE O MALE");
		String gen = reader.next();
		System.out.println("ingrese tipo de mascota");
		String tipe = reader.next();
		Pet a = new Pet(id, name, date, gen, tipe);
		relation.addPet(idOwner, a);
	}
	
	public int namesClubs() {
		System.out.println("seleccione el numero del club que desea registrarse"+"\n"+relation.showNameClubs());
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
		System.out.println("ingrese la fecha de nacimiento"+"\n"+"EJEMPLO: DD/MM/YYYY");
		String date = reader.next();
		Date a = relation.configDate(date);
		System.out.println("ingrese mascota favorita");
		String pet = reader.next();
		People p = new People(name, lastName, id, a, pet);
		return p;
	}
	
	public void case7() {
		System.out.println("ingrese el numero de la opcion que desea realizar");
		System.out.println("1. generar listados de personas");
		System.out.println("2. generar listados de mascotas");
		System.out.println("3. generar listados de clubes");
		int option = reader.nextInt();
		
		switch(option) {
		case 1:
			generatePeople();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
		
	}
	public void generatePeople() {
		System.out.println("seleccione el club que le desea ordenar las personas"+"\n"+relation.showNameClubs());
		int club = reader.nextInt();
		System.out.println("1. generar listado por nombre");
		System.out.println("2. generar listado por apellido");
		System.out.println("3. generar listado por identificacion");
		System.out.println("4. generar listado por fecha de nacimiento");
		System.out.println("5. generar listado por mascota de preferencia");
		System.out.println("6. generar listado de personas con mayor numero de mascotas");
		int method = reader.nextInt();
		try {
			relation.getClubs().get(club).organizePeople(method);
			System.out.println(relation.folderSavePeople(method));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generatePet() {
		System.out.println("ingrese el id del dueño de las mascotas a ordenar");
		String id = reader.next();
		try {
			People a =relation.searchPeopleAllClubs(id);
		} catch (ExceptionNoFound e) {
			e.printStackTrace();
		}
		System.out.println("1. generar listado por nombre");
		System.out.println("2. generar listado por identificacion");
		System.out.println("3. generar listado por fecha de nacimiento");
		System.out.println("4. generar listado por genero");
		System.out.println("5. generar listado por tipo de mascotas");
		int method = reader.nextInt();
		
	}
	
	public void generateClubes() {
		System.out.println("1. generar listado por nombre");
		System.out.println("2. generar listado por identificacion");
		System.out.println("3. generar listado por fecha de creacion");
		System.out.println("4. generar listado por tipo de mascota");
		System.out.println("5. generar listado de clubes  con mayor numero de personas");
		int method = reader.nextInt();
		
	}

}
