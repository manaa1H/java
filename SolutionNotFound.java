
/**
 * This class represents an exception to throw well preforming the solve method contained in the 
 * polynomial class. The class extends the exception superclass
 * 
 * @author Manaal Hashmi CS1027A SECTION 001 (Student #:251314405)
 * @since 2023-10-19
 */
/**
* Constructor method that creates an exception with a given message 
	 * @param message to show to user
	 */
public class SolutionNotFound extends Exception {

	public SolutionNotFound(String message) {
		super(message);
	}

}
