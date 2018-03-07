package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.IllegalMapChangeException;
import dkeep.logic.Map;


public class TestDungeonGameLogic {
	
	//TASK 1
	
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

	//TASK 2
	
	@Test
	public void testMoveHeroIntoOgre() 
	{
		Game game = new Game(1);
		char [][] map;
		game.getOgre().setBlocker(true);
		game.getClub().setBlocker(true);
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
		game.updateGame('w');
		game.updateGame('w');
		assertEquals('8', game.getOgre().getID());
		assertEquals(2, game.getOgre().getStunCounter());
		
	}
	
	@Test
	public void testMoveHeroIntoKey()
	{
		Game game = new Game(1);
		char [][] map;
		game.getOgre().setBlocker(true);
		game.getClub().setBlocker(true);
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
		game.getOgre().setBlocker(true);
		game.getClub().setBlocker(true);
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
		game.getOgre().setBlocker(true);
		game.getClub().setBlocker(true);
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
		game.getOgre().setBlocker(true);
		game.getClub().setBlocker(true);
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
	
	//TASK 3
	
	@Test(timeout=1000)
	public void testSomeRandomBehaviour() {
		
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
		
		//outcome1: Ogre moves left.
		//outcome2: Ogre moves right.
		//outcome3: Ogre moves up.
		//outcome4: Ogre moves down.
		
		boolean outcome1 = false, outcome2 = false, outcome3 = false, outcome4 = false;
		
		while(!outcome1 || !outcome2 || !outcome3 || !outcome4) {
			//Hero moves into a wall for the time being
			
			int coordX = game.getOgre().getcoordX();
			int coordY = game.getOgre().getcoordY();
			
			game.updateGame('s');
			
			if(game.getOgre().getRand() == 0) {
				assertEquals(coordX - 1 , game.getOgre().getcoordX());
				assertEquals(coordY, game.getOgre().getcoordY());
				outcome1 = true;
			}
			
			else if(game.getOgre().getRand() == 1) {
				assertEquals(coordX + 1, game.getOgre().getcoordX());
				assertEquals(coordY, game.getOgre().getcoordY());
				outcome2 = true;
			}
			
			else if(game.getOgre().getRand() == 3) {
				assertEquals(coordX, game.getOgre().getcoordX());
				assertEquals(coordY-1, game.getOgre().getcoordY());
				outcome3 = true;
			}
			
			else if(game.getOgre().getRand() == 2) {
				assertEquals(coordX, game.getOgre().getcoordX());
				assertEquals(coordY + 1, game.getOgre().getcoordY());
				outcome4 = true;
			}
			
			else {
				fail("Some error message.");
			}
		}
	}
	
	@Test(timeout=1000)
	public void testSomeClubBehaviour() {
		Game game = new Game(1);
		char [][] map;
		Map newmap = new Map(0);
		newmap.setmap(2);
		
		//hero moves into next level
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(7, game.getHero().getCoordY());
		
		game.setMap(newmap);
		
		try {
			game.getMap().setMap(1, 4, ' ');
		} catch (IllegalMapChangeException e) {
			// TODO Auto-generated catch bloco
		}
		
		try {
			game.getMap().setMap(2, 4, 'O');
		} catch (IllegalMapChangeException e) {
			// TODO Auto-generated catch block
		}
		
		try {
			game.getMap().setMap(1, 3, ' ');
		} catch (IllegalMapChangeException e) {
			// TODO Auto-generated catch block
		}
		
		try {
			game.getMap().setMap(2, 3, '*');
		} catch (IllegalMapChangeException e) {
			// TODO Auto-generated catch block
		}
		
		game.getOgre().setcoordY(2);
		game.getClub().setcoordY(2);
		game.getOgre().setBlocker(true);
		
		//outcome1: Club moves left.
		//outcome2: Club moves right.
		//outcome3: Club moves up.
		//outcome4: Club moves down.
		
		boolean outcome1 = false, outcome2 = false, outcome3 = false, outcome4 = false;
		
		while(!outcome1 || !outcome2 || !outcome3 || !outcome4) {
			//Hero moves into a wall for the time being
			
			game.updateGame('s');
			
			if(game.getClub().getRand() == 0) {
				System.out.println("left.");
				assertEquals(3, game.getClub().getcoordX());
				assertEquals(2, game.getClub().getcoordY());
				outcome1 = true;
			}
			
			else if(game.getClub().getRand() == 1) {
				System.out.println("right.");
				assertEquals(5, game.getClub().getcoordX());
				assertEquals(2, game.getClub().getcoordY());
				outcome2 = true;
			}
			
			else if(game.getClub().getRand() == 3) {
				System.out.println("up.");
				assertEquals(4, game.getClub().getcoordX());
				assertEquals(1, game.getClub().getcoordY());
				outcome3 = true;
			}
			
			else if(game.getClub().getRand() == 2) {
				System.out.println("down");
				assertEquals(4, game.getClub().getcoordX());
				assertEquals(3, game.getClub().getcoordY());
				outcome4 = true;
			}
			
			else {
				fail("Some error message.");
			}
		}
	}
}

