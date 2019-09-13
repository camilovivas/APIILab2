import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Pet;

class PetTest {

	@Test
	public void compareNameTest() throws ParseException {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Pet a = new Pet("10", "firulais", d, "male", "pet");
		Pet b = new Pet("05","boby" ,d , "male", "pet");
		int result = a.compareName(b);
		assertEquals(1, result);
	}
	@Test
	public void compareIdTest() throws ParseException {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Pet a = new Pet("10", "firulais", d, "male", "pet");
		Pet b = new Pet("05","boby" ,d , "male", "pet");
		int result = a.compareId(b);
		assertEquals(1, result);
	}
	@Test
	public void compareGenTest() throws ParseException {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Pet a = new Pet("10", "firulais", d, "male", "pet");
		Pet b = new Pet("05","boby" ,d , "male", "pet");
		int result = a.compareGen(b);
		assertEquals(0, result);
	}
	
	@Test
	public void compareKIndOfPetTest() throws ParseException {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Pet a = new Pet("10", "firulais", d, "male", "cat");
		Pet b = new Pet("05","boby" ,d , "male", "pet");
		int result = a.compareKindOfPet(b);
		assertEquals(-1, result);
	}
	

}
