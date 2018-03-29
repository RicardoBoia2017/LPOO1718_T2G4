package dkeep.logic;

import java.io.Serializable;
import java.awt.Point;
import java.util.ArrayList;

public class GuardLevel implements LevelLogic, Serializable{

	Hero hero;
	Guard guard;
	Point keyCoords;
	ArrayList <Point> exitDoors;
	String levelState;
	
	public GuardLevel(String guardPersonality, ArrayList <Point> exitDoors)
	{
		hero = new Hero(1,1);
		guard = new Guard (8,1,guardPersonality);
		keyCoords = new Point (7,8);
//		keyCoords[0] = 7;
//		keyCoords[1] = 8;	
		this.exitDoors = exitDoors;
		levelState = "Running";
	}
	
	public GuardLevel(Hero hero, Guard guard, Point keyCoords, ArrayList <Point> exitDoors)
	{
		this.hero = hero;
		this.guard = guard;
		this.keyCoords = keyCoords;
		this.exitDoors = exitDoors;
		levelState = "Running";
	}
	
	@Override
	public void updateGame(char heroMovement, Map map) {
		
		int heroMovementReturn = 0;

		// hero phase
		try {
			heroMovementReturn = hero.move(map, heroMovement, this);
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
		if (map.getMatrix()[(int) keyCoords.getY()][(int) keyCoords.getX()] == ' ') {
			map.updateMap( (int)keyCoords.getY(), (int)keyCoords.getX(), 'k');
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
	public void openExitDoor(Map map)
	{
		for (Point elem: exitDoors)
		{
			map.updateMap ( (int)elem.getY(), (int) elem.getX(), 'S');
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
		return (int) keyCoords.getX();
	}
	@Override
	public int getKeyCoordY()
	{
		return (int) keyCoords.getY();
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

	public ArrayList<Point> getExitDoors() {
		return exitDoors;
	}


}
