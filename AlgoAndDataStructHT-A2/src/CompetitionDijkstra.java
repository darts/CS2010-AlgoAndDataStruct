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
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {
	private static final double INFINITY = Integer.MAX_VALUE / 3;

	String filename;
	int sA, sB, sC;

	int numOfIntersections, numOfStreets;
	int slowest;
	double[][] roadConnections;

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
		double[][] tmpRoads = null;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(filename));
			numOfIntersections = Integer.parseInt(bReader.readLine());
			numOfStreets = Integer.parseInt(bReader.readLine());

			tmpRoads = new double[numOfStreets][];
			// read from file and write to array
			String line;
			int i = 0;
			while ((line = bReader.readLine()) != null) {
				String[] lSplit = line.split(" ");
				double[] lVals = { Double.parseDouble(lSplit[0]), Double.parseDouble(lSplit[1]),
						Double.parseDouble(lSplit[2]) };
				tmpRoads[i++] = lVals;
			}
			bReader.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		roadConnections = new double[numOfIntersections][];
		for(int i = 0; i < numOfIntersections; i++) {
			int numOfAdjStreets = 0;
			for(int j = 0; j < numOfStreets; j++) {
				if(tmpRoads[j][0] == i)
					numOfAdjStreets++;
			}
			
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

		for (int i = 0; i < numOfIntersections; i++) {

		}

		return -1;
	}
	
	
	private class Graph{
		double[][] streets;
		int numOfIntersections, numOfStreets;
		Intersection[] intersections;
		Graph(double[][] streets, int numOfIntersections, int numOfStreets){
			this.streets = streets; 
			this.numOfIntersections = numOfIntersections;
			this.numOfStreets = numOfStreets;
			intersections = new Intersection[numOfIntersections];
		}
		
		private class Intersection{
			
			Intersection(double[] connections) {
				
			}
		}
	}
	
	
}
