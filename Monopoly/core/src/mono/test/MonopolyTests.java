package mono.test;

import static org.junit.Assert.*;

import org.junit.Test;

import mono.controller.GameController;
import mono.model.Game;
import mono.model.entities.BuyableSquare;
import mono.model.entities.Pair;
import mono.model.entities.Player;
import mono.model.entities.Property;
import mono.model.entities.Square;

public class MonopolyTests {
	
	public Game createGameForTesting() {
		Game g1 = Game.getInstance();
		g1.setGameModelInstanceToNull();
		g1 = Game.getInstance();
		return g1;
	}
	
	//Create Game
	@Test
	public void testIfGameCreatesAndAddsPlayersProperly() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
		Player p1 = (g1.getPlayers()).get(0);
		Player p2 = (g1.getPlayers()).get(1);
		Player p3 = (g1.getPlayers()).get(2);
		Player p4 = (g1.getPlayers()).get(3);
		
		assertEquals(p1.getName(), "ActualPlayer");
		assertEquals(p1.getBoardPiece().getType(), "Hat");
		assertEquals(p2.getName(), "Bot1");
		assertEquals(p2.getBoardPiece().getType(), "Thimble");
		assertEquals(p3.getName(), "Bot2");
		assertEquals(p3.getBoardPiece().getType(), "Boot");
		assertEquals(p4.getName(), "Bot3");
		assertEquals(p4.getBoardPiece().getType(), "Car");
		
//		Square InitialGoSquare = g1.getBoard().getSquares().get(0);
		
		for (Player p : g1.getPlayers())
			assertEquals (0, p.getPosition());
		
//		assertEquals(InitialGoSquare.getNumPlayersOnTopOfSquare(), 4);
	}

	//Movement
	@Test
	public void testIfPlayerMoves() {
		Game g1 = createGameForTesting();
				
		g1.addPlayers("Boot");
		
		Player p1 = (g1.getPlayers()).get(0);
		
		g1.movePlayer(6, false);
		
		assertEquals(p1.getPosition(), 6);
		
		Square s1 = g1.getBoard().getSquares().get(p1.getPosition());
		
//		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
//		assertEquals(((s1.getplayersOnTopOfSquareArray()).get(0)).getName(), "ActualPlayer");
		assertEquals(s1.getName(), "Brussels");
		
		g1.movePlayer(7, false);
		
		assertEquals(p1.getPosition(), 13);
		
		Square s2 = g1.getBoard().getSquares().get(p1.getPosition());
		
//		assertEquals(s2.getNumPlayersOnTopOfSquare(), 1);
//		assertEquals(((s2.getplayersOnTopOfSquareArray()).get(0)).getName(), "ActualPlayer");
		assertEquals(s2.getName(), "Moscow");
	}
	
	@Test
	public void testIfPlayerMovesBeyond40() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Thimble");
		
		Player p1 = (g1.getPlayers()).get(0);
		
		g1.movePlayer(12, false);
		g1.movePlayer(12, false);
		g1.movePlayer(12, false);
		g1.movePlayer(12, false);
		
		assertEquals(p1.getPosition(), 8);
		Square s1 = g1.getBoard().getSquares().get(p1.getPosition());
