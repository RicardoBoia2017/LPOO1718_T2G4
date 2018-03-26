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
	
	public void setBlocker(boolean truefalse) {movementBlocker = truefalse;}
	
	public void move(Map map,Ogre character) throws IllegalMapChangeException {
				
		int nTries = 8;
		boolean Moved = false;
		
		while (nTries > 0) {
			
			Random randomnum = new Random();
			
			clubRand = randomnum.nextInt(4);

			
			switch (clubRand) {

			// appears at the left adjacent cell, in relation to our character -
			// the ogre.
			case 0: {
				if (map.getMatrix()[character.coordY][character.coordX - 1] == ' ') {
					if (id == '$')
						id = '*';
					map.updateMap(character.coordY, character.coordX - 1, id);
					map.updateMap(coordY, coordX, ' ');
					coordX = character.coordX - 1;
					coordY = character.coordY;
					Moved = true;
				} 

				break;
			}

			// .. right ...
			case 1: {
				if (map.getMatrix()[character.coordY][character.coordX + 1] == ' ') {
					if (id == '$')
						id = '*';
					map.updateMap(character.coordY, character.coordX + 1, id);
					map.updateMap(coordY, coordX, ' ');
					coordX = character.coordX + 1;
					coordY = character.coordY;
					Moved = true;
				}

				else if (map.getMatrix()[character.coordY][character.coordX + 1] == 'k') {
					id = '$';
					map.updateMap(character.coordY, character.coordX + 1, id);
					map.updateMap(coordY, coordX, ' ');
					coordX = character.coordX + 1;
					coordY = character.coordY;
					Moved = true;
				} 
				
				break;
			}

			// down
			case 2: {
				if (map.getMatrix()[character.coordY + 1][character.coordX] == ' ') {
					if (id == '$')
						id = '*';
					map.updateMap(character.coordY + 1, character.coordX, id);
					map.updateMap(coordY, coordX, ' ');
					coordY = character.coordY + 1;
					coordX = character.coordX;
					Moved = true;
				} 

				break;
			}

			// up
			case 3: {
				if (map.getMatrix()[character.coordY - 1][character.coordX] == ' ') {
					if (id == '$')
						id = '*';
					map.updateMap(character.coordY - 1, character.coordX, id);
					map.updateMap(coordY, coordX, ' ');
					coordY = character.coordY - 1;
					coordX = character.coordX;
					Moved = true;
				}

				else if (map.getMatrix()[character.coordY - 1][character.coordX] == 'k') {
					id = '$';
					map.updateMap(character.coordY - 1, character.coordX, id);
					map.updateMap(coordY, coordX, ' ');
					coordY = character.coordY - 1;
					coordX = character.coordX;
					Moved = true;
				} 

				break;
			}

			}
			if (Moved)
				break;
						
			nTries--;		
		}
	}

}
