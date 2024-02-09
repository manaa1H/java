/**
 * ArrayStack class to create array stack to use in MineEscape
 * @author Manaal Hashmi CS1027A SECTION 001 (Student #:251314405)
 * @since 2023-11-11
 */

//class implements stackADT interface 
public class ArrayStack<T> implements StackADT<T>{
	//private instance variables representing the array and the top of stack
	private T[] array;
	private int top;
	
	/**
	 * Constructor method ArrayStack
	 * initializes array and top
	 */
	public ArrayStack() {
		//creates array with capacity of 10
		array = (T[])(new Object[10]);
		top = -1;
	}
	
	/**
	 * Constructor method push adds new element to the ArrayStack
	 * @param element
	 */
	public void push (T element) {
		// making top and capacity doubles to see the fraction of array being used
		double topDouble =  (double)(top+1) ;
		double CapDouble = (double)array.length;
		
		// before adding new elements to the list expand capacity if fraction is >= 0.75
		if (topDouble/CapDouble >= 0.75) {
			expandCapacity();
		}
		
		//incriment top to keep track of its top value
		top++;
		// adds a new element to the top of stack
		array[top] = element;
	}
	
	/**
	 * Constructor method pop removes top element from ArrayStack and returns it
	 * @return result ( the value of the removed element )
	 * @exception StackException
	 */
	public T pop() throws StackException{
		double topDouble =  (double)(top+1) ;
		double CapDouble = (double)array.length;
		
		// check if stack is empty before attempting to remove an element to avoid error
		if (top == -1) {
			// if empty throw stack is empty exception
			throw new StackException("Stack is empty");
		}
		
		// establish the result we will return as the top element of the stack
		T result = array[top];
		
		// try to shrink capacity if the fraction of the array being used is <= one fourth
		// and the capacity is greater than equal to 20
		try {
			if (topDouble/CapDouble <=  0.25 && array.length >= 20) {
				shrinkCapacity();
			}
			
		// then remove the element from the top of the array and re incriment the top
		// the top index should one less than it was before	
		}finally{
			array[top] = null;
			top--;
		}
		// return the popped (removed) ArrayStack element
		return result;
		
		
	}
	
	/**
	 * Constructor method peek shows top element of the stack
	 * @return array[top] ( the top element )
	 * @exception StackException
	 */
	public T peek() throws StackException{
		// first check if stack is empty and throw exception if it is empty
		if (top == -1) {
			throw new StackException("Stack is empty");
		}
		// otherwise if there's a element to return show the top ArrayStack value
		return array[top];
	}
	
	/**
	 * Constructor method isEmpty boolean to check if ArrayStack is empty or not
	 * @return true / false
	 */
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Constructor method size to show user size of arraystack used
	 * @return top+1 Return the number of elements in the stack
	 */
	public int size() {
		return top+1;
	}
	
	/**
	 * Constructor method clear to reset stack to capacity of 10, and empty it
	 * 
	 */
	public void clear () {
		// to indicate its empty top must be -1
		top = -1;
		
		// if the lenght of the array is not 10 make it 10 again by creating new array
		if (array.length > 10) {
			T[] newArray = (T[]) new Object[10];
			//make array the new array with 10 spaces only
			array = newArray;
			
		} else {
			// otherwise empty the values of the array using a loop to remove each value
			for (int i = 0; i < array.length; i++) {
				array[i] = null;
			}
		}
	}
	
	/**
	 * Constructor method getCapacity Return the length (capacity) of the array
	 * @return capacity of the ArrayStack
	 */
	public int getCapacity () {
		return array.length;
	}
	
	/**
	 * Constructor method getTop to show the value of the top of the array
	 * @return Return the top index
	 */
	public int getTop () {
		return top;
	}
	
	/**
	 * Constructor method toString to show h "Stack: " followed by all the
	 * items in the stack from the top (first) to the bottom (last).
	 * @return result string
	 */
	public String toString() {
		// if the stack is empty let user know its empty
		if (top == -1) {
			return "Empty stack.";
		}
		
		String result = "Stack: ";
		
		// index from top to bottom adding each value to the string starting from top
		for (int index = top; index >= 0; index--){ //array.length or top+1
			// if its the last bottom value add a period to end string with a period
			if (index == 0) {
				result = result + array[index].toString() + ".";
			} else {
				result = result + array[index].toString() + ", ";
			}
		}
		return result;
	}
	
	/**
	 * Constructor method expandCapacity expand the capacity by adding 10 additional
	 * spots in the array. only do this if the fraction of the array being used is >= 3/4ths
	 */
	private void expandCapacity () {
		//create double to see the percentage of the array being used
		double topDouble =  (double)(top+1) ;
		double CapDouble = (double)array.length;
		
		// check if it meets percentage requirment of 75% for expansion
		//if it doesn't it won't expand the capacity
		if (topDouble/CapDouble >= 0.75) {
			
			//create a new larger array with 10 extra spaces
			T[] larger = (T[])(new Object[array.length + 10]);
			
			//add existing elements into larger array one by one
			for (int index = 0; index < array.length; index++) {
				larger[index] = array[index];
			}
			//array is now equal to larger array with 10 extra spots
			array = larger;
		}
	}
	
	/**
	 * Constructor method shrinkCapacity shrink the
	 * capacity by removing 10 spots from the array
	 * only does this if percentage of array used is 
	 * less than equal to 25% and
	 * capacity is greater than or equal to 20
	 */
	private void shrinkCapacity () {
		double topDouble =  (double)(top + 1) ;
		double CapDouble = (double)array.length;
		
		// only shrinks capacity if it meets the requirment
		if (topDouble/CapDouble <= 0.25 && array.length >= 20) {
			
			T[] smaller = (T[])(new Object[array.length - 10]);
			
			for (int index = 0; index < smaller.length; index++) {
				smaller[index] = array[index];
			}
			array = smaller;		
		}
	}
		
		
}
