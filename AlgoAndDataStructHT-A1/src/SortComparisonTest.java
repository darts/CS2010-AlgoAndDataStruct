import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 * Test class for SortComparison.java
 *
 * @author Senán d'Art
 * @version HT 2019
 */

/* ****************************************************************************************
 * |||||||||||||||||||||| Insert | Quick | Quick w/ Shuffle | MergeRec | MergeIt | Select |
 * |10 Random           |   0    |  0.01 |		0.02		|	0.01   |   0.02  |   0    |  
 * |100 Random          |  0.13  |  0.03 |		0.13		|	0.07   |   0.05  |  0.11  |
 * |1000 Random         |  6.85  |  0.36 |		1.43		|	0.67   |   0.54  |  5.68  |
 * |1000 Few Unique     |  6.78  |  0.40 |		1.23		|	0.71   |   0.63  |	5.01  |
 * |1000 Nearly Ordered |  2.13  |  1.03 |		1.44		|	0.63   |   0.61  |	5.01  |
 * |1000 Reverse Order  |  7.60  |  7.80 |		1.41		|	0.65   |   0.46  |	5.69  |
 * |1000 Sorted         |  0.07  |  7.46 |		1.44		|	0.61   |   0.66  |  5.25  |
 * ****************************************************************************************
 * 
 * 
 * a. Which of the sorting algorithms does the order of input have an impact on?
 * Why? 
 * -insertionSort, if the input is already ordered, the algorithm only has to pass
 * 	over the array with no modifications required i.e. it does nothing if the array
 * 	is ordered.
 * -quickSort, if the input is already ordered, quickSort is limited to running 
 * 	recursively on only 1 side of the array, recursively. This results in massive
 * 	overhead and very slow times from stack calls. 
 * 	Due to how picking the first element as pivot, the same is true for reverse 
 * 	sorted arrays.  
 * 
 * b. Which algorithm has the biggest difference between the best and worst
 * performance, based on the type of input, for the input of size 1000? Why?
 * -quickSort, it was very fast for random inputs but lagged significantly when the
 * 	input was ordered or reverse ordered as then it was only able to run recursively
 * 	on one side of the array each time, this added huge stack call overhead and 
 * 	resulted in long running times.
 * 
 * c.Which algorithm has the best/worst scalability, i.e., the difference in
 * performance time based on the input size? Please consider only input files
 * with random order for this answer. 
 * -BEST: quickSort (NlogN)
 * -WORST: insertionSort (N^2 and also more memory usage than selection sort)
 * 
 * d. Did you observe any difference between iterative and recursive 
 * implementations of merge sort? 
 * -The iterative version was marginally but consistently faster. This is 
 * 	presumably due to the stack overhead. On a machine with lower latency memory 
 *  this may be different.
 * 
 * e. Which algorithm is the fastest for each of the 7 input files?
 * -mergeSortIterative was marginally fastest
 */

