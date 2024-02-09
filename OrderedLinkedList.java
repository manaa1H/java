/**
 * OrderedLinkedList is a linked list generic class that allows ordered insertion.
 * 
 * @author Manaal Hashmi CS1027A SECTION 001 (Student #:251314405)
 * @since 2023-10-19
 */


//establishing the class as having a compareTo method
public class OrderedLinkedList<T extends Comparable<T>> {
	// instance variable of node type called head to store the start of the linked list
	private Node<T> head;
	// instance variable of integer type called size to keep track of the size of the list
	private int size;

	/**
	 * Constructor method  that accepts no parameters and creates an empty linked list. 
	 */
	public OrderedLinkedList() {
		this.head = null;
		this.size = 0;
	}
	
	/**
	 * Insert method that accepts a single parameter of type T (the generic type) and has
	 * void return type. The method inserts the value into the list in largest to smallest 
	 * order.
	 * @param data generic type T
	 */
	public void insert(T data) {
		// creates a new node
		Node <T> newNode = new Node<>(data);
		// if the start of the list is empty the newly made value we want to insert 
		// will become the head of the list so we establish it as the head
		if (head == null) {
			head = newNode;
		// otherwise we start indexing at the start of the list which is the head, and declare the prev node
		// as null
		} else {
			Node<T> currNode = head;
			Node<T> prevNode = null;
			// the while loop will keep going until it reaches null, and if
			// the value of the node we want to insert is less than the current node
			while (currNode != null && data.compareTo(currNode.getData()) < 0) {
				// updates prev nodes and curr nodes values to the next ones in the list
				prevNode = currNode;
				currNode = currNode.getNext();
			}
			// if the previous node is null the new node will be inserted at the beginning
			if (prevNode == null) {
				newNode.setNext(head);
				head = newNode;
			// if the previous node is not null we found the positon
			//to insert the new node
			} else {
				// now we put the new node between previous node and current node
				prevNode.setNext(newNode);
				newNode.setNext(currNode);
			}
		}
		// incriment the size of the list to keep track of the number of items in the list
		size++;
	} 
	/**
	 * Accessor get method that takes a single integer parameter i and returns the ith element in the list
	 *(of type T). The method is  zero indexed . If the ith element in the list does not exist, 
	 *the method should throw an IndexOutOfBoundsException
	 * @param integer i
	 * @return value stored in the node 
	 */
	public T get(int i) {
		// of the value we are looking for is out of the bounds of the list throw an exception
		if (i < 0 || i>= size) {
			throw new IndexOutOfBoundsException();
		}
		// indexing through each element in the list until it reaches i
		Node<T> currNode = head;
		for (int index = 0; index < i; index++) {
			currNode = currNode.getNext();
		}
		// once i is found return the data stored at i in the list
		return currNode.getData();
	}
	/**
	 * Accessor get method that gets the size of the number of elements of the list.
	 * @return size
	 */
	public int getSize() {
		return size;
	}
		
}
	
	



