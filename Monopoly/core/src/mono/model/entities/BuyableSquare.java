package mono.model.entities;

public abstract class BuyableSquare extends Square{

	int cost;
	Player owner;
	boolean inMortgage;
	int mortgageValue;
	
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
	
	protected abstract void payRent (Player p1);
	
	public void setOwner (Player p1) {owner = p1;}
	
	public int getCost () {return cost;}
	
	public void setInMortgage (boolean value) {inMortgage = value;}
	
	public Boolean getMortgageStatus() {
		return inMortgage;
	}
}
