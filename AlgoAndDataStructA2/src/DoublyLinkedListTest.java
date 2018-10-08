import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 * Test class for Doubly Linked List
 *
 * @author
 * @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest {
	// ~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new DoublyLinkedList<Integer>();
	}

	// ~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check if the insertBefore works
	 */
	@Test
	public void testInsertBefore() {
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 2);
		testDLL.insertBefore(2, 3);

		testDLL.insertBefore(0, 4);
		assertEquals("Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3",
				testDLL.toString());
		testDLL.insertBefore(1, 5);
		assertEquals("Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3",
				testDLL.toString());
		testDLL.insertBefore(2, 6);
		assertEquals("Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3",
				testDLL.toString());
		testDLL.insertBefore(-1, 7);
		assertEquals(
				"Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list",
				"7,4,5,6,1,2,3", testDLL.toString());
		testDLL.insertBefore(7, 8);
		assertEquals(
				"Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list",
				"7,4,5,6,1,2,3,8", testDLL.toString());
		testDLL.insertBefore(700, 9);
		assertEquals(
				"Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list",
				"7,4,5,6,1,2,3,8,9", testDLL.toString());

		// test empty list
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position 0 - expected the element at the head of the list",
				"1", testDLL.toString());
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(10, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position 10 - expected the element at the head of the list",
				"1", testDLL.toString());
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(-10, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position -10 - expected the element at the head of the list",
				"1", testDLL.toString());
	}

	@Test
	public void testStack() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		Integer i = 1; 
		testDLL.push(i);
		
		assertEquals("Checking popping an element from a stack of size 1", i , testDLL.pop());
		assertEquals("Checking popping an element from an empty stack", null, testDLL.pop());
		testDLL.push(1);
		testDLL.push(2);
		testDLL.push(3);
		testDLL.push(4);
		testDLL.push(5);
		i = 5;
		assertEquals("Checking popping an element from a stack of larger size.", i , testDLL.pop());
	}

	@Test
	public void testQueue() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		Integer i = 1; 
		testDLL.enqueue(i);
		
		assertEquals("Checking dequeue an element from a queue of size 1", i , testDLL.dequeue());
		assertEquals("Checking dequeue an element from an empty queue", null, testDLL.dequeue());
		
		testDLL.enqueue(1);
		testDLL.enqueue(2);
		testDLL.enqueue(3);
		testDLL.enqueue(4);
		testDLL.enqueue(5);
		
		assertEquals("Checking popping an element from a stack of larger size.", i , testDLL.dequeue());
	}
	
	@Test
	public void testGet() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		assertEquals("Checking getting a number if list is empty.", null , testDLL.get(-1));
		
		Integer i = 1; 
		testDLL.insertBefore(0, i);
		testDLL.insertBefore(1, 2);
		testDLL.insertBefore(2, 3);
		testDLL.insertBefore(3, 4);
		
		assertEquals("Checking getting a number.", i , testDLL.get(0));
		i = 3;
		assertEquals("Checking getting a number.", i , testDLL.get(2));
		assertEquals("Checking getting a number outside the list.", null , testDLL.get(-1));
		assertEquals("Checking getting a number outside the list.", null , testDLL.get(100));
	}
	
	
	@Test
	public void testDel() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
	
		assertEquals("Checking delete an element from an empty queue", false, testDLL.deleteAt(-1));
	}
	
	@Test
	public void testReverse() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		testDLL.reverse();

		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 2);
		testDLL.insertBefore(2, 3);
		testDLL.insertBefore(3, 4);
		testDLL.insertBefore(4, 2);

		testDLL.reverse();
		assertEquals("Check reverse list", "2,4,3,2,1", testDLL.toString());
	}
	
	@Test
	public void testMakeUnique() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		testDLL.reverse();

		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 2);
		testDLL.insertBefore(2, 3);
		testDLL.insertBefore(3, 4);
		testDLL.insertBefore(4, 2);
		testDLL.insertBefore(4, 3);
		testDLL.insertBefore(4, 2);

		testDLL.makeUniqueue();
		
		assertEquals("Check reverse list", "1,2,3,4", testDLL.toString());
	}
	// TODO: add more tests here. Each line of code in DoublyLinkedList.java should
	// be executed at least once from at least one test.

}
