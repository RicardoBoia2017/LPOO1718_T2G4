package mono.model.entities;

public class Companies extends BuyableSquare {
	
	private int rent = 0;
    private double mortgageValue;
    
	public Companies(String name, int cost, int position){
		super(name, position, cost);
		this.rent = 0;
	}

	@Override
	protected void payRent(Player p1) 
	{
		//rent is four times the dice value if one utility is owned, but ten times if both are owned
		if(p1 != owner) {
			int counter = 0;
			
			for(int i = 0; i < owner.getPropertiesOwned().size(); i++) {
				if(owner.getPropertiesOwned().get(i).getType().equals("Company")) {
					counter++;
				}
			}
			
			if(counter == 1) {
				p1.removeMoney(4*(p1.getAdditiveDiceRoll()));
				owner.addMoney(4*(p1.getAdditiveDiceRoll()));
			}
			
			if(counter == 2) {
				p1.removeMoney(10*(p1.getAdditiveDiceRoll()));
				owner.addMoney(10*(p1.getAdditiveDiceRoll()));
			}
		}
	}
	
	public void buyProperty(Player p)
	{
		setOwner(p);
		owner.addProperty(this);
	}

	public void setOwner (Player buyer)
	{
		owner = buyer;
	}
	
	@Override
	public String getType() {
		return "Company";
	}

	@Override
	public void doAction (Player p) {
		if(owner != null) {
			payRent(p);
		}
	}
}
