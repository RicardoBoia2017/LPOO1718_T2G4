package mono.test;

import static org.junit.Assert.*;

import org.junit.Test;

import mono.model.Game;
import mono.model.entities.Pair;
import mono.model.entities.Player;
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
		assertEquals(p2.getName(), "Bot");
		assertEquals(p2.getBoardPiece().getType(), "Thimble");
		assertEquals(p3.getName(), "Bot");
		assertEquals(p3.getBoardPiece().getType(), "Boot");
		assertEquals(p4.getName(), "Bot");
		assertEquals(p4.getBoardPiece().getType(), "Car");
		
		Square InitialGoSquare = g1.getBoard().getBoardArray().get(0);
		
		assertEquals(InitialGoSquare.getNumPlayersOnTopOfSquare(), 4);
	}

	//Movement
	@Test
	public void testIfPlayerMoves() {
		Game g1 = createGameForTesting();
				
		g1.addPlayers("Hat");
		
		Player p1 = (g1.getPlayers()).get(0);
		
		g1.movePlayer(6);
		
		assertEquals(p1.getPosition(), 6);
		
		Square s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		
		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		assertEquals(((s1.getplayersOnTopOfSquareArray()).get(0)).getName(), "ActualPlayer");
		assertEquals(s1.getName(), "Brussels");
		
		g1.movePlayer(7);
		
		assertEquals(p1.getPosition(), 13);
		
		Square s2 = g1.getBoard().getBoardArray().get(p1.getPosition());
		
		assertEquals(s2.getNumPlayersOnTopOfSquare(), 1);
		assertEquals(((s2.getplayersOnTopOfSquareArray()).get(0)).getName(), "ActualPlayer");
		assertEquals(s2.getName(), "Moscow");
	}
	
	@Test
	public void testIfPlayerMovesBeyond40() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
		Player p1 = (g1.getPlayers()).get(0);
		
		g1.movePlayer(12);
		g1.movePlayer(12);
		g1.movePlayer(12);
		g1.movePlayer(12);
		
		assertEquals(p1.getPosition(), 8);
		Square s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		assertEquals(((s1.getplayersOnTopOfSquareArray()).get(0)).getName(), "ActualPlayer");
		assertEquals(s1.getName(), "Casablanca");
	}
	
	//Buyable squares
	
	@Test
	public void testIfPlayerBuyProperty()
	{
		Game g1 = createGameForTesting();
		g1.addPlayers("Hat");

		Player p1 = g1.getPlayers().get(0);
		
		int p1Money = p1.getMoney();

		p1.move(1);
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

		p1.move(5);
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

		p1.move(12);
		System.out.println(g1.getBoard().getBoardArray().get(12).getName());
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
		
		owner.move(1);
		g1.buyProperty();
		
		g1.endTurn();
		
		payer.move(1);
		assertEquals(-2, g1.checkPropertyAvailibility());
	}
	
	@Test
	public void testIfPlayerCantBuyWhenSquareIsntBuyable()
	{
		Game g1 = createGameForTesting();

		g1.addPlayers("Hat");
		Player p1 = g1.getPlayers().get(0);
		
		p1.move(2);
				
		assertEquals(-1, g1.checkPropertyAvailibility());
	}

	@Test
	public void testIfPlayerCantBuyWhenHasntEnoughMoney()
	{
		Game g1 = createGameForTesting();

		g1.addPlayers("Hat");
		Player p1 = g1.getPlayers().get(0);
		
		p1.removeMoney(1490); //stays with 10�
		
		p1.move(1);
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
		
		owner.move(1);
		g1.buyProperty(); //pays the property price
		
		ownerMoney = owner.getMoney();
		
		g1.endTurn();
		
		payer.move(1);
		g1.squareAction();
		
		assertEquals (payer.getMoney(), payerMoney - 2);
		assertEquals (owner.getMoney(), ownerMoney + 2);
		
		payerMoney = payer.getMoney();
		ownerMoney = owner.getMoney();
	}
	
	//Jail 
	@Test
	public void testIfPlayerGetsSentToJail() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
		g1.movePlayer(30);
		
		Player p1 = (g1.getPlayers()).get(0);
		Square s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		
		assertEquals(s1.getName(), "GoToJail");
		assertEquals(p1.getPosition(), 30);
		
		g1.squareAction();
		
		assertEquals(p1.isInJail(), true);
		
		assertEquals(p1.getPosition(), 10);
		assertEquals(s1.getplayersOnTopOfSquareArray().size(), 0);
		
		s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		
		assertEquals(s1.getName(), "Jail");
	}
	
	/*@Test
	public void testIfPlayerMovementIsBlockedInJailThreeTurns() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
		g1.movePlayer(30);
		
		g1.squareAction();
		
		Player p1 = (g1.getPlayers()).get(0);
		Square s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		
		assertEquals(p1.getPosition(), 10);
		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		
		g1.movePlayer(1);
		g1.squareAction();
		
		s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		
		assertEquals(p1.getPosition(), 10);
		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		
		g1.movePlayer(1);
		g1.squareAction();
		
		s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		assertEquals(p1.getPosition(), 10);
		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		
		g1.movePlayer(1);
		g1.squareAction();
		
		s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		assertEquals(p1.getPosition(), 10);
		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		
		g1.movePlayer(1);
		g1.squareAction();
		
		s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		assertEquals(p1.getPosition(), 11);
		assertEquals(s1.getName(), "Cape Town");
	}*/
	
	@Test
	public void playerGetsOutOfJailByPaying() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
		g1.movePlayer(30);
		
		g1.squareAction();
		
		Player p1 = (g1.getPlayers()).get(0);
		Square s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		
		g1.movePlayer(1);
		g1.squareAction();
		
		s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		assertEquals(p1.getPosition(), 10);
		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		
		int moneyBefore = p1.getMoney();
		
		p1.setCurrentDiceroll(new Pair(3,1));
		
		g1.tellJailPlayerWantsToPayFine();
		s1.doAction(p1);
		
		assertEquals(p1.getMoney(), moneyBefore-50);
		
		s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		assertEquals(p1.getPosition(), 14);
		assertEquals(s1.getName(), "Amsterdam");
	}
	
	@Test
	public void playerGetsOutOfJailByRollingDoubles() {
		Game g1 = createGameForTesting();
		
		g1.addPlayers("Hat");
		
		Pair diceRollOdd = new Pair(3, 6);
		Pair diceRollSame = new Pair(3, 3);
		
		g1.movePlayer(30);
		g1.squareAction();
		
		Player p1 = (g1.getPlayers()).get(0);
		Square s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		
		g1.movePlayer(diceRollOdd);
		g1.squareAction();
		
		assertEquals(p1.getPosition(), 10);
		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		
		g1.movePlayer(diceRollSame);
		g1.squareAction();
		
		assertEquals(p1.getPosition(), 16);
		s1 =  g1.getBoard().getBoardArray().get(p1.getPosition());
		assertEquals(s1.getName(), "Madrid");
	}

	//Taxes
	
	@Test
	public void testPlayerPaysAndGetsTaxes()
	{
		Game g1 = createGameForTesting();

		g1.addPlayers("Hat");
		Player p1 = g1.getPlayers().get(0);
		int playerMoney = p1.getMoney();
		
		g1.movePlayer(4); //goes to income tax
		g1.squareAction();
		
		assertEquals (p1.getMoney(), playerMoney - 200);
		assertEquals (g1.getTaxMoney(), 200);
		
		
		playerMoney = p1.getMoney();
		
		g1.movePlayer(16); //goes to free parking
		g1.squareAction();
		
		assertEquals (p1.getMoney(), playerMoney + 200);
		assertEquals (g1.getTaxMoney(), 0);
		
		playerMoney = p1.getMoney();
		
		//test if he pays both taxes
		g1.movePlayer(18); //goes to luxury tax
		g1.squareAction();
		
		assertEquals (p1.getMoney(), playerMoney - 100);
		assertEquals (g1.getTaxMoney(), 100);
		
		playerMoney = p1.getMoney();

		
		g1.movePlayer(6); //goes to income tax
		g1.squareAction();
		
		assertEquals (p1.getMoney(), playerMoney - 200);
		assertEquals (g1.getTaxMoney(), 300);
				
		playerMoney = p1.getMoney();

		
		g1.movePlayer(16); //goes to free parking
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
		Pair diceRoll = new Pair(5,5);
		
		g1.movePlayer(diceRoll);
		g1.movePlayer(diceRoll);
		g1.movePlayer(diceRoll);
		
		Pair otherDiceRoll = new Pair(5,6);
		
		g1.movePlayer(otherDiceRoll);
		
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
		
		g1.movePlayer(diceRoll);
		g1.movePlayer(diceRoll);
		g1.movePlayer(diceRoll);
		g1.movePlayer(diceRoll);
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
		
		g1.movePlayer(5);
		g1.buyProperty();
		g1.endTurn();
		
		g1.movePlayer(5);
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
		
		g1.movePlayer(12);
		g1.buyProperty();
		g1.endTurn();
		
		Pair diceroll = new Pair(6,6);
		
		g1.movePlayer(diceroll);
		g1.squareAction();
		
		assertEquals(p1.getMoney(), (ownerMoney-150)+(4*12));
		assertEquals(p2.getMoney(), payerMoney-(4*12));
		
		g1.endTurn();
	}
}
