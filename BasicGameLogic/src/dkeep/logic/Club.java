package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

public class Club extends Character {
	
	boolean movementBlocker;
	int clubrand;

	public Club(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y, '*');
		movementBlocker = false;
	}
	
	public int getRand() {return clubrand;}
	
	public boolean getBlocker() {return movementBlocker;}
	
	public void setBlocker(boolean truefalse) {movementBlocker = truefalse;}
	
	public void move(Map map,Ogre character) throws IllegalMapChangeException {
		
//		if (nTries <= 0)
//			return;
		
//		Random randomnum = new Random();
		
//		clubrand = clubplace;
		
		int nTries = 8;
		boolean Moved = false;
		
		while (nTries > 0) {
			
			Random randomnum = new Random();
			
			clubrand = randomnum.nextInt(4);

			
			switch (clubrand) {

			// appears at the left adjacent cell, in relation to our character -
			// the ogre.
			case 0: {
				if (map.getmap()[character.coordY][character.coordX - 1] == ' ') {
					if (id == '$')
						id = '*';
					map.setMap(character.coordY, character.coordX - 1, id);
					map.setMap(coordY, coordX, ' ');
					coordX = character.coordX - 1;
					coordY = character.coordY;
					Moved = true;
				} 

				break;
			}

			// .. right ...
			case 1: {
				if (map.getmap()[character.coordY][character.coordX + 1] == ' ') {
					if (id == '$')
						id = '*';
					map.setMap(character.coordY, character.coordX + 1, id);
					map.setMap(coordY, coordX, ' ');
					coordX = character.coordX + 1;
					coordY = character.coordY;
					Moved = true;
				}

				else if (map.getmap()[character.coordY][character.coordX + 1] == 'k') {
					id = '$';
					map.setMap(character.coordY, character.coordX + 1, id);
					map.setMap(coordY, coordX, ' ');
					coordX = character.coordX + 1;
					coordY = character.coordY;
					Moved = true;
				} 
				
				break;
			}

			// down
			case 2: {
				if (map.getmap()[character.coordY + 1][character.coordX] == ' ') {
					if (id == '$')
						id = '*';
					map.setMap(character.coordY + 1, character.coordX, id);
					map.setMap(coordY, coordX, ' ');
					coordY = character.coordY + 1;
					coordX = character.coordX;
					Moved = true;
				} 

				break;
			}

			// up
			case 3: {
				if (map.getmap()[character.coordY - 1][character.coordX] == ' ') {
					if (id == '$')
						id = '*';
					map.setMap(character.coordY - 1, character.coordX, id);
					map.setMap(coordY, coordX, ' ');
					coordY = character.coordY - 1;
					coordX = character.coordX;
					Moved = true;
				}

				else if (map.getmap()[character.coordY - 1][character.coordX] == 'k') {
					id = '$';
					map.setMap(character.coordY - 1, character.coordX, id);
					map.setMap(coordY, coordX, ' ');
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
