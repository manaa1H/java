/**
 * This class represents a polynomial, on which the user can add monomial to it, or evaluate the polynomial, or
 * find the derivative of the polynomial, or solve using Newton's method, or view the string representation
 * of the entire polynomial.
 * 
 * @author Manaal Hashmi CS1027A SECTION 001 (Student #:251314405)
 * @since 2023-10-19
 */

public class Polynomial{
	/**
	 * Ordered linked list of monomial class called myPolynomial
	 */
	private OrderedLinkedList<Monomial> myPolynomial;
	/**
	 * Constructor method that creates a polynomial out of 
	 * a ordered linked list created from the monomial class
	 */
	public Polynomial() {
		myPolynomial = new OrderedLinkedList<Monomial>();

	}
	
	public void add(int coefficient, int degree) {
		Monomial newMonomial = new Monomial(coefficient, degree);
		myPolynomial.insert(newMonomial);	
	}
	/**
	 *  Method that takes the derivative of the given
	 * polynomial 
	 * @return a new polynomial that stores derivatives when .derivatives() method is called
	 */
	public Polynomial derivative() {
		/**creates a new polynomial list that stores derivatives of the polynomials 
		 * from a pre-existing list of polynomials
		*/
		Polynomial derivativePolynomial = new Polynomial();
		// establishing the size of the polynomial to index through each monomial in it
		int size = myPolynomial.getSize();
		// indexing through each monomial and preforming the formula nx^b= n*b*x^(b-1)
		for (int i = 0; i < size; i++) {
			// finds one momomial at a time and accesses it with get(i)
			Monomial monomial = myPolynomial.get(i);
			// multiplies the coefficient by the degree
			int coefficient = monomial.getCoefficient() * monomial.getDegree();
			// subtracts one from the coefficient
			int degree = monomial.getDegree() - 1;
			// makes sure to only add to the new list if the degree is >= zero just in case
			if (degree >= 0) {
				// adds the new derivatives of each monomial into the new list
				derivativePolynomial.add(coefficient, degree);
				
			}
		}
		// returns the newly made polynomial
		return derivativePolynomial;
	}
	/**
	 *  Method that takes a double parameter z and returns the value f(z) (also a double),
	 *	 where f is the polynomial stored in the class.
	 *	
	 * @param  double z 
	 * @return the value of f(z) as a double
	 */
	public double eval(double z) {
		double result  = 0.0;
		int size = myPolynomial.getSize();
		// indexing through each monomial until it reaches the size of the polynomial
		// the size is the total number of monomial in the polynomial
		for (int i = 0; i < size; i++) {
			Monomial monomial = myPolynomial.get(i);
			// accessing each coefficient and degree of the monomial
			double coefficient = monomial.getCoefficient();
			int degree = monomial.getDegree();
			// preforms the mathematical operations on each monomial
			// adding each monomial together as it iterates through the loop 
			// storing this in the double result
			result += coefficient * Math.pow(z,  degree);
		}
		//returns the new evaluated value f(z)
		return result;
	}
	/**
	 * toString method to build a representation of the polynomial
	 * as coefficient* x to the power of a number
	 * @return a string containing what the polynomial looks like as x raised
	 * to some power, where x is one monomial
	 * @return an empty string if polynomial is empty
	 */
	public String toString() {
		StringBuilder polynomialString = new StringBuilder();
		int size = myPolynomial.getSize();
		// accessing each monomial through iterating through the size of the polynomial
		for (int i = 0; i < size; i++) {
			Monomial monomial = myPolynomial.get(i);
			// accessing a monomials coefficient and degree
			int coefficient = monomial.getCoefficient();
			int degree = monomial.getDegree();
			// if the coefficient is not zero 
			if (coefficient != 0) {
				// if the coefficient is positive
				if (coefficient > 0) {
					// if this is the first monomial in the polynomial and is positive
					// there is no need to place a + sign in front of it, so this will not
					// add a + to the first monomial if it's positive
					if (i == 0){
						polynomialString.append(coefficient+"*x^"+degree);
						// adds a plus sign to represent one monmial being added to the polynomial
					}else {
						polynomialString.append(" + ");
						polynomialString.append(coefficient+"*x^"+degree);
					}
					
					// if the coefficient is negative add a - sign
				} else if(coefficient < 0) {
					polynomialString.append(" - ");
					// multiply the coefficient by one to neutralize the existing negtive sign
					// thats already on the coefficient, so we can add a negative sign that
					//looks better in the string representation
					coefficient = -1 * coefficient; 
					polynomialString.append(coefficient+"*x^"+degree);
					
				} 
				
			
			}
	
		}
		// if the polynomial doesn't contain any monomials return an empty string
		if (polynomialString.length()== 0) {
			return "";
		}
		// return the new string formatted polynomial
		return polynomialString.toString();
	}
	/**
	 *  Method that solves the polynomial using Newton's method, raises 
	 * a SolutionNotFound exception if unable to do so.
	 *	
	 * @param  double x0 This represents the initial value for the solution search.
	 * @param  double e value representing the tolerance of the solution
	 * @param  integer T representing the maximum number of iterations that Newton’s
	 *	method runs for
	 * @return  a double, representing the root of the polynomial
	 */
	public double solve(double x0, double e, int T) throws SolutionNotFound {
		// establishing the initial value x0
		double previous = x0;
		//declaring current as a double
		double current;
		// if the derivitive is not zero, establish current as Newton's formula using x0 (previous)
		if (this.derivative().eval(previous)!=0) {
			current = previous - ((this.eval(previous))/(this.derivative().eval(previous)));
		// if its equal to zero we can't move forward, it will lead to a divide by zero error so
		// we throw a SolutionNotFound exception
		} else {
			throw new SolutionNotFound("divide by zero error");
		} 
		// now a loop will preform the process of Newton's method, until it finds a
		//number within the tolerance e within T times. 
		for (int i = 0; i < T; i++) {
			// if a number is found in the tolerance it will return the current value
			if (Math.abs(previous - current)< e) {
				return current;
			}
			// if in the loop the derivative of current ever equals zero an exception will be thrown
			if (this.derivative().eval(current) == 0) {
				throw new SolutionNotFound("divide by zero error");
			}
			// this allows the next new value to be used in the loop
			previous = current;
				
			//preforms the formula of Newton's method if there's no 0 value to divide by
			if (this.derivative().eval(previous)!= 0) {
				current = previous - ((this.eval(previous))/(this.derivative().eval(previous)));
			// if any criterea is not met, and the method can't keep going the solution is not found
			} else {
				throw new SolutionNotFound("");
			}
			
		}
		// if the loop has fully iterated in the threshold T range, we throw a max numb of iterations exceeded
		// solutions not found exception
		throw new SolutionNotFound("maximum iteration exceeded");
		
		
	}

}
