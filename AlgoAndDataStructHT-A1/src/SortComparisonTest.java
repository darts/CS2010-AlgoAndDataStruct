import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author
 *  @version HT 2019
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
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
    }
    
    @Test
    public void test10() {
    	double[] a = new double[10];
    	double[] expectedResult = new double[10];
    	try (BufferedReader br = new BufferedReader(new FileReader("numbers10.txt"))) {
    	    String line;
    	    int i = 0;
    	    while ((line = br.readLine()) != null) {
    	    	a[i++] = Double.parseDouble(line); 
    	    }
    	}catch(Exception e) {
    		System.out.println("R.I.P - nice try at reading the file");
    		e.printStackTrace();
    	}
  
    	try (BufferedReader br = new BufferedReader(new FileReader("numbersSorted10.txt"))) {
    	    String line;
    	    int i = 0;
    	    while ((line = br.readLine()) != null) {
    	    	expectedResult[i++] = Double.parseDouble(line); 
    	    }
    	}catch(Exception e) {
    		System.out.println("R.I.P - nice try at reading the file");
    		e.printStackTrace();
    	}
    	
    	assertEquals("quickSort failed with 10 object array", expectedResult, SortComparison.quickSort(a));

    	assertEquals("insertionSort failed with 10 object array", expectedResult, SortComparison.insertionSort(a));
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

