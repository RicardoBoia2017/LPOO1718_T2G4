package dkeep.logic;

import java.util.Random;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Point;

public class Game implements Serializable {

	Map map;
	int numberOfOgres;
	LevelLogic currentLevel;
	
	public Game(int numbrOfOgrs, String gamStat, Map map, LevelLogic currentLevel) {
		numberOfOgres = numbrOfOgrs;
		this.map = map;
		this.currentLevel = currentLevel;
	}
			
	public Game(char[][] customMap) {
		
		//1. Map must be set to the custom made map.
		map = new Map(0);
		map.setCustomMapTo(customMap);
		map.setMap(3);
		
		//2. Must create Hero and Ogres depending on the edited map, also must find key.
		Point keyCoords = new Point();
		ArrayList<Point> exitDoors = new ArrayList <Point> ();
		Hero hero = null;
				
		for(int i = 0; i < map.getMatrix().length; i++) {
			for(int j = 0; j < map.getMatrix()[i].length; j++) {
				if(map.getMatrix()[i][j] == 'H') {
					//getting the hero
					hero = new Hero(j, i);
				}
				
				if(map.getMatrix()[i][j] == 'k') {
					//getting the key
					keyCoords.setLocation(j, i);
				}
				
				if(map.getMatrix()[i][j] == 'I') {
					//getting the key
					exitDoors.add(new Point (j,i));
				}
			}
		}
		
		ArrayList <Ogre> ogres = new ArrayList <Ogre> ();
		ArrayList <Club> clubs = new ArrayList <Club> ();
				
		for(int i = 0; i < map.getMatrix().length; i++) {
			for(int j = 0; j < map.getMatrix()[i].length; j++) {
				if(map.getMatrix()[i][j] == 'O') {
					//adding ogres to the array
					ogres.add(new Ogre (j,i) );
//					ogres.get(0).setBlocker(true);
				}
			}
		}
						
		for(int i = 0; i < map.getMatrix().length; i++) {
			for(int j = 0; j < map.getMatrix()[i].length; j++) {
				if(map.getMatrix()[i][j] == '*') {
					clubs.add(new Club (j,i));
//					clubs.get(0).setBlocker(true);
				}
			}
		}
		
		currentLevel = new OgreLevel(hero,ogres,clubs, keyCoords, exitDoors);
	}
	
	public Game(int numberOfOgres, String guardPersonality) {
		
		map = new Map(0);
		Point keyCoords = new Point (7,8);

		ArrayList <Point> exitDoors = new ArrayList <Point> ();
		exitDoors.add(new Point(0,5) );
		exitDoors.add(new Point(0,6) );
		
		currentLevel = new GuardLevel(new Hero (1,1), new Guard (8,1,guardPersonality), keyCoords, exitDoors);
		
		this.numberOfOgres = numberOfOgres;
	
	}
	
	public Game(int test) {

		if (test == 1) {
			//UNIT TEST MAP
			map = new Map(-1);
			Point keyCoords = new Point (1,3); 
			ArrayList <Point> exitDoors = new ArrayList <Point> ();
			exitDoors.add(new Point(0,2) );
			exitDoors.add(new Point(0,3) );
			
			currentLevel = new GuardLevel (new Hero(1,1), new Guard (3,1,"Rookie"), keyCoords, exitDoors);
			numberOfOgres = 1;
		}

		else {
			//NORMAL MAP
			map = new Map(0);
			ArrayList <Point> exitDoors = new ArrayList <Point> ();
			exitDoors.add(new Point(0,5) );
			exitDoors.add(new Point(0,6) );
			currentLevel = new GuardLevel ("Rookie",exitDoors);

			Random randomnumber = new Random();
			numberOfOgres = randomnumber.nextInt(3); //0-2
		}
	}
	
	public int getNumberOfOgres() {return numberOfOgres;}
	
	public LevelLogic getLevelLogic() {return currentLevel;}
	
	public Map getMap() {
		return map;
	};

	public void setMap(Map map) {this.map = map;}

	public Map updateGame(char herocommand) {

		currentLevel.updateGame(herocommand, map);

		if (currentLevel.getLevelState() == "Passed") 
		{
			map.setMap(2);
			currentLevel = new OgreLevel(numberOfOgres);
		}
		
		else if (currentLevel.getLevelState() == "Over")
		{
			System.out.println("Game Over.");

			return map;
		}
		
		else if (currentLevel.getLevelState() == "Victory")
		{
			System.out.println("Victory");

			return map;
		}

		return map;
	}





}
	