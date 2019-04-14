import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
    	CompetitionDijkstra comp = new CompetitionDijkstra("input-I.txt", 60,70,84);
    	assertEquals("constructor failed with valid input", comp.slowest, 60);
  
    	comp = new CompetitionDijkstra("input-J.txt", 70,60,84);
    	assertEquals("constructor failed with valid input", comp.slowest, 60);
    	
    	comp = new CompetitionDijkstra("input-J.txt", 90,60,50);
    	assertEquals("constructor failed with valid input", comp.slowest, 50);
    	
    	comp = new CompetitionDijkstra("notARealInputFile.txt", 70,60,84);
    	assertEquals("constructor failed with valid input", comp.fileInvalid, true);
    	
    	comp = new CompetitionDijkstra("input-noStreets.txt", 70,60,84);
    	assertEquals("constructor failed with valid input", comp.fileInvalid, true);

    }	
    
    @Test 
    public void testDijkstra() {
    	CompetitionDijkstra comp = new CompetitionDijkstra("input-I.txt", 60,70,84);
    	assertEquals("dijkstra failed with input I",comp.timeRequiredforCompetition(),200);

    	comp = new CompetitionDijkstra("input-J.txt", 70,60,84);
    	assertEquals("dijkstra failed with input I",comp.timeRequiredforCompetition(),-1);
    	

    	comp = new CompetitionDijkstra("input-J.txt", 90,60,50);
    	assertEquals("dijkstra failed with input j",comp.timeRequiredforCompetition(),-1);
    }

    @Test
    public void testFWConstructor() {
    	CompetitionFloydWarshall comp = new CompetitionFloydWarshall("input-I.txt", 60,70,84);
    	assertEquals("constructor failed with valid input", comp.slowest, 60);
    	
    	comp = new CompetitionFloydWarshall("input-J.txt", 70,60,84);
    	assertEquals("constructor failed with valid input", comp.slowest, 60);
    	
    	comp = new CompetitionFloydWarshall("input-J.txt", 90,60,50);
    	assertEquals("constructor failed with valid input", comp.slowest, 50);
    	
    	comp = new CompetitionFloydWarshall("notARealInputFile.txt", 70,60,84);
    	assertEquals("constructor failed with valid input", comp.fileInvalid, true);
    	
    	comp = new CompetitionFloydWarshall("input-noStreets.txt", 70,60,84);
    	assertEquals("constructor failed with valid input", comp.fileInvalid, true);
    }
    
    @Test
    public void testFW() {
    	CompetitionFloydWarshall comp = new CompetitionFloydWarshall("input-I.txt", 60,70,84);
    	assertEquals("FW failed with input I",comp.timeRequiredforCompetition(),200);
  
    	comp = new CompetitionFloydWarshall("input-J.txt", 70,60,84);
    	assertEquals("dijkstra failed with input I",comp.timeRequiredforCompetition(),-1);
    	
    	comp = new CompetitionFloydWarshall("input-J.txt", 90,60,50);
    	assertEquals("dijkstra failed with input I",comp.timeRequiredforCompetition(),-1);
    }
    
}
