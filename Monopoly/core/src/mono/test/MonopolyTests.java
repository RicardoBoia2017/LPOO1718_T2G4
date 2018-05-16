package mono.test;

import static org.junit.Assert.*;

import org.junit.Test;

import mono.model.GameModel;
import mono.model.entities.Player;
import mono.model.entities.Square;

public class MonopolyTests {
	
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
	
	/*@Test
	public void playerGetsOutOfJailByPaying() {
		
	}
	
	@Test
	public void playerGetsOutOfJailByRollingDoubles() {
		
	}*/

}
