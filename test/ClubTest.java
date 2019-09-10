import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Club;
import model.People;

class ClubTest {

	
	
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
	
	
}
