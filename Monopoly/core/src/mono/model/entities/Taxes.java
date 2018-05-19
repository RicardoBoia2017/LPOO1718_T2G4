package mono.model.entities;

import mono.model.Game;

public class Taxes extends Square {

	int valueToPay;
	
	public Taxes(String name, int position, int valueToPay) {
		super(name, position);
		this.valueToPay = valueToPay;
	}

	@Override
	public void doAction (Player p) {
		
		int currentTaxMoney = Game.getInstance().getTaxMoney();
		
		Game.getInstance().setTaxMoney(currentTaxMoney + valueToPay);
		
		p.removeMoney(valueToPay);
	}

	@Override
	public String getType() {
		return "Taxes";
	}

}
