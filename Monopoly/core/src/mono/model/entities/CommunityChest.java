package mono.model.entities;

import mono.model.Game;

/**
 * Creates a community chest square and select the action according to the card id. Subclass of Square.
 * 
 * @author ricar
 *
 */
public class CommunityChest extends Square {
	
	/**
	 * Creates community chest square
	 * @param position position in the board
	 */
	public CommunityChest(int position) {
		super("Community Chest", position);
	}

	@Override
	public void doAction(Player p) {
		int firstCardId = Game.getInstance().getFirstCChestCardId();

		switch (firstCardId)
		{
			case 1:
				interactionsPlayerToPlayer(p, 30);
				break;
				
			case 2:
				p.removeMoney (50, true);
				Game.getInstance().setTaxMoney(Game.getInstance().getTaxMoney() + 50);
				break;
			
			case 3:
				movePlayer (p,3);
				break;
			
			case 4:
				Game.getInstance().movePlayer(2, false);
				Game.getInstance().squareAction();
				break;
				
			case 5:
				p.removeMoney (120, true);
				Game.getInstance().setTaxMoney(Game.getInstance().getTaxMoney() + 120);
				break;
			
			case 6:
				p.removeMoney (20, true);
				Game.getInstance().setTaxMoney(Game.getInstance().getTaxMoney() + 20);
				break;				
			
			case 7:
				p.addMoney(60);
				break;				
			
			case 8:
				movePlayer (p,27);
				break;
			
			case 9:
				p.addMoney(100);
				break;	
				
			case 10:
				payAccordingToPropertiesOwned(p);
				break;
		}
				
	}

	/**
	 * Calculates the value the player has to pay (or receive) according to his properties
	 * 
	 * @param p current player
	 */
	private void payAccordingToPropertiesOwned(Player p) {
		
		int value = 0;
		
		for (BuyableSquare elem: p.getPropertiesOwned())
		{
			if (elem.getType() == "Property")
				value -= 20;
			
			else if (elem.getType() == "Company")
				value -= 35;
			
			else //Station
				value += 30;				
		}
		
		p.addMoney(value);
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
		Game.getInstance().movePlayer (squaresToMove, false);
		Game.getInstance().squareAction();
	}
	
	@Override
	public String getType() {
		return "Community Chest";
	}

}
