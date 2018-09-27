import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 * Test class for Collinear.java
 *
 * @author Senán d'Art
 * @version 18/09/18 12:21:26
 */
@RunWith(JUnit4.class)
public class CollinearTest {
	// ~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new Collinear();
	}

	// ~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the two methods work for empty arrays
	 */
	@Test
	public void testEmpty() {
		int expectedResult = 0;

		assertEquals("countCollinear failed with 3 empty arrays", expectedResult,
				Collinear.countCollinear(new int[0], new int[0], new int[0]));
		assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult,
				Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
	}

	// ----------------------------------------------------------
	/**
	 * Check for no false positives in a single-element array
	 */
	@Test
	public void testSingleFalse() {
		int[] a3 = { 15 };
		int[] a2 = { 5 };
		int[] a1 = { 10 };

		int expectedResult = 0;

		assertEquals("countCollinear({10}, {5}, {15})", expectedResult, Collinear.countCollinear(a1, a2, a3));
		assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
	}

	// ----------------------------------------------------------
	/**
	 * Check for no false positives in a single-element array
	 */
	@Test
	public void testSingleTrue() {
		int[] a3 = { 15, 5 };
		int[] a2 = { 5 };
		int[] a1 = { 10, 15, 5 };

		int expectedResult = 1;

		assertEquals(
				"countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",
				expectedResult, Collinear.countCollinear(a1, a2, a3));
		assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3)
				+ ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
	}

	// ---------------------------------------------------------
	/*
	 * a Evaluate all binarySearch cases.
	 *
	 */
	@Test
	public void evalBinarySearch() {
		int[] theArray = { 1, 7, 3, 5, 2 }; // 1, 2, 3, 5, 7

		boolean expectedResult = true;
		int numToFind = 3;
		assertEquals("binarySearch(" + Arrays.toString(theArray) + "," + numToFind, expectedResult,
				Collinear.binarySearch(theArray, numToFind));

		// If num to find is less than mid
		numToFind = 1;
		assertEquals("binarySearch(" + Arrays.toString(theArray) + "," + numToFind, expectedResult,
				Collinear.binarySearch(theArray, numToFind));

		// If num to find is greater than mid
		numToFind = 7;
		assertEquals("binarySearch(" + Arrays.toString(theArray) + "," + numToFind, expectedResult,
				Collinear.binarySearch(theArray, numToFind));

		// If the array is large.
		int[] bigArray = { 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21 };
		numToFind = 2;
		assertEquals("binarySearch(" + Arrays.toString(theArray) + "," + numToFind, expectedResult,
				Collinear.binarySearch(bigArray, numToFind));

		// If the array is large.
		numToFind = 20;
		assertEquals("binarySearch(" + Arrays.toString(theArray) + "," + numToFind, expectedResult,
				Collinear.binarySearch(bigArray, numToFind));

		// If num to find is not in the array
		expectedResult = false;
		numToFind = 8;
		assertEquals("binarySearch(" + Arrays.toString(theArray) + "," + numToFind, expectedResult,
				Collinear.binarySearch(bigArray, numToFind));
		
		//If the num to find is greater than the highest num in the array
		numToFind = 30;
		assertEquals("binarySearch(" + Arrays.toString(theArray) + "," + numToFind, expectedResult,
				Collinear.binarySearch(bigArray, numToFind));
		
		//If the num to find is lower than the lowest num in the array
		numToFind = -1;
		assertEquals("binarySearch(" + Arrays.toString(theArray) + "," + numToFind, expectedResult,
				Collinear.binarySearch(bigArray, numToFind));
		
		// If the array is null
		theArray = null;
		assertEquals("binarySearch(" + Arrays.toString(theArray) + "," + numToFind, expectedResult,
				Collinear.binarySearch(theArray, numToFind));
	}

	// Check for null arrays in search functions.
	@Test
	public void checkForNull() {

		int[] a1 = { 15, 5 };
		int[] a2 = { 5 };
		int[] a3 = { 10, 15, 5 };

		int expectedResult = 0;
		a1 = null;
		assertEquals("countCollinear(" + "NULL" + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",
				expectedResult, Collinear.countCollinear(a1, a2, a3));
		assertEquals("countCollinearFast(" + "NULL" + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",
				expectedResult, Collinear.countCollinearFast(a1, a2, a3));

		a2 = null;
		a1 = new int[2];
		a1[0] = 15;
		a1[1] = 5;
		assertEquals("countCollinear(" + Arrays.toString(a1) + "," + "NULL" + "," + Arrays.toString(a3) + ")",
				expectedResult, Collinear.countCollinear(a1, a2, a3));
		assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + "NULL" + "," + Arrays.toString(a3) + ")",
				expectedResult, Collinear.countCollinearFast(a1, a2, a3));

		a3 = null;
		a2 = new int[1];
		a2[0] = 5;
		assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + "NULL" + ")",
				expectedResult, Collinear.countCollinear(a1, a2, a3));
		assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + "NULL" + ")",
				expectedResult, Collinear.countCollinearFast(a1, a2, a3));

	}

}
