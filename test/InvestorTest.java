import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import exception.ExceptionNoFound;
import exception.ExceptionRegistry;
import model.*;

class InvestorTest {
	Investor investor = new Investor();
	
	

	
	/**
	 * this method check the method showNameClubs and saveClubs
	 * @throws ParseException
	 * @throws IOException 
	 */
	@Test
	public void showNameClubsTest() throws ParseException, IOException {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Club a = new Club("los michis","564",d, "gatos");
		Club b = new Club("los pets","564",d, "gatos");
		Club c = new Club("los guao","564",d, "gatos");
		Club e = new Club("los miau","564",d, "gatos");
		investor.getClubs().add(a);
		investor.getClubs().add(b);
		investor.getClubs().add(c);
		investor.getClubs().add(e);
		investor.saveClubs();
		String names = investor.showNameClubs();
		assertEquals("0. los michis"+"\n"+"1. los pets"+"\n"+"2. los guao"+"\n"+"3. los miau"+"\n", names);
	}
	
	@Test
	public void searchPeopleAllClubsTest() throws ParseException, ExceptionNoFound {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Club a = new Club("los michis","564",d, "gatos");
		People a1 = new People("noSoy", "n", "10090", d, "pet");
		People a2 = new People("noSoy", "n", "10094", d, "pet");
		People a3 = new People("noSoy", "n", "100905", d, "pet");
		a.addPeople(a1);
		a.addPeople(a2);
		a.addPeople(a3);
		
		Club b = new Club("los pets","564",d, "gatos");
		People b1 = new People("noSoy", "n", "100965", d, "pet");
		People b2 = new People("noSoy", "n", "10045", d, "pet");
		People b3= new People("siSoy", "n", "100605", d, "pet");
		b.addPeople(b1);
		b.addPeople(b2);
		b.addPeople(b3);
		
		investor.getClubs().add(a);
		investor.getClubs().add(b);
		People found = investor.searchPeopleAllClubs("100605");
		assertEquals("siSoy",found.getName());
		
	}
	
	@Test
	public void existTest() throws ParseException {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Club a = new Club("los michis","564",d, "gatos");
		People a1 = new People("noSoy", "n", "10090", d, "pet");
		People a2 = new People("noSoy", "n", "10094", d, "pet");
		People a3 = new People("noSoy", "n", "100905", d, "pet");
		a.addPeople(a1);
		a.addPeople(a2);
		a.addPeople(a3);
		
		Club b = new Club("los pets","564",d, "gatos");
		People b1 = new People("noSoy", "n", "100965", d, "pet");
		People b2 = new People("noSoy", "n", "10045", d, "pet");
		People b3= new People("siSoy", "n", "100605", d, "pet");
		b.addPeople(b1);
		b.addPeople(b2);
		b.addPeople(b3);
		
		investor.getClubs().add(a);
		investor.getClubs().add(b);
		People b4= new People("estoyRepetido", "n", "100605", d, "pet");
		boolean result = investor.exist(b4);
		assertEquals(true, result);
	
	}
	
	@Test
	public void addPetTest() throws ParseException, ExceptionRegistry, ExceptionNoFound {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Club a = new Club("los michis","564",d, "gatos");
		People a1 = new People("noSoy", "n", "100900", d, "pet");
		People a2 = new People("noSoy", "n", "100940", d, "pet");
		People a3 = new People("noSoy", "n", "100905", d, "pet");
		a.addPeople(a1);
		a.addPeople(a2);
		a.addPeople(a3);
		
		Club b = new Club("los pets","564",d, "gatos");
		People b1 = new People("noSoy", "n", "100965", d, "pet");
		Pet p1 = new Pet("133","michi",d, "macho","gato");
		b1.addPet(p1);
		People b2 = new People("noSoy", "n", "100450", d, "pet");
		People b3= new People("siSoy", "n", "100605", d, "pet");
		b.addPeople(b1);
		b.addPeople(b2);
		b.addPeople(b3);
		
		investor.getClubs().add(a);
		investor.getClubs().add(b);
		
		Pet p2 = new Pet("133","micho",d, "macho","gato");
		investor.addPet("100965", p2);
		int quantityPets = b1.cantidadMascotas();
		assertEquals(2, quantityPets);
		
	}
	@Test
	public void createWorld() throws ParseException, IOException, ExceptionRegistry {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Club a = new  Club("los animales", "465312", d, "cualquiera");
		investor.addClubs(a);
		a.chargePeople();
		a.addPetToPeople();
		
	}
	
//	@Test
	public void addPetToPeople() throws ParseException, IOException, ExceptionRegistry {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Club a = new  Club("los animales", "465312", d, "cualquiera");
		investor.addClubs(a);
		People b2 = new People("noSoy", "n", "10045", d, "pet");
		People b3= new People("siSoy", "n", "100605", d, "pet");
		a.addPeople(b2);
		a.addPeople(b3);
		a.addPetToPeople();
		
	}
	
