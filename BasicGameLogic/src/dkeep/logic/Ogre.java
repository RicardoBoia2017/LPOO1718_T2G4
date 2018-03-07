package dkeep.logic;

import java.util.Random;

public class Ogre extends Character {

	int stun_counter;
	private int randholder;
	boolean movementBlocker;
	
	public Ogre(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y, 'O');
		stun_counter = 0;
		randholder = 0;
		movementBlocker = false;
	}
	
	public void move(Map map, int ogreplace, int nTries) throws IllegalMapChangeException {
		
		Random randomnum = new Random();
		
		randholder = ogreplace;
		
		if (nTries == 0)
			return;
		
		if (stun_counter == 2){
			stun_counter--;
			return;
		}
		
		else if (stun_counter == 1)
		{
			stun_counter --;
			return;
		}
		
		switch (ogreplace){
		
		// left
		case 0:
		{
			if(map.getmap()[coordY][coordX-1] == ' '){
				map.setMap(coordY, coordX-1, id);
				map.setMap(coordY, coordX, ' ');
				coordX = coordX-1;
			}
			else 
			{
				randholder = randomnum.nextInt(4);
				move(map, randholder, nTries--);
			}
			
			break;
		}
		
		//right
		case 1:
		{
			 if(map.getmap()[coordY][coordX+1] == ' ') {
				 map.setMap(coordY, coordX+1, id);
				 map.setMap(coordY, coordX, ' ');
				 coordX = coordX+1;
			 }
			 
			 else if(map.getmap()[coordY][coordX+1] == 'k') {
				 map.setMap(coordY, coordX+1, '$');
				 map.setMap(coordY, coordX, ' ');
				 coordX = coordX+1;
			 }
			 
			 else 
			{
				randholder = randomnum.nextInt(4);
				move(map, randholder, nTries--);
			}
			 
			 break;
		}
		
		//down
		case 2:
		{
			 if(map.getmap()[coordY+1][coordX] == ' ') {
				 map.setMap(coordY+1, coordX, id);
				 map.setMap(coordY, coordX, ' ');
				 coordY = coordY+1;
			 }
			
			 else 
			{
				randholder = randomnum.nextInt(4);
				move(map, randholder, nTries--);
			}
			 
			 break;
		}
		
		//up
		case 3:
		{
			 if(map.getmap()[coordY-1][coordX] == ' ') {
				 map.setMap(coordY-1, coordX, id);
				 map.setMap(coordY, coordX, ' ');
				 coordY = coordY-1;
			 }
			 
			 else if(map.getmap()[coordY-1][coordX] == 'k') {
				 map.setMap(coordY-1, coordX, '$');
				 map.setMap(coordY, coordX, ' ');
				 coordY = coordY-1;
			 }
			
			 else 
			{
				randholder = randomnum.nextInt(4);
				move(map, randholder, nTries--);
			}
			 
			 break;
		}
		
		}
		
		if (id =='$' && (coordX != 7 || coordY != 1) )
			id = 'O';
	}

	public void stun(Map map) throws IllegalMapChangeException{
		stun_counter = 2;
		id = '8';
		map.setMap(coordY, coordX, id);
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
