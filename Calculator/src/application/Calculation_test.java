package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Calculation_test {

	@Test
	void testAdd() {
		Calculator add = new Calculator();
		int result = add.add(5, 10);
		assertEquals(15,result);
		
	}

	@Test
	void testConcat() {
		Calculator concat = new Calculator();
		String result = concat.concat("res", "ult");
		assertEquals("resolute",result);
		
	}

}
