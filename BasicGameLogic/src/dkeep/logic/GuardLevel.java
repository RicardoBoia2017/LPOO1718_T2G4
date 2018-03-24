package dkeep.logic;

import java.io.Serializable;

public class GuardLevel implements LevelLogic, Serializable{

	Hero hero;
	Guard guard;
	int keyCoordX;
	int keyCoordY;
	String levelState;
	
	public GuardLevel(String guardPersonality)
	{
		hero = new Hero(1,1);
		guard = new Guard (8,1,guardPersonality);
		keyCoordX = 7;
		keyCoordY = 8;	
		levelState = "Running";
	}
	
	public GuardLevel(Hero hero, Guard guard, int [] keyCoords)
	{
		this.hero = hero;
		this.guard = guard;
		this.keyCoordX = keyCoords[0];
		this.keyCoordY = keyCoords[1];
	}
	
	@Override
	public char[][] updateGame(char heroMovement, Map map) {
		
		int heroMovementReturn = 0;

		// hero phase
		try {
			heroMovementReturn = hero.move(map, heroMovement, 1);
		}

		catch (IllegalMapChangeException e) {
			System.out.println("Excecao mov hero");
		}
		
		if (heroMovementReturn == 1) {

			levelState = "Passed";
//				System.out.println(" ");
//				System.out.println("Now you went up the stairs, new stage.");  FOR LEVEL2
//				System.out.println(ogre.length + " ogre(s).");
//				System.out.println(" ");

				// you went up the stairs, now a new level must begin.

				// update game stage
//				hero.setcoordY(7);
//				hero.setid('A');
//				keycoordX = 7;
//				keycoordY = 1;
//
//				stage = 2;
//				map.setmap(2); // change to second map
//
//				return map.getmap();
		}
		
		if (map.getmap()[keyCoordY][keyCoordX] == ' ') {
			map.setMap(keyCoordY, keyCoordX, 'k');
		}

		// guard phase, he will only move in a given pattern according to
		// the array guardpositon.

		if (guard.getMovementBlocker() == false) {

			if (checkIfHeroGetsCaughtByGuard(map)) {

				// pass interface game over state, interface will print.

				System.out.println("Game Over.");
				levelState = "Over";
//				gameState = "Over";

				return map.getmap();
			}

			switch (guard.personality) {

			case "Rookie":
				try {
					guard.rookieMove(map);
				} catch (IllegalMapChangeException e) {
				}
				break;

			case "Drunken":
				try {
					guard.drunkenMove(map);
				} catch (IllegalMapChangeException e) {
				}
				break;

			case "Suspicious":
				try {
					guard.suspiciousMove(map);
				} catch (IllegalMapChangeException e) {
				}
				break;
			}

			if (checkIfHeroGetsCaughtByGuard(map)) {

				// pass interface game over state, interface will print.
				System.out.println("");
				System.out.println("Game Over.");

				levelState = "Over";

				return map.getmap();
			}

			// end of a turn of stage 1, by now the map has the current
			// state
			// and hero and guard have both moved and checked for collision.
		}
		return map.getmap();		
	}
	
	public boolean checkIfHeroGetsCaughtByGuard(Map map)
	{
		if (guard.getID() != 'g' && (map.getmap()[guard.coordY - 1][guard.coordX] == hero.id
				|| map.getmap()[guard.coordY + 1][guard.coordX] == hero.id
				|| map.getmap()[guard.coordY][guard.coordX - 1] == hero.id
				|| map.getmap()[guard.coordY][guard.coordX + 1] == hero.id))
			return true;

		return false;
	}

	
	@Override
	public String getLevelState() {
		return levelState;
	}

	@Override
	public Hero getHero() {
		return hero;
	}
	@Override
	public int getKeyCoordX() {
		return keyCoordX;
	}
	@Override
	public int getKeyCoordY() {
		return keyCoordY;
	}

	@Override
	public Guard getGuard() {
		return guard;
	}

	@Override
	public Ogre getOgre() {
		return null;
	}

	@Override
	public Club getClub() {
		return null;
	}
}
