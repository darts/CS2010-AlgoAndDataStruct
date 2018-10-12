import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ArithTest {

	// Test the constructor
	@Test
	public void testConstructor() {
		new Arith();
	}

	// Test validatePrefixOrder
	@Test
	public void testValidPrefix() {
		String[] input1 = { "*", "-", "1", "2", "3" };
		boolean expectedResult1 = true;
		assertEquals("validatePrefixOrder failed with valid array", expectedResult1, Arith.validatePrefixOrder(input1));

		String[] input2 = { "*", "2", "3" };
		assertEquals("validatePrefixOrder failed with valid array", expectedResult1, Arith.validatePrefixOrder(input2));

		String[] input3 = { "*", "1", "+", "2", "3" };
		assertEquals("validatePrefixOrder failed with valid array", expectedResult1, Arith.validatePrefixOrder(input3));

		String[] input4 = { "1", "2", "*" };
		boolean expectedResult2 = false;
		assertEquals("validatePrefixOrder failed with invalid array", expectedResult2,
				Arith.validatePrefixOrder(input4));

		String[] input5 = { "*", "1" };
		assertEquals("validatePrefixOrder failed with invalid array", expectedResult2, Arith.validatePrefixOrder(input5));

		String[] input6 = null;
		assertEquals("validatePrefixOrder failed with invalid array", expectedResult2, Arith.validatePrefixOrder(input6));
		
		String[] input7 = {};
		assertEquals("validatePrefixOrder failed with invalid array", expectedResult2, Arith.validatePrefixOrder(input7));
	}

	// Test validatePostfixOrder
	@Test
	public void testValidPostfix() {
		String[] input1 = {"1", "2","-", "3", "*"};
		boolean expectedResult1 = true;
		assertEquals("validatePostfixOrder failed with valid array", expectedResult1, Arith.validatePostfixOrder(input1));

		String[] input2 = {"2", "3", "*"};
		assertEquals("validatePostfixOrder failed with valid array", expectedResult1, Arith.validatePostfixOrder(input2));

		String[] input3 = {"1", "2", "3", "*", "-"};
		assertEquals("validatePostfixOrder failed with valid array", expectedResult1, Arith.validatePostfixOrder(input3));

		String[] input4 = {"*", "1", "2"};
		boolean expectedResult2 = false;
		assertEquals("validatePostfixOrder failed with invalid array", expectedResult2,
				Arith.validatePostfixOrder(input4));

		String[] input5 = { "*", "1" };
		assertEquals("validatePostfixOrder failed with invalid array", expectedResult2, Arith.validatePostfixOrder(input5));

		String[] input6 = null;
		assertEquals("validatePostfixOrder failed with invalid array", expectedResult2, Arith.validatePostfixOrder(input6));
		
		String[] input7 = {};
		assertEquals("validatePostfixOrder failed with invalid array", expectedResult2, Arith.validatePostfixOrder(input7));
		
		String[] input8 = {"1", "2", "3", "*"};
		assertEquals("validatePostfixOrder failed with invalid array", expectedResult2, Arith.validatePostfixOrder(input8));
	}
	
	//Test evaluatePrefixOrder
	@Test
	public void testEvalPrefixOrder() {
		String[] input1 = { "*", "-", "1", "2", "3" };
		int expectedResult1 = -3;
		assertEquals("evaluatePrefixOrder failed with valid array", expectedResult1, Arith.evaluatePrefixOrder(input1));

		int expectedResult2 = 6;
		String[] input2 = { "*", "2", "3" };
		assertEquals("evaluatePrefixOrder failed with valid array", expectedResult2, Arith.evaluatePrefixOrder(input2));

		int expectedResult3 = 5;
		String[] input3 = { "*", "1", "+", "2", "3" };
		assertEquals("evaluatePrefixOrder failed with valid array", expectedResult3, Arith.evaluatePrefixOrder(input3));

		int expectedResult4 = 0;
		String[] input4 = { "/", "1", "2"};
		assertEquals("evaluatePrefixOrder failed with invalid array", expectedResult4,
				Arith.evaluatePrefixOrder(input4));
	}
	
	//Test evaluatePostfixOrder
	@Test
	public void testEvalPostfixOrder() {
		String[] input1 = {"1", "2", "-", "3", "*"};
		int expectedResult1 = -3;
		assertEquals("evaluatePostfixOrder failed with valid array", expectedResult1, Arith.evaluatePostfixOrder(input1));

		int expectedResult2 = 6;
		String[] input2 = {"2", "3", "*"};
		assertEquals("evaluatePostfixOrder failed with valid array", expectedResult2, Arith.evaluatePostfixOrder(input2));

		int expectedResult3 = 7;
		String[] input3 = { "1","2", "3", "*", "+" };
		assertEquals("evaluatePostfixOrder failed with valid array", expectedResult3, Arith.evaluatePostfixOrder(input3));

		int expectedResult4 = 0;
		String[] input4 = {"1", "2", "/"};
		assertEquals("evaluatePostfixOrder failed with invalid array", expectedResult4,
				Arith.evaluatePostfixOrder(input4));
	}
	
	//Test convertPrefixToPostfix
	@Test
	public void testConvertPrefixToPostfix() {
		String[] input1 = {"*", "-", "1", "2", "3"};
		String[] expectedResult1 = {"1", "2","-", "3", "*"}; 
		Assert.assertArrayEquals("convertPrefixToPostfix failed with valid array", expectedResult1, Arith.convertPrefixToPostfix(input1));

		String[] expectedResult2 = {"3", "2", "*"}; 
		String[] input2 = {"*", "3", "2"};
		Assert.assertArrayEquals("convertPrefixToPostfix failed with valid array", expectedResult2, Arith.convertPrefixToPostfix(input2));

		String[] expectedResult3 = {"1", "2", "+", "3", "*"};
		String[] input3 = { "*", "+", "1","2", "3"};
		Assert.assertArrayEquals("evaluatePostfixOrder failed with valid array", expectedResult3, Arith.convertPrefixToPostfix(input3));

		String[] expectedResult4 = {"1", "2","/"};
		String[] input4 = {"/","1", "2"};
		Assert.assertArrayEquals("evaluatePostfixOrder failed with invalid array", expectedResult4,
				Arith.convertPrefixToPostfix(input4));
	}
	
	//Test convertPostfixToPrefix
	@Test
	public void testConvertPostfixToPrefix() {
		String[] input1 = {"3", "2", "1", "-", "*"};
		String[] expectedResult1 = {"*", "3", "-", "2","1"}; 
		Assert.assertArrayEquals("convertPostfixToPrefix failed with valid array", expectedResult1, 
				Arith.convertPostfixToPrefix(input1));

		String[] expectedResult2 = {"*", "2", "3"}; 
		String[] input2 = {"2", "3", "*"};
		Assert.assertArrayEquals("convertPostfixToPrefix failed with valid array", expectedResult2, Arith.convertPostfixToPrefix(input2));

		String[] expectedResult3 = {"*", "+", "1", "2","3"};
		String[] input3 = {"1", "2", "+", "3", "*"};
		Assert.assertArrayEquals("convertPostfixToPrefix failed with valid array", expectedResult3, Arith.convertPostfixToPrefix(input3));
		
		String[] expectedResult4 = {"*", "-", "1", "2", "3"};
		String[] input4 = {"1", "2", "-", "3", "*"};
		Assert.assertArrayEquals("convertPostfixToPrefix failed with valid array", expectedResult4, Arith.convertPostfixToPrefix(input4));
	}
	
	//Test convertPrefixToInfix
	@Test
	public void testConvertPrefixToInfix() {
		String[] input1 = {"*", "3", "-", "2","1"};
		String[] expectedResult1 = {"(","3", "*", "(", "2", "-","1", ")",")"};
		Assert.assertArrayEquals("convertPrefixToInfix failed with valid array", expectedResult1, Arith.convertPrefixToInfix(input1));
		
		String[] input2 = {"*", "3", "1"};
		String[] expectedResult2 = {"(","3", "*", "1",")"};
		Assert.assertArrayEquals("convertPrefixToInfix failed with valid array", expectedResult2, Arith.convertPrefixToInfix(input2));
		
		String[] input3 = {"*", "-", "2","3", "1"};
		String[] expectedResult3 = {"(","(","2", "-", "3",")", "*", "1",")"};
		Assert.assertArrayEquals("convertPrefixToInfix failed with valid array", expectedResult3, Arith.convertPrefixToInfix(input3));
	}
	
	//Test convertPostfixToInfix
		@Test
		public void testConvertPostfixToInfix() {
			String[] input1 = {"3", "2", "*"};
			String[] expectedResult1 = {"(","3", "*", "2",")"};
			Assert.assertArrayEquals("convertPostfixToInfix failed with valid array", expectedResult1, Arith.convertPostfixToInfix(input1));
			
			String[] input2 = {"1", "2", "+", "3","*"};
			String[] expectedResult2 = {"(","(","1", "+", "2",")","*","3",")"};
			Assert.assertArrayEquals("convertPostfixToInfix failed with valid array", expectedResult2, Arith.convertPostfixToInfix(input2));
			
			String[] input3 = {"3", "2", "1", "*", "+"};
			String[] expectedResult3 = {"(", "3", "+", "(", "2", "*", "1", ")", ")"};
			Assert.assertArrayEquals("convertPostfixToInfix failed with valid array", expectedResult3, Arith.convertPostfixToInfix(input3));
		}
}
