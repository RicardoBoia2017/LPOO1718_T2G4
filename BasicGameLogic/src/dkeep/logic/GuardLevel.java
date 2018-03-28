package dkeep.logic;

import java.io.Serializable;

public class GuardLevel implements LevelLogic, Serializable{

	Hero hero;
	Guard guard;
	int []keyCoords = new int[2];
	String levelState;
	
	public GuardLevel(String guardPersonality)
	{
		hero = new Hero(1,1);
		guard = new Guard (8,1,guardPersonality);
		keyCoords[0] = 7;
		keyCoords[1] = 8;	
		levelState = "Running";
	}
	
	public GuardLevel(Hero hero, Guard guard, int [] keyCoords)
	{
		this.hero = hero;
		this.guard = guard;
		this.keyCoords[0] = keyCoords[0];
		this.keyCoords[1] = keyCoords[1];
		levelState = "Running";
	}
	
	@Override
	public void updateGame(char heroMovement, Map map) {
		
		int heroMovementReturn = 0;

		// hero phase
		try {
			heroMovementReturn = hero.move(map, heroMovement, getLevelType());
		}

		catch (IllegalMapChangeException e) {
			System.out.println("Excecao mov hero");
		}
		
		if (heroMovementReturn == 1) {

			levelState = "Passed";
			return;
		}
		
		manageLeverVisibility(map);

		// guard phase, he will only move in a given pattern according to
		// the array guardpositon.

		if (guard.getMovementBlocker() == false) {

			if (checkIfHeroGetsCaughtByGuard(map)) {

				// pass interface game over state, interface will print.

				System.out.println("Game Over.");
				levelState = "Over";
				return;
			}

			callGuardMovement (map);
			
			if (checkIfHeroGetsCaughtByGuard(map)) {

				// pass interface game over state, interface will print.
				System.out.println("");
				System.out.println("Game Over.");

				levelState = "Over";
				return;
			}

			// end of a turn of stage 1, by now the map has the current
			// state
			// and hero and guard have both moved and checked for collision.
		}
		return;		
	}

	public boolean checkIfHeroGetsCaughtByGuard(Map map)
	{
		if (guard.getID() != 'g' && (map.getMatrix()[guard.coordY - 1][guard.coordX] == hero.id
				|| map.getMatrix()[guard.coordY + 1][guard.coordX] == hero.id
				|| map.getMatrix()[guard.coordY][guard.coordX - 1] == hero.id
				|| map.getMatrix()[guard.coordY][guard.coordX + 1] == hero.id))
			return true;

		return false;
	}

	public void manageLeverVisibility (Map map)
	{
		if (map.getMatrix()[keyCoords[1]][keyCoords[0]] == ' ') {
			map.updateMap(keyCoords[1], keyCoords[0], 'k');
		}
	}
	
	public void callGuardMovement (Map map)
	{
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
		return keyCoords[0];
	}
	@Override
	public int getKeyCoordY() {
		return keyCoords[1];
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
	@Override
	public String getLevelType() {
		return "Guard";
	}
}
