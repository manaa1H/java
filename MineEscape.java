/**
 * MineEscape class allows user who is trapped in the mines to find an escape route.
 * 
 * @author Manaal Hashmi CS1027A SECTION 001 (Student #:251314405)
 * @since 2023-11-11
 */


//instance variables map to create a map object from map class
// instance variables numGold (int) and numKeys (int array) to initialize for later use to keep count of each
public class MineEscape {
	private Map map;
	private int numGold;
	private int[] numKeys;

	/**
	 * Constructor method MineEscape that initializes map variable from a file name and numGold/ numkeys
	 * @param filename 
	 * @exception if exception occurs while accessing filename
	 */
	public MineEscape(String filename) {
		// tries to create map object with file
		try {
			map = new Map(filename);
			//initializing numGold and numKeys
			this.numGold = 0;
			this.numKeys = new int[3];
			//making numKeys have capacity of 3, each index holding 0
			for(int i =0; i < numKeys.length; i++) {
				numKeys[i] = 0;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Constructor method MapCell that determines the next cell to walk onto from the current cell
	 * following a set of rules
	 * @param cell (the current cell) 
	 * @return neighbor cell to curr cell that best fits selection hierarchy 
	 */
	private MapCell findNextCell(MapCell cell) {
		//first check all indexes of neighboring cells starting from the lowest number to search for exit cell
		// if any cells are marked, null, lava, or a wall they should not be the next cell to walk on
		// its nessessary to use individual for loops for each priority other wise the hierarchy won't be followed
		// if only a single for loop was used for checking exits/floor cells/keys
		//Finding exit cell is FIRST PRIORITY
	    for (int i = 0; i < 4; i++) {
	        MapCell neighbor = cell.getNeighbour(i);
	        if (neighbor != null && !neighbor.isWall() && !neighbor.isLava() && !neighbor.isMarked()) {
	            if (neighbor.isExit()) {
	                return neighbor;
	            }
	        }
	    }
	   //first check all indexes of neighboring cells starting from the lowest number to search for cell with item
	   // if any cells are marked, null, lava, or a wall they should not be the next cell to walk on
	   //Finding item holding cell is SECOND PRIORITY
	    for (int i = 0; i < 4; i++) {
	        MapCell neighbor = cell.getNeighbour(i);
	        if (neighbor != null && !neighbor.isWall() && !neighbor.isLava() && !neighbor.isMarked()) {
	            if (neighbor.isGoldCell() || neighbor.isKeyCell()) {
	                return neighbor;
	            }
	        }
	    }
	   //first check all indexes of neighboring cells starting from the lowest number to search for floor cell
	   // if any cells are marked, null, lava, or a wall they should not be the next cell to walk on
	   //Finding floor cell is THIRD PRIORITY
	    for (int i = 0; i < 4; i++) {
	        MapCell neighbor = cell.getNeighbour(i);
	        if (neighbor != null && !neighbor.isWall() && !neighbor.isLava() && !neighbor.isMarked()) {
	            if (neighbor.isFloor()) {
	                return neighbor;
	            }
	        }
	    }
	   //first check all indexes of neighboring cells starting from the lowest number to search for lock cell
	   //also checking if we have the corresponding key color to match any found lock cells, so we
	    //only return the neighbor if we are able to unlock that cell
	   // if any cells are marked, null, lava, or a wall they should not be the next cell to walk on
	   //Finding lock cell is FOURTH PRIORITY
	    for (int i = 0; i < 4; i++) {
	        MapCell neighbor = cell.getNeighbour(i);
	        if (neighbor != null && !neighbor.isWall() && !neighbor.isLava() && !neighbor.isMarked()) {
	            if (neighbor.isLockCell()) {
	            	// if numKeys[index] is greater than 0 we have a key of that colour
	            	// index 0 represents having a red key, 1 represents green key, and 2 represents blue key
	                if (neighbor.isRed() && numKeys[0] > 0) {
	                    return neighbor;
	                } else if (neighbor.isGreen() && numKeys[1] > 0) {
	                    return neighbor;
	                } else if (neighbor.isBlue() && numKeys[2] > 0) {
	                    return neighbor;
	                }
	            }
	        }
	    }
	    // after completing all if loop priorities if there's absolutely no option return null, this
	    //indicates we need to backtrack
	    return null;
	}

	/**
	 * Constructor method findEscapePath determines the path from the starting point to the exit cell,
	 *  if one exists, using the findNextCell() helper method.
	 * @return path
	 * @return no solution found
	 */
	public String findEscapePath () {
		//initialize the ArrayStack S to store MapCell objects
		ArrayStack<MapCell> S = new ArrayStack<>();
		// boolean that will break the while loop if false, initialized to true
		boolean running = true;
		//initialize start cell
		MapCell startCell = map.getStart();
		//mark the starting cell as in-stack and add to return string
		S.push(startCell);
		startCell.markInStack();
		//initialize the return string
		String path = "Path: ";
		path = path + startCell.getID() + " ";
		
		
		//while S is not empty and running is true
		while (!S.isEmpty() && running) {
			// peek at the top value of the stack, it will start with the startcell as curr
			MapCell curr = S.peek();
			
			// if the curr cell is the exit end the loop this is the first thing to check
			if (curr.isExit()) {
				running = false;
				break;
			}
			
			// if the cell contains a key pick it up and change the cell to a floor cell type
			// check if null to prevent errors
			if (curr.isKeyCell() && curr != null) {
				//checking the color of the key and updating the key count to corresponding color
				if (curr.isRed()) {
					numKeys[0]+=1;
					curr.changeToFloor();
				}
				
				if (curr.isGreen()) {
					numKeys[1]+=1;
					curr.changeToFloor();
				}
				
				if (curr.isBlue()) {
					numKeys[2]+=1;
					curr.changeToFloor();
				}
			}
			
			// pick up gold and add to gold count, then set cell type to floor type to indicate
			//it has been picked up
			if (curr.isGoldCell() && curr != null) {
				numGold+=1;
				curr.changeToFloor();
			}
			
			//if any neighbor cells are lava your gold count is set back to 0
			// here we check all indexes of the neighbors of curr to see if any are lava
			for (int i = 0; i < 4; i++) {
				if (curr.getNeighbour(i) != null && curr.getNeighbour(i).isLava()) {
					numGold = 0;	
				}
			}
			
			// make sure curr is marked in stack to assure findNextCell works
			curr.markInStack(); 
			// find the next best cell to walk on using findNextCell
			MapCell next = findNextCell(curr);
			
			// if there is no viable next cell then back track by marking curr as outstack
			// and removing the curr value from arraystack S
			if (next == null) {
				curr = S.pop();
				curr.markOutStack();
				
			// if there is a next found we add its id to the return string and mark it in stack 
			//and put it in the array stack
			} else {
				path = path + next.getID() + " ";
				S.push(next);
				next.isMarkedInStack();
				
				// before we walk on the next cell we need to unlock it if its a locked cell
				if (next.isLockCell()) {
					// if next is a locked cell unlock it if you have the correct colored key
					 if (next.isRed() && numKeys[0] > 0) {
						 //change to floor to make it possible to walk on
				            next.changeToFloor();
				            numKeys[0]--;
				        } else if (next.isGreen() && numKeys[1] > 0) {
				           
				            next.changeToFloor();
				            numKeys[1]--;
				        } else if (next.isBlue() && numKeys[2] > 0) {
				           
				            next.changeToFloor();
				            numKeys[2]--;
				        }
				}
			}
			
		}
		//after everything above has been checked we can stop the loop if running is false
		if (running == false) {
			path = path + numGold + "G";
			// return the string with all IDS and the number of gold picked up
			return path;
		} else {
			// if even after looping through everything no exit is found return solution not found
			return "No solution found";
		
		}
	}
	
}
