import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Club;
import model.People;

class ClubTest {

	
	
	/**
	 * this method check the method organizePeople, savePeopleOrganize and organizePeopleName
	 * @throws ParseException
	 * @throws IOException
	 */
	@Test
	public void organizePeopleNameTest() throws ParseException, IOException {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		People a = new People("camilo","vivas", "1006015105",d, "perro");
		People b = new People("andres","vivas", "1006015105",d, "perro");
		People c = new People("brayan","vivas", "1006015105",d, "perro");
		People g = new People("zacarias","vivas", "1006015105",d, "perro");
		People e = new People("jordan","vivas", "1006015105",d, "perro");
		People f = new People("sebastian","vivas", "1006015105",d, "perro");
		Club j = new Club("los perros", "128942",d ,"peros");
		j.addPeople(a);
		j.addPeople(b);
		j.addPeople(c);
		j.addPeople(g);
		j.addPeople(e);
		j.addPeople(f);
		j.organizePeople(1);
		String names = "";
		for (int i = 0; i < j.getOwners().size(); i++) {
			names += j.getOwners().get(i).getName()+" ";
		}
		assertEquals("andres brayan camilo jordan sebastian zacarias ", names);
		
	}
	@Test
	public void organizePeopleIdTest() throws ParseException, IOException {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		People a = new People("camilo","vivas", "01",d, "perro");
		People b = new People("andres","vivas", "15",d, "perro");
		People c = new People("brayan","vivas", "12",d, "perro");
		People g = new People("zacarias","vivas", "10",d, "perro");
		People e = new People("jordan","vivas", "05",d, "perro");
		People f = new People("sebastian","vivas", "04",d, "perro");
		Club j = new Club("los perros", "128942",d ,"peros");
		j.addPeople(a);
		j.addPeople(b);
		j.addPeople(c);
		j.addPeople(g);
		j.addPeople(e);
		j.addPeople(f);
		j.organizePeople(3);
		String id = "";
		for (int i = 0; i < j.getOwners().size(); i++) {
			id += j.getOwners().get(i).getId()+" ";
		}
		assertEquals("01 04 05 10 12 15 ", id);
	}
	
	@Test
	public void existTest() throws ParseException {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		People a = new People("camilo","vivas", "1006015105",d, "perro");
		People b = new People("andres","vivas", "1006015105",d, "perro");
		People c = new People("brayan","vivas", "1006015105",d, "perro");
		People g = new People("zacarias","vivas", "1006015105",d, "perro");
		People e = new People("jordan","vivas", "1006015105",d, "perro");
		Club j = new Club("los perros", "128942",d ,"peros");
		j.addPeople(a);
		j.addPeople(b);
		j.addPeople(c);
		j.addPeople(g);
		j.addPeople(e);
		boolean result = j.exist("1006015105");
		assertEquals(true, result);
	}
	
	
	
	
	
}
