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

	/**
	 * Sorts an array of doubles using Quick Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] quickSort(double a[]) {
		if (a == null || a.length < 2)
			return a;
		double[] array = quickSortRecursive(a, 0, a.length - 1);
		return array;
	}// end quicksort

	private static double[] quickSortRecursive(double a[], int start, int finish) {
//		assert (start >= 0 && finish < a.length);
		if (finish - start >= 2 && start >= 0 && finish < a.length) {
			int i = start + 1;
			int j = finish;

			while (i < j) {
				while (a[i] < a[start] && i < a.length)
					i++;
				while (a[j] > a[start] && j > 0)
					j--;
				if (a[i] > a[j] && i < j) {
					double tmp = a[i];
					a[i] = a[j];
					a[j] = tmp;
				}
			}
			double tmp = a[start];
			a[start] = a[j];
			a[j] = tmp;

			if (start != j)
				a = quickSortRecursive(a, start, j-1);
			if (finish != j)
				a = quickSortRecursive(a, j+1, finish);
		} else {
			if (a[start] > a[finish]) {
				double tmp = a[start];
				a[start] = a[finish];
				a[finish] = tmp;
			}
		}

		return a;
	}

	private static double[] shuffleArr(double a[]) {

		return a;
	}

	/**
	 * Sorts an array of doubles using Merge Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort. This
	 * method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */

	static double[] mergeSortIterative(double a[]) {

		// todo: implement the sort
		return null;
	}// end mergesortIterative

	/**
	 * Sorts an array of doubles using recursive implementation of Merge Sort. This
	 * method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */
	static double[] mergeSortRecursive(double a[]) {
		return null;
		// todo: implement the sort

	}// end mergeSortRecursive

	/**
	 * Sorts an array of doubles using Selection Sort. This method is static, thus
	 * it can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] selectionSort(double a[]) {
		return null;
		// todo: implement the sort

	}// end selectionsort

	public static void main(String[] args) {

		// todo: do experiments as per assignment instructions
	}

}// end class
