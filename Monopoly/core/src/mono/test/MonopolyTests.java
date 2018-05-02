package mono.test;

import static org.junit.Assert.*;

import org.junit.Test;

import mono.model.GameModel;
import mono.model.entities.Player;
import mono.model.entities.Square;

public class MonopolyTests {
	
	@Test
	public void testIfGameCreatesAndAddsPlayersProperly() {
		GameModel g1 = null; 
		
		g1.getInstance().addPlayers("Hat");
		
		Player p1 = (g1.getInstance().getPlayers())[0];
		Player p2 = (g1.getInstance().getPlayers())[1];
		Player p3 = (g1.getInstance().getPlayers())[2];
		Player p4 = (g1.getInstance().getPlayers())[3];
		
		assertEquals(p1.getName(), "ActualPlayer");
		assertEquals(p1.getBoardPiece(), "Hat");
		assertEquals(p2.getName(), "Bot");
		assertEquals(p2.getBoardPiece(), "Thimble");
		assertEquals(p3.getName(), "Bot");
		assertEquals(p3.getBoardPiece(), "Boot");
		assertEquals(p4.getName(), "Bot");
		assertEquals(p4.getBoardPiece(), "Car");
		
		Square InitialGoSquare = g1.getInstance().getBoard().getBoardArray().get(0);
		
		assertEquals(InitialGoSquare.getNumPlayersOnTopOfSquare(), 4);
	}

	@Test
	public void testIfPlayerMoves() {
		GameModel g1 = null; 
				
		g1.getInstance().addPlayers("Hat");
		
		Player p1 = (g1.getInstance().getPlayers())[0];
		
		g1.getInstance().updateGame(6);
		
		assertEquals(p1.getPosition(), 6);
		
		Square s1 = g1.getInstance().getBoard().getBoardArray().get(p1.getPosition());
		
		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		assertEquals(((s1.getplayersOnTopOfSquareArray()).get(0)).getName(), "ActualPlayer");
		assertEquals(s1.getName(), "Brussels");
		
		g1.getInstance().updateGame(7);
		
		assertEquals(p1.getPosition(), 13);
		
		Square s2 = g1.getInstance().getBoard().getBoardArray().get(p1.getPosition());
		
		assertEquals(s2.getNumPlayersOnTopOfSquare(), 1);
		assertEquals(((s2.getplayersOnTopOfSquareArray()).get(0)).getName(), "ActualPlayer");
		assertEquals(s2.getName(), "Moscow");
	}
	
	@Test
	public void testIfPlayerMovesBeyond40() {
		GameModel g1 = null; 
		
		g1.getInstance().addPlayers("Hat");
		
		Player p1 = (g1.getInstance().getPlayers())[0];
		
		g1.getInstance().updateGame(12);
		g1.getInstance().updateGame(12);
		g1.getInstance().updateGame(12);
		g1.getInstance().updateGame(12);
		
		assertEquals(p1.getPosition(), 8);
		Square s1 = g1.getInstance().getBoard().getBoardArray().get(p1.getPosition());
		assertEquals(s1.getNumPlayersOnTopOfSquare(), 1);
		assertEquals(((s1.getplayersOnTopOfSquareArray()).get(0)).getName(), "ActualPlayer");
		assertEquals(s1.getName(), "Casablanca");
	}

}
