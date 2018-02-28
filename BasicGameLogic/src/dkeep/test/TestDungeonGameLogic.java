package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.Map;


public class TestDungeonGameLogic {

	@Test
	public void testMoveHeroIntoFreeCell()
	{
		Map map = new Map(1);
		Game game = new Game(1);
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
		game.updateGame('s');
		assertEquals(1, game.getHero().getCoordX());
		assertEquals(1, game.getHero().getCoordY());
	}

}

