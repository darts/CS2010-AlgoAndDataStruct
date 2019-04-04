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

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA,       sB, sC: speeds for 3 contestants
	 */
	String filename;
	int sA, sB, sC;

	int numOfIntersections, numOfStreets;
	double gridArr[][];

	CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {
		this.filename = filename;
		this.sA = sA;
		this.sB = sB;
		this.sC = sC;

	}

	private void readFromFile() {
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(filename));
			numOfIntersections = Integer.parseInt(bReader.readLine());
			numOfStreets = Integer.parseInt(bReader.readLine());

			gridArr = new double[numOfIntersections][numOfIntersections]; //create array
			//init array values to infinite except for a 
			for(int i = 0; i < numOfIntersections; i++) 
				for(int j = 0; j < numOfIntersections; j++) 
					gridArr[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
			
			String line;
			while ((line = bReader.readLine()) != null) {
				String[] lVals = line.split(" ");
//				gridArr[]
			}
			bReader.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants can
	 *         meet
	 */
	public int timeRequiredforCompetition() {

		// TO DO
		return -1;
	}

}