package dkeep.logic;

//import java.io.Serializable;
import java.util.Random;

public class Guard extends Character {

	char[] pathArray = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	char[] invertedPath = {'s','d','w','w','w','w','d','d','d','d','d','d','w','a','a','a','a','a','a','a','s','s','s','s'};
	int currentPosition;
	String personality;
	boolean reversedRoute; //tells you whether he reversed the route
	boolean movementBlocker;
	
	public Guard(int x, int y, String persona) {
		super(x, y, 'G');
		currentPosition = 0;
		personality = persona;
		reversedRoute = false;
		this.movementBlocker = false;
	}
	
	public void rookieMove(Map map) throws IllegalMapChangeException {
		switch(pathArray[currentPosition]) {
		 
		 case 'a': 
		 {
			 if(map.getMatrix()[coordY][coordX-1] == ' ') {
				 map.updateMap(coordY, coordX-1, id);
				 map.updateMap(coordY, coordX, ' ');
				 coordX = coordX-1;
			 } 
						 
			 break;
		 }
		 
		 case 'd': 
		 {
			 
			 if(map.getMatrix()[coordY][coordX+1] == ' ') {
				 map.updateMap(coordY, coordX+1, id);
				 map.updateMap(coordY, coordX, ' ');
				 coordX = coordX+1;
			 }
			 
			 break;
		 }
		 
		 case 's': 
		 {
			 
			 if(map.getMatrix()[coordY+1][coordX] == ' ') {
				 map.updateMap(coordY+1, coordX, id);
				 map.updateMap(coordY, coordX, ' ');
				 coordY = coordY+1;
			 }
			 
			 break;
		 }
		 
		 case 'w': 
		 {
			 if(map.getMatrix()[coordY-1][coordX] == ' ') {
				 map.updateMap(coordY-1, coordX, id);
				 map.updateMap(coordY, coordX, ' ');
				 coordY = coordY-1;
			 }
	
			 break;
		 }
		}
    
    if(currentPosition == pathArray.length-1) {
    	currentPosition = 0;
    } else {
    	currentPosition++;
    }
    
   }
	
	public void drunkenMove(Map map) throws IllegalMapChangeException {
	    Random passout = new Random();
	    char[] temp;
	    
	    int awake; //he has 25% chance to be asleep, (if the random number generator generates 2 going from 0 to 3)
		
	    awake = passout.nextInt(4);
	    
	    if(awake != 2) { //he only applies this movement logic if he is awake!
	    // if awake == 3, he'll invert his route. how to invert the route?
	    // swap positionarray by the inverted path randomly, carry on from currentposition.
	    
	    if(awake == 3 && id == 'g') {
	    	//if awake happens to be 3, and he was asleep previously, AKA had id 'g'.
	    	id = 'G';
		    
	    	//if he woke up, and hadn't inverted, he inverts.
	    	if(reversedRoute == false) {
	    		reversedRoute = true;
		    	
		    	temp = pathArray.clone();
		    	
		    	pathArray = invertedPath.clone();
		    	
		    	invertedPath = temp;
		    }
	    	
	    	//if he woke up, and inverted previously, he goes back to normal.
	    	else {
	    		reversedRoute = false;
	    		
				temp = invertedPath.clone();
				
				invertedPath = pathArray.clone();
				
				pathArray = temp;
	    	}
		    
	    } else {
	    	if(id == 'g') {
	    		//if the numbers 1 or 0 are generated, and he was asleep previously, now he is awake and following whatever his previous route was.
	    		//1 and 0 don't reverse the route, only 3 does.
	    		id = 'G';
	    		map.updateMap(coordY, coordX, id);
	    	}
	    }
	    
		switch(pathArray[currentPosition]) {
		 
			 case 'a': 
			 {
				 if(map.getMatrix()[coordY][coordX-1] == ' ') {
					 map.updateMap(coordY, coordX-1, id);
					 map.updateMap(coordY, coordX, ' ');
					 coordX = coordX-1;
				 } 
							 
				 break;
			 }
			 
			 case 'd': 
			 {
				 
				 if(map.getMatrix()[coordY][coordX+1] == ' ') {
					 map.updateMap(coordY, coordX+1, id);
					 map.updateMap(coordY, coordX, ' ');
					 coordX = coordX+1;
				 }
				 
				 break;
			 }
			 
			 case 's': 
			 {
				 
				 if(map.getMatrix()[coordY+1][coordX] == ' ') {
					 map.updateMap(coordY+1, coordX, id);
					 map.updateMap(coordY, coordX, ' ');
					 coordY = coordY+1;
				 }
				 
				 break;
			 }
			 
			 case 'w': 
			 {
				 if(map.getMatrix()[coordY-1][coordX] == ' ') {
					 map.updateMap(coordY-1, coordX, id);
					 map.updateMap(coordY, coordX, ' ');
					 coordY = coordY-1;
				 }
		
				 break;
			 }
		}
		
		//if he didn't invert
		if(reversedRoute == false) {
			if(currentPosition == pathArray.length-1) {
				currentPosition = 0;
			} else {
				currentPosition++;
			}
		}
		
		//if he inverted
		else {
			if(currentPosition == 0) {
				currentPosition = pathArray.length-1;
			} else {
				currentPosition--;
			}
		}
	  } 
	   
	else {
		id = 'g';
		map.updateMap(coordY, coordX, id);
	}
}
	
