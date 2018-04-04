package dkeep.logic;

import java.io.Serializable;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Class responsible for updating guard level.
 * 
 * Calls hero's and guard's movement functions, checks if hero gets caught by the guard and updates the game map
 */
public class GuardLevel implements LevelLogic, Serializable{

	Hero hero;
	Guard guard;
	Point keyCoords;
	ArrayList <Point> exitDoors;
	String levelState;
		
	/**
	 * Constructor
	 * 
	 * @param guard {@link Guard}
	 */
	public GuardLevel(Guard guard)
	{
		this.guard = guard;
		levelState = "Running";
	}
	
	@Override
	public void updateGame(char heroMovement, Map map) {
		
		int heroMovementReturn = 0;

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

		if (guard.getMovementBlocker() == false) {

			if (checkIfHeroGetsCaughtByGuard(map)) {

				levelState = "Over";
				return;
			}

			callGuardMovement (map);
			
			if (checkIfHeroGetsCaughtByGuard(map)) {

				levelState = "Over";
				return;
			}
		}
		return;		
	}

	/**
	 * Checks if hero gets caught by guard. If he does, the game ends
	 * 
	 * @param map current {@link Map}
	 * @return true if hero gets caught, otherwise returns false
	 */
	public boolean checkIfHeroGetsCaughtByGuard(Map map)
	{
		if (guard.getID() != 'g' && (map.getMatrix()[guard.coordY - 1][guard.coordX] == hero.id
				|| map.getMatrix()[guard.coordY + 1][guard.coordX] == hero.id
				|| map.getMatrix()[guard.coordY][guard.coordX - 1] == hero.id
				|| map.getMatrix()[guard.coordY][guard.coordX + 1] == hero.id))
			return true;

		return false;
	}

	/**
	 * Makes lever visible when no character is on top of it
	 * 
	 * @param map current {@link Map}
	 */
	public void manageLeverVisibility (Map map)
	{
		if (map.getMatrix()[(int) keyCoords.getY()][(int) keyCoords.getX()] == ' ') {
			map.updateMap( (int)keyCoords.getY(), (int)keyCoords.getX(), 'k');
		}
	}
	
	/**
	 * Calls the correct guard movement function according to his personality
	 * 
	 * @param map current {@link Map}
	 */
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
	public void setHero(Hero hero) {
		this.hero = hero;
	}

	@Override
	public void setKeyCoords(Point keyCoords) {	
		this.keyCoords = keyCoords;
	}

	@Override
	public void setExitDoors(ArrayList<Point> exitDoors) {
		this.exitDoors = exitDoors;
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
	/**
	 * @return null
	 */
	@Override
	public Ogre getOgre() {
		return null;
	}
	/**
	 * @return null
	 */
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
