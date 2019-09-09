import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Investor;

class InvestorTest {
	Investor a = new Investor();
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void configDateTest() throws ParseException {
		String date = "15/09/2000";
		Date resultado = a.configDate(date);
		assertEquals(resultado);
	}

}
