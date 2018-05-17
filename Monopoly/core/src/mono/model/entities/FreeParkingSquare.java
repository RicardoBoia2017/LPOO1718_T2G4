package mono.model.entities;

import mono.model.Game;

public class FreeParkingSquare extends Square {

	public FreeParkingSquare() {
		super("Parking", 20);
	}

	@Override
	public void doAction(Player p) {

		int currentTaxMoney = Game.getInstance().getTaxMoney();
		
		p.addMoney(currentTaxMoney);

		Game.getInstance().setTaxMoney(0);
		
	}
	
}