//		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
//		assertEquals(((s1.getplayersOnTopOfSquareArray()).get(0)).getName(), "ActualPlayer");
		assertEquals(s1.getName(), "Casablanca");
	}
	
	//Buyable squares
	
	@Test
	public void testIfPlayerBuyProperty()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Car");

		Player p1 = g1.getPlayers().get(0);
		
		int p1Money = p1.getMoney();

		g1.movePlayer (1, false);
		g1.buyProperty();

		assertEquals (p1.getMoney(), p1Money - 60);
		assertEquals (p1.getPropertiesOwned().size(), 1); 
	}
	
	@Test
	public void testIfPlayerBuyStation()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");

		Player p1 = g1.getPlayers().get(0);
		
		int p1Money = p1.getMoney();

		g1.movePlayer (5, false);
		g1.buyProperty();

		assertEquals (p1.getMoney(), p1Money - 200);
		assertEquals (p1.getPropertiesOwned().size(), 1); 
	}

	@Test
	public void testIfPlayerBuyCompany()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");

		Player p1 = g1.getPlayers().get(0);
		
		int p1Money = p1.getMoney();

		g1.movePlayer (12, false);
		
		System.out.println(g1.getBoard().getSquares().get(12).getName());
		g1.buyProperty();

		assertEquals (p1.getMoney(), p1Money - 150);
		assertEquals (p1.getPropertiesOwned().size(), 1); 
	}
	
	@Test
	public void testIfPlayerCantBuyPropertyWhenOwned()
	{
		Game g1 = createGameForTesting();

		g1.addPlayers("Hat");
		Player owner = g1.getPlayers().get(0);
		Player payer = g1.getPlayers().get(1);
		
		owner.move(1, false);
		g1.buyProperty();
		
		g1.endTurn();
		
		payer.move(1, false);
		assertEquals(-2, g1.checkPropertyAvailibility());
	}
	
	@Test
	public void testIfPlayerCantBuyWhenSquareIsntBuyable()
	{
		Game g1 = createGameForTesting();

		g1.addPlayers("Hat");
		Player p1 = g1.getPlayers().get(0);
		
		g1.movePlayer(2, false);
				
		assertEquals(-1, g1.checkPropertyAvailibility());
	}

	@Test
	public void testIfPlayerCantBuyWhenHasntEnoughMoney()
	{
		Game g1 = createGameForTesting();

		g1.addPlayers("Hat");
		Player p1 = g1.getPlayers().get(0);
		
		p1.removeMoney(1490, false); //stays with 10
		
		g1.movePlayer(1, false);
		g1.buyProperty();
				
		assertEquals(-3, g1.buyProperty());
	}

	@Test
	public void testIfPlayerPaysRent()
	{
		Game g1 = createGameForTesting();

		g1.addPlayers("Hat");
		Player owner = g1.getPlayers().get(0);
		Player payer = g1.getPlayers().get(1);
		
		int payerMoney = payer.getMoney();
		int ownerMoney = owner.getMoney();
		
		owner.move(1, false);
		g1.buyProperty(); //pays the property price
		
		ownerMoney = owner.getMoney();
		
		g1.endTurn();
		
		payer.move(1, false);
		g1.squareAction();
		
		assertEquals (payer.getMoney(), payerMoney - 2);
		assertEquals (owner.getMoney(), ownerMoney + 2);
		
		payerMoney = payer.getMoney();
		ownerMoney = owner.getMoney();
	}
	
	//Mortgage
	
	@Test
	public void testIfPropertyGetsMortgaged()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");

		Player p1 = g1.getPlayers().get(0);
		
		int p1Money = p1.getMoney();

		p1.move(1, false);
		g1.buyProperty();
		
		BuyableSquare s1 = p1.getPropertiesOwned().get(0);
		
		assertEquals (false, s1.getMortgageStatus());
		
		g1.mortgageProperty(0);
		
		assertEquals (true, s1.getMortgageStatus());
		assertEquals (p1.getMoney(), p1Money - s1.getCost() + s1.getMortgateValue());
	}

	@Test
	public void testIfPlayerReBuysProperty()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");

		Player p1 = g1.getPlayers().get(0);
		
		int p1Money = p1.getMoney();

		p1.move(1, false);
		g1.buyProperty();
		
		BuyableSquare s1 = p1.getPropertiesOwned().get(0);
		
		assertEquals (false, s1.getMortgageStatus());
		
		g1.mortgageProperty(0);
		
		assertEquals (true, s1.getMortgageStatus());
		assertEquals (p1.getMoney(), p1Money - s1.getCost() + s1.getMortgateValue());
		
		p1Money = p1.getMoney();
		
		g1.reBuyProperty(0);
		
		assertEquals (false, s1.getMortgageStatus());
		assertEquals (p1.getMoney(), p1Money - (int)Math.ceil(s1.getMortgateValue() * 1.10));
	}
	
	//Jail 
	@Test
	public void testIfPlayerGetsSentToJail() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
		g1.movePlayer(30, false);
		
		Player p1 = (g1.getPlayers()).get(0);
		Square s1 = g1.getBoard().getSquares().get(p1.getPosition());
		
		assertEquals(s1.getName(), "GoToJail");
		assertEquals(p1.getPosition(), 30);
		
		g1.squareAction();
		
		assertEquals(p1.isInJail(), true);
		
		assertEquals(p1.getPosition(), 10);
