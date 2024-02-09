/**
 * This class represents a given sudoku grid, with a given size, the user can get the grid,
 * size, a specific digit at a given row and column, and check the validity
 * of the solutions to the game. The validity of a given row,  column, box if (if 9x9 size), 
 * or entire solution can be checked.
 * 
 * @author Manaal Hashmi CS1027A SECTION 001 (Student #:251314405)
 * @since 2023-10-3
 */
public class Sudoku {
	/**
	 * Size of the sudoku grid
	 */
	private int size;
	/**
	 * Array that builds the grid the game is made out of
	 */
	private int[][] grid;
	/**
	 * Constructor establishes the numbers of the grid, and
	 * size
	 * @param numbers of the grid
	 */
	public Sudoku (int[][]numbers) {
		this.size = numbers.length;
		this.grid = numbers;
		
	}
	/**
	 * Accessor method to get the size of the sudoku
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Accessor method to get the grid of the game
	 * @return the grid
	 */
	public int[][] getGrid(){
		return grid;
	}
	/**
	 * Accessor method to get a digit at a given row and column
	 * @return a specific digit from the grid
	 */
	public int getDigitAt(int row, int col) {
		if ((row < 0) || (row >= size)|| (col< 0) || (col >= size)) {
			return -1; // checks if the row and columns given by the user are in range
		}
		return grid[row][col];
	}
	/**
	 * Boolean method that determines if the solutions in a given row are valid.
	 * Checks if the size is valid, and if the numbers in the row are in range.
	 * Then checks to see if there are any repeated values that would make the solution wrong
	 * @return true or false depending on validity of the row
	 */
	public boolean isValidRow(int row) {
		//checking if the row is in range of the size
	    if (row < 0 || row >= size) { 
	    	// returns false if out of range
	        return false; 
	    }

	    // keep track of seen numbers being read to make sure there are no repeats
	    boolean[] seenNumbersRow = new boolean[size + 1];
        // for loop to go through each number in the row
	    for (int i = 0; i < size; i++) {
	    	//  loop will go through each number in the row and read it
	        int currentNumberReadRow = grid[row][i];
	        // checking if the currently read number is in range of the size 
	        if (currentNumberReadRow < 1 || currentNumberReadRow > size) {
	        	//if out of range will return false
	            return false;
	        }
	        // checking if any values are repeated within the row
	        if (seenNumbersRow[currentNumberReadRow]) {
	        	//returns false if there are repeats of the currently read number
	            return false;
	        }
	        // establishing row as true if no repeats
	        seenNumbersRow[currentNumberReadRow] = true;
	    }
	    // returns true if all loops are passed through without fail
	    return true;
	}
	/**
	 * Boolean method that determines if the solutions in a given column are valid.
	 * Checks if the size is valid, and if the numbers in the column are in range.
	 * Then checks to see if there are any repeated values that would make the solution wrong
	 * @return true or false depending on validity of the column
	 */
	public boolean isValidCol(int col) {
		//checking if the column is in range of the size
		if (col<0 || col >= size) {
			// returns false if out of range
			return false;
		}
		// keep track of seen numbers being read to make sure there are no repeats
		boolean[] seenColumnNumbers = new boolean[size+1];
		
		// for loop to go through each number in the column
		for (int i = 0; i < size; i++) {
		//  loop will go through each number in the column and read it
			int CurrentNumbersReadCol = grid[i][col];
			 // checking if the currently read number is in range of the size 
			if(CurrentNumbersReadCol < 1 || CurrentNumbersReadCol > size) {
				//if out of range will return false
				return false;
			}
			 // checking if any values are repeated within the column
			if(seenColumnNumbers[CurrentNumbersReadCol]) {
				//returns false if there are repeats of the currently read number
				return false;
			}
			 // establishing column as true if no repeats
			seenColumnNumbers[CurrentNumbersReadCol] = true;
		}
		 // returns true if all loops are passed through without fail
		return true;
	}
	/**
	 * Boolean method that determines if the solution of a 3x3 box in a 9x9 sized grid are valid
	 * Checks if the size is valid, and if the numbers in the box are in range.
	 * Then checks to see if there are any repeated values that would make the solution wrong
	 * @return true or false depending on validity of the box
	 */
	public boolean isValidBox(int row, int col) {
		//checking if the rows and columns are in range of the size
		if (col<0 || col >= size||row < 0 || row >= size) {
			// returns false if out of range
			return false;
		}
		
		// keep track of seen numbers being read to make sure there are no repeats
		boolean[] seenColumAndRowNumbers = new boolean[size+1];
		// for loop to go through each number in the specified 3x3 box, starts
		// at a row and goes through each column within the row, then goes to next row and repeats
		//process until its went through 3 column values 3 times for each given row.
		for (int j = row; j<row+3; j++) {
			for(int i = col; i < col+3; i++) {
			//  loop will go through each specified grid number and read it
				int CurrentNumberRead = grid[i][j];
				 // checking if the currently read number is in range of the size 
				if(CurrentNumberRead < 1 || CurrentNumberRead > size) {
					//if out of range will return false
					return false;
				}
				// checking if any values are repeated within the 3x3 box
				if(seenColumAndRowNumbers[CurrentNumberRead]) {
					//returns false if there are repeats of the currently read number
					return false;
				}
				// establishing box as true if no repeats
				seenColumAndRowNumbers[CurrentNumberRead] = true;
			}
			
		}
		 // returns true if all loops are passed through without fail
		return true;
	}
	/**
	 * Boolean method that determines if the solutions of the entire sudoku are true
	 * Checks if the size is valid, and if the numbers in the rows and columns are in range.
	 * Then checks to see if there are any repeated values that would make the solution wrong
	 * @return true or false depending on validity of the solution
	 */
	public boolean isValidSolution() {
		// loop to go through every number in each row and check if it's valid
		for (int row = 0; row <size; row++) {
			//calling boolean method .isValidRow on each row being read
			if (!isValidRow(row)) {
				// returns false if the .isValidRow method is false for at least 1 row
				return false;
			}
		}
		// loop to go through every number in each column and check if it's valid
		for (int col = 0; col< size; col++) {
			//calling boolean method .isValidCol on each column being read
			if (!isValidCol(col)) {
				// returns false if the .isValidCol method is false for at least 1 column
				return false;
			}
		}
		/*
		 *  if the row and column for loops are both true, and the size of the sudoku is 9x9
		 *  checks if the 3x3 portions of the grid are also valid with no repeats
		 *  and if all numbers in a portion are in range
		 */
		if (size == 9) {
			/*
			 *  goes through each 3x3 box of a grid and calls .isValidBox method to
			 *  check validity
			 */
			for (int row = 0; row < size; row+=3) {
				for (int col = 0; col < size; col+=3) {
					if (!isValidBox(row,col)) {
						/*
						 * if at least one box currently being read by the loop returns
						 * a false solution, entire sudoku solution will be deemed false 
						 */
						return false;
					}
				}
			}
		}
		// if all of the above conditions are passed will return true if Sudoku solution correct
		return true;
	}
	/**
	 * Boolean equals method that determines if two compared given sudoku 
	 * contain the exact same values in the same places, and are of the same size.
	 * @return true or false depending on if the sudokus are the same in values.
	 */
	public boolean equals (Sudoku other) {
		// checks if the sizes are the same for each grid
		if (size != other.size) {
			// if the sized are diff it will return false because it means they are diff
			return false;
		}
		// loops through each number one by one in the span of each grid
		for (int i = 0; i < size; i++) {
			for (int j = 0; j< size; j++) {
				// checks if the value stored in the exact same position on each grid matches
				if (grid[i][j] != other.grid[i][j]) {
					// if a single number doesn't match returns false bec they are not the same grid
					return false;
				}
			}
		}
		// if all conditions above are passed the sudokus are the same in values so returns true
		return true;
	}
	/**
	 * toString method to build a representation of the sudoku grid as a string.
	 * @return what the sudoku grid looks like
	 */
	public String toString() {
		// creating a string to store grid in
		StringBuilder gridMaker = new StringBuilder();
		// for loop goes through every value in the sudoku and appends it to the string
		// i represents row being read
		for (int i = 0; i < size; i++) {
			//j represents column location read
			for (int j = 0; j< size; j++) {
				//appending value one by one into string
				gridMaker.append(grid[i][j]).append(" ");
				// of the column reaches the size range of the grid it will create a new line
				if (j == size - 1) {
					// the new line allows string to be in grid formation and not on a single line
					gridMaker.append("\n");
				}
			}
			
			
		}
		//returns the created string
		return gridMaker.toString();
	}
    
}

