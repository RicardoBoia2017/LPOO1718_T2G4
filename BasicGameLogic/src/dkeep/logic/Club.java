package dkeep.logic;

import java.util.Random;

public class Club extends Character {

	public Club(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y, '*');
	}
	
	public void move(char [][] map, int clubplace, Ogre character) {
		Random randomnum = new Random();
		
		int rand;
		
		switch(clubplace) {
		
		//appears at the left adjacent cell, in relation to our character - the ogre.
		case 0:
		{
			if(map[character.coordY][character.coordX-1] == ' '){
				map[character.coordY][character.coordX-1] = id;
				map[coordY][coordX] = ' ';
				coordX = character.coordX-1;
				coordY = character.coordY;
			} else {
				rand = randomnum.nextInt(4);
				move(map, rand, character); //recursion to avoid cases where ogre has an X in the position the club wants to move to.
			}
			
			break;
		}
		
		//.. right ...
		case 1:
		{
			 if(map[character.coordY][character.coordX+1] == ' ') {
				 map[character.coordY][character.coordX+1] = id;
				 map[coordY][coordX] = ' ';
				 coordX = character.coordX+1;
				 coordY = character.coordY;
			 }
			 
			 else if(map[character.coordY][character.coordX+1] == 'k') {
				 id = '$';
				 map[character.coordY][character.coordX+1] = id;
				 map[coordY][coordX] = ' ';
				 coordX = character.coordX+1;
				 coordY = character.coordY;
			 } else {
					rand = randomnum.nextInt(4);
					move(map, rand, character);
				}
			 
			 break;
		}
		
		//down
		case 2:
		{
			 if(map[character.coordY+1][character.coordX] == ' ') {
				 map[character.coordY+1][character.coordX] = id;
				 map[coordY][coordX] = ' ';
				 coordY = character.coordY+1;
				 coordX = character.coordX;
			 } else {
					rand = randomnum.nextInt(4);
					move(map, rand, character); 
				}
			 
			 break;
		}
		
		//up
		case 3:
		{
			 if(map[character.coordY-1][character.coordX] == ' ') {
				 map[character.coordY-1][character.coordX] = id;
				 map[coordY][coordX] = ' ';
				 coordY = character.coordY-1;
				 coordX = character.coordX;
			 }
			 
			 else if(map[character.coordY-1][character.coordX] == 'k') {
				 id = '$';
				 map[character.coordY-1][character.coordX] = id;
				 map[coordY][coordX] = ' ';
				 coordY = character.coordY-1;
				 coordX = character.coordX;
			 } else {
					rand = randomnum.nextInt(4);
					move(map, rand, character);
				}
			 
			 break;
		}
		
		}
	}
}
