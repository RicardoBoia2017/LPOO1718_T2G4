package dkeep.logic;

//import java.io.Serializable;
import java.util.Random;

/**
 * 
 * The following class represents the Club character, including methods of how to move, the interactions it has with
 * other elements, gets and sets.
 *
 */

public class Club extends Character {
	
	boolean movementBlocker;
	int clubRand;
	
	/**
	 * Creates a Club.
	 * @param x X coordinate of the Club.
	 * @param y Y coordinate of the Club.
	 */
	public Club(int x, int y) {
		super(x, y, '*');
		movementBlocker = false;
	}
	
	/**
	 * 
	 * @return The random number the Club generates before moving each time to know the direction in which it will move.
	 */
	public int getRand() {return clubRand;}
	
	/**
	 * 
	 * @return The state of the movement blocker, if it is true, the club will not move, if it is false, it will.
	 */
	public boolean getBlocker() {return movementBlocker;}
	
	/**
	 * 
	 * @param b Desired state of the blocker.
	 */
	public void setBlocker(boolean b) {movementBlocker = b;}
	
	/**
	 * Moves the Club in a given direction.
	 * @param map Map in which to move in.
	 * @param ogre The Ogre this Club belongs to (is adjacent to).
	 * @param level The current level logic to follow.
	 * @throws IllegalMapChangeException Exception implemented to make sure Characters don't go through walls.
	 */
	public void move(Map map,Ogre ogre, LevelLogic level) throws IllegalMapChangeException {
				
		int nTries = 8;
		boolean Moved = false;
		
		while (nTries > 0) {
			
			Random randomnum = new Random();
			
			clubRand = randomnum.nextInt(4);

			
			switch (clubRand) {

			// appears at the left adjacent cell, in relation to our character -
			// the ogre.
			case 0: {

				if (moveAux (map,ogre.getCoordX() - 1, ogre.getCoordY() ) )
					Moved = true;
				
				break;
			}

			//right 
			case 1: {
				
				if (moveAux (map,ogre.getCoordX() + 1, ogre.getCoordY()) )
					Moved = true;
				
				break;
			}

			// down
			case 2: {

				if (moveAux (map,ogre.getCoordX(), ogre.getCoordY() + 1) )
					Moved = true;
				
				break;
			}

			// up
			case 3: {

				if (moveAux (map,ogre.getCoordX(), ogre.getCoordY() - 1) )
					Moved = true;
				
				break;
			}

			}
			if (Moved)
				break;
						
			nTries--;		
		}
		
		checkIfClubIsInKey(map, level);
	}

	/**
	 * Auxiliary move function.
	 * @param map Map to move in.
	 * @param valueX Coordinate X of the cell it's trying to move into.
	 * @param valueY Coordinate Y of the cell it's trying to move into.
	 * @return
	 */
	private boolean moveAux (Map map, int valueX, int valueY)
	{
		if (map.getMatrix()[valueY][valueX] == ' ') {
			map.updateMap(valueY, valueX, id);
			map.updateMap(this.coordY, this.coordX, ' ');
			coordY = valueY;
			coordX = valueX;
			return true;
		}

		else if (map.getMatrix()[valueY][valueX] == 'k') {
			id = '$';
			map.updateMap(valueY, valueX, id);
			map.updateMap(coordY, coordX, ' ');
			coordY = valueY;
			coordX = valueX;
			return true;
		} 
		return false;
	}
	
	/**
	 * Checks if the Club stepped over the key.
	 * @param map Map it's moving on.
	 * @param level Current level logic.
	 */
	private void checkIfClubIsInKey(Map map, LevelLogic level) {
		if (id =='$' && (coordX != level.getKeyCoordX() || coordY != level.getKeyCoordY()) )
		{	
			id = '*';
			map.updateMap(coordY, coordX, id);
		}
	}
}