//		assertEquals(s1.getplayersOnTopOfSquareArray().size(), 0);
		
		s1 = g1.getBoard().getSquares().get(p1.getPosition());
		
		assertEquals(s1.getName(), "Jail");
	}
	
	@Test
	public void testIfPlayerMovementIsBlockedInJailThreeTurns() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Car");
		
		g1.movePlayer(30, false);
		
		g1.squareAction();
		
		Player p1 = (g1.getPlayers()).get(0);
		Square s1 = g1.getBoard().getSquares().get(p1.getPosition());
		
		assertEquals(p1.getPosition(), 10);
//		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		 
		g1.movePlayer(1, false);
		g1.squareAction();
		
		s1 = g1.getBoard().getSquares().get(p1.getPosition());
		
		assertEquals(p1.getPosition(), 10);
//		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		
		g1.movePlayer(1, false);
		g1.squareAction();
		
		s1 = g1.getBoard().getSquares().get(p1.getPosition());
		assertEquals(p1.getPosition(), 10);
//		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		
		g1.movePlayer(1, false);
		g1.squareAction();
		
		s1 = g1.getBoard().getSquares().get(p1.getPosition());
		assertEquals(p1.getPosition(), 11);
		assertEquals(s1.getName(), "Cape Town");
	}
	
	@Test
	public void playerGetsOutOfJailByPaying() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Boot");
		
		g1.movePlayer(30, false);
		
		g1.squareAction();
		
		Player p1 = (g1.getPlayers()).get(0);
		Square s1 = g1.getBoard().getSquares().get(p1.getPosition());
		
		g1.movePlayer(1, false);
		g1.squareAction();
		
		s1 = g1.getBoard().getSquares().get(p1.getPosition());
		assertEquals(p1.getPosition(), 10);
//		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		
		int moneyBefore = p1.getMoney();
		
		p1.setCurrentDiceroll(4);
		
		g1.tellJailPlayerWantsToPayFine();
		s1.doAction(p1);
		
		assertEquals(p1.getMoney(), moneyBefore-50);
		
		s1 = g1.getBoard().getSquares().get(p1.getPosition());
		assertEquals(p1.getPosition(), 14);
		assertEquals(s1.getName(), "Amsterdam");
	}
	
	@Test
	public void playerGetsOutOfJailByRollingDoubles() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
//		Pair diceRollOdd = new Pair(3, 6);
//		Pair diceRollSame = new Pair(3, 3);
		
		g1.movePlayer(30, false);
		g1.squareAction();
		
		Player p1 = (g1.getPlayers()).get(0);
		Square s1 = g1.getBoard().getSquares().get(p1.getPosition());
		
		g1.movePlayer(9, false);
		g1.squareAction();
		
		assertEquals(p1.getPosition(), 10);
