package dkeep.logic;

import java.util.Random;

public class Ogre extends Character {

	int stun_counter;
	
	public Ogre(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y, 'O');
		stun_counter = 0;
	}
	
	public void move(Map map, int ogreplace, int nTries) { 
		
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
		
		Random randomnum = new Random();
		
		int rand;
		
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
				rand = randomnum.nextInt(4);
				move(map, rand, nTries--);
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
				rand = randomnum.nextInt(4);
				move(map, rand, nTries--);
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
				rand = randomnum.nextInt(4);
				move(map, rand, nTries--);
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
				rand = randomnum.nextInt(4);
				move(map, rand, nTries--);
			}
			 
			 break;
		}
		
		}
		if (id =='$' && (coordX != 7 || coordY != 1) )
			id = 'O';
	}

	public void stun(Map map){
		stun_counter = 2;
		id = '8';
		map.setMap(coordY, coordX, id);
	}
	
	public void setId (char newId) {id = newId;}

}
