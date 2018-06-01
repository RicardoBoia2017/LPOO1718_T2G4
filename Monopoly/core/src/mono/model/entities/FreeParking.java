package mono.model.entities;

import mono.model.Game;

/**
 * Creates a Free Parking square and gives tax money if a player ends up in it. Subclass of Square
 * 
 * @author ricar
 *
 */
public class FreeParking extends Square {

	/**
	 * Creates Free Parking square
	 */
	public FreeParking() {
		super("Free Parking", 20);
	}

	@Override
	public void doAction(Player p) {

		int currentTaxMoney = Game.getInstance().getTaxMoney();
		
		p.addMoney(currentTaxMoney);

		Game.getInstance().setTaxMoney(0);
		
	}

	@Override
	public String getType() {
		return "Free Parking";
	}
	
}
