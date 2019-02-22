//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// -------------------------------------------------------------------------

/**
 * This class contains static methods that implementing sorting of an array of
 * numbers using different sort algorithms.
 *
 * @author Senán d'Art
 * @version HT 2019
 */

class SortComparison {

	/**
	 * Sorts an array of doubles using InsertionSort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 *
	 */
	static double[] insertionSort(double a[]) {
		if (a == null || a.length < 2)
			return a;
		return insertionSortSpec(a, 0, a.length);
	}// end insertionsort

	private static double[] insertionSortSpec(double a[], int start, int finish) {
		for (int i = start + 1; i < finish; i++) {
			boolean minReached = false;
			for (int j = i; j >= 1 && !minReached; j--) {
				if (a[j] < a[j - 1]) {
					double tmp = a[j - 1];
					a[j - 1] = a[j];
					a[j] = tmp;
				} else {
					minReached = true;
				}
			}
		}
		return a;
	}

	// ***********************end of insertionSort***********************

	/**
	 * Sorts an array of doubles using Quick Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] quickSort(double a[]) {
		if (a == null || a.length < 2) // check for invalid/already sorted arrays
			return a;
		quickSortRecursive(a, 0, a.length - 1);
		return a;
	}

	private static void quickSortRecursive(double a[], int start, int finish) {
		int pivot = partition(a, start, finish);
		if (start < pivot - 1)
			quickSortRecursive(a, start, pivot - 1);
		if (finish > pivot + 1)
			quickSortRecursive(a, pivot + 1, finish);
	}

	private static int partition(double a[], int start, int finish) {
		int i = start;
		int j = finish + 1;

		while (true) {
			while (a[++i] < a[start]) // find number higher than pivot
				if (i == finish)
					break;
			while (a[--j] > a[start]) {} // find number higher than pivot
				
			if (j <= i)
				break;
			double tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;

		}
		double tmp = a[start]; // move pivot to center
		a[start] = a[j];
		a[j] = tmp;
		return j;
	}

	// test with shuffling array
	static double[] quickSortWithShuffle(double a[]) {
		if (a == null || a.length < 2) // check for invalid/already sorted arrays
			return a;
		a = shuffleArr(a);
		quickSortRecursive(a, 0, a.length - 1);
		return a;
	}

	/*
	 * This is an algorithm I found online on StackOverFlow:
	 * https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
	 * Function has running cost of N. Where N is the size of the array.
	 */
	private static double[] shuffleArr(double a[]) {
		double tmp = 0;
		int index = 0;
		Random rnd = ThreadLocalRandom.current();
		for (int i = a.length - 1; i > 0; i--) {
			index = rnd.nextInt(i + 1); // pick a number 0<=index<=i (rnd.nextInt upper bound is exclusive)
			tmp = a[index];
			a[index] = a[i];
			a[i] = tmp;
		}
		return a;
	}

	// *******************end of quickSort****************

	/**
	 * Sorts an array of doubles using Merge Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */

	/**
	 * Sorts an array of doubles using recursive implementation of Merge Sort. This
	 * method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */
	static double[] mergeSortRecursive(double[] a) {
		if (a == null || a.length < 2)
			return a;
		double[] aux = new double[a.length];
		mSortR(a, aux, 0, a.length - 1);
		return a;
	}

	private static void mSortR(double[] a, double[] aux, int lo, int hi) {
		if (lo >= hi)
			return;
		int mid = lo + (hi - lo) / 2;
		mSortR(a, aux, lo, mid);
		mSortR(a, aux, mid + 1, hi);
		mergeR(a, aux, lo, mid, hi);
	}

	private static void mergeR(double[] a, double[] aux, int lo, int mid, int hi) {
		System.arraycopy(a, lo, aux, lo, (hi-lo) + 1);
		
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (aux[i] > aux[j])
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}

	// ***********************end mergeSortRecursive************************

	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort. This
	 * method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */

	static double[] mergeSortIterative(double a[]) {
		if (a == null || a.length < 2)
			return a;
		return mSortI(a);
	}

	private static double[] mSortI(double a[]) {
		int arrLen = a.length;
		double[] aux = new double[arrLen];
		for (int i = 1; i < arrLen; i += i) {
			for (int j = 0; j < arrLen - i; j += i + i) {
				mergeR(a, aux, j, j + i - 1, Math.min(j + i + i - 1, arrLen - 1));
			}
		}
		return a;
	}

	// *********************end mergesortIterative*************************

	/**
	 * Sorts an array of doubles using Selection Sort. This method is static, thus
	 * it can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] selectionSort(double a[]) {
		if (a == null || a.length < 2)
			return a;
		for (int i = 0; i < a.length; i++) {
			int currentSmallestIndex = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[currentSmallestIndex]) {
					currentSmallestIndex = j;
				}
			}
			double tmp = a[i];
			a[i] = a[currentSmallestIndex];
			a[currentSmallestIndex] = tmp;
		}
		return a;
	}

	// *****************************end selectionSort**********************

	public static void main(String[] args) {
		//Uncomment this whole section to test timing
		//Also uncomment lines 1 through 3
		
		//TEST START ******************************************************
		
//		ThreadLocalRandom.current(); // the first call to ThreadLocalRandom takes a very long time (~0.4
//										// milliseconds)
//		int numOfRuns = 3; // how many times to run
//		double averageTime = 0;
//		for (int k = 0; k < numOfRuns; k++) {
//			// READ ELEMENTS FROM FILE ************************************
//			ArrayList<Double> aList = new ArrayList<Double>();
//			try {
//				// change path as required
//				String filePath = "numbers10.txt";
//				BufferedReader bReader = new BufferedReader(new FileReader(filePath));
//				String number;
//				while ((number = bReader.readLine()) != null) {
//					aList.add(Double.parseDouble(number));
//				}
//				bReader.close();
//			} catch (Exception e) {
//				e.printStackTrace(System.out);
//			}
//			int i = 0;
//			double[] arr = new double[aList.size()];
//			for (Double dubdub : aList)
//				arr[i++] = dubdub;
//			// FINISH READING FROM FILE ***********************************
//
//			double startTime = System.nanoTime();
//			// Uncomment the one you want to test:
////			insertionSort(arr);
////			quickSort(arr);
////			quickSortWithShuffle(arr);
////			mergeSortRecursive(arr);
////			mergeSortIterative(arr);
////			selectionSort(arr);
//			double finTime = System.nanoTime();
//			averageTime += ((finTime - startTime) / 1000000);
//		}
//		System.out.println(averageTime / numOfRuns);
		
		
		//TEST END *********************************************************
	}
}// end class
