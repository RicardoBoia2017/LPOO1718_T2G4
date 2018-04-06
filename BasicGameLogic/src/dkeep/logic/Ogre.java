package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * 
 * The following class represents the Ogre character, including methods of how to move, the interactions it has with
 * other elements, gets and sets.
 *
 */
public class Ogre extends Character {

	private int stun_counter;
	private int randHolder;
	boolean movementBlocker;
	
	/**
	 * Ogre constructor
	 * 
	 * @param x initial X coordinate
	 * @param y initial X coordinate
	 */
	public Ogre(int x, int y) {
		super(x, y, 'O');
		stun_counter = 0;
		movementBlocker = false;
	}
	
	/**
	 * If ogre isn't stunned, calls auxiliary function with values according to the direction of his movement
	 * 
	 * @param map current {@link Map}
	 * @param level current level
	 * @throws IllegalMapChangeException  Exception implemented to make sure Characters don't go through walls
	 */
	public void move(Map map, LevelLogic level) throws IllegalMapChangeException {
		
		if (manageStun())
			return;
		
		int nTries = 8;
		boolean Moved = false;
		
		while (nTries > 0) {
			Random randomnum = new Random();
			
			int random = randomnum.nextInt(4);
			
			switch (random) {

			// left
			case 0: {

				if ( moveAux (map, coordX-1,coordY) ) {
					Moved = true;
					randHolder = 0;
				}
				
				break;
			}

			// right
			case 1: {

				if ( moveAux (map, coordX+1,coordY) ) {
					Moved = true;
					randHolder = 1;
				}
			
				break;
			}

			// down
			case 2: {

				if ( moveAux (map, coordX,coordY + 1) )	{
					Moved = true;
					randHolder = 2;
				}
				
				break;
			}

			// up
			case 3: {
				
				if ( moveAux (map, coordX,coordY - 1) ) {
					Moved = true;
					randHolder = 3;
				}
				
				break;
			}
			
			}
			
			if (Moved)
				break;
		
			nTries--;
		}
		
		if (!Moved)
			randHolder = -1;
				
		checkIfOgreIsInKey(map, level);
	}

	/**
	 * Controls ogre's stun
	 * 
	 * @return true if ogre is stunned, otherwise returns false
	 */
	private boolean manageStun ()
	{
		if (stun_counter == 2){
			stun_counter--;
			return true;
		}
		
		else if (stun_counter == 1)
		{
			stun_counter --;
			return true;
		}
		
		return false;
	}
	
	/**
	 * Auxiliary function that changes the map according to the ogre's movement
	 * 
	 * @param map current {@link Map}
	 * @param valueX X coordinate of Ogre's new position
	 * @param valueY Y coordinate of Ogre's new position
	 * @return true if Ogre changed his position, otherwise returns false
	 */
	public boolean moveAux(Map map, int valueX, int valueY)
	{
		if (map.getMatrix()[valueY][valueX] == ' ' || map.getMatrix()[valueY][valueX] == 'O') {
			
			if(id == '$') {
				id = 'O';
			}
			
			map.updateMap(valueY, valueX, id);
			map.updateMap(this.coordY, this.coordX, ' ');
			coordY = valueY;
			coordX = valueX;
			return true;
		}

		else if (map.getMatrix()[valueY][valueX] == 'k') {
			map.updateMap(valueY, valueX, '$');
			map.updateMap(this.coordY, this.coordX, ' ');
			coordY = valueY;
			coordX = valueX;
			id = '$';
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if ogre is on top of the key. If he is, his id remains '$', otherwise if his id is '$' and he isn't, his id changed back to 'O'
	 * 
	 * @param map current {@link Map}
	 * @param level current level
	 */
	private void checkIfOgreIsInKey(Map map, LevelLogic level)
	{
		if (id =='$' && (coordX != level.getKeyCoordX() || coordY != level.getKeyCoordY()) )
		{
			id = 'O';
			map.updateMap(coordY, coordX, id);
		}
	}
	
	/**
	 * Stuns ogre by changing his stun counter to 2 and changing his id to '8'
	 * 
	 * @param map current {@link Map}
	 * @throws IllegalMapChangeException  Exception implemented to make sure Characters don't go through walls
	 */
	public void stun(Map map) throws IllegalMapChangeException{
		stun_counter = 2;
		id = '8';
		map.updateMap(coordY, coordX, id);
	}
	
	/**
	 * Changes ogre's id
	 * 
	 * @param newId new Id
	 */
	public void setId (char newId) {id = newId;}
	
	/**
	 * 
	 * @return The random number the Ogre generates before moving each time to know the direction in which it will move.
	 */

	public int getRand() {return randHolder;};
	
	/**
	 * 
	 * @return ogre's stun counter which indicated during how many shifts the ogre is gonna be stunned.
	 */
	public int getStunCounter () {return stun_counter;}
	
	/**
	 * 
	 * @return ogre's blocker. If true, ogre cant move, otherwise he can. Used on tests.
	 */
	public boolean getBlocker () {return movementBlocker;}
	
	/**
	 * Changed ogre's blocker value
	 * 
	 * @param b new blocker value
	 */
	public void setBlocker (boolean b)
	{
		this.movementBlocker = b;
	}
}
