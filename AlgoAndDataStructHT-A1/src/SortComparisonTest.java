import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Senán d'Art
 *  @version HT 2019
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
	private static double[] arr10 = {2377.88,2910.66,8458.14,1522.08,5855.37,1934.75,8106.23,1735.31,4849.83,1518.63};
	private static double[] arr10sorted = {1518.63,1522.08,1735.31,1934.75,2377.88,2910.66,4849.83,5855.37,8106.23,8458.14};

	
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    	double[] a = null;
    	double[] expectedResult = null;
    	assertEquals("quickSort failed with null array", expectedResult, SortComparison.quickSort(a));

    	assertEquals("insertionSort failed with null array", expectedResult, SortComparison.insertionSort(a));
    	
    	assertEquals("mergeSortRecursive failed with null array", expectedResult, SortComparison.mergeSortRecursive(a));

    }
    
    @Test
    public void test10() {
    	
    	boolean arrayCorrectlySorted = Arrays.equals(arr10sorted, SortComparison.quickSort(arr10));
    	assertEquals("quickSort failed with 10 object array", true, arrayCorrectlySorted);
    	SortComparison.mergeSortRecursive(arr10);
    	arrayCorrectlySorted = Arrays.equals(arr10sorted, SortComparison.insertionSort(arr10));
    	assertEquals("insertionSort failed with 10 object array", true, arrayCorrectlySorted);
    	
    	arrayCorrectlySorted = Arrays.equals(arr10sorted, SortComparison.mergeSortRecursive(arr10));
    	assertEquals("mergeSortRecursive failed with 10 object array", true, arrayCorrectlySorted);

    }
    

    // TODO: add more tests here. Each line of code and ech decision in Collinear.java should
    // be executed at least once from at least one test.

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args)
    {
        //TODO: implement this method
    }

}

