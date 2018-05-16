package mono.test;

import static org.junit.Assert.*;

import org.junit.Test;

import mono.model.GameModel;
import mono.model.entities.Pair;
import mono.model.entities.Player;
import mono.model.entities.Square;

public class MonopolyTests {
	
	//Create Game
	@Test
	public void testIfGameCreatesAndAddsPlayersProperly() {
		GameModel g1 = GameModel.getInstance();
		
		g1.setGameModelInstanceToNull();
		
		g1 = GameModel.getInstance();
		
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
		GameModel g1 = GameModel.getInstance();
		
		g1.setGameModelInstanceToNull();
		
		g1 = GameModel.getInstance();
				
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
		GameModel g1 = GameModel.getInstance();
		g1.setGameModelInstanceToNull();
		g1 = GameModel.getInstance();
		
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
	
	//Properties
	
	@Test
	public void testIfPlayerPaysRent()
	{
		GameModel.getInstance().setGameModelInstanceToNull();
		
		GameModel g1 = GameModel.getInstance();

		g1.addPlayers("Hat");
		Player owner = g1.getPlayers().get(0);
		Player payer = g1.getPlayers().get(1);
		
		int payerMoney = payer.getMoney();
		int ownerMoney = owner.getMoney();
		
		owner.move(1);
		g1.squareAction();
		
		g1.setCurrentPlayer(2);
		
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
		GameModel g1 = GameModel.getInstance();
		g1.setGameModelInstanceToNull();
		g1 = GameModel.getInstance();
		
		g1.addPlayers("Hat");
		
		g1.movePlayer(30);
		
		Player p1 = (g1.getPlayers()).get(0);
		Square s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		
		assertEquals(s1.getName(), "GoToJail");
		assertEquals(p1.getPosition(), 30);
		
		g1.squareAction();
		
		assertEquals(p1.wasSentToJail(), true);
		
		assertEquals(p1.getPosition(), 10);
		assertEquals(s1.getplayersOnTopOfSquareArray().size(), 0);
		
		s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		
		assertEquals(s1.getName(), "Jail");
	}
	
	@Test
	public void testIfPlayerMovementIsBlockedInJailThreeTurns() {
		GameModel g1 = GameModel.getInstance();
		g1.setGameModelInstanceToNull();
		g1 = GameModel.getInstance();
		
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
	}
	
	/*@Test
	public void playerGetsOutOfJailByPaying() {
		
	}*/
	
	@Test
	public void playerGetsOutOfJailByRollingDoubles() {
		GameModel g1 = GameModel.getInstance();
		g1.setGameModelInstanceToNull();
		g1 = GameModel.getInstance();
		
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
		GameModel.getInstance().setGameModelInstanceToNull();
		
		GameModel g1 = GameModel.getInstance();

		g1.addPlayers("Hat");
		Player p1 = g1.getPlayers().get(0);

		g1.movePlayer(4); //goes to income tax
		g1.squareAction();
		
		assertEquals (p1.getMoney(), 14800);
		assertEquals (g1.getTaxMoney(), 200);
		
		g1.movePlayer(16); //goes to free parking
		g1.squareAction();
		
		assertEquals (p1.getMoney(), 15000);
		assertEquals (g1.getTaxMoney(), 0);
		
		//test if he pays both taxes
		g1.movePlayer(18); //goes to luxury tax
		g1.squareAction();
		
		assertEquals (p1.getMoney(), 14900);
		assertEquals (g1.getTaxMoney(), 100);
		
		g1.movePlayer(6); //goes to income tax
		g1.squareAction();
		
		assertEquals (p1.getMoney(), 14700);
		assertEquals (g1.getTaxMoney(), 300);
		
		g1.movePlayer(16); //goes to free parking
		g1.squareAction();
		
		assertEquals (p1.getMoney(), 15000);
		assertEquals (g1.getTaxMoney(), 0);
	}
}
