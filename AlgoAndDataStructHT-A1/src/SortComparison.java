import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// -------------------------------------------------------------------------

/**
 * This class contains static methods that implementing sorting of an array of
 * numbers using different sort algorithms.
 *
 * @author
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
		if (a == null || a.length < 2)
			return a;
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
		double[] array = quickSortRecursive(a, 0, a.length - 1);
		return array;
	}

	private static double[] quickSortRecursive(double a[], int start, int finish) {
		if (finish - start >= 1) {// check for valid arrays again
			int i = start + 1;
			int j = finish;

			while (i < j) {
				while (a[i] < a[start] && i < a.length) // find number higher than pivot
					i++;
				while (a[j] > a[start] && j > 0) // find number higher than pivot
					j--;
				if (a[i] > a[j] && i < j) { // swap
					double tmp = a[i];
					a[i] = a[j];
					a[j] = tmp;
				}
			}
			double tmp = a[start]; // move pivot to center
			a[start] = a[j];
			a[j] = tmp;

			if (start != j) // call recursively on left sub-array
				a = quickSortRecursive(a, start, j - 1);
			if (finish != j) // call recursively on right sub-array
				a = quickSortRecursive(a, j + 1, finish);
		} 
//		else{ // only 2 items
//			if (a[start] > a[finish]) { // swap if required
//				double tmp = a[start];
//				a[start] = a[finish];
//				a[finish] = tmp;
//			}
//		}

		return a;
	}

	// test with shuffling array
	static double[] quickSortWithShuffle(double a[]) {
		if (a == null || a.length < 2) // check for invalid/already sorted arrays
			return a;
		a = shuffleArr(a);
		return quickSortRecursive(a, 0, a.length - 1);
	}

//	//test with shuffling array and insertionSort for arrays < 10
//		static double[] quickSortWithShuffle(double a[]) {
//			if (a == null || a.length < 2) // check for invalid/already sorted arrays
//				return a;
//			a = shuffleArr(a);
//			return quickSortRecursive(a, 0, a.length - 1);
//		}

	/*
	 * This is an algorithm I found online on StackOverFlow:
	 * https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
	 * Function has running cost of N. Where N is the size of the array. I worked
	 * through it on paper and understand its function
	 */
	private static double[] shuffleArr(double a[]) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = a.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1); // pick a number 0<=index<=i (rnd.nextInt upper bound is exclusive)
			double tmp = a[index];
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
		if(a == null || a.length < 2)
			return a;
		
		return mSortR(a, 0, a.length-1);
	}
	
	private static double[] mSortR(double[] a, int lo, int hi) {
		if(lo == hi)
			return a;
		int mid = lo + (hi-lo)/2;
		double[] aLo = mSortR(a, lo, mid);
		double[] aHi = mSortR(a, mid+1, hi);
		return mergeR(aLo, aHi, lo, mid, hi);
	}
	
	private static double[] mergeR(double[] aLo, double[] aHi, int lo, int mid, int hi) {
		double[] a = aHi;	//for better performance on almost sorted arrays
		int i = lo, j = mid+1;
		for(int k = lo; k < hi && i <= mid; k++) {
			if(aLo[i] <= aHi[j] || j > hi)
				a[k] = aLo[i++];
			else
				a[k] = aHi[j++];
		}
		return a;
	}
	

	//***********************end mergeSortRecursive************************
	
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort. This
	 * method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */

	static double[] mergeSortIterative(double a[]) {
		if(a == null || a.length < 2)
			return a;
		return mSortI(a);
	}
	
	private static double[] mSortI(double a[]) {
		
	}
	
	//*********************end mergesortIterative*************************

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
	
	//*****************************end selectionSort**********************

	public static void main(String[] args) {

		// todo: do experiments as per assignment instructions
	}

}// end class
