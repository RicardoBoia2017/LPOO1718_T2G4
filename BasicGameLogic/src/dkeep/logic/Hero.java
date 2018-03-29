package dkeep.logic;

import java.io.Serializable;

public class Hero extends Character {

	public Hero(int x, int y) {
		super(x, y, 'H');
	}
	
	public int move(Map map, char command, LevelLogic level) throws IllegalMapChangeException {

		int auxReturn = 0;

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
	

	private void moveIntoCell (Map map, int newY, int newX)
	{
		 map.updateMap(newY, newX, id);
		 map.updateMap(this.coordY, this.coordX, ' ');
		 this.coordX = newX;
		 this.coordY = newY;
	}

	public boolean checkIfOgreNearby (Map map)
	{
		if (map.getMatrix()[coordY-1][coordX] == 'O' || 
			map.getMatrix()[coordY+1][coordX] == 'O' ||
			map.getMatrix()[coordY][coordX-1] == 'O' ||
			map.getMatrix()[coordY][coordX+1] == 'O' )
			return true;
		
		return false;
	}
}
