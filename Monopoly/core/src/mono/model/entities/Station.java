package mono.model.entities;

public class Station extends BuyableSquare {
	
	private int rent;
	private double mortgageValue;

	public Station(String name, int cost, int position) {
		super(name, position, cost);
		this.rent = 25;
	}
	
	protected void payRent (Player p)
	{		
		p.removeMoney(rent);
		owner.addMoney(rent);
	}
	
	public void buyProperty(Player p)
	{
		this.owner = p;
	}

	public void setOwner (Player buyer)
	{
		owner = buyer;
	}

	
	@Override
	public String getType() {
		return "Station";
	}

}
