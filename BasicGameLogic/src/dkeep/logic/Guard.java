package dkeep.logic;

public class Guard extends Character{

	char[] positionarray = {'a', 's', 's','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	int currentposition;
	
	public Guard(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y, 'G');
		currentposition = 0;
	}
	
	public void move(char [][] map) {
    switch(positionarray[currentposition]) {
		 
		 case 'a': 
		 {
			 if(map[coordY][coordX-1] == ' ') {
				 map[coordY][coordX-1] = id;
				 map[coordY][coordX] = ' ';
				 coordX = coordX-1;
			 } 
						 
			 break;
		 }
		 
		 case 'd': 
		 {
			 
			 if(map[coordY][coordX+1] == ' ') {
				 map[coordY][coordX+1] = id;
				 map[coordY][coordX] = ' ';
				 coordX = coordX+1;
			 }
			 
			 break;
		 }
		 
		 case 's': 
		 {
			 
			 if(map[coordY+1][coordX] == ' ') {
				 map[coordY+1][coordX] = id;
				 map[coordY][coordX] = ' ';
				 coordY = coordY+1;
			 }
			 
			 break;
		 }
		 
		 case 'w': 
		 {
			 if(map[coordY-1][coordX] == ' ') {
				 map[coordY-1][coordX] = id;
				 map[coordY][coordX] = ' ';
				 coordY = coordY-1;
			 }
	
			 break;
		 }
		}
   }
}
