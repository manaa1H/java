/**
 * UniqueOrderedList class is an ADT where the elements are in order but are also unique: if you try to insert
 * the same element in the ADT twice, nothing happens the second time. At most one copy of each
 * element is stored in the ADT
 * 
 * @author Manaal Hashmi CS1027A SECTION 001 (Student #:251314405)
 * @since 2023-12-1
 */
public class UniqueOrderedList<T extends Comparable<T>> implements UniqueOrderedListADT<T> {
	// instance variables of the size of the list and the head node
	private int size;
	private LinearNode<T> head;
	

	/**
	 * Constructor UniqueOrderedList initializes the head as null, and size as 0
	 */
	public UniqueOrderedList() {
		this.head = null;
		size = 0;
	}
	
	/**
	 * Constructor UniqueOrderedList allows data to be added into list
	 * @param data user wants to add
	 */
	public UniqueOrderedList(T[] data) { 
		this();
		for (int i = 0; i < data.length; i++) {
			this.add(data[i]);
		}
	}
	/**
	 * Boolean method contains checks if the list contains a given element
	 * @param element user wants to check is in the list or not
	 * @return true if element is found in the list
	 * @return false if list does not contain element
	 */
	public boolean contains (T element) { 

		LinearNode<T> curr = this.head;
		while (curr != null  && curr.getData().compareTo(element) <= 0)  {
			if (curr.getData().equals(element)) { 
				return true;
			} else {
				curr = curr.getNext();
			}
		}
		return false;

	}
	/**
	 * Boolean method add allows an element to be added into the list, only if
	 * it is not in the list already.
	 * @param element user wants to add to list
	 * @return true if the element was successfully added to the list
	 * @return false if the element the user wants too add is already in the list
	 * so therefore should not be added into the list
	 */
	public boolean add(T element) { 

		if (this.contains(element)) { 
			return false;
		} else {

			if (this.head == null || this.head.getData().compareTo(element) > 0) {
				this.head = new LinearNode<T>(element,head);
			} else {
				LinearNode<T> curr = this.head;
				LinearNode<T> prev = null;

				while (curr != null && curr.getData().compareTo(element) < 0) { 
					prev = curr;
					curr = curr.getNext();
				}

				prev.setNext(new LinearNode<T> (element, curr));
			}
		}
		size+=1;
		return true;
	}
	
	/**
	 * Constructor size allows the user to access the size of the list
	 * @return the size of the UniqueOrderedList
	 */
	public int size() {
		return this.size;
	}
	/**
	 * Interface SimpleIterable allows the unique list to use a CopyableIterator
	 */
	public interface SimpleIterable<T>{
		public CopyableIterator<T> iterator();
	}
	/**
	 * Constructor iterator method that has no parameters and returns an object that implements the
     * CopyableIterator interface. This iterator method instantiates and return a new iterator
     * object from the UOLIterator class and  starts iteration at the
     * beginning of the list.
	 * @return an UOLIterator starting at a given head node
	 */
	public CopyableIterator<T> iterator() {
        UOLIterator<T> iterator = new UOLIterator<>(head);
        return iterator;
    }
}
