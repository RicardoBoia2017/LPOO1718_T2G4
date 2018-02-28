package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.Map;


public class TestDungeonGameLogic {
 
	@Test
	public void testMoveHeroIntoFreeCell()
	{
		Game game = new Game(1);
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		
		//check if the hero moved down with 's'
		game.updateGame('s');
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(2, game.getHero().getCoordY());
	}
	
	@Test
	public void testMoveHeroIntoWall()
	{
		Game game = new Game(1);
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		
		//check if the hero moved against the wall
		game.updateGame('a');
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
	}
	
	@Test
	public void testMoveHeroIntoGuard()
	{
		Game game = new Game(1);
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		
		//check if the hero moved next to guard and lost
		char[][] map;
		map = game.updateGame('d');
		
		assertEquals(2, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		
		assertEquals(3, game.getguard().getcoordX());
		assertEquals(1, game.getguard().getcoordY());
		
		assertEquals('E', map[0][0]);
	}
	
	@Test
	public void testMoveHeroIntoDoor()
	{
		Game game = new Game(1);
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		
		//check if the hero moved next to guard and lost
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(2, game.getHero().getCoordY());
	}
	
	@Test
	public void testMoveHeroIntoLever()
	{
		char[][] map;
		Game game = new Game(1);
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		
		//check if the hero moved next to guard and lost
		game.updateGame('s');
		map = game.updateGame('s');
		assertEquals('S', map[2][0]);
		assertEquals('S', map[3][0]);
	} 
	
	@Test
	public void testMoveHeroIntoVictory()
	{
		Game game = new Game(1);
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		
		//check if the hero moved next to guard and lost
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		
		assertEquals(2, game.getMap().getcurrentlevel());
	}

}

