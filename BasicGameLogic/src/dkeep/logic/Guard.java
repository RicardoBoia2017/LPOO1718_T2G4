package dkeep.logic;

import java.util.Random;

public class Guard extends Character{

	char[] positionarray = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	char[] invertedposition = {'s','s','s','s','s','a','a','a','a','a','a','a','w','d','d','d','d','d','d','w','w','w','w','d'};
	int currentposition;
	String personality;
	boolean reversedroute; //tells you whether he reversed the route
	
	public Guard(int x, int y, String persona) {
		// TODO Auto-generated constructor stub
		super(x, y, 'G');
		currentposition = 0;
		personality = persona;
		reversedroute = false;
	}
	
	public void rookieMove(Map map) {
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
	
	public void drunkenMove(Map map) {
	    Random passout = new Random();
	    char[] temp;
	    
	    int awake; //he has 25% chance to be asleep, (if the random number generator generates 2 going from 0 to 3)
		
	    awake = passout.nextInt(4);
	    
	    if(awake != 2) { //he only applies this movement logic if he is awake!
	    // if awake == 3, he'll invert his route. how to invert the route?
	    	//swap positionarray by the inverted path randomly, carry on from currentposition.
	    
	    if(awake == 3 && id == 'g' && reversedroute == false) {
	    	//he can also only invert his route if he was asleep previously, and following his normal route.
	    	id = 'G';
	    	reversedroute = true;
	    } else {
	    	if(id == 'g') {
	    		//if the numbers 1 or 0 are generated, and he was asleep previously, now he is awake and following his normal route.
	    		id = 'G';
	    		map.setMap(coordY, coordX, id);
	    	}
	    }
	    
	    if(reversedroute = true) {
	    	
	    	temp = positionarray.clone();
	    	
	    	positionarray = invertedposition.clone();
	    	
	    	invertedposition = temp;
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
		
		if(currentposition == positionarray.length-1) {
			
			if(reversedroute) {
				reversedroute = false;
				
				temp = invertedposition.clone();
				
				invertedposition = positionarray.clone();
				
				positionarray = temp;
			}
			
	    	currentposition = 0;
	    } else {
	    	currentposition++;
	    }
	  } 
	   
	else {
		id = 'g';
		map.setMap(coordY, coordX, id);
	}
}
	
	public void suspiciousMove(Map map) {
	    Random check = new Random();
	    char[] temp;
	    
	    int paranoid; //he has 25% chance to go backwards (if the random number generator generates 2 going from 0 to 3)
		
	    paranoid = check.nextInt(4);
	    
	    if(paranoid == 2 && reversedroute == false) {
	    	//he can invert his route here.
	    	reversedroute = true;
	    }
	    
	    if(reversedroute = true) {
	    	
	    	//swapping forward path with inverted path
	    	
	    	temp = positionarray.clone();
	    	
	    	positionarray = invertedposition.clone();
	    	
	    	invertedposition = temp;
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
		
		if(currentposition == positionarray.length-1) {
			
			if(reversedroute) {
				//swapping inverted path with forward path
				
				reversedroute = false;
				
				temp = invertedposition.clone();
				
				invertedposition = positionarray.clone();
				
				positionarray = temp;
			}
			
	    	currentposition = 0;
	    } else {
	    	currentposition++;
	    }
	}
}