@RunWith(JUnit4.class)
public class SortComparisonTest {
	// I have no idea why by even when these were set as final, they were being
	// modified, hence duplicates
	private static double[] arr10 = { 2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23, 1735.31, 4849.83,
			1518.63 };
	private static double[] arr10dupe = { 2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23, 1735.31,
			4849.83, 1518.63 };
	private static double[] arr10sorted = { 1518.63, 1522.08, 1735.31, 1934.75, 2377.88, 2910.66, 4849.83, 5855.37,
			8106.23, 8458.14 };
	private static double[] arr100 = { 2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23, 1735.31, 4849.83,
			1518.63, 3669.57, 4917.36, 9100.94, 2652.57, 8931.88, 2203.51, 6317.98, 5710.77, 3321.58, 1044.55, 5029.31,
			5673.94, 8541.65, 401.409, 1080.22, 6393.96, 131.11, 7201.84, 1018.14, 4829.45, 2543.55, 6766.97, 8967.82,
			7598.96, 7202.92, 9076.23, 9286.11, 3776.63, 8997.56, 7788.8, 3242.55, 1942.31, 9955.53, 1612.96, 7080.34,
			5015.19, 9363.01, 7163.23, 1051.9, 5593.97, 2092.05, 7054.32, 782.337, 5105.3, 1961.97, 2742.11, 6386.02,
			4482.08, 398.718, 4672.51, 777.565, 381.004, 1600.44, 3978.32, 6888.98, 5347.12, 6869.2, 2724.57, 4659.33,
			2765.55, 4500.86, 9350.69, 869.318, 4538.89, 9456.25, 8557.19, 1209.76, 141.639, 2345.15, 8663.13, 4373.9,
			7695.48, 8740.03, 3769.34, 9443.28, 7984.93, 8386.34, 9466.56, 5860.52, 5991.63, 3193.47, 4211.62, 9041.07,
			1584.62, 8351.59, 7319.42, 4918.37, 2121.29, 1197.52, 483.631 };
	private static double[] arr100dupe = { 2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23, 1735.31,
			4849.83, 1518.63, 3669.57, 4917.36, 9100.94, 2652.57, 8931.88, 2203.51, 6317.98, 5710.77, 3321.58, 1044.55,
			5029.31, 5673.94, 8541.65, 401.409, 1080.22, 6393.96, 131.11, 7201.84, 1018.14, 4829.45, 2543.55, 6766.97,
			8967.82, 7598.96, 7202.92, 9076.23, 9286.11, 3776.63, 8997.56, 7788.8, 3242.55, 1942.31, 9955.53, 1612.96,
			7080.34, 5015.19, 9363.01, 7163.23, 1051.9, 5593.97, 2092.05, 7054.32, 782.337, 5105.3, 1961.97, 2742.11,
			6386.02, 4482.08, 398.718, 4672.51, 777.565, 381.004, 1600.44, 3978.32, 6888.98, 5347.12, 6869.2, 2724.57,
			4659.33, 2765.55, 4500.86, 9350.69, 869.318, 4538.89, 9456.25, 8557.19, 1209.76, 141.639, 2345.15, 8663.13,
			4373.9, 7695.48, 8740.03, 3769.34, 9443.28, 7984.93, 8386.34, 9466.56, 5860.52, 5991.63, 3193.47, 4211.62,
			9041.07, 1584.62, 8351.59, 7319.42, 4918.37, 2121.29, 1197.52, 483.631 };
	private static double[] arr100sorted = { 131.11, 141.639, 381.004, 398.718, 401.409, 483.631, 777.565, 782.337,
			869.318, 1018.14, 1044.55, 1051.9, 1080.22, 1197.52, 1209.76, 1518.63, 1522.08, 1584.62, 1600.44, 1612.96,
			1735.31, 1934.75, 1942.31, 1961.97, 2092.05, 2121.29, 2203.51, 2345.15, 2377.88, 2543.55, 2652.57, 2724.57,
			2742.11, 2765.55, 2910.66, 3193.47, 3242.55, 3321.58, 3669.57, 3769.34, 3776.63, 3978.32, 4211.62, 4373.9,
			4482.08, 4500.86, 4538.89, 4659.33, 4672.51, 4829.45, 4849.83, 4917.36, 4918.37, 5015.19, 5029.31, 5105.3,
			5347.12, 5593.97, 5673.94, 5710.77, 5855.37, 5860.52, 5991.63, 6317.98, 6386.02, 6393.96, 6766.97, 6869.2,
			6888.98, 7054.32, 7080.34, 7163.23, 7201.84, 7202.92, 7319.42, 7598.96, 7695.48, 7788.8, 7984.93, 8106.23,
			8351.59, 8386.34, 8458.14, 8541.65, 8557.19, 8663.13, 8740.03, 8931.88, 8967.82, 8997.56, 9041.07, 9076.23,
			9100.94, 9286.11, 9350.69, 9363.01, 9443.28, 9456.25, 9466.56, 9955.53 };

