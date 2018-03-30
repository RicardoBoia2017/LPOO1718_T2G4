package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

public class Ogre extends Character {

	private int stun_counter;
	private int randholder;
	boolean movementBlocker;
	
	public Ogre(int x, int y) {
		super(x, y, 'O');
		stun_counter = 0;
		randholder = 0;
		movementBlocker = false;
	}
	
	public void move(Map map) throws IllegalMapChangeException {
				
		
		if (manageStun())
			return;
		
		int nTries = 8;
		boolean Moved = false;
		
		while (nTries > 0) {
			Random randomnum = new Random();
			
			randholder = randomnum.nextInt(4);
			
			switch (randholder) {

			// left
			case 0: {

				if ( moveAux (map, coordX-1,coordY) )
					Moved = true;
				
				break;
			}

			// right
			case 1: {

				if ( moveAux (map, coordX+1,coordY) )
					Moved = true;
			
				break;
			}

			// down
			case 2: {

				if ( moveAux (map, coordX,coordY + 1) )	
					Moved = true;
				
				break;
			}

			// up
			case 3: {
				
				if ( moveAux (map, coordX,coordY - 1) )
					Moved = true;
				
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
	
	private boolean moveAux(Map map, int valueX, int valueY)
	{
		if (map.getMatrix()[valueY][valueX] == ' ' || map.getMatrix()[valueY][valueX] == 'O') {
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
			return true;
		}
		
		return false;
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
