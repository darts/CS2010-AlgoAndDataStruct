import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.Arrays;

@RunWith(JUnit4.class)
public class ArithTest {

	// Test the constructor
	@Test
	public void testConstructor() {
		new Arith();
	}

	// Test for null array
	@Test
	public void testNull() {

		boolean expectedResult = false;
		String[] theInput = null;
		assertEquals("validatePrefixOrder failed with a null array", expectedResult,
				Arith.validatePrefixOrder(theInput));

		assertEquals("validatePostfixOrder failed with a null array", expectedResult,
				Arith.validatePostfixOrder(theInput));
	}
	
	//Test for an empty array
	@Test
	public void testEmptyArray() {
		boolean expectedResult = false;
		String[] theInput = {};
		assertEquals("validatePrefixOrder failed with an empty array", expectedResult,
				Arith.validatePrefixOrder(theInput));

		assertEquals("validatePostfixOrder failed with an empty array", expectedResult,
				Arith.validatePostfixOrder(theInput));
	}
	
	//Test for erroneous array
	@Test
	public void testErrArray() {
		boolean expectedResult = false;
		
		String[] theInput = {"2", "5", "2"};
		assertEquals("validatePrefixOrder failed with no ops in array", expectedResult,
				Arith.validatePrefixOrder(theInput));
		
		assertEquals("validatePostfixOrder failed with no ops in array", expectedResult,
				Arith.validatePostfixOrder(theInput));
		
		Arrays.fill(theInput, "*");
		assertEquals("validatePrefixOrder failed with no nums in array", expectedResult,
				Arith.validatePrefixOrder(theInput));

		assertEquals("validatePostfixOrder failed with no nums in array", expectedResult,
				Arith.validatePostfixOrder(theInput));
		
		theInput[0] = "5";
		assertEquals("validatePrefixOrder failed with num ahead of op in array", expectedResult,
				Arith.validatePrefixOrder(theInput));

		theInput[1] = "5";
		theInput[0] = "*";
		assertEquals("validatePostfixOrder failed with op ahead of num in array", expectedResult,
				Arith.validatePostfixOrder(theInput));
		
	}
	
	//Test for Correct array
	@Test
	public void testValid() {
		boolean expectedResult = true;
		String[] input = {"3", "7", "2", "+", "/",};
		assertEquals("validatePostfixOrder failed with a valid array", expectedResult,
				Arith.validatePostfixOrder(input));
		
		String[] theInput = {"+","*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3"};
		assertEquals("validatePrefixOrder failed with a valid array", expectedResult,
				Arith.validatePrefixOrder(theInput));	
	}

}
