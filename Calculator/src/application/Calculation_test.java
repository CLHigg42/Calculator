package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Calculation_test {

	@Test
	void test() {
		
		double result = Consume_String.evaluate("10 + 10");
		assertEquals(20,result);
		System.out.println(result);
	}
	@Test
	void test2() {
		double result = Consume_String.evaluate("10 + 2 * 6");
		assertEquals(22,result);
		System.out.println(result);
	}
	@Test
	void test3() {
		double result = Consume_String.evaluate("100 * 2 + 12");
		assertEquals(212,result);
		System.out.println(result);
	}
	@Test
	void test4() {
		double result = Consume_String.evaluate("100 * ( 2 + 12 )");
		assertEquals(1400,result);
		System.out.println(result);
	}
	@Test
	void test5() {
		double result = Consume_String.evaluate("100 * ( 2 + 12 ) / 14");
		assertEquals(100,result);
		System.out.println(result);
	}

}
