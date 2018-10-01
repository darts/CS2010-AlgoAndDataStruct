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
	}
	
	//Test for Correct infix array
	@Test
	public void testInfixValid() {
		boolean expectedResult = true;
		String[] theInput = {"+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3"};
		assertEquals("validatePrefixOrder failed with a valid array", expectedResult,
				Arith.validatePrefixOrder(theInput));
	}

}
