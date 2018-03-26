package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

public class Ogre extends Character {

	private int stun_counter;
	private int randholder;
	boolean movementBlocker;
	
	public Ogre(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y, 'O');
		stun_counter = 0;
		randholder = 0;
		movementBlocker = false;
	}
	
	public void move(Map map) throws IllegalMapChangeException {
				
		if (stun_counter == 2){
			stun_counter--;
			return;
		}
		
		else if (stun_counter == 1)
		{
			stun_counter --;
			return;
		}
		
		int nTries = 8;
		boolean Moved = false;
		
		while (nTries > 0) {
			Random randomnum = new Random();
			
			randholder = randomnum.nextInt(4);

			//randholder = ogreplace;
			
			switch (randholder) {

			// left
			case 0: {
				if (map.getMatrix()[coordY][coordX - 1] == ' ' || map.getMatrix()[coordY][coordX - 1] == 'O') {
					map.updateMap(coordY, coordX - 1, id);
					map.updateMap(coordY, coordX, ' ');
					coordX = coordX - 1;
					Moved = true;
				} 

				break;
			}

			// right
			case 1: {
				if (map.getMatrix()[coordY][coordX + 1] == ' ' || map.getMatrix()[coordY][coordX + 1] == 'O') {
					map.updateMap(coordY, coordX + 1, id);
					map.updateMap(coordY, coordX, ' ');
					coordX = coordX + 1;
					Moved = true;
				}

				else if (map.getMatrix()[coordY][coordX + 1] == 'k') {
					map.updateMap(coordY, coordX + 1, '$');
					map.updateMap(coordY, coordX, ' ');
					coordX = coordX + 1;
					Moved = true;
				}

				break;
			}

			// down
			case 2: {
				if (map.getMatrix()[coordY + 1][coordX] == ' ' || map.getMatrix()[coordY+1][coordX] == 'O') {
					map.updateMap(coordY + 1, coordX, id);
					map.updateMap(coordY, coordX, ' ');
					coordY = coordY + 1;
					Moved = true;
				}

				break;
			}

			// up
			case 3: {
				if (map.getMatrix()[coordY - 1][coordX] == ' ' || map.getMatrix()[coordY-1][coordX] == 'O') {
					map.updateMap(coordY - 1, coordX, id);
					map.updateMap(coordY, coordX, ' ');
					coordY = coordY - 1;
					Moved = true;
				}

				else if (map.getMatrix()[coordY - 1][coordX] == 'k') {
					map.updateMap(coordY - 1, coordX, '$');
					map.updateMap(coordY, coordX, ' ');
					coordY = coordY - 1;
					Moved = true;
				}

				break;
			}
			}
			if (Moved)
				break;
			nTries--;
		}
		
		if (id =='$' && (coordX != 7 || coordY != 1) )
			id = 'O';
	}

	public void stun(Map map) throws IllegalMapChangeException{
		stun_counter = 2;
		id = '8';
		map.updateMap(coordY, coordX, id);
	}
	
	public void setId (char newId) {id = newId;}
	
	public int getRand() {return randholder;};
	
	public int getStunCounter () {return stun_counter;}
	
	public boolean getBlocker () {return movementBlocker;}
	
	public void setBlocker (boolean b)
	{
		this.movementBlocker = b;
	}
}
