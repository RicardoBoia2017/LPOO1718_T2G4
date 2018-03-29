package dkeep.logic;

import java.io.Serializable;

public class Hero extends Character {

	public Hero(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y, 'H');
	}
	
	public int move(Map map, char command, LevelLogic level) throws IllegalMapChangeException {

		int auxReturn = 0;

		switch (command) {

		case 'a': {
			System.out.println(coordX + "   " + coordY);
			auxReturn = moveAux(map, coordX - 1, coordY, level);

			if (coordX > 1) {
				if (level.getLevelType() == "Ogre" && (map.getMatrix()[coordY][coordX - 1] == 'O'
						|| map.getMatrix()[coordY - 1][coordX] == 'O' || map.getMatrix()[coordY + 1][coordX] == 'O'))
					return 2;
			}
			
			break;
		}

		case 'd': {
			auxReturn = moveAux(map, coordX + 1, coordY, level);

			if (coordX <= map.getMatrix().length - 2) {
				if (level.getLevelType() == "Ogre" && (map.getMatrix()[coordY][coordX + 1] == 'O'
						|| map.getMatrix()[coordY - 1][coordX] == 'O' || map.getMatrix()[coordY + 1][coordX] == 'O'))
					return 2;
			}
			
			break;
		}

		case 's': {
			System.out.println(coordX + "   " + coordY);
			auxReturn = moveAux(map, coordX, coordY + 1, level);

			if (coordY <= map.getMatrix().length - 2) {
				if (level.getLevelType() == "Ogre" && (map.getMatrix()[coordY + 1][coordX] == 'O'
						|| map.getMatrix()[coordY][coordX - 1] == 'O' || map.getMatrix()[coordY][coordX + 1] == 'O'))
					return 2;
			}
			
			break;
		}

		case 'w': {
			auxReturn = moveAux(map, coordX, coordY - 1, level);

			if (coordY >= 1) {
				if (level.getLevelType() == "Ogre" && (map.getMatrix()[coordY - 1][coordX] == 'O'
						|| map.getMatrix()[coordY][coordX - 1] == 'O' || map.getMatrix()[coordY][coordX + 1] == 'O'))
					return 2;
			}
			break;
		}

		}

		return auxReturn;
	}

	public int moveAux(Map map,int coordX, int coordY, LevelLogic level)
	{
		 if(map.getMatrix()[coordY][coordX] == ' ') {
			 moveIntoCell (map, coordY, coordX);
		 } 
		 
		 //Checks if hero steps over the lever
		 else if(map.getMatrix()[coordY][coordX] == 'k' && level.getLevelType() == "Guard") {
			 
			 moveIntoCell (map, coordY, coordX);
			 level.openExitDoor(map);
		 }
		 
		 else if(map.getMatrix()[coordY][coordX] == 'k' && level.getLevelType() == "Ogre") {
			 id = 'K';
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

	public void moveIntoCell (Map map, int newY, int newX)
	{
		 map.updateMap(newY, newX, id);
		 map.updateMap(this.coordY, this.coordX, ' ');
		 this.coordX = newX;
		 this.coordY = newY;
	}
}
