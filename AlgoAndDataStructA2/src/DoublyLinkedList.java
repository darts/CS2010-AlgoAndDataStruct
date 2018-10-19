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
		head = null;
		tail = null;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * 
	 * @return true if list is empty, and false otherwise
	 *
	 *         Worst-case asymptotic running time cost: O(1)
	 *
	 *         Justification: There single if statement (which we assume to take
	 *         O(1) time to execute) does not change with the size of the list and
	 *         therefore will always have an asymptotic running time of O(1).
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
	 *         Worst-case asymptotic running time cost: O(N)
	 *
	 *         Justification: We have assumed the cost of inserting an element to be
	 *         O(1). The if statement at the start takes constant time again. The
	 *         for loop can run a maximum of N/2 times, therefore the worst case
	 *         running time is O(N/2) -> O(N), with N being the size of the list.
	 *         The if statements at the start have constant running time of O(1),
	 *         therefore we can ignore their effect.
	 */
	public void insertBefore(int pos, T data) {
		if (isEmpty()) {// first obj, head = tail = obj
			head = new DLLNode(data, null, null);
			tail = head;
			listSize++;
			return;
		} else if (listSize == 1) {// only one obj in list
			if (pos <= 0) {// insert as first obj
				head = new DLLNode(data, null, tail);

				tail.prev = head;
			} else {// insert as second obj
				tail = new DLLNode(data, head, null);
				head.next = tail;
			}
			listSize++;
			return;
		} else if (pos <= 0) {// insert as first object
			DLLNode tmpNode = new DLLNode(data, null, head);
			head.prev = tmpNode;
			head = tmpNode;
			listSize++;
			return;
		} else if (pos >= listSize) {// insert as last object
			DLLNode tmpNode = new DLLNode(data, tail, null);
			tail.next = tmpNode;
			tail = tmpNode;
			listSize++;
			return;
		}

		if (pos < listSize / 2) {// item is in the first half of the list
			DLLNode tmpObj = head;
			for (int i = 0; i < pos; i++) {// loop through to find the item
				tmpObj = tmpObj.next;
			}
			insertElement(data, tmpObj.prev, tmpObj);
		} else {// item is in the second half of the list
			DLLNode tmpObj = tail;
			int adPos = listSize - pos;// new position as we start from the other end of the list
			for (int i = 0; i < adPos - 1; i++) {// loop through to find the item
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
	 *         Worst-case asymptotic running time cost: O(N)
	 *
	 *         Justification: the getObjAt function has a worst case running time of
	 *         O(N) and since this function only calls that function (and runs a
	 *         constant time test (if) which we can ignore), the running time is
	 *         O(N).
	 *
	 */
	public T get(int pos) {
		DLLNode tmpObj = getObjAt(pos);
		if (tmpObj != null)
			return tmpObj.data;
		return null;
	}

	/*
	 * Running time: O(N)
	 * 
	 * Justification: Before running, the function determines which half of the list
	 * to run the search on, therefore in the worst case, the for loop runs for N/2
	 * (N/2 -> N) iterations (with N being the size of the list). The if statements
	 * are assumed to have constant running time and can therefore be ignored.
	 */

	private DLLNode getObjAt(int pos) {
		if (isEmpty() || pos < 0 || pos >= listSize) // ensure the item can be in the list
			return null;

		if (pos < listSize / 2) {// item is in the first half of the list
			DLLNode tmpObj = head;
			for (int i = 0; i < pos; i++) {// loop through to find the item
				tmpObj = tmpObj.next;
			}
			return tmpObj;
		} else {// item is in the second half of the list
			DLLNode tmpObj = tail;
			int adPos = listSize - pos;// new position as we start from the other end of the list
			for (int i = 0; i < adPos - 1; i++) {// loop through to find the item
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
	 *         Worst-case asymptotic running time cost: O(N)
	 *
	 *         Justification: The getObjAt function has a running time of N and the
	 *         delNode function has a constant running time. O(1) + O(N) = O(N)
	 */
	public boolean deleteAt(int pos) {
		DLLNode tmpNode = getObjAt(pos);
		return delNode(tmpNode);
	}

	/*
	 * Worst-case asymptotic running time cost: O(1)
	 *
	 * Justification: we have assumed all java functions have constant running time.
	 * This means that in the worst case we have O(1) * O(1) * O(1) running time
	 * which is O(1).
	 */
	private boolean delNode(DLLNode node) {
		if (node != null) {// the node exists
			if (node.prev != null) {// node is not head
				node.prev.next = node.next;
			} else { // node is head
				head = node.next;
				if (head != null)
					head.prev = null;
			}
			if (node.next != null) {// node is not tail
				node.next.prev = node.prev;
			} else {// node is tail
				tail = node.prev;
				if (tail != null)
					tail.next = null;
			}
			node = null;// delete the node
			listSize--;
			return true;
		}
		return false;
	}

	/**
	 * Reverses the list. If the list contains "A", "B", "C", "D" before the method
	 * is called Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic running time cost: O(N)
	 *
	 * Justification: The loop runs N/2 times and each of the internal functions
	 * have constant running time so O(N/2) * (O(1) + O(1)+...+O(1)) = O(N/2) = O(N)
	 */

	public void reverse() {
		if (!isEmpty()) {
			DLLNode fNode = head;// first obj
			DLLNode lNode = tail;// last obj
			for (int i = 0; i < listSize / 2; i++) {
				DLLNode fTmp = fNode.next;// first obj
				DLLNode lTmp = lNode.prev;// last obj
				swapElems(fNode, lNode);
				fNode = fTmp;
				lNode = lTmp;
			}
		}
	}

	/*
	 * Worst case running time cost: O(1)
	 * 
	 * Justification: each of the operations has O(1) running time. O(1) + O(1) +
	 * .... = O(1)
	 */

	private void swapElems(DLLNode first, DLLNode second) {
		DLLNode tmpFirst = new DLLNode(first.data, first.prev, first.next);
		DLLNode tmpSecond = new DLLNode(second.data, second.prev, second.next);

		second.prev = tmpFirst.prev;
		if (tmpFirst.next != second)// 2 items not next to each other
			second.next = tmpFirst.next;
		else
			second.next = first;

		first.next = tmpSecond.next;
		if (tmpSecond.prev != first)// 2 items not next to each other
			first.prev = tmpSecond.prev;
		else
			first.prev = second;

		if (tmpFirst.prev == null) {
			head = second;
			tail = first;
		}

		if (second.prev != null)
			second.prev.next = second;
		second.next.prev = second;
		if (first.next != null)
			first.next.prev = first;
		first.prev.next = first;
	}

	/**
	 * Removes all duplicate elements from the list. The method should remove the
	 * _least_number_ of elements to make all elements unique. If the list contains
	 * "A", "B", "C", "B", "D", "A" before the method is called Then it should
	 * contain "A", "B", "C", "D" after it returns. The relative order of elements
	 * in the resulting list should be the same as the starting list.
	 *
	 * Worst-case asymptotic running time cost: O(N^2)
	 *
	 * Justification: the outer loop runs N times and the inner loop runs N-1 times.
	 * This results in N * (N - 1) -> N^2 - N -> N^2 as all other operations are
	 * constant time.
	 */
	public void makeUnique() {
		DLLNode primNode = head;
		for (int i = 0; i < listSize - 1; i++) {
			DLLNode tmp = primNode.next;
			for (int j = i + 1; j < listSize; j++) {
				if (primNode.data.equals(tmp.data)) {
					tmp = tmp.next;
					if (tmp != null)
						delNode(tmp.prev);
					else
						delNode(tail);
					j--;
				} else
					tmp = tmp.next;
			}
			primNode = primNode.next;
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
	 *             Worst-case asymptotic running time cost: O(1)
	 *
	 *             Justification: insertBefore has constant running time when the
	 *             location is -1.
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
	 *         Worst-case asymptotic running time cost: O(1)
	 *
	 *         Justification: getObjAt has constant running time for first item in
	 *         the list. All other items also have constant running time. O(1) +
	 *         O(1) + .... = O(1)
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
	 *             Worst-case asymptotic running time cost: O(1)
	 *
	 *             Justification: insertBefore has constant running time for
	 *             location -1.
	 */
	public void enqueue(T item) {
		insertBefore(0, item);
	}

	/**
	 * This method returns and removes the element that was least recently added by
	 * the enqueue method.
	 * 
	 * @return the earliest item inserted with an enqueue; or null when the list is
	 *         empty.
	 *
	 *         Worst-case asymptotic running time cost: O(1)
	 *
	 *         Justification: getObjAt has constant running time for the last item
	 *         in the list. All other operations are constant time. O(1) + O(1) +
	 *         .... = O(1)
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
		for (DLLNode iter = head; iter != null; iter = iter.next) {
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
