
/**
 * This class stores a monomial.
 * 
 * @author Manaal Hashmi CS1027A SECTION 001 (Student #:251314405)
 * @since 2023-10-19
 */

// defining the monomial class as having a compareTo method
public class Monomial implements Comparable<Monomial> {
	// instance variables of the coefficient and degree of the monomial
	private int coefficient;
	private int degree;
	
	/**
	 * Constructor method  that accepts both coefficient and degree instance variables
	 * @param coefficient of the monomial
	 * @param degree of the monomial
	 * @return the 
	 */
	public Monomial(int coefficient, int degree) {
		this.coefficient = coefficient;
		this.degree = degree;
	}
	/**
	 * Accessor method to get the coefficient of the monomial
	 * @return coefficient
	 */
	public int getCoefficient() {
		return coefficient;
	}
	/**
	 * Accessor method to get the degree of the monomial
	 * @return degree
	 */
	public int getDegree() {
		return degree;
	}
	/**
	 * compareTo method accepts a monomial as a parameter and returns an integer. It
	 * should determine which monomial has the higher degree.
	 * @return integer 
	 */
	public int compareTo(Monomial m) {
		return this.degree - m.degree;
	}

}
