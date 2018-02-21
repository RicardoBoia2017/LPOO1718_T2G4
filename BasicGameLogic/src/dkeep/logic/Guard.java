package dkeep.logic;

public class Guard extends Character{

	char[] positionarray = {'a', 's', 's','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	int currentposition;
	
	public Guard(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y, 'G');
		currentposition = 0;
	}
	
	public void move(Map map) {
    switch(positionarray[currentposition]) {
		 
		 case 'a': 
		 {
			 if(map.getmap()[coordY][coordX-1] == ' ') {
				 map.setMap(coordY, coordX-1, id);
				 map.setMap(coordY, coordX, ' ');
				 coordX = coordX-1;
			 } 
						 
			 break;
		 }
		 
		 case 'd': 
		 {
			 
			 if(map.getmap()[coordY][coordX+1] == ' ') {
				 map.setMap(coordY, coordX+1, id);
				 map.setMap(coordY, coordX, ' ');
				 coordX = coordX+1;
			 }
			 
			 break;
		 }
		 
		 case 's': 
		 {
			 
			 if(map.getmap()[coordY+1][coordX] == ' ') {
				 map.setMap(coordY+1, coordX, id);
				 map.setMap(coordY, coordX, ' ');
				 coordY = coordY+1;
			 }
			 
			 break;
		 }
		 
		 case 'w': 
		 {
			 if(map.getmap()[coordY-1][coordX] == ' ') {
				 map.setMap(coordY-1, coordX, id);
				 map.setMap(coordY, coordX, ' ');
				 coordY = coordY-1;
			 }
	
			 break;
		 }
		}
    
    if(currentposition == positionarray.length-1) {
    	currentposition = 0;
    } else {
    	currentposition++;
    }
    
   }
}
