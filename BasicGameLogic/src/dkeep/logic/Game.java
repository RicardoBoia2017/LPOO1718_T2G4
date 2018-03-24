package dkeep.logic;

import java.util.Random;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
	//Hero hero;
	//Guard guard;
	//Ogre[] ogre;
	//Club[] club;
	Map map;
//	int keycoordX;
//	int keycoordY;
	String gameState;
	int numberOfOgres;
	LevelLogic currentLevel;
	
	public Game(int numbrOfOgrs, String gamStat, Map mp, LevelLogic currntLvl) {
		numberOfOgres = numbrOfOgrs;
		gameState = gamStat;
		map = mp;
		currentLevel = currntLvl;
	}
	
	public Game getGame() {return this;}
	
	public int getNumberOfOgres() {return numberOfOgres;}
	
	public LevelLogic getLevelLogic() {return currentLevel;}
	
	public void setMap(Map map) {this.map = map;}
	
	public Game(char[][] custommap) {
		
		//1. Map must be set to the custom made map.
		map = new Map(0);
		map.setCustomMapTo(custommap);
		map.setmap(3);
		
		//2. Must create Hero and Ogres depending on the edited map, also must find key.
		int [] keyCoords = new int [2] ;
//		int ogreCounter = 0;
		Hero hero = null;
				
		for(int i = 0; i < map.getmap().length; i++) {
			for(int j = 0; j < map.getmap()[i].length; j++) {
				if(map.getmap()[i][j] == 'H') {
					//getting the hero
					hero = new Hero(j, i);
				}
				
//				if(map.getmap()[i][j] == 'O') {
//					//counting ogres
//					ogreCounter++;
//				}
				
				if(map.getmap()[i][j] == 'k') {
					//getting the key
					keyCoords[0] = j;
					keyCoords[1] = i;
				}
			}
		}
		
		ArrayList <Ogre> ogres = new ArrayList <Ogre> ();
		ArrayList <Club> clubs = new ArrayList <Club> ();
		
//		int ogreindex = 0;
		
		for(int i = 0; i < map.getmap().length; i++) {
			for(int j = 0; j < map.getmap()[i].length; j++) {
				if(map.getmap()[i][j] == 'O') {
					//adding ogres to the array
	//				ogre[ogreindex] = new Ogre(j, i);
					ogres.add(new Ogre (j,i) );
		//			ogreindex++;
				}
			}
		}
				
		int clubindex = 0;
		
		for(int i = 0; i < map.getmap().length; i++) {
			for(int j = 0; j < map.getmap()[i].length; j++) {
				if(map.getmap()[i][j] == '*') {
//					club[clubindex] = new Club(j, i);
//					clubindex++;
					clubs.add(new Club (j,i));
				}
			}
		}
		
		currentLevel = new OgreLevel(hero,ogres,clubs, keyCoords);

		//3. Declare that the game is running.
		gameState = "Running";
	}
	
	public Game(int numberOfOgres, String guardPersonality) {
		
//		hero = new Hero(1,1);
//		guard = new Guard(8,1,guardPersonality);
		map = new Map(0);
//		keycoordX = 7;
//		keycoordY = 8;
		int [] keyCoords = new int [2];
		keyCoords [0] = 7;
		keyCoords [1] = 8;
		currentLevel = new GuardLevel(new Hero (1,1), new Guard (8,1,guardPersonality), keyCoords);
		
		this.numberOfOgres = numberOfOgres;
//		int nOgres = numberOfOgres;
//		
//		ogre = new Ogre[nOgres];
//		for (int i = 0; i < nOgres; i++)
//		{
//			ogre[i] = new Ogre (4,1);
//		}
//		
//		club = new Club[nOgres];
//		for (int i = 0; i < nOgres; i++)
//		{
//			club[i] = new Club(3,1);
//		}
//		
		gameState = "Running";
	}
	
	public Game(int test) {

		if (test == 1) {
			//UNIT TEST MAP
//			hero = new Hero(1, 1);
//			guard = new Guard(3, 1, "Rookie");
			map = new Map(-1);
			int [] keyCoords = new int[2];
			keyCoords[0] = 1;
			keyCoords[1] = 3;
			currentLevel = new GuardLevel (new Hero(1,1), new Guard (3,1,"Rookie"), keyCoords);
			numberOfOgres = 1;
//			ogre = new Ogre[1];
//			ogre[0] = new Ogre(4, 1);
//			club = new Club[1];
//			club[0] = new Club(3, 1);
//			keycoordX = 1;
//			keycoordY = 3;
			gameState = "Running";
		}

		else {
			//NORMAL MAP
//			hero = new Hero(1, 1);
//			guard = new Guard(8, 1, "Rookie");
			map = new Map(0);
//			keycoordX = 7;
//			keycoordY = 8;
			currentLevel = new GuardLevel ("Rookie");

			Random randomnumber = new Random();
			numberOfOgres = randomnumber.nextInt(3); //0-2

//			ogre = new Ogre[nOgres + 1];
//			for (int i = 0; i < nOgres + 1; i++) {
//				ogre[i] = new Ogre(4, 1);
//			}
//
//			club = new Club[nOgres + 1];
//			for (int i = 0; i < nOgres + 1; i++) {
//				club[i] = new Club(3, 1);
//			}
//			
			gameState = "Running";
		}
	}

	public char[][] getmap() {
		return map.getmap();
	};

	public Guard getGuard() {
		return currentLevel.getGuard();
	};

	public Map getMap() {
		return map;
	};

	public Ogre getOgre() {
		return currentLevel.getOgre();
	};

	public Club getClub() {
		return currentLevel.getClub();
	};

	public char[][] updateGame(char herocommand) {
		int stage = map.getcurrentlevel();

		int hero_mov = 0;
		currentLevel.updateGame(herocommand, map);
//
//		// hero phase
//		try {
//			hero_mov = hero.move(map, herocommand, stage);
//		}
//
//		catch (IllegalMapChangeException e) {
//			System.out.println("Excecao mov hero");
//		}

		if (currentLevel.getLevelState() == "Passed") {

//		if (stage == 1 || stage == -1) {
			map.setmap(2);
			currentLevel = new OgreLevel(numberOfOgres);
//				System.out.println(" ");
//				System.out.println("Now you went up the stairs, new stage.");
//				System.out.println(ogre.length + " ogre(s).");
//				System.out.println(" ");
//
//				// you went up the stairs, now a new level must begin.
//
//				// update game stage
//				hero.setcoordY(7);
//				hero.setid('A');
//				keycoordX = 7;
//				keycoordY = 1;
//
//				stage = 2;
//				map.setmap(2); // change to second map
//
//				return map.getmap();
//			}

//			else {
//				// he achieved the S victory door in stage 2, the game is over.
//				gameState = "Victory";
//				return map.getmap();
//			}

		}
		
		else if (currentLevel.getLevelState() == "Over")
		{
			System.out.println("Game Over.");

			gameState = "Over";

			return map.getmap();
		}
		
		else if (currentLevel.getLevelState() == "Victory")
		{
			System.out.println("Victory");

			gameState = "Victory";

			return map.getmap();
		}

		// k's position has to be k whenever the hero steps out, same for I.
		// this is not so true for stage 2. In stage 2 hero picks k up,
		// ogre is the one where k stays in place.

//		if (stage == 1 || stage == -1) {
//			if (map.getmap()[keycoordY][keycoordX] == ' ') {
//				
//				try {
//					map.setMap(keycoordY, keycoordX, 'k');
//				} 
//				
//				catch (IllegalMapChangeException e) {}
//			}

			// guard phase, he will only move in a given pattern according to
			// the array guardpositon.

//			if (guard.getMovementBlocker() == false) {
//
//				if (checkHeroGetsCaught(guard)) {
//
//					// pass interface game over state, interface will print.
//
//					System.out.println("Game Over.");
//
//					gameState = "Over";
//
//					return map.getmap();
//				}
//
//				switch (guard.personality) {
//
//				case "Rookie":
//					try {
//						guard.rookieMove(map);
//					} catch (IllegalMapChangeException e) {
//					}
//					break;
//
//				case "Drunken":
//					try {
//						guard.drunkenMove(map);
//					} catch (IllegalMapChangeException e) {
//					}
//					break;
//
//				case "Suspicious":
//					try {
//						guard.suspiciousMove(map);
//					} catch (IllegalMapChangeException e) {
//					}
//					break;
//				}
//
//				if (checkHeroGetsCaught(guard)) {
//
//					// pass interface game over state, interface will print.
//					System.out.println("");
//					System.out.println("Game Over.");
//
//					gameState = "Over";
//
//					return map.getmap();
//				}
//
//				// end of a turn of stage 1, by now the map has the current
//				// state
//				// and hero and guard have both moved and checked for collision.
//			}
//			return map.getmap();
//
//		}

		// the ogre moves randomly, we're going to have to generate random
		// numbers.

//		else if (current.getLevelState() == "idk"){
//			for (int i = 0; i < ogre.length; i++) {
//				if (hero_mov == 2 && ogre[i].getStunCounter() == 0 && hero.getID() == 'A') {
//					switch (herocommand) {
//					case 'a': {
//						if ((ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX - 1)
//								|| (ogre[i].coordY == hero.coordY - 1 && ogre[i].coordX == hero.coordX)
//								|| (ogre[i].coordY == hero.coordY + 1 && ogre[i].coordX == hero.coordX - 1))
//							try {
//								ogre[i].stun(map);
//							} catch (IllegalMapChangeException e) {
//							}
//
//						break;
//					}
//
//					case 'd': {
//						if ((ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX + 1)
//								|| (ogre[i].coordY == hero.coordY + 1 && ogre[i].coordX == hero.coordX)
//								|| (ogre[i].coordY == hero.coordY - 1 && ogre[i].coordX == hero.coordX))
//							try {
//								ogre[i].stun(map);
//							} catch (IllegalMapChangeException e) {
//							}
//
//						break;
//					}
//
//					case 's': {
//						if ((ogre[i].coordY == hero.coordY + 1 && ogre[i].coordX == hero.coordX)
//								|| (ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX + 1)
//								|| (ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX - 1))
//							try {
//								ogre[i].stun(map);
//							} catch (IllegalMapChangeException e) {
//							}
//
//						break;
//					}
//
//					case 'w': {
//						if ((ogre[i].coordY == hero.coordY - 1 && ogre[i].coordX == hero.coordX)
//								|| (ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX + 1)
//								|| (ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX - 1))
//							try {
//								ogre[i].stun(map);
//							} catch (IllegalMapChangeException e) {
//							}
//
//						break;
//					}
//					}
//				}
//
//				int stun = ogre[i].getStunCounter();
//				
//				if (stun == 0 && checkHeroGetsCaught(ogre[i])) {
//
//					System.out.println("Game Over.");
//
//					gameState = "Over";
//
//					return map.getmap();
//				}
//
//				if (ogre[i].getBlocker() == false) {
//					// ogre moves
//					try {
//						ogre[i].move(map);
//					} catch (IllegalMapChangeException e) {
//					}
//				}
//
//				if (club[i].getBlocker() == false) {
//
//					try {
//						club[i].move(map, ogre[i]);
//					} catch (IllegalMapChangeException e) {
//					}
//				}
//
//				if ((stun == 0 && (checkHeroGetsCaught(ogre[i]))
//						|| checkHeroGetsCaught(club[i]) ) ) {
//					// interface
//					System.out.println("");
//					System.out.println("Game Over.");
//
//					gameState = "Over";
//
//					return map.getmap();
//				}
//
//				if (ogre[i].id == '8' && stun == 0) {
//					ogre[i].id = 'O';
//					try {
//						map.setMap(ogre[i].coordY, ogre[i].coordX, ogre[i].id);
//					} catch (IllegalMapChangeException e) {
//					}
//				}
//
//				if (map.getmap()[keycoordY][keycoordX] == ' ' && hero.id == 'A')
//					try {
//						map.setMap(keycoordY, keycoordX, 'k');
//					} catch (IllegalMapChangeException e) {
//					}
//			}
//			// by now both the club and the ogre, also hero have moved which
//			// concludes a turn in stagee2
//			return map.getmap();
//		}
		return map.getmap();
	}

//	public boolean checkHeroGetsCaught(Character c) {
//		if (c.getID() != 'g' && (map.getmap()[c.coordY - 1][c.coordX] == hero.id
//				|| map.getmap()[c.coordY + 1][c.coordX] == hero.id
//				|| map.getmap()[c.coordY][c.coordX - 1] == hero.id
//				|| map.getmap()[c.coordY][c.coordX + 1] == hero.id))
//			return true;
//
//		return false;
//	}

	public Hero getHero() {
		return currentLevel.getHero();
	}

	public String getGameState() {
		return gameState;
	}

	public int getKeyCoordX() {return currentLevel.getKeyCoordX();}
	
	public int getKeyCoordY() {return currentLevel.getKeyCoordY();}
}
	