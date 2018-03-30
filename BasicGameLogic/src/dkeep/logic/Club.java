package dkeep.logic;

//import java.io.Serializable;
import java.util.Random;

public class Club extends Character {
	
	boolean movementBlocker;
	int clubRand;

	public Club(int x, int y) {
		super(x, y, '*');
		movementBlocker = false;
	}
	
	public int getRand() {return clubRand;}
	
	public boolean getBlocker() {return movementBlocker;}
	
	public void setBlocker(boolean b) {movementBlocker = b;}
	
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
	
	private void checkIfClubIsInKey(Map map, LevelLogic level) {
		if (id =='$' && (coordX != level.getKeyCoordX() || coordY != level.getKeyCoordY()) )
		{	
			id = '*';
			map.updateMap(coordY, coordX, id);
		}
	}
	
	

}
