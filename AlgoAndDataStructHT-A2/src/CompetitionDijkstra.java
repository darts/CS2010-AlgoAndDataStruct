import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

	private static final double INFINITY = Integer.MAX_VALUE / 3;

	String filename;
	int sA, sB, sC;

	int numOfIntersections, numOfStreets;
	int slowest;
	double[][] gridArr;
	boolean fileInvalid = false;

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA,       sB, sC: speeds for 3 contestants
	 */
	CompetitionDijkstra(String filename, int sA, int sB, int sC) {
		this.filename = filename;
		this.sA = sA;
		this.sB = sB;
		this.sC = sC;
		this.initArrayAndSlowest();
	}

	// initialise the array and get slowest person
	private void initArrayAndSlowest() {
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(filename));
			numOfIntersections = Integer.parseInt(bReader.readLine());
			numOfStreets = Integer.parseInt(bReader.readLine());
			if (numOfIntersections == 0 || numOfStreets == 0)
				fileInvalid = true;
			else {
				gridArr = new double[numOfIntersections][numOfIntersections]; // create array
				// init array values to infinite except for a
				for (int i = 0; i < numOfIntersections; i++)
					for (int j = 0; j < numOfIntersections; j++)
						gridArr[i][j] = INFINITY;

				// read from file and write to array
				String line;
				while ((line = bReader.readLine()) != null) {
					String[] lVals = line.split(" ");
					gridArr[Integer.parseInt(lVals[0])][Integer.parseInt(lVals[1])] = Double.parseDouble(lVals[2]);
				}
				bReader.close();
			}
		} catch (Exception e) {
			fileInvalid = true;
		}

		if (sA < sB && sA < sC)
			slowest = sA;
		else if (sB < sA && sB < sC)
			slowest = sB;
		else
			slowest = sC;
	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants can
	 *         meet
	 */
	public int timeRequiredforCompetition() {
		if ((sA > 100 || sA < 50) || (sB > 100 || sB < 50) || (sC > 100 || sC < 50))
			return -1;
		
		if (fileInvalid)
			return -1;
		double longestShortest = 0;
		for (int i = 0; i < numOfIntersections; i++) {
			double[] dist = new double[numOfIntersections];
			boolean[] permanent = new boolean[numOfIntersections];
			boolean[] reached = new boolean[numOfIntersections];
			int numActive = 1;
			for (int m = 0; m < numOfIntersections; m++) {
				dist[m] = INFINITY;
				permanent[m] = false;
				reached[m] = false;
			}
			dist[i] = 0;
			reached[i] = true;

			do {
				int currentLowestAddr = getLowestAddr(dist, permanent);
				for (int j = 0; j < numOfIntersections; j++) {
					if ((gridArr[currentLowestAddr][j] + dist[currentLowestAddr]) < dist[j] && !permanent[j]) {
						dist[j] = (gridArr[currentLowestAddr][j] + dist[currentLowestAddr]);
						numActive++;
						reached[j] = true;
					}
				}
				permanent[currentLowestAddr] = true;
				numActive--;
			} while (numActive > 0);
			double tmpLS = getHighestValue(dist);
			if (tmpLS == INFINITY)
				return -1;
			longestShortest = (tmpLS > longestShortest) ? tmpLS : longestShortest;
		}
		longestShortest *= 1000; // convert to meters
		return (int) Math.ceil(longestShortest / slowest);

	}

	private int getLowestAddr(double[] arr, boolean[] perm) {
		int lowest = 0;
		for (int i = 1; i < arr.length; i++)
			lowest = ((arr[i] < arr[lowest] && !perm[i]) || perm[lowest]) ? i : lowest;
		return lowest;
	}

	private double getHighestValue(double[] arr) {
		double highest = 0;
		for (int i = 0; i < arr.length; i++)
			highest = (arr[i] > highest) ? arr[i] : highest;
		return highest;
	}
	
	class minHeap {
		double[] arr;
		int activeItems;
		int size;
		
		minHeap(int size){
			this.size = size;
			arr = new double[size]; //reduce array size to preserve memory
			activeItems = 0;
		}
		
		boolean isEmpty() {
			return activeItems == 0;
		}
		
		double getMin() {
			return (activeItems > 0) ? arr[1] : -1;
		}
		
		//the array is kept smaller by default in order to preserve memory
		//this function changes the size of the array
		private void changeSize(int newSize) {
			double[] tmp = new double[newSize];
			for(int i = 0; i < activeItems; i++) 
				tmp[i] = arr[i];
			arr = tmp;
		}
		
		void swap(int a, int b) {
			double tmp = arr[a];
			arr[a] = arr[b];
			arr[b] = tmp;
		}
		
		private boolean greater(int i, int j) {
	        return arr[i] > arr[j];
	    }
		
		private void swim(int k) {
	        while (k > 1 && greater(k/2, k)) {
	            swap(k, k/2);
	            k = k/2;
	        }
	    }

	    private void sink(int k) {
	        while (2*k <= activeItems) {
	            int j = 2*k;
	            if (j < activeItems && greater(j, j+1)) j++;
	            if (!greater(k, j)) break;
	            swap(k, j);
	            k = j;
	        }
	    }
		
		void insert(double item) {
			if(activeItems == arr.length - 1) changeSize(activeItems*2);
			arr[++activeItems] = item;
			swim(activeItems);	
		}
		
		double delMin() {
	        double min = arr[1];
	        swap(1, activeItems--);
	        sink(1);

	        if ((activeItems > 0) && (activeItems == (arr.length - 1) / 4)) changeSize(arr.length / 2);
	        return min;
	    }
		
	}
	

}
