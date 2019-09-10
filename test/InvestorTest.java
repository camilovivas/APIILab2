import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.*;

class InvestorTest {
	Investor investor = new Investor();
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void configDateTest() throws ParseException {
		String date = "15/09/2000";
		Date resultado = investor.configDate(date);
	}
	
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
		String names = investor.showNameClubs();
		assertEquals("seleccione el numero del club que desea registrarse"+"\n"+"0. los michis"+"\n"+"1. los pets"+"\n"+"2. los guao"+"\n"+"3. los miau"+"\n", names);
	}

}
