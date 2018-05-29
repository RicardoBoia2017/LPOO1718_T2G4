package mono.model.entities;

public class Station extends BuyableSquare {
	
	private int rent;

	public Station(String name, int cost, int position) {
		super(name, position, cost);
		this.rent = 25;
	}
	
	protected void payRent (Player p)
	{		
		if(p != owner) {
			int multiplier = 0;
		
			for(int i = 0; i < owner.getPropertiesOwned().size(); i++) {
				if(owner.getPropertiesOwned().get(i).getType().equals("Station")) {
					multiplier++;
				}
			}
		
			p.removeMoney(rent*multiplier, true);
			owner.addMoney(rent*multiplier);
		}
	}
	
	@Override
	public String getType() {
		return "Station";
	}

}
