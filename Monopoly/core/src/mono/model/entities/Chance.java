package mono.model.entities;

import mono.model.Game;
import mono.model.entities.Pair;

public class Chance extends Square {

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
				p.sendToJail();
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

	private void interactionsPlayerToPlayer(Player p, int value)
	{
		for (Player elem: Game.getInstance().getPlayers())
		{
			elem.removeMoney(value, true);
			p.addMoney(value);
		}
		
	}
	
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
