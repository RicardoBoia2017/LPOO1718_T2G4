package mono.model.entities;

/**
 * Sub class of Square and super class of Property, Station and Company. Stores the square cost, its owner, its mortgage value and if he is in mortgage
 * 
 * @author ricar
 *
 */
public abstract class BuyableSquare extends Square{

	int cost;
	Player owner;
	boolean inMortgage;
	int mortgageValue;
	
	/**
	 * Creates buyable square
	 * 
	 * @param name square's name
	 * @param position square's position in the board
	 * @param cost square's cost
	 */
	public BuyableSquare (String name, int position, int cost)
	{
		super (name,position);
		this.cost = cost;
		owner = null;
		inMortgage = false;
		mortgageValue = cost/2;
	}
	
	public void doAction(Player p1)
	{
		if (owner != null && inMortgage == false)
			payRent(p1);		
	}
	
	/**
	 * Calculates the rent p1 has to pay to the owner
	 * 
	 * @param p1 current player
	 */
	protected abstract void payRent (Player p1);
	
	/**
	 * Sets the owner of buyableSquare
	 * @param p1 new owner of buyable square
	 */
	public void setOwner (Player p1) {owner = p1;}
	
	/**
	 * 
	 * @return owner
	 */
	public Player getOwner() {return owner;}
	
	/**
	 * 
	 * @return square's cost
	 */
	public int getCost () {return cost;}
	
	/**
	 * Add or removes mortgage from squares, according to value
	 * @param value new mortgage value
	 */
	public void setInMortgage (boolean value) {inMortgage = value;}
	
	/**
	 * 
	 * @return if square's mortgage value
	 */
	public int getMortgateValue(){return mortgageValue;}
	
	/**
	 * 
	 * @return whether a square is mortgaged or not
	 */
	public Boolean getMortgageStatus() {
		return inMortgage;
	}
}
