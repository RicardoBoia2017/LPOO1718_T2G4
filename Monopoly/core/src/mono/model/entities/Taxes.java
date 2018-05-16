package mono.model.entities;

import mono.model.GameModel;

public class Taxes extends Square {

	int valueToPay;
	
	public Taxes(String name, int position, int valueToPay) {
		super(name, position);
		this.valueToPay = valueToPay;
	}

	@Override
	public void doAction (Player p) {
		GameModel.getInstance().addTaxMoney(valueToPay);
	}

}