	// ~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new SortComparison();
	}

	// ~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the methods work for empty arrays
	 */
	@Test
	public void testNull() {
		double[] a = null;
		double[] expectedResult = null;
		assertEquals("quickSort failed with null array", expectedResult, SortComparison.quickSort(a));

		assertEquals("quickSortWithShuffle failed with null array", expectedResult,
				SortComparison.quickSortWithShuffle(a));

		assertEquals("insertionSort failed with null array", expectedResult, SortComparison.insertionSort(a));

		assertEquals("selectionSort failed with null array", expectedResult, SortComparison.selectionSort(a));

		assertEquals("mergeSortRecursive failed with null array", expectedResult, SortComparison.mergeSortRecursive(a));

		assertEquals("mergeSortIterative failed with null array", expectedResult, SortComparison.mergeSortIterative(a));
	}

	// Test for arrays of size == 1
	@Test
	public void testS1() {
		double[] a = { 123 };
		double[] expectedResult = { 123 };

		boolean arrayCorrectlySorted = Arrays.equals(expectedResult, SortComparison.quickSort(a));
		assertEquals("quickSort failed with size == 1", true, arrayCorrectlySorted);

		arrayCorrectlySorted = Arrays.equals(expectedResult, SortComparison.quickSortWithShuffle(a));
		assertEquals("quickSortWithShuffle failed with size == 1", true, arrayCorrectlySorted);

		arrayCorrectlySorted = Arrays.equals(expectedResult, SortComparison.insertionSort(a));
		assertEquals("insertionSort failed with size == 1", true, arrayCorrectlySorted);

		arrayCorrectlySorted = Arrays.equals(expectedResult, SortComparison.selectionSort(a));
		assertEquals("selectionSort failed with size == 1", true, arrayCorrectlySorted);

		arrayCorrectlySorted = Arrays.equals(expectedResult, SortComparison.mergeSortRecursive(a));
		assertEquals("mergeSortRecursive failed with size == 1", true, arrayCorrectlySorted);

		arrayCorrectlySorted = Arrays.equals(expectedResult, SortComparison.mergeSortIterative(a));
		assertEquals("mergeSortIterative failed with size == 1", true, arrayCorrectlySorted);
	}

	// test with array of 10 items
	@Test
	public void test10() {
		boolean arrayCorrectlySorted = Arrays.equals(arr10sorted, SortComparison.quickSort(arr10));
		assertEquals("quickSort failed with 10 object array", true, arrayCorrectlySorted);
		System.arraycopy(arr10dupe, 0, arr10, 0, arr10.length);

		arrayCorrectlySorted = Arrays.equals(arr10sorted, SortComparison.quickSortWithShuffle(arr10));
		assertEquals("quickSortWithShuffle failed with 10 object array", true, arrayCorrectlySorted);
		System.arraycopy(arr10dupe, 0, arr10, 0, arr10.length);

		arrayCorrectlySorted = Arrays.equals(arr10sorted, SortComparison.insertionSort(arr10));
		assertEquals("insertionSort failed with 10 object array", true, arrayCorrectlySorted);
		System.arraycopy(arr10dupe, 0, arr10, 0, arr10.length);

		arrayCorrectlySorted = Arrays.equals(arr10sorted, SortComparison.selectionSort(arr10));
		assertEquals("selectionSort failed with 10 object array", true, arrayCorrectlySorted);
		System.arraycopy(arr10dupe, 0, arr10, 0, arr10.length);

		arrayCorrectlySorted = Arrays.equals(arr10sorted, SortComparison.mergeSortRecursive(arr10));
		assertEquals("mergeSortRecursive failed with 10 object array", true, arrayCorrectlySorted);
		System.arraycopy(arr10dupe, 0, arr10, 0, arr10.length);

		arrayCorrectlySorted = Arrays.equals(arr10sorted, SortComparison.mergeSortIterative(arr10));
		assertEquals("mergeSortIterative failed with 10 object array", true, arrayCorrectlySorted);
	}

	// test with array of 100 items
	@Test
	public void test100() {
		boolean arrayCorrectlySorted = Arrays.equals(arr100sorted, SortComparison.quickSort(arr100));
		assertEquals("quickSort failed with 100 object array", true, arrayCorrectlySorted);
		System.arraycopy(arr100dupe, 0, arr100, 0, arr100.length);

		arrayCorrectlySorted = Arrays.equals(arr100sorted, SortComparison.quickSortWithShuffle(arr100));
		assertEquals("quickSortWithShuffle failed with 100 object array", true, arrayCorrectlySorted);
		System.arraycopy(arr100dupe, 0, arr100, 0, arr100.length);

		arrayCorrectlySorted = Arrays.equals(arr100sorted, SortComparison.insertionSort(arr100));
		assertEquals("insertionSort failed with 100 object array", true, arrayCorrectlySorted);
		System.arraycopy(arr100dupe, 0, arr100, 0, arr100.length);

		arrayCorrectlySorted = Arrays.equals(arr100sorted, SortComparison.selectionSort(arr100));
		assertEquals("selectionSort failed with 100 object array", true, arrayCorrectlySorted);
		System.arraycopy(arr100dupe, 0, arr100, 0, arr100.length);

		arrayCorrectlySorted = Arrays.equals(arr100sorted, SortComparison.mergeSortRecursive(arr100));
		assertEquals("mergeSortRecursive failed with 100 object array", true, arrayCorrectlySorted);
		System.arraycopy(arr100dupe, 0, arr100, 0, arr100.length);

		arrayCorrectlySorted = Arrays.equals(arr100sorted, SortComparison.mergeSortIterative(arr100));
		assertEquals("mergeSortIterative failed with 100 object array", true, arrayCorrectlySorted);
	}

	// test mergeSort and quickSort with edge cases
	@Test
	public void testSortedAndRev() {
		double[] a = { 1, 2, 3, 4, 5, 6 };
		double[] aRev = { 6, 5, 4, 3, 2, 1 };

		boolean arrayCorrectlySorted = Arrays.equals(a, SortComparison.mergeSortRecursive(a));
		assertEquals("mergeSort failed with sorted array", true, arrayCorrectlySorted);

		arrayCorrectlySorted = Arrays.equals(a, SortComparison.mergeSortRecursive(aRev));
		assertEquals("mergeSort failed with reverse sorted array", true, arrayCorrectlySorted);
	}
	
	public static void main(String[] args) {
		// TODO: implement this method
	}

}
