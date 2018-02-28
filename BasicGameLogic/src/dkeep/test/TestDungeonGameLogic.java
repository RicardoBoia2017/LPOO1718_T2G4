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

	//TASK2
	
	@Test
	public void testMoveHeroIntoOgre() //Não é suposto acontecer o que está no enunciado
	{
		Game game = new Game(1);
		char [][] map;
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(7, game.getHero().getCoordY());
		
		//hero moves in ogre level
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		map = game.updateGame('w');
		game.updateGame('w');
		
//		for(int i = 0; i < map.length; i++) {
//			 System.out.println(map[i]);
//		 }
	}
	
	@Test
	public void testMoveHeroIntoKey()
	{
		Game game = new Game(1);
		char [][] map;
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(7, game.getHero().getCoordY());
		
		//hero moves in ogre level
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		assertEquals('K', game.getHero().getID());
	}
	
	@Test
	public void testMoveHeroIntoDoor2 ()
	{
		Game game = new Game(1);
		char [][] map;
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(7, game.getHero().getCoordY());
		
		//hero moves in ogre level
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		map = game.updateGame('a');
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		assertEquals('I', map [1][0]);
	}
	
	@Test
	public void testHeroOpensDoor()
	{
		Game game = new Game(1);
		char [][] map;
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(7, game.getHero().getCoordY());
		
		//hero gets key
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		assertEquals('K', game.getHero().getID());

		//hero goes to door
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		game.updateGame('a');
		game.updateGame('a');
		game.updateGame('a');
		game.updateGame('a');
		game.updateGame('a');
		game.updateGame('w');
		game.updateGame('w');
		map = game.updateGame('a');
		assertEquals('S', map [1][0]);
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
	}
	
	@Test
	public void testMoveHeroIntoVictory2()
	{
		Game game = new Game(1);
		char [][] map;
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(7, game.getHero().getCoordY());
		
		//hero gets key
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		assertEquals('K', game.getHero().getID());

		//hero goes to door
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		game.updateGame('a');
		game.updateGame('a');
		game.updateGame('a');
		game.updateGame('a');
		game.updateGame('a');
		game.updateGame('w');
		game.updateGame('w');
		map = game.updateGame('a');
		assertEquals('S', map [1][0]);
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		
		//hero wins
		map = game.updateGame('a');
		assertEquals('W', map [0][0]);
		
	}
}

