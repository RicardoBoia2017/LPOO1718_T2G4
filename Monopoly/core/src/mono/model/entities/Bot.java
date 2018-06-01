package mono.model.entities;

import java.util.ArrayList;

import mono.model.Game;

/**
 * Sub class of Player. Contain methods that control bot's behaviour according to certain conditions.
 * 
 * @author ricar
 *
 */
public class Bot extends Player {

	/**
	 * Bot constructor
	 * 
	 * @param gameId id in the game (1-4)
	 * @param name Bot1, Bot2 or Bot3
	 * @param pieceType bot's board piece
	 */
	public Bot(int gameId, String name, String pieceType) {
		super(gameId, name, pieceType, true);
		
	}
	
	/**
	 * Function that call methods that control if he buys properties, builds houses and hotels and if he is in jail or not
	 */
	public void botTurn()
	{	
		if (Game.getInstance().checkPropertyAvailibility() == 0)
			botBuySquare();
		
		if (inJail)
			botManageJail();
			
		botBuyBuildings();
		
	}
 
	/**
	 * Manages if bot buys square
	 */
	private void botBuySquare()
	{
		BuyableSquare bs1 = (BuyableSquare) Game.getInstance().getCurrentSquare();;
		
		int moneyAfterBuy = money - bs1.getCost();
		
		if (bs1.getType() == "Property")
			botTurnProperty(bs1, moneyAfterBuy);
		
		else if (bs1.getType() == "Station")
			botTurnStation(bs1, moneyAfterBuy);

		else 
			botTurnCompany (bs1, moneyAfterBuy);
	}
	
	/**
	 * Manages if bot buys property
	 * 
	 * @param s property 
	 * @param moneyAfterBuy player money if the square is bought
	 */
	private void botTurnProperty (BuyableSquare s, int moneyAfterBuy) {
		 
		Property property = (Property) s;
		int sameColorCounter = Game.getInstance().countPropertiesOfAColor(property.getColor());
		
		ArrayList <String> twoPropertiesColor = new ArrayList <String> ();
		twoPropertiesColor.add("BROWN");
		twoPropertiesColor.add("DARK_BLUE");
		
		if ( (sameColorCounter == 0) ||
			 (sameColorCounter == 1 && !twoPropertiesColor.contains(property.getColor()) ) )
		{
			if (moneyAfterBuy >= 500)
			Game.getInstance().buyProperty();
		}
		
		else if ( (sameColorCounter == 1 && twoPropertiesColor.contains(property.getColor())) ||
				  (sameColorCounter == 2 && !twoPropertiesColor.contains(property.getColor()) ))
		{
			if (moneyAfterBuy >= 200)
				Game.getInstance().buyProperty ();
		}
		
	}
	
	/**
	 * Manages if bot buys station
	 * 
	 * @param s station
	 * @param moneyAfterBuy player money if the square is bought
	 */
	private void botTurnStation (BuyableSquare s, int moneyAfterBuy) {
		
		int stationsCounter = 0;

		for (BuyableSquare bs: propertiesOwned)
		{
			if (bs.getType() == "Station")
				stationsCounter++;
			
		}
				
		if (stationsCounter == 0 && moneyAfterBuy >= 500)
			Game.getInstance().buyProperty();		
		
		else if ((stationsCounter == 1 || stationsCounter == 2) && moneyAfterBuy >= 350)
			Game.getInstance().buyProperty();
		
		else if (stationsCounter == 3 && moneyAfterBuy >= 200)
			Game.getInstance().buyProperty();

	}

	/**
	 * Manages if bot buys company
	 * 
	 * @param s company
	 * @param moneyAfterBuy player money if the square is bought
	 */
	private void botTurnCompany (BuyableSquare s, int moneyAfterBuy) {
		
		int companiesCounter = 0;
		
		for (BuyableSquare bs: propertiesOwned)
		{
			if (bs.getType() == "Company")
				companiesCounter++;
		}
		
		if (companiesCounter == 0 && moneyAfterBuy >= 500)
			Game.getInstance().buyProperty();
		
		else if (companiesCounter == 1 && moneyAfterBuy >= 200)
			Game.getInstance().buyProperty();
	}
	
	/**
	 * Manages if bot pays fine to get out of jail or waits
	 */
	private void botManageJail ()
	{
		if (money - 50 >= 500)
		{
			inJail = false;
			turnsWithoutMoving = 0;
			removeMoney (50, false);
		}
	}
	
	/**
	 * Manages if bot buys buildings
	 */
	private void botBuyBuildings() {
		
		for (BuyableSquare elem: propertiesOwned)
		{
			int res = Game.getInstance().checkHouseAvailability(elem);
			
			if (res == 0)
			{
				if (botBuyHouse( (Property) elem) == 0);
					break;
			}
			
			else if (res == -5)
			{
				if (botBuyHotel( (Property) elem) == 0);
					break;
			}
				
			else
				continue;
			
		}
	}

	/**
	 * Manages if bot buys houses
	 * 
	 * @param p property
	 * @return 0 if bot buys houses
	 */
	private int botBuyHouse (Property p) {

		int nHouses = p.getHouses();
		int valueAfterPurchase = money - p.getBuildingCost();
		int res = 0;
		
		if (nHouses <= 1 && valueAfterPurchase >= 300) 
			res = Game.getInstance().buyHouse(p);
		
		else if (nHouses <= 3 && valueAfterPurchase >= 200)
			res = Game.getInstance().buyHouse(p);
		
		return res;
		
	}
	
	/**
	 * Manages if bot buys houses
	 * 
	 * @param p property
	 * @return 0 if bot buys hotel
	 */
	private int botBuyHotel (Property p) {
		
		int valueAfterPurchase = money - p.getBuildingCost();
		int res = 0;
		
		if (valueAfterPurchase >= 200)
			res = Game.getInstance().buyHotel(p);
		
		return res;
	}
}
