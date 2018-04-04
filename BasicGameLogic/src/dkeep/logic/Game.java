package dkeep.logic;

import java.util.Random;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Point;

/**
 * Class where is stored the current {@link Map} and the current type of level.
 *
 * Used to call the function responsible for updating each type of level, to change the level when asked by the {@LevelLogic}, and to return the updated Map to the classes that print it.
 * 
 */
public class Game implements Serializable {

	Map map;
	int numberOfEnemies;
	LevelLogic currentLevel;
	
	/**
	 * Constructor used to load a saved game
	 * 
	 * @param numbrOfEnemies number of ogres the hero has to deal with in the ogre level
	 * @param map object of {@link Map} where the game is simulated
	 * @param currentLevel - {@link LevelLogic} of the ssaved game
	 */
	public Game(int numbrOfEnemies, Map map, LevelLogic currentLevel) {
		this.numberOfEnemies = numbrOfEnemies;
		this.map = map;
		this.currentLevel = currentLevel;
	}
			
	/**
	 * Constructor used to build a game with a custom map
	 * 
	 * @param customMap object of {@link Map} built by the user
	 */
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
					hero.setID('A');
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
				}
			}
		}
						
		for(int i = 0; i < map.getMatrix().length; i++) {
			for(int j = 0; j < map.getMatrix()[i].length; j++) {
				if(map.getMatrix()[i][j] == '*') {
					clubs.add(new Club (j,i));
				}
			}
		}
		
		currentLevel = new OgreLevel(ogres, clubs);
		currentLevel.setHero(hero);
		currentLevel.setKeyCoords(keyCoords);
		currentLevel.setExitDoors(exitDoors);
	}
	
	/**
	 * Constructor used to build a game with user chosen settings
	 *
	 * @param numberOfEnemies number of {@link Ogre}s the hero has to deal with in the ogre level
	 * @param guardPersonality personality of the {@link Guard} in the guard level
	 */
	public Game(int numberOfEnemies, String guardPersonality) {
		
		map = new Map(0);
		Point keyCoords = new Point (7,8);

		ArrayList <Point> exitDoors = new ArrayList <Point> ();
		exitDoors.add(new Point(0,5) );
		exitDoors.add(new Point(0,6) );
		
		currentLevel = new GuardLevel(new Guard (8,1,guardPersonality) );
		currentLevel.setHero(new Hero (1,1));
		currentLevel.setKeyCoords(keyCoords);
		currentLevel.setExitDoors(exitDoors);
		
		this.numberOfEnemies = numberOfEnemies;
	
	}
	
	/**
	 * Constructor used to build a game with the test map or with the default map
	 * 
	 * @param test used as a boolean. Game uses the test map if true, otherwise uses default map 
	 */
	public Game(int test) {

		if (test == 1) {
			//UNIT TEST MAP
			map = new Map(-1);
			Point keyCoords = new Point (1,3); 
			ArrayList <Point> exitDoors = new ArrayList <Point> ();
			exitDoors.add(new Point(0,2) );
			exitDoors.add(new Point(0,3) );
			
			currentLevel = new GuardLevel (new Guard (3,1,"Rookie"));
			currentLevel.setHero(new Hero (1,1));
			currentLevel.setKeyCoords(keyCoords);
			currentLevel.setExitDoors(exitDoors);
			numberOfEnemies = 1;
		}

		else if (test == 0){
			//NORMAL MAP
			map = new Map(0);
			
			ArrayList <Point> exitDoors = new ArrayList <Point> ();
			exitDoors.add(new Point(0,5) );
			exitDoors.add(new Point(0,6) );
			
			Point keyCoords = new Point (7,8);

			currentLevel = new GuardLevel (new Guard(8,1,"Rookie"));
			
			currentLevel.setHero(new Hero (1,1));
			currentLevel.setKeyCoords(keyCoords);
			currentLevel.setExitDoors(exitDoors);
			
			Random randomnumber = new Random();
			numberOfEnemies = randomnumber.nextInt(3) + 1; //1-3
		}
	}
	
	/** 
	 * 
	 * @return Number of [@link Ogre}s and {@link Club}s in ogre level 
	 */
	public int getNumberOfEnemies() {return numberOfEnemies;}
	
	/**
	 * 
	 * @return Current {@link LevelLogic}
	 */
	public LevelLogic getLevelLogic() {return currentLevel;}
	
	/**
	 * 
	 * @return Current {@link Map}
	 */
	public Map getMap() {
		return map;
	};

	/**
	 * Changes the current map 
	 * 
	 * @param map new {@link Map} 
	 */
	public void setMap(Map map) {this.map = map;}

	/**
	 * 
	 * @param heroCommand char which represents the hero's movement direction in this shift
	 * @return Updated map after all characters moved
	 */
	public Map updateGame(char heroCommand) {

		currentLevel.updateGame(heroCommand, map);

		if (currentLevel.getLevelState() == "Passed") 
		{
			map.setMap(2);
			currentLevel = new OgreLevel(numberOfEnemies);
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
	