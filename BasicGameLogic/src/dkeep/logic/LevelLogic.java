package dkeep.logic;

import java.util.ArrayList;
import java.awt.Point;

/**
 * Interface responsible for the logic of the levels.
 * 
 * Has functions to get all the components of the levels, to set a few of them, to open the exit doors and to update the game.
 *
 */
public interface LevelLogic {

	/**
	 * Updates the game by calling all existing characters' moving functions, checking if the level is over (either if the hero gets caught or if the hero wins), managing the key/level visibility and opening the exit doors when needed 
	 * 
	 * @param heroMovement char which represents the hero's movement direction in this shift
	 * @param map current {@link Map}
	 */
	void updateGame (char heroMovement, Map map);
	
	/**
	 * Opens the correct exit doors
	 * 
	 * @param map current {@link Map}
	 */
	void openExitDoor (Map map);
	
	/**
	 * Sets the level's {@link Hero}
	 * 
	 * @param hero new {@link Hero}
	 */
	public void setHero (Hero hero);
	
	/**
	 * Sets the key's coordinates
	 * 
	 * @param keyCoords new key's coordinates
	 */
	public void setKeyCoords (Point keyCoords);
	
	/**
	 * Sets the exit doors
	 * 
	 * @param exitDoors new ArrayList of exitDoors
	 */
	public void setExitDoors (ArrayList <Point> exitDoors);
	
	/**
	 * 
	 * @return type of the current level
	 */
	public String getLevelType();
	/**
	 * 
	 * @return state of the current level
	 */
	public String getLevelState();
	/**
	 * 
	 * @return {@link Hero}
	 */
	public Hero getHero();
	/**
	 * 
	 * @return {@link Guard}
	 */
	public Guard getGuard();
	/**
	 * 
	 * @return first {@link Ogre} of the ArrayList
	 */
	public Ogre getOgre();
	/**
	 * 
	 * @return first {@link Club} of the ArrayList
	 */
	public Club getClub();
	/**
	 * 
	 * @return the X coordinate of key
	 */
	public int getKeyCoordX();
	/**
	 * 
	 * @return the Y coordinate of key
	 */
	public int getKeyCoordY();
	/**
	 * 
	 * @return ArrayList with all exit doors
	 */
	public ArrayList <Point> getExitDoors ();
}