//		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		
		g1.movePlayer(6, true);
		g1.squareAction();
		
		assertEquals(p1.getPosition(), 16);
		s1 =  g1.getBoard().getSquares().get(p1.getPosition());
		assertEquals(s1.getName(), "Madrid");
	}

	//Chance
	
	@Test
	public void testChanceCards()
	{
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");

		Player p1 = g1.getPlayers().get(0);
		Player p2 = g1.getPlayers().get(1);
		
		int firstCardId;
		int beforeActionP1Money;
		int beforeActionP2Money;
		int beforeActionTaxMoney;

		g1.movePlayer(7, false);
		
		for (int i = 1; i <= 10; i++)
		{
			
			firstCardId = g1.getFirstChanceCardId();
			beforeActionP1Money = p1.getMoney();
			beforeActionP2Money = p2.getMoney();			
			beforeActionTaxMoney = g1.getTaxMoney();
			
			g1.squareAction();

			switch (firstCardId)
			{
				case 1:
					assertEquals (p1.getMoney(), beforeActionP1Money + 100);
					break;
					
				case 2:
					assertEquals (p1.getMoney(), beforeActionP1Money + 30 * (g1.getPlayers().size() - 1) );
					assertEquals (p2.getMoney(), beforeActionP2Money - 30);
					break;
					 
				case 3:
					assertEquals (10, p1.getPosition());
					assertEquals (true, p1.isInJail());
					p1.freeFromJail();
					g1.movePlayer(-3, false);
					break;
					
				case 4:
					assertEquals (p1.getMoney(), beforeActionP1Money + 150);
					break;
					
				case 5:
					assertEquals (13, p1.getPosition());
					g1.movePlayer(-6, false);
					break;
					
				case 6:
					assertEquals (p1.getMoney(), beforeActionP1Money - 20 * (g1.getPlayers().size() - 1) );
					assertEquals (p2.getMoney(), beforeActionP2Money + 20);
					break;
					
				case 7:
					assertEquals (p1.getMoney(), beforeActionP1Money); // GO BACK TO THIS
					break;
					
				case 8:
					assertEquals (32, p1.getPosition());
					g1.movePlayer(15, false);
					break;
					
				case 9:
					assertEquals (4, p1.getPosition());
					g1.movePlayer(3, false);
					break;
					
				case 10:
					assertEquals (p1.getMoney(), beforeActionP1Money - 75);
					assertEquals (g1.getTaxMoney(), beforeActionTaxMoney + 75);
					break;
			}
			
			g1.changeCardEndTurn("CH");
		}
	}
	
	//Community Chest
	
	@Test
	public void testCommunityChestCards()
	{
	Game g1 = createGameForTesting();
	
	g1.addPlayers("Hat");

	Player p1 = g1.getPlayers().get(0);
	Player p2 = g1.getPlayers().get(1);
	int firstCardId;
	int beforeActionP1Money;
	int beforeActionP2Money;
	int beforeActionTaxMoney;
	
	g1.movePlayer(2, false);
	
	for (int i = 1; i <= 10; i++)
	{
		
		firstCardId = g1.getFirstCChestCardId();
		beforeActionP1Money = p1.getMoney();
		beforeActionP2Money = p2.getMoney();	
		beforeActionTaxMoney = g1.getTaxMoney();
		
		g1.squareAction();

		switch (firstCardId)
		{
			case 1:
				assertEquals (p1.getMoney(), beforeActionP1Money + 30 * (g1.getPlayers().size() - 1) );
				assertEquals (p2.getMoney(), beforeActionP2Money - 30);
				break;
				
			case 2:
				assertEquals (p1.getMoney(), beforeActionP1Money - 50);
				assertEquals (g1.getTaxMoney(), beforeActionTaxMoney + 50);
				break;
				 
			case 3:
				assertEquals (3, p1.getPosition());
				g1.movePlayer(-1, false);
				break;
				
			case 4:
				assertEquals (4, p1.getPosition());
				g1.movePlayer(-2, false);
				break;
				
			case 5:
				assertEquals (p1.getMoney(), beforeActionP1Money - 120);
				assertEquals (g1.getTaxMoney(), beforeActionTaxMoney + 120);
				break;
				
			case 6:
				assertEquals (p1.getMoney(), beforeActionP1Money - 20);
				assertEquals (g1.getTaxMoney(), beforeActionTaxMoney + 20);
				break;
				 
			case 7:
				assertEquals (p1.getMoney(), beforeActionP1Money + 60);
				break;
				
			case 8:
				assertEquals (27, p1.getPosition());
				g1.movePlayer(-25, false);
				break;
				
			case 9:
				assertEquals (p1.getMoney(), beforeActionP1Money + 100);
				break;
				
			case 10:
				assertEquals (p1.getMoney(), beforeActionP1Money); //GO BACK TO THIS
				break;
		}
			
		g1.changeCardEndTurn("CC");
		
	}
}
	
	//Taxes
	
	@Test
	public void testPlayerPaysAndGetsTaxes()
	{
		Game g1 = createGameForTesting();

		g1.addPlayers("Hat");
		Player p1 = g1.getPlayers().get(0);
		int playerMoney = p1.getMoney();
		
		g1.movePlayer(4, false); //goes to income tax
		g1.squareAction();
		System.out.println("Current position " + p1.getPosition()); 

		assertEquals (p1.getMoney(), playerMoney - 200);
		assertEquals (g1.getTaxMoney(), 200);
		
		
		playerMoney = p1.getMoney();
		
		g1.movePlayer(16, false); //goes to free parking
		g1.squareAction();
		
		assertEquals (p1.getMoney(), playerMoney + 200);
		assertEquals (g1.getTaxMoney(), 0);

		playerMoney = p1.getMoney();
		
		//test if he pays both taxes
		g1.movePlayer(18, false); //goes to luxury tax
		g1.squareAction();

		assertEquals (p1.getMoney(), playerMoney - 100);
		assertEquals (g1.getTaxMoney(), 100);
		
		playerMoney = p1.getMoney();
		
		g1.movePlayer(6, false); //goes to income tax
		g1.squareAction();
		

		assertEquals (p1.getMoney(), playerMoney);
		assertEquals (g1.getTaxMoney(), 300);
				
		playerMoney = p1.getMoney();
		
		g1.movePlayer(16, false); //goes to free parking
		g1.squareAction();
		
		assertEquals (p1.getMoney(), playerMoney + 300);
		assertEquals (g1.getTaxMoney(), 0);
	}
	
	@Test
	public void testIfPlayerReceives200ByPassingGo() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		Player p1 = g1.getPlayers().get(0);
		int playerMoney = p1.getMoney();
