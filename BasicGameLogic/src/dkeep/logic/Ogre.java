package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

public class Ogre extends Character {

	private int stun_counter;
	private int randHolder;
	boolean movementBlocker;
	
	public Ogre(int x, int y) {
		super(x, y, 'O');
		stun_counter = 0;
		movementBlocker = false;
	}
	
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
	
	private void checkIfOgreIsInKey(Map map, LevelLogic level)
	{
		if (id =='$' && (coordX != level.getKeyCoordX() || coordY != level.getKeyCoordY()) )
		{
			id = 'O';
			map.updateMap(coordY, coordX, id);
		}
	}
	
	public void stun(Map map) throws IllegalMapChangeException{
		stun_counter = 2;
		id = '8';
		map.updateMap(coordY, coordX, id);
	}
	
	public void setId (char newId) {id = newId;}
	
	public int getRand() {return randHolder;};
	
	public int getStunCounter () {return stun_counter;}
	
	public boolean getBlocker () {return movementBlocker;}
	
	public void setBlocker (boolean b)
	{
		this.movementBlocker = b;
	}
}
