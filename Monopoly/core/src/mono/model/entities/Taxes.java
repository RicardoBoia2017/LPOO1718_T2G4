package mono.model.entities;

import mono.model.Game;

/**
 * Creates taxes square and removes valueToPay from players and adds it to tax money. Subclass of Square.
 * @author ricar
 *
 */
public class Taxes extends Square {

	private int valueToPay;
	
	/**
	 * Creates tax square
	 * 
	 * @param name tax's name
	 * @param position tax's position 
	 * @param valueToPay tax value
	 */
	public Taxes(String name, int position, int valueToPay) {
		super(name, position);
		this.valueToPay = valueToPay;
	}

	@Override
	public void doAction (Player p) {
		
		int currentTaxMoney = Game.getInstance().getTaxMoney();
		
		Game.getInstance().setTaxMoney(currentTaxMoney + valueToPay);
		
		p.removeMoney(valueToPay, true);
	} 

	@Override
	public String getType() {
		return "Taxes";
	}

}
