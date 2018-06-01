package mono.model.entities;

/**
 * Creates a company square (Eletricity and Water) and calculates rent according to the number of companies owned and dice values. Subclass of BuyableSquare. 
 * 
 * @author ricar
 *
 */
public class Companies extends BuyableSquare {
	    
	/**
	 * Creates a company square
	 * 
	 * @param name square's name
	 * @param cost square's cost
	 * @param position square's position
	 */
	public Companies(String name, int cost, int position){
		super(name, position, cost);
	}

	@Override
	protected void payRent(Player p1) 
	{
		if(p1 != owner) {
			int counter = 0;
			
			for(int i = 0; i < owner.getPropertiesOwned().size(); i++) {
				if(owner.getPropertiesOwned().get(i).getType().equals("Company")) {
					counter++;
				}
			}
			
			if(counter == 1) {
				p1.removeMoney(4*(p1.getAdditiveDiceRoll()), true);
				owner.addMoney(4*(p1.getAdditiveDiceRoll()));
			}
			
			if(counter == 2) { 
				p1.removeMoney(10*(p1.getAdditiveDiceRoll()), true);
				owner.addMoney(10*(p1.getAdditiveDiceRoll()));
			}
		}
	}
		
	@Override
	public String getType() {
		return "Company";
	}

}
