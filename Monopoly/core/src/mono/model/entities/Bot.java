package mono.model.entities;

import java.util.ArrayList;

import mono.model.Game;

public class Bot extends Player {

	public Bot(int gameId, String name, String pieceType, boolean bot) {
		super(gameId, name, pieceType, bot);
		
	}
	
	public void botTurn()
	{	
		if (Game.getInstance().checkPropertyAvailibility() == 0)
			botBuyProperty();
		
		botBuyBuildings();
		
	}
 

	private void botBuyProperty()
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
	
	private void botTurnStation (BuyableSquare s, int moneyAfterBuy) {
		
		int stationsCounter = 0;

		for (BuyableSquare bs: propertiesOwned)
		{
			if (bs.getType() == "Station")
				stationsCounter++;
			
		}
		
		System.out.println("Stations counter: " + stationsCounter);
		
		if (stationsCounter == 0 && moneyAfterBuy >= 500)
			Game.getInstance().buyProperty();		
		
		else if ((stationsCounter == 1 || stationsCounter == 2) && moneyAfterBuy >= 350)
			Game.getInstance().buyProperty();
		
		else if (stationsCounter == 3 && moneyAfterBuy >= 200)
			Game.getInstance().buyProperty();

	}

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
	
	private void botBuyBuildings() {
		
		for (BuyableSquare elem: propertiesOwned)
		{
			int res = Game.getInstance().checkHouseAvailability(elem);
			
			if (res == 0)
			{
				botBuyHouse( (Property) elem);
				break;
			}
			
			else if (res == -5)
			{
				botBuyHotel( (Property) elem);
				break;
			}
				
			else
				continue;
			
		}
	}

	private void botBuyHouse (Property p) {

		int nHouses = p.getHouses();
		int valueAfterPurchase = money - p.getBuildingCost();
		
		if (nHouses <= 1 && valueAfterPurchase >= 300)
			Game.getInstance().buyHouse(p);
		
		else if (nHouses <= 3 && valueAfterPurchase >= 200)
			Game.getInstance().buyHouse(p);
		
	}

	private void botBuyHotel (Property p) {
		
		int valueAfterPurchase = money - p.getBuildingCost();
		
		if (valueAfterPurchase >= 200)
			Game.getInstance().buyHotel(p);
	}
}
