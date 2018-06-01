package mono.model.entities;

import mono.model.Game;

/**
 * Creates a chance square and select the action according to the card id. Subclass of Square.
 * 
 * @author ricar
 *
 */
public class Chance extends Square {

	/**
	 * Creates a chance square
	 * @param position position in the board
	 */
	public Chance(int position) { 
		super("Chance", position);
	}

	@Override
	public void doAction(Player p) {
		int firstCardId = Game.getInstance().getFirstChanceCardId();

		switch (firstCardId)
		{
			case 1:
				p.addMoney(100);
				break;
				
			case 2:
				interactionsPlayerToPlayer(p, 30);
				break;
			
			case 3:
				movePlayer (p,10);
				p.setInJail(true);
				p.setCurrentDiceroll(0);
				break;
			
			case 4:
				p.addMoney(150);
				break;
				
			case 5:
				movePlayer (p,13);
				break;
			
			case 6:
				interactionsPlayerToPlayer(p, -20);
				break;
			
			case 7:
				payBuildingTaxes(p);
				break;
			
			case 8:
				movePlayer (p,32);
				break;
			
			case 9:
				Game.getInstance().movePlayer(-3, false); 
				Game.getInstance().squareAction();
				break;
				
			case 10:
				p.removeMoney (75, true);
				Game.getInstance().setTaxMoney(Game.getInstance().getTaxMoney() + 75);
				break;
		}
		
	}

	/**
	 * Calculates the value the player has to pay, removes it from them and adds is to tax money
	 * 
	 * @param p current player
	 */
	private void payBuildingTaxes(Player p) {
		
		int value = 0;
		
		for (BuyableSquare elem: p.getPropertiesOwned())
		{
			if (elem.getType() == "Property")
			{
				Property elemProperty = (Property) elem;
				value += elemProperty.getHouses() * 30;
				value += elemProperty.getHotels() * 100;
			}
		}
		
		Game.getInstance().setTaxMoney(Game.getInstance().getTaxMoney() + value);
		p.removeMoney(value, true);
	}

	/**
	 * Called when the player has to pay to the others instead of paying to the bank
	 * 
	 * @param p current player
	 * @param value value to be payed
	 */
	private void interactionsPlayerToPlayer(Player p, int value)
	{
		for (Player elem: Game.getInstance().getPlayers())
		{
			elem.removeMoney(value, true);
			p.addMoney(value);
		}
		
	}
	
	/**
	 * Moves player to the specified square
	 * 
	 * @param p current player
	 * @param destPosition destination of the player
	 */
	private void movePlayer (Player p, int destPosition)
	{
		int squaresToMove = destPosition - p.getPosition();
		Game.getInstance().movePlayer(squaresToMove, false);
		Game.getInstance().squareAction();
	}
	
	@Override
	public String getType() {

		return "Chance";
	}

}
 