package mono.model.entities;

public class Station extends Square {
	private int price;
	private Player owner;
	private int rent;
	private boolean mortgage;
	private double mortgageValue;

	public Station(String name, int price, int position) {
		super(name, position);
		this.price=price;
		this.owner=null;
		this.rent=25;
		this.mortgage=false;
		this.mortgageValue=price/2;
	}

	@Override
	public void doAction (Player p) {
		int value = 0;
		
		if (owner != null)
			payRent(p);

		//asks if he wants to buy TODO
		buyProperty(p);		
	}
	
	private void payRent (Player p)
	{		
		p.removeMoney(rent);
		owner.addMoney(rent);
	}
	
	private void buyProperty(Player p)
	{
		this.owner = p;
	}

	public void setOwner (Player buyer)
	{
		owner = buyer;
	}
}
