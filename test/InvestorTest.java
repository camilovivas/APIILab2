import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import exception.ExceptionNoFound;
import model.*;

class InvestorTest {
	Investor investor = new Investor();
	
	
	@Test
	public void configDateTest() throws ParseException {//como compruebo un date?
		String date = "15/09/2000";
		Date resultado = investor.configDate(date);
	}
	
	/**
	 * this method check the method showNameClubs and saveClubs
	 * @throws ParseException
	 */
	@Test
	public void showNameClubsTest() throws ParseException {
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

}
