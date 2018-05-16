package mono.model.entities;

import mono.model.GameModel;

public class FreeParkingSquare extends Square {

	public FreeParkingSquare() {
		super("Parking", 20);
	}

	@Override
	public void doAction(Player p) {
		GameModel.getInstance().giveTaxMoney();
	}
	
}
