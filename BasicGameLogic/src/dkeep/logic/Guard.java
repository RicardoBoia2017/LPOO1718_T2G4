package dkeep.logic;

//import java.io.Serializable;
import java.util.Random;

/**
 * 
 * This represents the Guard Character class, as in this project we do not include custom guards, he will
 * only have the ability to move in the first stage, his path to follow (and consequently the inverse path) will
 * always be hard-locked to the ones followed in the first stage.
 * 
 * Includes movement methods for a Drunken, Suspicious and Rookie guard, gets and sets.
 *
 */

public class Guard extends Character {

	char[] pathArray = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	char[] invertedPath = {'s','d','w','w','w','w','d','d','d','d','d','d','w','a','a','a','a','a','a','a','s','s','s','s'};
	int currentPosition;
	String personality;
	boolean reversedRoute; //tells you whether he reversed the route
	boolean movementBlocker;
	int awake;
	int paranoid;
	
	/**
	 * 
	 * @return Boolean that represents whether the Guard reversed his route.
	 */	
	public boolean getReversedRoute() {
		return reversedRoute;
	}
	
	/**
	 * 
	 * @return The array of positions the guard normally follows.
	 */	
	public char[] getPathArray() {
		return pathArray;
	}
	
	/**
	 * 
	 * @return The inverted array of positions the guard normally follows.
	 */
	public char[] getInvertedPathArray() {
		return invertedPath;
	}
	
	/**
	 * 
	 * @return A number that represents whether the Guard is awake.
	 */
	public int getAwake()
	{
		return awake;
	}
	
	/**
	 * 
	 * @return A number that represents whether the Suspicious guard inverted his path.
	 */
	public int getParanoid() {
		return paranoid;
	}
	
	/**
	 * Creates a Guard.
	 * @param x X coordinate of the Guard.
	 * @param y Y coordinate of the Guard.
	 * @param persona Guard's personality (Drunken, Suspicious, Rookie).
	 */
	public Guard(int x, int y, String persona) {
		super(x, y, 'G');
		currentPosition = 0;
		personality = persona;
		reversedRoute = false;
		this.movementBlocker = false;
	}
	
	/**
	 * 
	 * @return The Guard's personality.
	 */
	public String getPersonality() {
		return personality;
	}
	
	/**
	 * Movement for the Rookie guard, simply follows the normal path.
	 * @param map Map to move in.
	 * @throws IllegalMapChangeException Exception to prevent going through walls.
	 */
	public void rookieMove(Map map) throws IllegalMapChangeException {

	callMoveByCommand(map);
    
    if(currentPosition == pathArray.length-1) {
    	currentPosition = 0;
    } 
    else {
    	currentPosition++;
    }
    
   }
	
	/**
	 * Movement for the Drunken guard, follows the normal path but sometimes sleeps and may sometimes invert once he awakes.
	 * @param map Map to move in.
	 * @throws IllegalMapChangeException Exception to prevent going through walls.
	 */
	public void drunkenMove(Map map) throws IllegalMapChangeException {
	    Random passout = new Random();
	    char[] temp;
	    
	    //he has 25% chance to be asleep, (if the random number generator generates 2 going from 0 to 3)
		
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
	    
	    callMoveByCommand(map);
		
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

	/**
	 * Suspicious guard movement, sometimes inverts his path.
	 * @param map Map to move in.
	 * @throws IllegalMapChangeException Exception to prevent going through walls.
	 */
	public void suspiciousMove(Map map) throws IllegalMapChangeException {
	    Random check = new Random();
	    char[] temp;
	    
	   //he has 25% chance to go backwards (if the random number generator generates 2 going from 0 to 3)
	    
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
	    
	    callMoveByCommand(map);
		
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
	
	/**
	 * Auxiliary move function that moves according to a given char.
	 * @param map Map to move in.
	 */
	public void callMoveByCommand (Map map)
	{
		switch(pathArray[currentPosition]) {
		 
		 case 'a': 
		 {
			 this.moveIntoCell(map, coordY, coordX-1);			 
			 break;
		 }
		 
		 case 'd': 
		 {
			 this.moveIntoCell(map, coordY, coordX+1);			 
			 break;
		 }
		 
		 case 's': 
		 {
			 this.moveIntoCell(map, coordY+1, coordX);			 
			 break;
		 }
		 
		 case 'w': 
		 {
			 this.moveIntoCell(map, coordY-1, coordX);			 
			 break;
		 }
		}
	}
	
	/**
	 * Auxiliary function for the auxiliary movement function that handles the movement into an actual cell.
	 * @param map Map to move in.
	 * @param newY New X Coordinate of the Guard.
	 * @param newX New Y Coordinate of the Guard.
	 */
	private void moveIntoCell (Map map, int newY, int newX)
	{
		 map.updateMap(newY, newX, id);
		 map.updateMap(this.coordY, this.coordX, ' ');
		 this.coordX = newX;
		 this.coordY = newY;
	}
	
	/**
	 * 
	 * @param newValue Value to set the blocker to.
	 */
	public void setMovementBlocker(boolean newValue)
	{
		this.movementBlocker = newValue;
	}
	
	/**
	 * 
	 * @return The Guard's movement blocker.
	 */
	public boolean getMovementBlocker(){return this.movementBlocker;}
}