//		Pair diceRoll = new Pair(5,5);
		
		g1.movePlayer(10, false);
		g1.movePlayer(10, false);
		g1.movePlayer(10, false);
		
//		Pair otherDiceRoll = new Pair(5,6);
		
		g1.movePlayer(11, false);
		
		assertEquals(p1.getPosition(), 1);
		
		assertEquals(p1.getMoney(), playerMoney+200);
	}
	
	@Test
	public void testIfPlayerReceives400ByLandingGo() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		Player p1 = g1.getPlayers().get(0);
		int playerMoney = p1.getMoney();
		Pair diceRoll = new Pair(5,5);
		
		g1.movePlayer(10, false);
		g1.movePlayer(10, false);
		g1.movePlayer(10, false);
		g1.movePlayer(10, false);
		
		g1.squareAction();
		
		assertEquals(p1.getPosition(), 0);
		
		assertEquals(p1.getMoney(), playerMoney+400);
	}
	
	@Test
	public void testIfPlayerCollectsAndPaysStation() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
		Player p1 = g1.getPlayers().get(0);
		Player p2 = g1.getPlayers().get(1);
		
		int ownerMoney = p1.getMoney();
		int payerMoney = p2.getMoney();
		
		assertEquals(ownerMoney, 1500);
		assertEquals(payerMoney, 1500);
		
		g1.movePlayer(5, false);
		g1.buyProperty();
		g1.endTurn();
		
		g1.movePlayer(5, false);
		g1.squareAction();
		
		assertEquals(p1.getMoney(), (ownerMoney-200)+25);
		assertEquals(p2.getMoney(), payerMoney-25);
		
		g1.endTurn();
	}
	
	@Test
	public void testIfPlayerCollectsAndPaysCompany() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
		Player p1 = g1.getPlayers().get(0);
		Player p2 = g1.getPlayers().get(1);
		
		int ownerMoney = p1.getMoney();
		int payerMoney = p2.getMoney();
		
		assertEquals(ownerMoney, 1500);
		assertEquals(payerMoney, 1500);
		
		g1.movePlayer(12, false);
		g1.buyProperty();
		
		g1.endTurn();
		
		g1.movePlayer(12, false);
		g1.squareAction();
		
		assertEquals(p1.getMoney(), (ownerMoney-150)+(4*12));
		assertEquals(p2.getMoney(), payerMoney-(4*12));
	}
	
	@Test
	public void testIfPlayerCantPlaceHouseInInvalidPlaces() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
		Player p1 = g1.getPlayers().get(0);
		
		g1.movePlayer(1, false);
		
		BuyableSquare s1 = (BuyableSquare) g1.getBoard().getSquares().get(p1.getPosition());
		
		assertEquals(g1.checkHouseAvailability(s1), -4); //player doesn't own all brown spaces
		
		g1.buyProperty();
		
		g1.movePlayer(2, false);
		
		g1.buyProperty();
		
		s1 = (BuyableSquare) g1.getBoard().getSquares().get(p1.getPosition());
		
		s1.setInMortgage(true);
		
		assertEquals(g1.checkHouseAvailability(s1), -2); //house is in mortgage
		
		g1.movePlayer(2, false);
		
		g1.buyProperty();
		
		s1 = (BuyableSquare) g1.getBoard().getSquares().get(p1.getPosition());
		
		assertEquals(s1.getType(), "Station");
		assertEquals(g1.checkHotelAvailability(s1), -1); //can't place house in a station;
	}	
	
	@Test
	public void testifPlayerCantBuyAHouseWithoutMoney() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
		Player p1 = g1.getPlayers().get(0);
		
		g1.movePlayer(1, false);
		
		g1.buyProperty();
		
		g1.movePlayer(2, false);
		
		g1.buyProperty();
		
		Property s1 = (Property) g1.getBoard().getSquares().get(p1.getPosition());
		
		p1.removeMoney(1331, false); //he has 49 left but a brown house costs 50
		
		assertEquals(g1.buyHouse(s1), -3); //no money
	}
	
	@Test
	public void testIfPlayerCanPlaceHouseInValidPlaces() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
		Player p1 = g1.getPlayers().get(0);
		
		g1.movePlayer(1, false);
		
		g1.buyProperty();
		
		g1.movePlayer(2, false);
		
		g1.buyProperty();
		
		BuyableSquare s1 = (BuyableSquare) g1.getBoard().getSquares().get(p1.getPosition());
		
		assertEquals(g1.checkHouseAvailability(s1), 0);
		
		Property s2 = (Property) s1;
		
		assertEquals(g1.buyHouse(s2), 0);
		
		assertEquals(s2.getHouses(), 1);
		
		g1.movePlayer(3, false);
		
		g1.buyProperty();
		
		g1.movePlayer(2, false);
		
		g1.buyProperty();
		
		g1.movePlayer(1, false);
		
		g1.buyProperty();
		
		BuyableSquare s4 = (BuyableSquare) g1.getBoard().getSquares().get(p1.getPosition());
		
		assertEquals(g1.checkHouseAvailability(s4), 0);
		
		Property s5 = (Property) s4;
		
		assertEquals(g1.buyHouse(s5), 0);
		assertEquals(s5.getHouses(), 1);
	}

	@Test
	public void testIfPlayerCantPlaceHotelInInvalidPlaces() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
		Player p1 = g1.getPlayers().get(0);
		
		g1.movePlayer(1, false);
		
		BuyableSquare s1 = (BuyableSquare) g1.getBoard().getSquares().get(p1.getPosition());
		
		assertEquals(g1.checkHotelAvailability(s1), -2); //player doesn't have 4 houses
		
		g1.buyProperty();
		
		g1.movePlayer(2, false);
		
		g1.buyProperty();
		
		s1 = (BuyableSquare) g1.getBoard().getSquares().get(p1.getPosition());
		
		Property s2 = (Property) s1;
		
		g1.buyHouse(s2);
		g1.buyHouse(s2);
		g1.buyHouse(s2);
		g1.buyHouse(s2);
		
		g1.buyHotel(s2);
		
		assertEquals(s2.getHotels(), 1);
		
		assertEquals(g1.checkHotelAvailability(s2), -4); // already has an hotel
		
		g1.movePlayer(2, false);
		
		s1 = (BuyableSquare) g1.getBoard().getSquares().get(p1.getPosition());
		
		g1.buyProperty();
		
		assertEquals(g1.checkHotelAvailability(s1), -1); // can't place hotels there
	}
	
	@Test
	public void testifPlayerCanBuyAnotherPropertyFromTheOther() {
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");
		
		Player p1 = g1.getPlayers().get(0);
		Player p2 = g1.getPlayers().get(1);
		
		g1.movePlayer(1, false);
		
		g1.buyProperty();
		
		g1.endTurn();
		
		int ownerMoney = p1.getMoney();
		int buyerMoney = p2.getMoney();
		Property athens = (Property) p1.getPropertiesOwned().get(0);
		int costOfTheProperty = athens.getCost();
		
		GameController.getInstance().buyPropertyFromOtherPlayer(p1, p2, p1.getPropertiesOwned().get(0));
		
		assertEquals(p1.getMoney(), ownerMoney + costOfTheProperty);
		assertEquals(p1.getPropertiesOwned().size(), 0);
		assertEquals(p2.getPropertiesOwned().size(), 1);
		assertEquals(p2.getMoney(), buyerMoney - costOfTheProperty);
		assertEquals(athens.getOwner(), p2.getName());
	}
	
	@Test
	public void testifPlayerGoesBankrupt() {
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");
		Player p1 = g1.getPlayers().get(0);
		
		p1.removeMoney(1304, false);
		
		assertEquals(p1.getBankrupcyState(), false);
		
		g1.movePlayer(4, false); //bankrupcy from a tax square;
		g1.squareAction();
		
		assertEquals(p1.getBankrupcyState(), true);
		
		p1.addMoney(1500);
		
		g1.movePlayer(1, false);
		g1.buyProperty();
		
		g1.movePlayer(1, false);
		g1.endTurn();
		
		Player p2 = g1.getPlayers().get(1);
		
		assertEquals(p2.getBankrupcyState(), false);
		
		p2.removeMoney(1480, false); //bankrupcy from paying rent of another player's property
		
		g1.movePlayer(5, false);
		g1.squareAction();
		
		assertEquals(p2.getBankrupcyState(), true);
	}

	//Bot
	
	@Test
	public void testIfBotBuysProperty()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");
		
		Player bot1 = g1.getPlayers().get(1);
		
		g1.endTurn();
		
		assertEquals("Bot1", g1.getCurrentPlayer().getName());
		
		g1.movePlayer(1, false);
		g1.botTurn();
		
		assertEquals (1, bot1.getPropertiesOwned().size());
		assertEquals (1440, bot1.getMoney() );
	
	}
	
	@Test
	public void testIfBotBuysPropertyWhenOwns1BROWN()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");
		
		Player bot1 = g1.getPlayers().get(1);
		
		g1.endTurn();
		
		assertEquals("Bot1", g1.getCurrentPlayer().getName());
		
		g1.movePlayer(1, false);
		g1.botTurn();
		
		assertEquals (1, bot1.getPropertiesOwned().size());
		assertEquals (1440, bot1.getMoney());
		
		bot1.removeMoney(1180, false);
		g1.movePlayer(2, false);
		g1.botTurn();
		
		assertEquals (2, bot1.getPropertiesOwned().size());
		assertEquals (200, bot1.getMoney());
	
	}
	
	@Test
	public void testIfBotBuysPropertyWhenOwns2LIGHTBLUE()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");
		
		Player bot1 = g1.getPlayers().get(1);
		
		g1.endTurn();
		
		assertEquals("Bot1", g1.getCurrentPlayer().getName());
		
		g1.movePlayer(6, false);
		g1.botTurn();
		
		assertEquals (1, bot1.getPropertiesOwned().size());
		assertEquals (1400, bot1.getMoney());
		
		g1.movePlayer(2, false);
		g1.botTurn();
		
		System.out.println(g1.getBoard().getSquares().get(bot1.getPosition()).getName());
		
		assertEquals (2, bot1.getPropertiesOwned().size());
		assertEquals (1300, bot1.getMoney());
		
		bot1.removeMoney(980, false);
		g1.movePlayer(1, false);
		g1.botTurn();
		
		assertEquals (3, bot1.getPropertiesOwned().size());
		assertEquals (200, bot1.getMoney());
	
	}
	
	@Test
	public void testIfBotBuysStation()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");
		
		Player bot1 = g1.getPlayers().get(1);
		
		g1.endTurn();
		
		assertEquals("Bot1", g1.getCurrentPlayer().getName());
		
		g1.movePlayer(5, false);
		g1.botTurn();
		
		assertEquals (1, bot1.getPropertiesOwned().size());
		assertEquals (1300, bot1.getMoney() );
	
	}
	
	@Test
	public void testIfBotBuysStationWhenOwns1()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");
		
		Player bot1 = g1.getPlayers().get(1);
		
		g1.endTurn();
		
		assertEquals("Bot1", g1.getCurrentPlayer().getName());
		
		g1.movePlayer(5, false);
		g1.botTurn();
		
		assertEquals (1, bot1.getPropertiesOwned().size());
		assertEquals (1300, bot1.getMoney());
		
		bot1.removeMoney(750, false);
		g1.movePlayer(10, false);
		g1.botTurn();
		
		assertEquals (2, bot1.getPropertiesOwned().size());
		assertEquals (350, bot1.getMoney());
	}
	
	@Test
	public void testIfBotBuysStationWhenOwns3()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");
		
		Player bot1 = g1.getPlayers().get(1);
		
		g1.endTurn();
		
		assertEquals("Bot1", g1.getCurrentPlayer().getName());
		
		g1.movePlayer(5, false);
		g1.botTurn();
		
		assertEquals (1, bot1.getPropertiesOwned().size());
		assertEquals (1300, bot1.getMoney());
		
		g1.movePlayer(10, false);
		g1.botTurn();
		
		assertEquals (2, bot1.getPropertiesOwned().size());
		assertEquals (1100, bot1.getMoney());
		
		g1.movePlayer(10, false);
		g1.botTurn();
		
		assertEquals (3, bot1.getPropertiesOwned().size());
		assertEquals (900, bot1.getMoney());
		
		bot1.removeMoney(500, false);
		
		g1.movePlayer(10, false);
		g1.botTurn();
		
		assertEquals (4, bot1.getPropertiesOwned().size());
		assertEquals (200, bot1.getMoney());
	}
	
	@Test
	public void testIfBotBuysCompany()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");
		
		Player bot1 = g1.getPlayers().get(1);
		
		g1.endTurn();
		
		assertEquals("Bot1", g1.getCurrentPlayer().getName());
		
		g1.movePlayer(12, false);
		g1.botTurn();
		
		assertEquals (1, bot1.getPropertiesOwned().size());
		assertEquals (1350, bot1.getMoney() );
	
	}
	
	@Test
	public void testIfBotBuysCompanyWhenOwns1()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");
		
		Player bot1 = g1.getPlayers().get(1);
		
		g1.endTurn();
		
		assertEquals("Bot1", g1.getCurrentPlayer().getName());
		
		g1.movePlayer(12, false);
		g1.botTurn();
		
		assertEquals (1, bot1.getPropertiesOwned().size());
		assertEquals (1350, bot1.getMoney());
		
		bot1.removeMoney(1000, false);
		g1.movePlayer(16, false);
		g1.botTurn();
		
		assertEquals (2, bot1.getPropertiesOwned().size());
		assertEquals (200, bot1.getMoney());
	
	}
}


