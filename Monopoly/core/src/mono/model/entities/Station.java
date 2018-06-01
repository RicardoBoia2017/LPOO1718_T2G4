package mono.model.entities;

/**
 * Creates station square and calculates rent according to station owned. Subclass of buyable square.
 * @author ricar
 *
 */
public class Station extends BuyableSquare {
	
	private int rent;

	/**
	 * Creates station square
	 * 
	 * @param name station's name
	 * @param cost station's cost
	 * @param position station's position
	 */
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
