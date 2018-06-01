package mono.model.entities;

/**
 * Class used to store dice values. 
 * 
 * @author ricar
 *
 */
public class Pair {
	
	private int value1;
	private int value2;
	
	/**
	 * Creates pair with default values
	 */
	public Pair ()
	{
		this.value1 = 1;
		this.value2 = 1;
	}
	
	/**
	 * Creates pair with specific values
	 * 
	 * @param value1 1st value
	 * @param value2 2nd value
	 */
	public Pair (int value1, int value2){
		this.value1 = value1;
		this.value2 = value2;;
	}
	
	/**
	 * 
	 * @return 1st value
	 */
	public int getValue1() {return this.value1;}
	
	/**
	 * 
	 * @return 2nd value
	 */
	public int getValue2() {return this.value2;}
	
	/**
	 * Sets 1st value
	 * 
	 * @param newValue new value
	 */
	public void setValue1 (int newValue) {this.value1 = newValue;}
	
	/**
	 * Sets 2nd value
	 * 
	 * @param newValue new value
	 */
	public void setValue2 (int newValue) {this.value2 = newValue;}

	/**
	 * 
	 * @return true if values are equals, otherwise return false
	 */
	public boolean sameValue () {return value1 == value2;}

}
