/**
 * BillSplitter class uses a recursive algorithm to determine a way to split the bill 
 * between you and your friend. You've agreed on an amount that you will pay 
 * (which we call the target). Your friend has agreed to pay for everything else. 
 * The class will determine the items you should pay for to meet your target requirement.
 * 
 * @author Manaal Hashmi CS1027A SECTION 001 (Student #:251314405)
 * @since 2023-12-1
 */
public class BillSplitter {
	
	/**
	 * Constructor split method will call the next recursive method by calling
	 * private method to find the split.
	 * @param UniqueOrderedList in, this is the list of items. Each item 
	 * is represented in terms of cost in the list.
	 * @param int target is the amount of money you are willing to pay
	 * @returns the items you will pay for (yourSplit(iterator, target)) or
	 * null if no solution is found.
	 */
	public static UniqueOrderedList<Integer> split(UniqueOrderedList<Integer> in, int target){
		// copy the UniqueOrderedList in into an iterator 
		CopyableIterator<Integer> iterator = in.iterator().copy();
		// use the iterator in a call to the private method your split to find solution
	    return yourSplit(iterator, target);
	}
	
	/**
	 * Constructor yourSplit uses algorithm to recursively find items you must
	 * pay for.
	 * @param accepts a copyable iterator that represents remaining items, this 
	 * comes from the UniqueOrderedList object that is a param to the public method
	 * split.
	 * @param int target is the amount of money you are willing to pay
	 * @returns the items you will pay for (yourSplit(iterator, target)) or null
	 * if no solution is found
	 */
	private static UniqueOrderedList<Integer> yourSplit(CopyableIterator<Integer> it, int target){
		//initialize soln as a UniqueOrderedList so we can use operations that work
		//on a UniqueOrderedList on soln.
		UniqueOrderedList<Integer>  soln = new UniqueOrderedList<>();
		
		// if the iterator does not have a next node it indicates
		// there are no items remaining in the list this is the BASECASE
		if (!it.hasNext()) {
			//if the target is 0, it means we payed for the items and reached
			//the target amount so we return the solution as a list of items
			// we payed for
			if (target == 0) { //BASECASE
				return soln;
			// if target is not 0, we didn't pay for anything and there's no solution
			// of items in the list that add up to reach the target cost we want to pay
			// for, so in this case return null to show there was no viable solution found
			} else {
				return null; //BASECASE
			}
		
		// if there's still elements in the iterator we can search for a solution
		} else {
			// we set curr to the first item in the remaining items
			int curr = it.next();
			// of curr is less than equal to the target cost, we can check if we
			//can add it into our solution
			if (curr <= target) {
				// initialize a new UniqueOrderedList to create a separate soln to
				// create a soln for when we payed for an item (when we pay we substract
				// the value of the item we payed for from the target
				 UniqueOrderedList<Integer> solnPlusCurr = yourSplit(it.copy(), target - curr);
				 // if this UniqueOrderedList  solnPlusCurr is not empty we can add curr to
				 // our solution list solnPlusCurr and return it as a solution until it
				 // recursively hits the base case.
				 if (solnPlusCurr != null) {
					solnPlusCurr.add(curr);
					return solnPlusCurr;
				}
			}
			// initialize a new UniqueOrderedList to create a soln with no curr
			// soln will have no curr if you assign your friend to pay for the item 
			// in this case we leave target as is because we have not payed for anything
			UniqueOrderedList<Integer> solnNoCurr = yourSplit(it, target);
			if (solnNoCurr != null) {
				// return the soln for the case our friend pays
				return solnNoCurr;
			}
			// base case, no items left in the list and no solution found
			return null;
		}
	}
}



