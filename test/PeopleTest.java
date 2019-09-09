import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import exception.ExceptionRegistry;
import model.People;
import model.Pet;

class PeopleTest {
		
		
	public PeopleTest() throws ParseException {
		
		
		
	}
	@Test
	public void organizePetNameTest() throws ParseException, ExceptionRegistry {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		People e = new People("camilo","vivas", "1006015105",d, "perro");
		Pet a= new Pet("10", "firulais", d, "male", "perro");
		Pet b= new Pet("10", "andres", d, "male", "perro");
		Pet c= new Pet("10", "zapata", d, "male", "perro");
		Pet f= new Pet("10", "bosco", d, "male", "perro");
		e.addPet(a);
		e.addPet(b);
		e.addPet(c);
		e.addPet(f);
		e.organizePetName();
		String retorno = "";
		for (int i = 0; i < e.getPets().size(); i++) {
			retorno += e.getPets().get(i).getName()+" ";
		}
		assertEquals("andres bosco firulais zapata ", retorno);
	}

}
