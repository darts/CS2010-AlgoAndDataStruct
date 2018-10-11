// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Senán d'Art
 *  @version 01/10/18 17:35:49
 */

/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * 
 * @param <T> This is a type parameter. T is used as a class name in the
 *        definition of this class.
 *
 *        When creating a new DoublyLinkedList, T should be instantiated with an
 *        actual class name that extends the class Comparable. Such classes
 *        include String and Integer.
 *
 *        For example to create a new DoublyLinkedList class containing String
 *        data: DoublyLinkedList<String> myStringList = new
 *        DoublyLinkedList<String>();
 *
 *        The class offers a toString() method which returns a comma-separated
 *        sting of all elements in the data structure.
 * 
 *        This is a bare minimum class you would need to completely implement.
 *        You can add additional methods to support your code. Each method will
 *        need to be tested by your jUnit tests -- for simplicity in jUnit
 *        testing introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>> {

	private int listSize = 0; 
	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode {
		public final T data; // this field should never be updated. It gets its
								// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * 
		 * @param theData  : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) {
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	/**
	 * Constructor of an empty DLL
	 * 
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() {
		head = new DLLNode(null, null, tail);
		tail = new DLLNode(null, head, null);
		head.next = tail;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * 
	 * @return true if list is empty, and false otherwise
	 *
	 *         Worst-case asymptotic running time cost: Theta(1)
	 *
	 *         Justification: There single if statement (which we assume to take
	 *         Theta(1) time to execute) does not change with the size of the list
	 *         and therefore will always have an asymptotic running time of
	 *         Theta(1).
	 */
	public boolean isEmpty() {
		if (listSize < 1)
			return true;
		return false;
	}

	/**
	 * Inserts an element in the doubly linked list
	 * 
	 * @param pos  : The integer location at which the new data should be inserted
	 *             in the list. We assume that the first position in the list is 0
	 *             (zero). If pos is less than 0 then add to the head of the list.
	 *             If pos is greater or equal to the size of the list then add the
	 *             element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 *         Worst-case asymptotic running time cost: Theta(N/2)
	 *
	 *         Justification: We have assumed the cost of inserting an element to be
	 *         O(1). The if statement at the start takes constant time again. The
	 *         for loop can run a maximum of N/2 times, therefore the worst case
	 *         running time is Theta(N/2), with N being the size of the list.
	 */
	public void insertBefore(int pos, T data) {
		if (isEmpty() || pos < 0) {
			insertElement(data, head, head.next);
			return;
		} else if (pos >= listSize) {
			insertElement(data, tail.prev, tail);
			return;
		}

		if (pos < listSize / 2) {// item is in the first half of the list
			DLLNode tmpObj = head;
			for (int i = 0; i <= pos; i++) {// loop through to find the item
				tmpObj = tmpObj.next;
			}
			insertElement(data, tmpObj.prev, tmpObj);
		} else {// item is in the second half of the list
			DLLNode tmpObj = tail;
			int adPos = listSize - pos;// new position as we start from the other end of the list
			for (int i = 0; i < adPos; i++) {// loop through to find the item
				tmpObj = tmpObj.prev;
			}
			insertElement(data, tmpObj.prev, tmpObj);
		}
	}

	// Running time: O(1)
	// Justification: the creation of a new object does not vary in difficulty. This
	// function does no calculation, just assignment. Therefore it is constant.
	private void insertElement(T data, DLLNode prev, DLLNode next) {
		DLLNode tmpNode = new DLLNode(data, prev, next);
		prev.next = tmpNode;
		next.prev = tmpNode;
		listSize++;
	}

	/**
	 * Returns the data stored at a particular position
	 * 
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null
	 *         otherwise.
	 *
	 *         Worst-case asymptotic running time cost: TODO
	 *
	 *         Justification: TODO
	 *
	 */
	public T get(int pos) {
		DLLNode tmpObj = getObjAt(pos);
		if (tmpObj != null)
			return tmpObj.data;
		return null;
	}

	/*
	 * Running time: Theta(N/2) Justification: Before running the function
	 * determines which half of the list to run the search on, therefore in the
	 * worst case, the for loop runs for N/2 iterations (with N being the size of
	 * the list).
	 */

	private DLLNode getObjAt(int pos) {
		if (isEmpty() || pos < 0 || pos >= listSize) // ensure the item can be in the list
			return null;

		if (pos < listSize / 2) {// item is in the first half of the list
			DLLNode tmpObj = head;
			for (int i = 0; i <= pos; i++) {// loop through to find the item
				tmpObj = tmpObj.next;
			}
			return tmpObj;
		} else {// item is in the second half of the list
			DLLNode tmpObj = tail;
			int adPos = listSize - pos;// new position as we start from the other end of the list
			for (int i = 0; i < adPos; i++) {// loop through to find the item
				tmpObj = tmpObj.prev;
			}
			return tmpObj;
		}
	}

	/**
	 * Deletes the element of the list at position pos. First element in the list
	 * has position 0. If pos points outside the elements of the list then no
	 * modification happens to the list.
	 * 
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified.
	 *
	 *         Worst-case asymptotic running time cost: TODO
	 *
	 *         Justification: TODO
	 */
	public boolean deleteAt(int pos) {
		DLLNode tmpNode = getObjAt(pos);
		return delNode(tmpNode);
	}

	private boolean delNode(DLLNode node) {
		if (node != null) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			node = null;
			listSize--;
			return true;
		}
		return false;
	}

	/**
	 * Reverses the list. If the list contains "A", "B", "C", "D" before the method
	 * is called Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic running time cost: TODO
	 *
	 * Justification: TODO
	 */
	public void reverse() {
		if (!isEmpty()) {
			for (int i = 0; i <= listSize / 2; i++) {
				if (i == 0) {
					swapElems(getObjAt(i), getObjAt((listSize - 1) - i));
				} else {
					swapElems(getObjAt(i), getObjAt((listSize - 1) - i));
				}
			}
		}
	}

	private void swapElems(DLLNode first, DLLNode second) {
		DLLNode tmpFirst = new DLLNode(first.data, first.prev, first.next);
		DLLNode tmpSecond = new DLLNode(second.data, second.prev, second.next);

		second.prev = tmpFirst.prev;
		second.next = tmpFirst.next;
		first.prev = tmpSecond.prev;
		first.next = tmpSecond.next;
		second.prev.next = second;
		second.next.prev = second;
		first.prev.next = first;
		first.next.prev = first;
	}

	/**
	 * Removes all duplicate elements from the list. The method should remove the
	 * _least_number_ of elements to make all elements uniqueue. If the list
	 * contains "A", "B", "C", "B", "D", "A" before the method is called Then it
	 * should contain "A", "B", "C", "D" after it returns. The relative order of
	 * elements in the resulting list should be the same as the starting list.
	 *
	 * Worst-case asymptotic running time cost: TODO
	 *
	 * Justification: TODO
	 */
	public void makeUnique() {
		for (int i = 0; i < listSize - 1; i++) {
			DLLNode primNode = getObjAt(i);
			for (int j = i + 1; j < listSize; j++) {
				DLLNode tmp = getObjAt(j);
				if (primNode.data.equals(tmp.data)) {
					delNode(tmp);
					j--;
				}
			}
		}
	}

	/*----------------------- STACK API 
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 */

	/**
	 * This method adds an element to the data structure. How exactly this will be
	 * represented in the Doubly Linked List is up to the programmer.
	 * 
	 * @param item : the item to push on the stack
	 *
	 *             Worst-case asymptotic running time cost: TODO
	 *
	 *             Justification: TODO
	 */
	public void push(T item) {
		insertBefore(-1, item);
	}

	/**
	 * This method returns and removes the element that was most recently added by
	 * the push method.
	 * 
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 *         Worst-case asymptotic running time cost: TODO
	 *
	 *         Justification: TODO
	 */
	public T pop() {
		if (!isEmpty()) {
			DLLNode node = getObjAt(0);
			T data = node.data;
			delNode(node);
			return data;
		}
		return null;
	}

	/*----------------------- QUEUE API
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 */

	/**
	 * This method adds an element to the data structure. How exactly this will be
	 * represented in the Doubly Linked List is up to the programmer.
	 * 
	 * @param item : the item to be enqueued to the stack
	 *
	 *             Worst-case asymptotic running time cost: TODO
	 *
	 *             Justification: TODO
	 */
	public void enqueue(T item) {
		insertBefore(-1, item);
	}

	/**
	 * This method returns and removes the element that was least recently added by
	 * the enqueue method.
	 * 
	 * @return the earliest item inserted with an equeue; or null when the list is
	 *         empty.
	 *
	 *         Worst-case asymptotic running time cost: TODO
	 *
	 *         Justification: TODO
	 */
	public T dequeue() {
		if (!isEmpty()) {
			DLLNode node = getObjAt(listSize - 1);
			T data = node.data;
			delNode(node);
			return data;
		}
		return null;
	}

	/**
	 * @return a string with the elements of the list as a comma-separated list,
	 *         from beginning to end
	 *
	 *         Worst-case asymptotic running time cost: Theta(n)
	 *
	 *         Justification: We know from the Java documentation that
	 *         StringBuilder's append() method runs in Theta(1) asymptotic time. We
	 *         assume all other method calls here (e.g., the iterator methods above,
	 *         and the toString method) will execute in Theta(1) time. Thus, every
	 *         one iteration of the for-loop will have cost Theta(1). Suppose the
	 *         doubly-linked list has 'n' elements. The for-loop will always iterate
	 *         over all n elements of the list, and therefore the total cost of this
	 *         method will be n*Theta(1) = Theta(n).
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;

		// iterate over the list, starting from the head
		for (DLLNode iter = head.next; iter.next != null; iter = iter.next) {
			if (!isFirst) {
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}

}
