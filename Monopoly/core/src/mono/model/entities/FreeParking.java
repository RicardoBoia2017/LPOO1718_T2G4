package mono.model.entities;

import mono.model.Game;

public class FreeParking extends Square {

	public FreeParking() {
		super("Parking", 20);
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