	@Test
	public void chargeClubsTest() throws IOException, ParseException {
		investor.chargeClubs();
		int tamanio = investor.getClubs().size();
		assertEquals(1, tamanio);
	}
	
	@Test
	public void organizeClubsNameTest() throws ParseException, IOException {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Club a = new  Club("los animales", "465312", d, "cualquiera");
		Club b = new  Club("animales tiernos", "465312", d, "cualquiera");
		Club c = new  Club("becerros sin rienda", "465312", d, "cualquiera");
		investor.getClubs().add(a);
		investor.getClubs().add(b);
		investor.getClubs().add(c);
		investor.organizeClubs(1);
		String name = "";
		for (int i = 0; i < investor.getClubs().size(); i++) {
			name += investor.getClubs().get(i).getName()+" ";
		}
		assertEquals("animales tiernos becerros sin rienda los animales ", name);
	}
	
	@Test
	public void organizeClubsIdTest() throws ParseException, IOException {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Club a = new  Club("los animales", "52", d, "cualquiera");
		Club b = new  Club("animales tiernos", "10", d, "cualquiera");
		Club c = new  Club("becerros sin rienda", "30", d, "cualquiera");
		investor.getClubs().add(a);
		investor.getClubs().add(b);
		investor.getClubs().add(c);
		investor.organizeClubs(2);
		String name = "";
		for (int i = 0; i < investor.getClubs().size(); i++) {
			name += investor.getClubs().get(i).getId()+" ";
		}
		assertEquals("10 30 52 ", name);
	}
	
	@Test
	public void organizeClubsKindOfPetTest() throws ParseException, IOException {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Club a = new  Club("los animales", "465312", d, "perros");
		Club b = new  Club("animales tiernos", "465312", d, "aves");
		Club c = new  Club("becerros sin rienda", "465312", d, "zorros");
		investor.getClubs().add(a);
		investor.getClubs().add(b);
		investor.getClubs().add(c);
		investor.organizeClubs(4);
		String name = "";
		for (int i = 0; i < investor.getClubs().size(); i++) {
			name += investor.getClubs().get(i).getKindOfPet()+" ";
		}
		assertEquals("aves perros zorros ", name);
	}
	
	@Test
	public void organizeClubsWhitMorePeopleTest() throws ParseException, IOException {
		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		Date d = date.parse("12/06/2012");
		Club a = new  Club("los animales", "465312", d, "cualquiera");
		People a1 = new People("noSoy", "n", "10045", d, "pet");
		a.addPeople(a1);
		
		Club b = new  Club("animales tiernos", "465312", d, "cualquiera");
		People b1 = new People("noSoy", "n", "10045", d, "pet");
		People b2 = new People("noSoy", "n", "10045", d, "pet");
		b.addPeople(b1);
		b.addPeople(b2);

		Club c = new  Club("becerros sin rienda", "465312", d, "cualquiera");
		People c1 = new People("noSoy", "n", "10045", d, "pet");
		People c2 = new People("noSoy", "n", "10045", d, "pet");
		People c3 = new People("noSoy", "n", "10045", d, "pet");
		c.addPeople(c1);
		c.addPeople(c2);
		c.addPeople(c3);
		
		investor.getClubs().add(a);
		investor.getClubs().add(b);
		investor.getClubs().add(c);
		investor.organizeClubs(5);
		String name = "";
		for (int i = 0; i < investor.getClubs().size(); i++) {
			name += investor.getClubs().get(i).getName()+" ";
		}
		assertEquals("los animales animales tiernos becerros sin rienda ", name);
	}

}
