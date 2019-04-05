import java.io.BufferedReader;
import java.io.FileReader;
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
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {

	public static void main(String[] args) {
		CompetitionFloydWarshall comp = new CompetitionFloydWarshall("tinyEWD.txt", 50, 50, 50);
		int time = comp.timeRequiredforCompetition();
		System.out.print(time);
//		comp.printSolution();
	}

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA,       sB, sC: speeds for 3 contestants
	 */
	private static final double INFINITY = Integer.MAX_VALUE / 3;

	String filename;
	int sA, sB, sC;

	int numOfIntersections, numOfStreets;
	double gridArr[][]; // road[from][to]
	int slowest;

	CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {
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
		} catch (Exception e) {
			e.printStackTrace(System.out);
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
		if ((sA > 100 && sA < 50) || (sB > 100 && sB < 50) || (sC > 100 && sC < 50))
			return -1;

		// run Floyd Warshall
		for (int k = 0; k < numOfIntersections; k++)
			for (int i = 0; i < numOfIntersections; i++)
				for (int j = 0; j < numOfIntersections; j++)
					if (gridArr[i][k] + gridArr[k][j] < gridArr[i][j])
						gridArr[i][j] = gridArr[i][k] + gridArr[k][j];

		double max = getMax();
		max *= 1000; // convert to meters
		return (int) Math.ceil(max/slowest);
	}

	//gets the largest num in the array
	private double getMax() {
		double max = -1;
		for (int i = 0; i < numOfIntersections; i++)
			for (int j = 0; j < numOfIntersections; j++)
				max = (gridArr[i][j] > max && gridArr[i][j] != INFINITY) ? gridArr[i][j] : max;
		return max;
	}

	void printSolution() {
		for (int i = 0; i < numOfIntersections; ++i) {
			for (int j = 0; j < numOfIntersections; ++j) {
				if (gridArr[i][j] == INFINITY)
					System.out.print("INF ");
				else
					System.out.print(gridArr[i][j] + "   ");
			}
			System.out.println();
		}
	}

}