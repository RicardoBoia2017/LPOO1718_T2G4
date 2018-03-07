package dkeep.logic;

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
	
	public void move(Map map, int clubplace, Ogre character, int nTries) {
		
		if (nTries == 0)
			return;
		
		Random randomnum = new Random();
		
		clubrand = clubplace;
		
		switch(clubplace) {
		
		//appears at the left adjacent cell, in relation to our character - the ogre.
		case 0:
		{
			if(map.getmap()[character.coordY][character.coordX-1] == ' '){
				if (id == '$')
					id = '*';
				map.setMap(character.coordY, character.coordX-1, id);
				map.setMap(coordY, coordX, ' ');
				coordX = character.coordX-1;
				coordY = character.coordY;

			} else {
				clubrand = randomnum.nextInt(4);
				move(map, clubrand, character, nTries--); //recursion to avoid cases where ogre has an X in the position the club wants to move to.
			}
			
			break;
		}
		
		//.. right ...
		case 1:
		{
			 if(map.getmap()[character.coordY][character.coordX+1] == ' ') {
				if (id == '$')
					id = '*';
				 map.setMap(character.coordY, character.coordX+1, id);
				 map.setMap(coordY, coordX, ' ');
				 coordX = character.coordX+1;
				 coordY = character.coordY;
			 }
			 
			 else if(map.getmap()[character.coordY][character.coordX+1] == 'k') {
				 id = '$';
				 map.setMap(character.coordY, character.coordX+1, id);
				 map.setMap(coordY, coordX, ' ');
				 coordX = character.coordX+1;
				 coordY = character.coordY;
			 } else {
					clubrand = randomnum.nextInt(4);
					move(map, clubrand, character, nTries--);
				}
			 
			 break;
		}
		
		//down
		case 2:
		{
			 if(map.getmap()[character.coordY+1][character.coordX] == ' ') {
				if (id == '$')
					id = '*';
				 map.setMap(character.coordY+1, character.coordX, id);
				 map.setMap(coordY, coordX, ' ');
				 coordY = character.coordY+1;
				 coordX = character.coordX;

			 } else {
					clubrand = randomnum.nextInt(4);
					move(map, clubrand, character, nTries--); 
				}
			 
			 break;
		}
		
		//up
		case 3:
		{
			 if(map.getmap()[character.coordY-1][character.coordX] == ' ') {
				if (id == '$')
					id = '*';
				 map.setMap(character.coordY-1, character.coordX, id);
				 map.setMap(coordY, coordX, ' ');
				 coordY = character.coordY-1;
				 coordX = character.coordX;
			 }
			 
			 else if(map.getmap()[character.coordY-1][character.coordX] == 'k') {
				 id = '$';
				 map.setMap(character.coordY-1, character.coordX, id);
				 map.setMap(coordY, coordX, ' ');
				 coordY = character.coordY-1;
				 coordX = character.coordX;
			 } else {
					clubrand = randomnum.nextInt(4);
					move(map, clubrand, character, nTries--);
				}
			 
			 break;
		}
		
		}
	}
}
