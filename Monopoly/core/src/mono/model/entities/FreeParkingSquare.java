package mono.model.entities;

import mono.model.GameModel;

public class FreeParkingSquare extends Square {

	public FreeParkingSquare() {
		super("Parking", 20);
	}

	@Override
	public void doAction(Player p) {

		int currentTaxMoney = GameModel.getInstance().getTaxMoney();
		
		p.addMoney(currentTaxMoney);

		GameModel.getInstance().setTaxMoney(0);
		
	}
	
}
