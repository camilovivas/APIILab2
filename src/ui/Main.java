package ui;
import java.io.Reader;
import java.util.Scanner;

import model.*;

public class Main {
	
	private Investor relation;
	private Scanner	reader;
	
	public Main() {
		reader = new Scanner(System.in);
		relation = new Investor();
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		

	}
	
	public int menu() {
		int option = 0;
		while(option != 5) {
			System.out.println("ingrese la opcion que desea realizar");
			System.out.println("1. registrar un club");
			System.out.println("2. registrar una mascota");
			System.out.println("3. eliminar un club");
			System.out.println("4. eliminar una mascota");
			System.out.println("5. eliminar una persona");
			System.out.println("6. generar listados ordenados");
			
			option =  reader.nextInt();
		}
		return option;
	}
	
	public void options() {
		switch(menu()) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			
		
		}
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
