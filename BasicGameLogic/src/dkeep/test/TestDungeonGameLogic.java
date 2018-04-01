package dkeep.test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.Guard;
import dkeep.logic.GuardLevel;
import dkeep.logic.Hero;
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
		
		assertEquals(3, game.getLevelLogic().getGuard().getCoordX());
		assertEquals(1, game.getLevelLogic().getGuard().getCoordY());
		
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
	public void testOgreRandomBehaviour() {
		
		int coordX, coordY;
		
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
		
		boolean outcome1 = false, outcome2 = false, outcome3 = false, outcome4 = false, outcome5 = false;
		
		while(!outcome1 || !outcome2 || !outcome3 || !outcome4 || !outcome5) {
			//Hero moves into a wall for the time being
			
			coordX = game.getLevelLogic().getOgre().getCoordX();
			coordY = game.getLevelLogic().getOgre().getCoordY();
			
			game.updateGame('s');
						
			if(game.getLevelLogic().getOgre().getRand() == 0 ) {
				if (game.getLevelLogic().getOgre().getID() != '8')
					assertEquals(coordX - 1, game.getLevelLogic().getOgre().getCoordX());
					assertEquals(coordY, game.getLevelLogic().getOgre().getCoordY());
					outcome1 = true;
			}
			
			else if(game.getLevelLogic().getOgre().getRand() == 1 ) {
				if (game.getLevelLogic().getOgre().getID() != '8')
				{
					assertEquals(coordX + 1, game.getLevelLogic().getOgre().getCoordX());
					assertEquals(coordY, game.getLevelLogic().getOgre().getCoordY());
					outcome2 = true;
				}
			}
			
			else if(game.getLevelLogic().getOgre().getRand() == 3) {
				if (game.getLevelLogic().getOgre().getID() != '8')
				{
					assertEquals(coordX, game.getLevelLogic().getOgre().getCoordX());
					assertEquals(coordY - 1, game.getLevelLogic().getOgre().getCoordY());
					outcome3 = true;
				}
			}
			
			else if(game.getLevelLogic().getOgre().getRand() == 2) {
				if (game.getLevelLogic().getOgre().getID() != '8')
				{
					assertEquals(coordX, game.getLevelLogic().getOgre().getCoordX());
					assertEquals(coordY + 1, game.getLevelLogic().getOgre().getCoordY());
					outcome4 = true;
				}
			}
			
			else if(game.getLevelLogic().getOgre().getRand() == -1) {
				if (game.getLevelLogic().getOgre().getID() != '8')
				{
					assertEquals(coordX, game.getLevelLogic().getOgre().getCoordX());
					assertEquals(coordY, game.getLevelLogic().getOgre().getCoordY());
					outcome5 = true;
				}
			}
			
			else {
				fail("Some error message.");
			}
		}
	}
	
	@Test(timeout=3000)
	public void testClubRandomBehaviour() {
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
				assertEquals(3, game.getLevelLogic().getClub().getCoordX());
				assertEquals(2, game.getLevelLogic().getClub().getCoordY());
				outcome1 = true;
			}
			
			//right
			else if(game.getLevelLogic().getClub().getRand() == 1) {
				assertEquals(5, game.getLevelLogic().getClub().getCoordX());
				assertEquals(2, game.getLevelLogic().getClub().getCoordY());
				outcome2 = true;
			}
			
			//up
			else if(game.getLevelLogic().getClub().getRand() == 3) {
				assertEquals(4, game.getLevelLogic().getClub().getCoordX());
				assertEquals(1, game.getLevelLogic().getClub().getCoordY());
				outcome3 = true;
			}
			
			//down
			else if(game.getLevelLogic().getClub().getRand() == 2) {
				assertEquals(4, game.getLevelLogic().getClub().getCoordX());
				assertEquals(3, game.getLevelLogic().getClub().getCoordY());
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
				assertEquals(5, game.getLevelLogic().getClub().getCoordX());
				assertEquals(1, game.getLevelLogic().getClub().getCoordY());
				assertEquals('*', game.getLevelLogic().getClub().getID());
				assertEquals ('k', map.getMatrix()[game.getLevelLogic().getKeyCoordY()][game.getLevelLogic().getKeyCoordX()]);
				outcome1 = true;
			}
			
			//right
			else if(game.getLevelLogic().getClub().getRand() == 1) {
				assertEquals(7, game.getLevelLogic().getClub().getCoordX());
				assertEquals(1, game.getLevelLogic().getClub().getCoordY());
				assertEquals('$', game.getLevelLogic().getClub().getID());
				assertEquals ('$', map.getMatrix()[game.getLevelLogic().getKeyCoordY()][game.getLevelLogic().getKeyCoordX()]);
				outcome2 = true;
			}
			
			//down
			else if(game.getLevelLogic().getClub().getRand() == 2) {
				assertEquals(6, game.getLevelLogic().getClub().getCoordX());
				assertEquals(2, game.getLevelLogic().getClub().getCoordY());
				assertEquals('*', game.getLevelLogic().getClub().getID());
				assertEquals ('k', map.getMatrix()[game.getLevelLogic().getKeyCoordY()][game.getLevelLogic().getKeyCoordX()]);
				outcome3 = true;
			}
		}
	}
	
	
	@Test
	public void testRookieGuardFollowsPath() {
		Game game = new Game(0);
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(1, game.getLevelLogic().getHero().getCoordY());
		assertEquals(8, game.getLevelLogic().getGuard().getCoordX());
		assertEquals(1, game.getLevelLogic().getGuard().getCoordY());
		
 		int i = 0;
	
		while(i < 20) {
			game.updateGame('a');
			assertEquals(7, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(1, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(7, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(2, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(7, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(3, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(7, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(4, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(7, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(5, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(6, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(5, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(5, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(5, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(4, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(5, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(3, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(5, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(2, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(5, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(1, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(5, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(1, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(6, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(2, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(6, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(3, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(6, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(4, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(6, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(5, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(6, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(6, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(6, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(7, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(6, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(8, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(6, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(8, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(5, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(8, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(4, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(8, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(3, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(8, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(2, game.getLevelLogic().getGuard().getCoordY());
			game.updateGame('a');
			assertEquals(8, game.getLevelLogic().getGuard().getCoordX());
			assertEquals(1, game.getLevelLogic().getGuard().getCoordY());
			
			i++;
		}
	}
	

	@Test
	public void testDrunkenGuardFallAsleep() {
		Game game = new Game(1, "Drunken");
		assertEquals("Drunken", game.getLevelLogic().getGuard().getPersonality());
		
		int oldcoordX, oldcoordY, newcoordX, newcoordY;
		
		while(true) {
			oldcoordX =  game.getLevelLogic().getGuard().getCoordX();
			oldcoordY = game.getLevelLogic().getGuard().getCoordY();
			
			game.updateGame('a');
			
			newcoordX = game.getLevelLogic().getGuard().getCoordX();
			newcoordY = game.getLevelLogic().getGuard().getCoordY();
			
			if(newcoordX == oldcoordX && newcoordY == oldcoordY) {
				assertEquals('g', game.getLevelLogic().getGuard().getID());
				break;
			} 
			
			else {
				assertEquals('G', game.getLevelLogic().getGuard().getID());
			}
		}
	}
	
	@Test
	public void testOgreStepsintoKey() {
		Game game = new Game(1);
		Map newmap = new Map(0);
		newmap.setMap(2);
		
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(7, game.getLevelLogic().getHero().getCoordY());
		
		game.getLevelLogic().getClub().setBlocker(true);
		
		Map map;
		
		int  coordX, coordY;
		
		
		while (true)
		{
			map =  game.updateGame('s');
			
			if(game.getLevelLogic().getOgre().getCoordX() == game.getLevelLogic().getKeyCoordX() && game.getLevelLogic().getOgre().getCoordY() == game.getLevelLogic().getKeyCoordY()) {
				assertEquals('$', game.getLevelLogic().getOgre().getID());
				assertEquals ('$', map.getMatrix()[game.getLevelLogic().getKeyCoordY()][game.getLevelLogic().getKeyCoordX()]);
				break;
			} 
			
			else {
				assertEquals ('k', map.getMatrix()[game.getLevelLogic().getKeyCoordY()][game.getLevelLogic().getKeyCoordX()]);
			}
		}
	}
	
	@Test
	public void testifHeroLoosesNextoSleepingGuard() {
		Game game = new Game(0);
		
		game.getLevelLogic().getGuard().setID('g');
		
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('s');
		
		while(true) {
			
			game.updateGame('a');
			if(game.getMap().getMatrix()[game.getLevelLogic().getGuard().getCoordY()][game.getLevelLogic().getGuard().getCoordX() - 1] == game.getLevelLogic().getHero().getID()) {
				assertEquals("Running", game.getLevelLogic().getLevelState());
				break;
			}
		}
	}
	
	@Test
	public void testOgremoveintoOgre() {
		Game game = new Game(1);
		
		game.updateGame('s');
		game.updateGame('s');
		game.updateGame('a');
		assertEquals(1, game.getLevelLogic().getHero().getCoordX());
		assertEquals(7, game.getLevelLogic().getHero().getCoordY());
		
		game.getMap().updateMap(1, 5, 'O');
		game.getMap().updateMap(2, 4, 'O');
		
		game.updateGame('s');
			
		switch (game.getLevelLogic().getOgre().getRand())
		{
			case 1:
			{
				assertEquals(5, game.getLevelLogic().getOgre().getCoordX());
				assertEquals(1, game.getLevelLogic().getOgre().getCoordY());
				break;
			}
			
			case 2:
			{
				assertEquals(4, game.getLevelLogic().getOgre().getCoordX());
				assertEquals(2, game.getLevelLogic().getOgre().getCoordY());
				break;
			}
		}
	}
	
	@Test
	public void testPersonalizedMapConstructor()
	{
		int width = 10;
		int height = 10;
		
		Map map = new Map (width,height);
		
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				if (i == 0 || i == width -1)
				{
					assertEquals ('X', map.getMatrix() [j][i]);
				}
				
				else if (j == 0 || j == height - 1)
				{
					assertEquals ('X', map.getMatrix() [j][i]);
				}
				
				else
				{
					assertEquals (' ', map.getMatrix() [j][i]);
				}
			}
		}
	}
/*	@Test
	public void testInvertPath() {
		//TODO:
		fail("not done yet");
	}
	
	@Test
	public void testGuardAwake() {
		//TODO:
		fail("not done yet");
	}
	
	@Test
	public void testifUpdateGame(){
		//TODO:
		fail("not done yet");
	}
	*/
	
	@Test
	public void testMakesTestGame() {
		Game game = new Game(1);
		
		assertEquals(game.getMap().getCurrentMap(), -1);
		
		assertEquals(game.getLevelLogic().getExitDoors().get(0).x, 0);
		assertEquals(game.getLevelLogic().getExitDoors().get(0).y, 2);
		
		assertEquals(game.getLevelLogic().getExitDoors().get(1).x, 0);
		assertEquals(game.getLevelLogic().getExitDoors().get(1).y, 3);
		
		assertEquals(game.getLevelLogic().getHero().getCoordX(), 1);
		assertEquals(game.getLevelLogic().getHero().getCoordY(), 1);
		
		assertEquals(game.getLevelLogic().getLevelState(), "Running");
		
		assertEquals(game.getLevelLogic().getGuard().getPersonality(), "Rookie");
		assertEquals(game.getLevelLogic().getGuard().getCoordX(), 3);
		assertEquals(game.getLevelLogic().getGuard().getCoordY(), 1);
		assertEquals(game.getLevelLogic().getKeyCoordX(), 1);
		assertEquals(game.getLevelLogic().getKeyCoordY(), 3);
		
		assertEquals(game.getNumberOfOgres(), 1);
	}
	
	@Test
	public void testMakesNormalGame() {
		Game game = new Game(0);
		
		assertEquals(game.getMap().getCurrentMap(), 1);
		
		assertEquals(game.getLevelLogic().getExitDoors().get(0).x, 0);
		assertEquals(game.getLevelLogic().getExitDoors().get(0).y, 5);
		
		assertEquals(game.getLevelLogic().getExitDoors().get(1).x, 0);
		assertEquals(game.getLevelLogic().getExitDoors().get(1).y, 6);
		
		assertEquals(game.getLevelLogic().getHero().getCoordX(), 1);
		assertEquals(game.getLevelLogic().getHero().getCoordY(), 1);
		
		assertEquals(game.getLevelLogic().getGuard().getPersonality(), "Rookie");
		assertEquals(game.getLevelLogic().getGuard().getCoordX(), 8);
		assertEquals(game.getLevelLogic().getGuard().getCoordY(), 1);
		assertEquals(game.getLevelLogic().getKeyCoordX(), 7);
		assertEquals(game.getLevelLogic().getKeyCoordY(), 8);
		
		assertEquals(game.getLevelLogic().getLevelState(), "Running");
		
		assert(game.getNumberOfOgres() >= 1 && game.getNumberOfOgres() <= 3);
	}
	
	@Test
	public void testMakesCustomGame() {
		char[][] empty = {{'H', 'k', 'I', 'O', '*'}};
		
		Game game = new Game(empty);
		
		assertEquals(game.getMap().getCurrentMap(), 3);
		assertEquals(game.getMap().getMatrix().length, empty.length);
		
		for(int i = 0; i < game.getMap().getMatrix().length; i++) {
			for(int j = 0; j < game.getMap().getMatrix().length; j++) {
				assertEquals(game.getMap().getMatrix()[i][j], empty[i][j]);
			}
		}
		
		assertEquals(game.getLevelLogic().getOgre().getCoordX(), 3);
		assertEquals(game.getLevelLogic().getOgre().getCoordY(), 0);
		
		assertEquals(game.getLevelLogic().getHero().getCoordX(), 0);
		assertEquals(game.getLevelLogic().getHero().getCoordY(), 0);
		
		assertEquals(game.getLevelLogic().getKeyCoordX(), 1);
		assertEquals(game.getLevelLogic().getKeyCoordY(), 0);
		
		assertEquals(game.getLevelLogic().getClub().getCoordX(), 4);
		assertEquals(game.getLevelLogic().getClub().getCoordY(), 0);
		
		assertEquals(game.getLevelLogic().getExitDoors().get(0).x, 2);
		assertEquals(game.getLevelLogic().getExitDoors().get(0).y, 0);
	}
	
	@Test
	public void testMakesGameWithInput() {
		
		Game game = new Game(3, "Drunken");
		
		assertEquals(game.getMap().getCurrentMap(), 1);
		
		assertEquals(game.getLevelLogic().getKeyCoordX(), 7);
		assertEquals(game.getLevelLogic().getKeyCoordY(), 8);
		
		assertEquals(game.getLevelLogic().getExitDoors().get(0).x, 0);
		assertEquals(game.getLevelLogic().getExitDoors().get(0).y, 5);
		
		assertEquals(game.getLevelLogic().getExitDoors().get(1).x, 0);
		assertEquals(game.getLevelLogic().getExitDoors().get(1).y, 6);
		
		assertEquals(game.getLevelLogic().getHero().getCoordX(), 1);
		assertEquals(game.getLevelLogic().getHero().getCoordY(), 1);
		
		assertEquals(game.getLevelLogic().getGuard().getCoordX(), 8);
		assertEquals(game.getLevelLogic().getGuard().getCoordY(), 1);
		
		assertEquals(game.getLevelLogic().getGuard().getPersonality(), "Drunken");
		
		assertEquals(game.getNumberOfOgres(), 3);
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
	
	//FALTA -> OGRE STEPS INTO KEY, GUARD INVERTS(Suspicious and drunken), GUARD FALLS ASLEEP(Drunken).
}

