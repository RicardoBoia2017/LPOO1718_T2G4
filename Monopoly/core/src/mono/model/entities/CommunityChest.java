package mono.model.entities;

import mono.model.Game;

public class CommunityChest extends Square {

	public CommunityChest(int position) {
		super("CommunityChest", position);
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
				p.removeMoney (50);
				Game.getInstance().setTaxMoney(Game.getInstance().getTaxMoney() + 50);
				break;
			
			case 3:
				movePlayer (p,3);
				break;
			
			case 4:
				Game.getInstance().movePlayer(2);
				break;
				
			case 5:
				p.removeMoney (120);
				Game.getInstance().setTaxMoney(Game.getInstance().getTaxMoney() + 120);
				break;
			
			case 6:
				p.removeMoney (20);
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

	private void interactionsPlayerToPlayer(Player p, int value)
	{
		for (Player elem: Game.getInstance().getPlayers())
		{
			elem.removeMoney(value);
			p.addMoney(value);
		}
		
	}
	
	private void movePlayer (Player p, int destPosition)
	{
		int squaresToMove = destPosition - p.getPosition();
		Game.getInstance().setMoveFromCards(squaresToMove);
	}
	
	@Override
	public String getType() {
		return "Community Chest";
	}

}
