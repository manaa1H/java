/**
 * UOLIterator class creates a iterator that keeps a pointer to the element in the list
 * that is next to be returned by the iterator The elements should be returned in the 
 * order that they appear in the structure.
 * 
 * @author Manaal Hashmi CS1027A SECTION 001 (Student #:251314405)
 * @since 2023-12-1
 */
import java.util.NoSuchElementException;
import java.util.Iterator;

public class UOLIterator<T> implements CopyableIterator<T> {
	// instance variable curr declared as type linear node 
	private LinearNode<T> curr;
	
	/**
	 * Constructor UOLIterator initializes curr as null if the list is empty.
	 * If the start node of the list is null it means the list is empty.
	 * If its not empty the curr will be initialized as the startNode.
	 * @param the startNode of the list
	 */
	public UOLIterator(LinearNode<T> startNode) {
		// start curr at null of list is empty
		if (startNode == null) {
            this.curr = null;
        } else {
        	//initialize curr to startnode if one exists
            this.curr = startNode;
        }
    }
	
	/**
	 * Interface CopyableIterator that extends iterator established in the class 
	 * to be implemented.
	 */
	public interface CopyableIterator<T> extends Iterator<T>{
		public CopyableIterator<T> copy();
	}
	
	/**
	 * Boolean hasNext method that checks if the curr node is null or not
	 * @return true if current is not null
	 * @return false if current is null
	 */
	public boolean hasNext() {
		return curr != null; 
		
	}
	
	/**
	 * Constructor next method that gets the next
	 * unvisited element in the list.
	 * @return element of type T which is the next element
	 * @exception NoSuchElementException if there is no next element
	 */
	public T next() throws NoSuchElementException {
		// first we make sure there's a next element and tell user if theres
		//no next with the NoSuchElementException
	    if (!hasNext()) {
	        throw new NoSuchElementException("iterator empty");
	    }
	    //if there is an element we can get whats contained in curr and return it
	    T result = curr.getData();
	    //after this operation  we set curr to the next element
	    curr = curr.getNext();
	    return result;
	}
	
	/**
	 * Constructor copy method copy that copies the iterator position to another,
	 *  separate iterator. After copying, both iterators can progress through
t	 *  the collection from the same (copied) point. This should result in two
	 *  independent iterators
	 * @return a new UOLIterator object that is at the same position as the current iterator
	 */
	public UOLIterator<T> copy() {
		// if curr is null we can start the copied iterator at null
		//since curr is empty anyways
	    if (curr == null) {
	       return new UOLIterator<>(null);
	    } else {
	    	//if curr is not null we start the new iterator at the current position
	    	// of the first iterator so they start at the same place
	    	return new UOLIterator<>(curr);      
	    }
	}
	
}