	public void suspiciousMove(Map map) throws IllegalMapChangeException {
	    Random check = new Random();
	    char[] temp;
	    
	    int paranoid; //he has 25% chance to go backwards (if the random number generator generates 2 going from 0 to 3)
		
	    paranoid = check.nextInt(4);
	    
	    if(paranoid == 2) {
	    	
	    	//if he hadn't inverted, he inverts.
	    	if(reversedRoute == false) {
	    		reversedRoute = true;
		    	
		    	temp = pathArray.clone();
		    	
		    	pathArray = invertedPath.clone();
		    	
		    	invertedPath = temp;
		    }
	    	
	    	//if he inverted previously, he goes back to normal.
	    	else {
	    		reversedRoute = false;
	    		
				temp = invertedPath.clone();
				
				invertedPath = pathArray.clone();
				
				pathArray = temp;
	    	}
	    	
	    }
	    
		switch(pathArray[currentPosition]) {
		 
			 case 'a': 
			 {
				 if(map.getMatrix()[coordY][coordX-1] == ' ') {
					 map.updateMap(coordY, coordX-1, id);
					 map.updateMap(coordY, coordX, ' ');
					 coordX = coordX-1;
				 } 
							 
				 break;
			 }
			 
			 case 'd': 
			 {
				 
				 if(map.getMatrix()[coordY][coordX+1] == ' ') {
					 map.updateMap(coordY, coordX+1, id);
					 map.updateMap(coordY, coordX, ' ');
					 coordX = coordX+1;
				 }
				 
				 break;
			 }
			 
			 case 's': 
			 {
				 
				 if(map.getMatrix()[coordY+1][coordX] == ' ') {
					 map.updateMap(coordY+1, coordX, id);
					 map.updateMap(coordY, coordX, ' ');
					 coordY = coordY+1;
				 }
				 
				 break;
			 }
			 
			 case 'w': 
			 {
				 if(map.getMatrix()[coordY-1][coordX] == ' ') {
					 map.updateMap(coordY-1, coordX, id);
					 map.updateMap(coordY, coordX, ' ');
					 coordY = coordY-1;
				 }
		
				 break;
			 }
		}
		
		//if he didn't invert
		if(reversedRoute == false) {
			if(currentPosition == pathArray.length-1) {
				currentPosition = 0;
			} else {
				currentPosition++;
			}
		}
		
		//if he inverted
		else {
			if(currentPosition == 0) {
				currentPosition = pathArray.length-1;
			} else {
				currentPosition--;
			}
		}
	}

	public void setMovementBlocker(boolean newValue)
	{
		this.movementBlocker = newValue;
	}
	
	public boolean getMovementBlocker(){return this.movementBlocker;}
}
