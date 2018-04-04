package dkeep.logic;

import java.io.Serializable;

/**
 * 
 * Represents the Hero class, movement, gets and sets.
 *
 */

public class Hero extends Character {

	char lastMovement;
	
	/**
	 * Creates Hero.
	 * @param x X coordinate of the Hero.
	 * @param y Y coordinate of the Hero.
	 */
	public Hero(int x, int y) {
		super(x, y, 'H');
	}
	
	/**
	 * Moves the Hero.
	 * @param map Map to move in.
	 * @param command Command, char to follow.
	 * @param level Current level logic.
	 * @return A number that tells you whether the Hero managed to pass to the second stage of a level (if he started at level 1).
	 * @throws IllegalMapChangeException Prevents moving into walls.
	 */
	public int move(Map map, char command, LevelLogic level) throws IllegalMapChangeException {

		int auxReturn = 0;
		lastMovement = command;
		
		switch (command) {

		case 'a': {
			auxReturn = moveAux(map, coordX - 1, coordY, level);
			break;
		}

		case 'd': {
			auxReturn = moveAux(map, coordX + 1, coordY, level);
			break;
		}

		case 's': {
			auxReturn = moveAux(map, coordX, coordY + 1, level);
			break;
		}

		case 'w': {
			auxReturn = moveAux(map, coordX, coordY - 1, level);
			break;
		}

		}

		if (level.getLevelType() == "Ogre")
			if (this.checkIfOgreNearby(map))
				return 2;
		
		return auxReturn;
	}

	/**
	 * Auxiliary movement function.
	 * @param map Map the Hero is moving in.
	 * @param coordX X coordinate of the Hero.
	 * @param coordY Y coordinate of the Hero.
	 * @param level Current level logic.
	 * @return Number that tells you whether you transitioned to the second stage.
	 */
	public int moveAux(Map map,int coordX, int coordY, LevelLogic level)
	{
		 if(map.getMatrix()[coordY][coordX] == ' ') {
			 moveIntoCell (map, coordY, coordX);
		 } 
		 
		 //Checks if hero steps over the lever/key
		 else if(map.getMatrix()[coordY][coordX] == 'k') {
			 
			 if (level.getLevelType() == "Guard")
				 level.openExitDoor(map);
			 
			 else if (level.getLevelType() == "Ogre")
				 id ='K';
			 
			 moveIntoCell (map, coordY, coordX);

		 }

		 //to open the door you need to check if the Hero has the key, aka, is in K state.
		 else if(map.getMatrix()[coordY][coordX] == 'I' && level.getLevelType() == "Ogre" && id == 'K') {
			 level.openExitDoor(map);
		 }
		 
		 else if(map.getMatrix()[coordY][coordX] == 'S') {
			 
			 System.out.println("Victory.");
			 return 1;
		 }
		 
		 return 0;
	}
	
	/**
	 * Auxiliary function for the auxiliary movement function that handles the movement into an actual cell.
	 * @param map Map to move in.
	 * @param newY New X Coordinate of the Hero.
	 * @param newX New Y Coordinate of the Hero.
	 */
	private void moveIntoCell (Map map, int newY, int newX)
	{
		 map.updateMap(newY, newX, id);
		 map.updateMap(this.coordY, this.coordX, ' ');
		 this.coordX = newX;
		 this.coordY = newY;
	}

	/**
	 * Checks if there is an Ogre adjacent to the Hero.
	 * @param map Map to check in.
	 * @return Whether or not the Hero was adjacent to the Ogre.
	 */
	public boolean checkIfOgreNearby (Map map)
	{
		if (map.getMatrix()[coordY-1][coordX] == 'O' || 
			map.getMatrix()[coordY+1][coordX] == 'O' ||
			map.getMatrix()[coordY][coordX-1] == 'O' ||
			map.getMatrix()[coordY][coordX+1] == 'O' )
			return true;
		
		return false;
	}

	/**
	 * @return The last movement the Hero made.
	 */
	public char getLastMovement() {return lastMovement;}
}
