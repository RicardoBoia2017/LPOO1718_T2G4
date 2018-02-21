package dkeep.logic;

public class Ogre extends Character {

	public Ogre(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y, 'O');
	}
	
	public void move(char [][] map, int command) {
		
		switch (command){
		
		// left
		case 0:
		{
			if(map[coordY][coordX-1] == ' '){
				map[coordY][coordX-1] = id;
				map[coordY][coordX] = ' ';
				coordX = coordX-1;
			}
			
			break;
		}
		
		//right
		case 1:
		{
			 if(map[coordY][coordX+1] == ' ') {
				 map[coordY][coordX+1] = id;
				 map[coordY][coordX] = ' ';
				 coordX = coordX+1;
			 }
			 
			 else if(map[coordY][coordX+1] == 'k') {
				 map[coordY][coordX+1] = '$';
				 map[coordY][coordX] = ' ';
				 coordX = coordX+1;
			 }
			 
			 break;
		}
		
		//down
		case 2:
		{
			 if(map[coordY+1][coordX] == ' ') {
				 map[coordY+1][coordX] = id;
				 map[coordY][coordX] = ' ';
				 coordY = coordY+1;
			 }
			 
			 break;
		}
		
		//up
		case 3:
		{
			 if(map[coordY-1][coordX] == ' ') {
				 map[coordY-1][coordX] = id;
				 map[coordY][coordX] = ' ';
				 coordY = coordY-1;
			 }
			 
			 else if(map[coordY-1][coordX] == 'k') {
				 map[coordY-1][coordX] = '$';
				 map[coordY][coordX] = ' ';
				 coordY = coordY-1;
			 }
			 
			 break;
		}
		}
	}
}
