package dkeep.logic;

import java.util.Random;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

	Map map;
	int numberOfOgres;
	LevelLogic currentLevel;
	
	public Game(int numbrOfOgrs, String gamStat, Map mp, LevelLogic currntLvl) {
		numberOfOgres = numbrOfOgrs;
		map = mp;
		currentLevel = currntLvl;
	}
			
	public Game(char[][] custommap) {
		
		//1. Map must be set to the custom made map.
		map = new Map(0);
		map.setCustomMapTo(custommap);
		map.setMap(3);
		
		//2. Must create Hero and Ogres depending on the edited map, also must find key.
		int [] keyCoords = new int [2] ;
		Hero hero = null;
				
		for(int i = 0; i < map.getMatrix().length; i++) {
			for(int j = 0; j < map.getMatrix()[i].length; j++) {
				if(map.getMatrix()[i][j] == 'H') {
					//getting the hero
					hero = new Hero(j, i);
				}
				
				if(map.getMatrix()[i][j] == 'k') {
					//getting the key
					keyCoords[0] = j;
					keyCoords[1] = i;
				}
			}
		}
		
		ArrayList <Ogre> ogres = new ArrayList <Ogre> ();
		ArrayList <Club> clubs = new ArrayList <Club> ();
		
//		int ogreindex = 0;
		
		for(int i = 0; i < map.getMatrix().length; i++) {
			for(int j = 0; j < map.getMatrix()[i].length; j++) {
				if(map.getMatrix()[i][j] == 'O') {
					//adding ogres to the array
					ogres.add(new Ogre (j,i) );
				}
			}
		}
				
		int clubindex = 0;
		
		for(int i = 0; i < map.getMatrix().length; i++) {
			for(int j = 0; j < map.getMatrix()[i].length; j++) {
				if(map.getMatrix()[i][j] == '*') {
					clubs.add(new Club (j,i));
				}
			}
		}
		
		currentLevel = new OgreLevel(hero,ogres,clubs, keyCoords);
	}
	
	public Game(int numberOfOgres, String guardPersonality) {
		

		map = new Map(0);
		int [] keyCoords = new int [2];
		keyCoords [0] = 7;
		keyCoords [1] = 8;
		currentLevel = new GuardLevel(new Hero (1,1), new Guard (8,1,guardPersonality), keyCoords);
		
		this.numberOfOgres = numberOfOgres;
	
	}
	
	public Game(int test) {

		if (test == 1) {
			//UNIT TEST MAP
			map = new Map(-1);
			int [] keyCoords = new int[2];
			keyCoords[0] = 1;
			keyCoords[1] = 3;
			currentLevel = new GuardLevel (new Hero(1,1), new Guard (3,1,"Rookie"), keyCoords);
			numberOfOgres = 1;
		}

		else {
			//NORMAL MAP
			map = new Map(0);
			currentLevel = new GuardLevel ("Rookie");

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
	