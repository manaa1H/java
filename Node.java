/**
 * Node class of generic type
 * 
 * @author Manaal Hashmi CS1027A SECTION 001 (Student #:251314405)
 * @since 2023-10-19
 */
public class Node<T> {
	// private instance variables data of type T, and next of type Node<T>
	private T data;
	private Node<T> next;
	
	/**
	 * Constructor method  takes both data and next parameters.
	 * @param data
	 * @param next
	 */
	public Node(T data) {
		this.next = next;
		this.data = data;
	}
	
	/**
	 * Accessor getNext method 
	 * @return next
	 */
	public Node<T> getNext(){
		return next;
	}
	
	/**
	 * Accessor getData method 
	 * @return data
	 */
	public T getData(){
		return data;
	}
	
	/**
	 * Mutator method setNext sets next node
	 * @param node 
	 */
	public void setNext(Node<T> node) {
		this.next = node;
	}
	
	
}

