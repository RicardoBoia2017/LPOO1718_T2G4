package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.IllegalMapChangeException;
import dkeep.logic.Map;
import dkeep.logic.Ogre;


public class TestDungeonGameLogic {
	
	@Test
	public void testHeroMove()
	{
		Game game = new Game (1);
		game.getLevelLogic().getGuard().setMovementBlocker(true);
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		game.updateGame('s');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(2, game.getLevelLogic().getHero().getCoordY());
		
		game.updateGame('d');
		assertEquals(2, game.getLevelLogic().getHero().getCoordX());
		assertEquals(2, game.getLevelLogic().getHero().getCoordY());
		
		game.updateGame('w');
		assertEquals(2, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
	}
	
	//TASK 1
	
	@Test
	public void testMoveHeroIntoFreeCell()
	{
		Game game = new Game(1);
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		//check if the hero moved down with 's'
		game.updateGame('s');

		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(2, game.getLevelLogic().getHero().getCoordY());
	}
	
	@Test
	public void testMoveHeroIntoWall()
	{
		Game game = new Game(1);
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		//check if the hero moved against the wall
		game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
	}
	
	@Test
	public void testMoveHeroIntoGuard()
	{
		Game game = new Game(1);
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		//check if the hero moved next to guard and lost
		
		game.updateGame('d');
		
		assertEquals(2, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		assertEquals(3, game.getLevelLogic().getGuard().getcoordX());
		assertEquals(1, game.getLevelLogic().getGuard().getcoordY());
		
		assertEquals("Over", game.getLevelLogic().getLevelState());
	}
	
	@Test
	public void testMoveHeroIntoDoor()
	{
		Game game = new Game(1);
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		//check if the hero moved next to guard and lost
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(2, game.getLevelLogic().getHero().getCoordY());
	}
	
	@Test
	public void testMoveHeroIntoLever()
	{
		Map map;
		Game game = new Game(1);
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		//check if the hero moved next to guard and lost
		game.updateGame('s');
		map = game.updateGame('s');
		assertEquals('S', map.getMatrix()[2][0]);
		assertEquals('S', map.getMatrix()[3][0]);
	} 
	
	@Test
	public void testMoveHeroIntoVictory()
	{
		Game game = new Game(1);
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		//check if the hero moved next to guard and lost
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');

		
		assertEquals("Ogre", game.getLevelLogic().getLevelType());
	}

	//TASK 2
	
	@Test
	public void testMoveHeroIntoOgre() 
	{
		Game game = new Game(1);
		char [][] map; 
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(7, game.getLevelLogic().getHero().getCoordY());
		
		//hero moves in ogre level
		game.getLevelLogic().getOgre().setBlocker(true);
		game.getLevelLogic().getClub().setBlocker(true);
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		assertEquals('8', game.getLevelLogic().getOgre().getID());
		assertEquals(2, game.getLevelLogic().getOgre().getStunCounter());
		
	}
	
	@Test
	public void testMoveHeroIntoKey()
	{
		Game game = new Game(1);
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(7, game.getLevelLogic().getHero().getCoordY());
		
		//hero moves in ogre level
		game.getLevelLogic().getOgre().setBlocker(true);
		game.getLevelLogic().getClub().setBlocker(true);
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
		assertEquals('K', game.getLevelLogic().getHero().getID());
	}
	
	@Test
	public void testMoveHeroIntoDoor2 ()
	{
		Game game = new Game(1);
		Map map;
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(7, game.getLevelLogic().getHero().getCoordY());
		
		
		//hero moves in ogre level
		game.getLevelLogic().getOgre().setBlocker(true);
		game.getLevelLogic().getClub().setBlocker(true);
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		game.updateGame('w');
		map = game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		assertEquals('I', map.getMatrix() [1][0]);
	}
	
	@Test
	public void testHeroOpensDoor()
	{
		Game game = new Game(1);
		Map map;
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(7, game.getLevelLogic().getHero().getCoordY());
		
		//hero gets key
		game.getLevelLogic().getOgre().setBlocker(true);
		game.getLevelLogic().getClub().setBlocker(true);
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
		assertEquals('K', game.getLevelLogic().getHero().getID());

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
		assertEquals('S', map.getMatrix() [1][0]);
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
	}
	
	@Test
	public void testMoveHeroIntoVictory2()
	{
		Game game = new Game(1);
		Map map;
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(7, game.getLevelLogic().getHero().getCoordY());
		
		//hero gets key
		game.getLevelLogic().getOgre().setBlocker(true);
		game.getLevelLogic().getClub().setBlocker(true);
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
		assertEquals('K', game.getLevelLogic().getHero().getID());

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
		assertEquals('S', map.getMatrix() [1][0]);
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		//hero wins
		game.updateGame('a');
		assertEquals("Victory", game.getLevelLogic().getLevelState());
	}
	
	//TASK 3
	
	@Test(timeout=1000)
	public void testSomeRandomBehaviour() {
		
		Game game = new Game(1);
		char [][] map;
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(7, game.getLevelLogic().getHero().getCoordY());
		
		//outcome1: Ogre moves left.
		//outcome2: Ogre moves right.
		//outcome3: Ogre moves up.
		//outcome4: Ogre moves down.
		
		boolean outcome1 = false, outcome2 = false, outcome3 = false, outcome4 = false;
		
		while(!outcome1 || !outcome2 || !outcome3 || !outcome4) {
			//Hero moves into a wall for the time being
			
			int coordX = game.getLevelLogic().getOgre().getcoordX();
			int coordY = game.getLevelLogic().getOgre().getcoordY();
			
			game.updateGame('s');
			
			if(game.getLevelLogic().getOgre().getRand() == 0) {
				assertEquals(coordX - 1 , game.getLevelLogic().getOgre().getcoordX());
				assertEquals(coordY, game.getLevelLogic().getOgre().getcoordY());
				outcome1 = true;
			}
			
			else if(game.getLevelLogic().getOgre().getRand() == 1) {
				assertEquals(coordX + 1, game.getLevelLogic().getOgre().getcoordX());
				assertEquals(coordY, game.getLevelLogic().getOgre().getcoordY());
				outcome2 = true;
			}
			
			else if(game.getLevelLogic().getOgre().getRand() == 3) {
				assertEquals(coordX, game.getLevelLogic().getOgre().getcoordX());
				assertEquals(coordY-1, game.getLevelLogic().getOgre().getcoordY());
				outcome3 = true;
			}
			
			else if(game.getLevelLogic().getOgre().getRand() == 2) {
				assertEquals(coordX, game.getLevelLogic().getOgre().getcoordX());
				assertEquals(coordY + 1, game.getLevelLogic().getOgre().getcoordY());
				outcome4 = true;
			}
			
			else {
				fail("Some error message.");
			}
		}
	}
	
	@Test(timeout=3000)
	public void testSomeClubBehaviour() {
		Game game = new Game(1);
		char [][] map;
		Map newmap = new Map(0);
		newmap.setMap(2);
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(7, game.getLevelLogic().getHero().getCoordY());
		
		game.setMap(newmap);
		
		game.getMap().updateMap(1, 4, ' ');
		
		game.getMap().updateMap(2, 4, 'O');
		
		game.getMap().updateMap(1, 3, ' ');
	
		game.getMap().updateMap(2, 3, '*');
	
		
		game.getLevelLogic().getOgre().setcoordY(2);
		game.getLevelLogic().getClub().setcoordY(2);
		game.getLevelLogic().getOgre().setBlocker(true);

		//outcome1: Club moves left.
		//outcome2: Club moves right.
		//outcome3: Club moves up.
		//outcome4: Club moves down.
		
		boolean outcome1 = false, outcome2 = false, outcome3 = false, outcome4 = false;
		
		while(!outcome1 || !outcome2 || !outcome3 || !outcome4) {
			//Hero moves into a wall for the time being
			
			game.updateGame('s');
			
			//left
			if(game.getLevelLogic().getClub().getRand() == 0) {
				assertEquals(3, game.getLevelLogic().getClub().getcoordX());
				assertEquals(2, game.getLevelLogic().getClub().getcoordY());
				outcome1 = true;
			}
			
			//right
			else if(game.getLevelLogic().getClub().getRand() == 1) {
				assertEquals(5, game.getLevelLogic().getClub().getcoordX());
				assertEquals(2, game.getLevelLogic().getClub().getcoordY());
				outcome2 = true;
			}
			
			//up
			else if(game.getLevelLogic().getClub().getRand() == 3) {
				assertEquals(4, game.getLevelLogic().getClub().getcoordX());
				assertEquals(1, game.getLevelLogic().getClub().getcoordY());
				outcome3 = true;
			}
			
			//down
			else if(game.getLevelLogic().getClub().getRand() == 2) {
				assertEquals(4, game.getLevelLogic().getClub().getcoordX());
				assertEquals(3, game.getLevelLogic().getClub().getcoordY());
				outcome4 = true;
			}
			
			else {
				fail("Some error message.");
			}
		}
	}

	@Test (timeout = 1000)
	public void testClubStepsintoKey()
	{
		Game game = new Game(1);
		Map newmap = new Map(0);
		newmap.setMap(2);
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(7, game.getLevelLogic().getHero().getCoordY());
		
		game.setMap(newmap);
		
		game.getMap().updateMap(1, 4, ' ');
		
		game.getMap().updateMap(1, 6, 'O');
		
		game.getMap().updateMap(1, 3, ' ');
	
		game.getMap().updateMap(1, 5, '*');
	
		
		game.getLevelLogic().getOgre().setcoordX(6);
		game.getLevelLogic().getClub().setcoordX(5);
		game.getLevelLogic().getOgre().setBlocker(true);
		
		boolean outcome1 = false, outcome2 = false, outcome3 = false;
		
		Map map;
		
		while (!outcome1 || !outcome2 || !outcome3)
		{
			map =  game.updateGame('s');
			
			 
			//left
			if(game.getLevelLogic().getClub().getRand() == 0) {
				assertEquals(5, game.getLevelLogic().getClub().getcoordX());
				assertEquals(1, game.getLevelLogic().getClub().getcoordY());
				assertEquals('*', game.getLevelLogic().getClub().getID());
				assertEquals ('k', map.getMatrix()[game.getLevelLogic().getKeyCoordY()][game.getLevelLogic().getKeyCoordX()]);
				outcome1 = true;
			}
			
			//right
			else if(game.getLevelLogic().getClub().getRand() == 1) {
				assertEquals(7, game.getLevelLogic().getClub().getcoordX());
				assertEquals(1, game.getLevelLogic().getClub().getcoordY());
				assertEquals('$', game.getLevelLogic().getClub().getID());
				assertEquals ('$', map.getMatrix()[game.getLevelLogic().getKeyCoordY()][game.getLevelLogic().getKeyCoordX()]);
				outcome2 = true;
			}
			
			//down
			else if(game.getLevelLogic().getClub().getRand() == 2) {
				assertEquals(6, game.getLevelLogic().getClub().getcoordX());
				assertEquals(2, game.getLevelLogic().getClub().getcoordY());
				assertEquals('*', game.getLevelLogic().getClub().getID());
				assertEquals ('k', map.getMatrix()[game.getLevelLogic().getKeyCoordY()][game.getLevelLogic().getKeyCoordX()]);
				outcome3 = true;
			}
		}
	}

//	public void testOgre()
//	{
//		Game game = new Game(1);
//		char [][] map;
//		Map newmap = new Map(0);
//		newmap.setmap(2);
//		game.getLevelLogic().getClub().setBlocker(true);
//		
//		//hero moves into next level
//		game.updateGame('s');
//		game.updateGame('s');
//		game.updateGame('a');
//		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
//		assertEquals(7, game.getLevelLogic().getHero().getCoordY());
//		
//		try {
//			game.getMap().setMap(1, 5, 'X');
//		} catch (IllegalMapChangeException e) {
//		}
//		
//		try {
//			game.getMap().setMap(2, 4, 'X');
//		} catch (IllegalMapChangeException e) {
//		}
//
//		int n = 15;
//		
//		while (n > 0)
//		{	
//			game.updateGame('s');
//			assertEquals(4 , game.getLevelLogic().getOgre().getcoordX());
//			assertEquals(1, game.getLevelLogic().getOgre().getcoordY());
//			n--;
//		}
//	}
}

