/**
 * This class extends the Sudoku class, and inherits methods from it.
 * Unique Diagonal Sudoku overrides the is valid solution method from sudoku
 * and adds a new condition, which is whether the numbers in at least diagonal
 * do not repeat on the grid. If the solution is confirmed valid by
 * sudoku and at least one diagonal is valid then this class will return 
 * true or false depending on the solution. 
 * @author Manaal Hashmi CS1027A SECTION 001 (Student #:251314405)
 * @since 2023-10-3
 */
public class UniqueDiagonalSudoku extends Sudoku {
	/**
	 * Constructor establishes the numbers of the grid
	 * @param numbers of the grid referenced from superclass sudoku
	 */
    public UniqueDiagonalSudoku(int[][] numbers) {
    	// calls constructor from parent class to access grid
        super(numbers);
    }
    /**
	 * public boolean isValidSolution overriden from sudoku
	 * Now checks if a diagonal from top left to bottom right (LR) is valid, or if a diagonal
	 * from top right to bottom left (RL) is valid.
	 * numbers of the grid
	 */

    public boolean isValidSolution() {
    	// initializing the status of validity for each diagonal
    	boolean LRDiagonalValidityStatus = true;
    	boolean RLDiagonalValidityStatus = true;
    	
    	// first checking if grid meets the regular rules of Sudoku
        if (!super.isValidSolution()) {
        	// if it doesn't meet the regular rules its already a false solution
            return false;
        }
        // @param getting size and grid from the superclass
        int size = getSize();
        int[][] grid = getGrid();
        // setting up arrays to keep track of numbers in each diagonal type
        boolean[] seenDiagonalNumbersLR = new boolean[size + 1];
        boolean[] seenDiagonalNumbersRL = new boolean[size + 1];
        
        // checking the left to right diagonal starting at top left of grid 
        for (int rc = 0; rc < size; rc++) {
        	// reading grid number one at a time
            int currentDiagonalNumReadLR = grid[rc][rc];
            // if the grid number is out of range of the size of the grid it will return false
            if (currentDiagonalNumReadLR < 1 || currentDiagonalNumReadLR > size) {
            	//establishing status of left right diagonal as false
            	LRDiagonalValidityStatus = false;
            	// returns false for entire sudoku because it must be in range
                return false;
            }
            // if there are any repeated values it will establish the LR diagonal as false
            if (seenDiagonalNumbersLR[currentDiagonalNumReadLR]) {
            	seenDiagonalNumbersLR[currentDiagonalNumReadLR] = false; 
            	LRDiagonalValidityStatus = false;
            	break;
            	
            }
            // if it passes all conditions it will be true
            seenDiagonalNumbersLR[currentDiagonalNumReadLR] = true;
            
            	
        }
        
        // checking the right to left diagonal starting at top right of grid 
        for (int row = 0; row < size; row++) {
        	// reads grid from top right going down a row and reading columns from right to left
            int currentDiagonalNumReadRL = grid[row][size - 1 - row];
         // if the grid number is out of range of the size of the grid it will return false
            if (currentDiagonalNumReadRL < 1 || currentDiagonalNumReadRL > size) {
            	//establishing status of right to left diagonal as false
            	RLDiagonalValidityStatus = false;
                return false;
            }
            // if there are any repeated values it will establish the RL diagonal as false
            if (seenDiagonalNumbersRL[currentDiagonalNumReadRL]) {
            	seenDiagonalNumbersRL[currentDiagonalNumReadRL] = false;
            	RLDiagonalValidityStatus = false;
            	break;
            }
         // if it passes all conditions it will be true
            seenDiagonalNumbersRL[currentDiagonalNumReadRL] = true;      
            	
        }
       // returns true of at least one diagonal solution has a true validity status
       return RLDiagonalValidityStatus || LRDiagonalValidityStatus;
       
    }
}
