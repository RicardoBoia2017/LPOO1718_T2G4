package dkeep.logic;

import java.util.Random;

public class Guard extends Character{

	char[] positionarray = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	char[] inverposition = {'s','d','w','w','w','w','d','d','d','d','d','d','w','a','a','a','a','a','a','a','s','s','s','s'};
	int currentposition;
	String personality;
	boolean reversedroute; //tells you whether he reversed the route
	boolean movementBlocker;
	
	public Guard(int x, int y, String persona) {
		// TODO Auto-generated constructor stub
		super(x, y, 'G');
		currentposition = 0;
		personality = persona;
		reversedroute = false;
		this.movementBlocker = false;
	}
	
	public void rookieMove(Map map) throws IllegalMapChangeException {
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
	    	if(reversedroute == false) {
	    		reversedroute = true;
		    	
		    	temp = positionarray.clone();
		    	
		    	positionarray = inverposition.clone();
		    	
		    	inverposition = temp;
		    }
	    	
	    	//if he woke up, and inverted previously, he goes back to normal.
	    	else {
	    		reversedroute = false;
	    		
				temp = inverposition.clone();
				
				inverposition = positionarray.clone();
				
				positionarray = temp;
	    	}
		    
	    } else {
	    	if(id == 'g') {
	    		//if the numbers 1 or 0 are generated, and he was asleep previously, now he is awake and following whatever his previous route was.
	    		//1 and 0 don't reverse the route, only 3 does.
	    		id = 'G';
	    		map.setMap(coordY, coordX, id);
	    	}
	    }
	    
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
		
		//if he didn't invert
		if(reversedroute == false) {
			if(currentposition == positionarray.length-1) {
				currentposition = 0;
			} else {
				currentposition++;
			}
		}
		
		//if he inverted
		else {
			if(currentposition == 0) {
				currentposition = positionarray.length-1;
			} else {
				currentposition--;
			}
		}
	  } 
	   
	else {
		id = 'g';
		map.setMap(coordY, coordX, id);
	}
}
	
	public void suspiciousMove(Map map) throws IllegalMapChangeException {
	    Random check = new Random();
	    char[] temp;
	    
	    int paranoid; //he has 25% chance to go backwards (if the random number generator generates 2 going from 0 to 3)
		
	    paranoid = check.nextInt(4);
	    
	    if(paranoid == 2) {
	    	
	    	//if he hadn't inverted, he inverts.
	    	if(reversedroute == false) {
	    		reversedroute = true;
		    	
		    	temp = positionarray.clone();
		    	
		    	positionarray = inverposition.clone();
		    	
		    	inverposition = temp;
		    }
	    	
	    	//if he inverted previously, he goes back to normal.
	    	else {
	    		reversedroute = false;
	    		
				temp = inverposition.clone();
				
				inverposition = positionarray.clone();
				
				positionarray = temp;
	    	}
	    	
	    }
	    
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
		
		//if he didn't invert
		if(reversedroute == false) {
			if(currentposition == positionarray.length-1) {
				currentposition = 0;
			} else {
				currentposition++;
			}
		}
		
		//if he inverted
		else {
			if(currentposition == 0) {
				currentposition = positionarray.length-1;
			} else {
				currentposition--;
			}
		}
	}

	public void setMovementBlocker(boolean newValue)
	{
		this.movementBlocker = newValue;
	}
	
	public boolean getMovementBlocker(){return this.movementBlocker;}
}
