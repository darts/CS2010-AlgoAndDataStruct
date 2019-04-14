
/*************************
 * 1: The use of a 2D array in FW was the correct choice because 
 * the algorithm uses such a structure. A dedicated graph class 
 * could have been used but that would have been a waste of both 
 * memory and processing time and would offer no real benefit.
 * 
 * The use of arrays in dijkstra was justified as they provide a 
 * memory-efficient solution that avoids the overhead of dedicated
 * classes.
 * 
 * 2:  FW running time is consistently O(V^3) with little 
 * variation on input. Dijkstra depends heavily on the input. 
 * For a sparsely populated graph, Dijkstra is much faster that 
 * FW as it will only have to do relatively few compares for each
 * node however FW will have many.
 * For a densly populated graph, FW far outperforms dijkstra 
 * in this application as it reuses previous calculations in 
 * order to reduce running time of subsequent cycles.
 * 
 * 
 * 
 ************************/

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompetitionTests {

	@Test
	public void testDijkstraConstructor() {
		CompetitionDijkstra comp = new CompetitionDijkstra("input-I.txt", 60, 70, 84);
		assertEquals("constructor failed with valid input", comp.slowest, 60);

		comp = new CompetitionDijkstra("input-J.txt", 70, 60, 84);
		assertEquals("constructor failed with valid input", comp.slowest, 60);

		comp = new CompetitionDijkstra("input-J.txt", 90, 60, 50);
		assertEquals("constructor failed with valid input", comp.slowest, 50);

		comp = new CompetitionDijkstra("notARealInputFile.txt", 70, 60, 84);
		assertEquals("constructor failed with valid input", comp.fileInvalid, true);

		comp = new CompetitionDijkstra("input-noStreets.txt", 70, 60, 84);
		assertEquals("constructor failed with valid input", comp.fileInvalid, true);

	}

	@Test
	public void testDijkstra() {
		CompetitionDijkstra comp = new CompetitionDijkstra("input-I.txt", 60, 70, 84);
		assertEquals("dijkstra failed with input I", comp.timeRequiredforCompetition(), 200);

		comp = new CompetitionDijkstra("input-J.txt", 70, 60, 84);
		assertEquals("dijkstra failed with input I", comp.timeRequiredforCompetition(), -1);

		comp = new CompetitionDijkstra("input-J.txt", 90, 60, 50);
		assertEquals("dijkstra failed with input j", comp.timeRequiredforCompetition(), -1);
	}

	@Test
	public void testFWConstructor() {
		CompetitionFloydWarshall comp = new CompetitionFloydWarshall("input-I.txt", 60, 70, 84);
		assertEquals("constructor failed with valid input", comp.slowest, 60);

		comp = new CompetitionFloydWarshall("input-J.txt", 70, 60, 84);
		assertEquals("constructor failed with valid input", comp.slowest, 60);

		comp = new CompetitionFloydWarshall("input-J.txt", 90, 60, 50);
		assertEquals("constructor failed with valid input", comp.slowest, 50);

		comp = new CompetitionFloydWarshall("notARealInputFile.txt", 70, 60, 84);
		assertEquals("constructor failed with valid input", comp.fileInvalid, true);

		comp = new CompetitionFloydWarshall("input-noStreets.txt", 70, 60, 84);
		assertEquals("constructor failed with valid input", comp.fileInvalid, true);
	}

	@Test
	public void testFW() {
		CompetitionFloydWarshall comp = new CompetitionFloydWarshall("input-I.txt", 60, 70, 84);
		assertEquals("FW failed with input I", comp.timeRequiredforCompetition(), 200);

		comp = new CompetitionFloydWarshall("input-J.txt", 70, 60, 84);
		assertEquals("dijkstra failed with input I", comp.timeRequiredforCompetition(), -1);

		comp = new CompetitionFloydWarshall("input-J.txt", 90, 60, 50);
		assertEquals("dijkstra failed with input I", comp.timeRequiredforCompetition(), -1);
	}

}
