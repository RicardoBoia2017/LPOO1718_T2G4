package dkeep.logic;

import java.util.Random;

public class Game {
	Hero hero;
	Guard guard;
	Ogre [] ogre;
	Club [] club;
	Map map;
	int keycoordX;
	int keycoordY;
	
	public void setMap(Map map) {this.map = map;}
	
	public Game(int test) {
		// TODO Auto-generated constructor stub
		
		if (test == 1)
		{
			hero = new Hero(1,1);
			guard = new Guard(3,1,"Rookie");
			map = new Map(-1);
			ogre = new Ogre[1];
			ogre[0] = new Ogre(4,1);
			club = new Club[1];
			club[0] = new Club(3,1);
			keycoordX = 1;
			keycoordY = 3;
		}
		
		else
		{
			hero = new Hero(1,1);
			guard = new Guard(8,1,"Suspicious");
			map = new Map(0);
			keycoordX = 7;
			keycoordY = 8;
			
			Random randomnumber = new Random();
			int nOgres = randomnumber.nextInt(3);
	
			
			ogre = new Ogre[nOgres+1];
			for (int i = 0; i < nOgres+1; i++)
			{
				ogre[i] = new Ogre (4,1);
			}
			
			club = new Club[nOgres+1];
			for (int i = 0; i < nOgres+1; i++)
			{
				club[i] = new Club(3,1);
			}
		}
	}
	
	public char[][] getmap(){return map.getmap();};	
	
	public Guard getguard() {return guard;};
	
	public Map getMap() {return map;};
	
	public Ogre getOgre() {return ogre[0];};
	
	public Club getClub() {return club[0];};
	
	public char[][] updateGame(char herocommand) {
		int stage = map.getcurrentlevel();
		int rand;
		int clubplacement;
		
		Random randomnumber = new Random();
		Random randomclub = new Random();
		
		int hero_mov = 0;
		
		//hero phase
		try {
		hero_mov = hero.move(map, herocommand, stage);
		}
		
		catch(IllegalMapChangeException e) {
			
		}
		
		if(hero_mov == 1) {
			 
			if (stage == 1 || stage == -1){
			
			System.out.println(" ");
			System.out.println("Now you went up the stairs, new stage.");
			System.out.println(ogre.length + " ogre(s).");
			System.out.println(" ");
			 
			// you went up the stairs, now a new level must begin.
			 
			//update game stage
			 
			//hero.setcoordX(1);
			hero.setcoordY(7);
			hero.setid('A');
			keycoordX = 7;
			keycoordY = 1;

			 
			stage = 2;
			map.setmap(2); //change to second map
			
			return map.getmap();
			}
			
			else {
				//he achieved the S victory door in stage 2, the game is over.
				 
				 try {
					map.setMap(0, 0, 'W');
					map.setMap(0, 1, 'I');
					map.setMap(0, 2, 'N');
				} catch (IllegalMapChangeException e) {
					// TODO Auto-generated catch block
				}
				 
				 return map.getmap();
			}
			
		 }
		 
		 //k's position has to be k whenever the hero steps out, same for I.
		 //this is not so true for stage 2 I think. In stage 2 hero picks it up, ogre is the one where it stays.
		
		if(stage == 1 || stage == -1) {
		 if(map.getmap()[keycoordY][keycoordX] == ' ') {
			 try {
				map.setMap(keycoordY, keycoordX, 'k');
			} catch (IllegalMapChangeException e) {
				// TODO Auto-generated catch block
			}
		 }
		}
		
		 //guard phase, he will only move in a given pattern according to the array guardpositon.
		 
		 if(stage == 1 || stage == -1){
	
			 if(guard.id == 'G' && (map.getmap()[guard.coordY-1][guard.coordX] == 'H' || map.getmap()[guard.coordY+1][guard.coordX] == 'H' || map.getmap()[guard.coordY][guard.coordX-1] == 'H' || map.getmap()[guard.coordY][guard.coordX+1] == 'H')) {
				 
				 //pass interface game over state, interface will print.
				 
				 System.out.println("Game Over.");
				 
				 try {
				    map.setMap(0, 0, 'E');
					map.setMap(0, 1, 'N');
					map.setMap(0, 2, 'D');
				} catch (IllegalMapChangeException e) {
					// TODO Auto-generated catch block
				}
				 
				 return map.getmap();
			 }
		 
		switch(guard.personality) {
		 
		 case "Rookie":
			 try {
				guard.rookieMove(map);
			} catch (IllegalMapChangeException e) {
				// TODO Auto-generated catch block
			}
			 break;
		
		 case "Drunken":
			 try {
				guard.drunkenMove(map);
			} catch (IllegalMapChangeException e) {
				// TODO Auto-generated catch block
			}
			 break;
			 
		 case "Suspicious":
			 try {
				guard.suspiciousMove(map);
			} catch (IllegalMapChangeException e) {
				// TODO Auto-generated catch block
			}
			 break;
		 }
	
		 
		 if(guard.id == 'G' && (map.getmap()[guard.coordY-1][guard.coordX] == 'H' || map.getmap()[guard.coordY+1][guard.coordX] == 'H' || map.getmap()[guard.coordY][guard.coordX-1] == 'H' || map.getmap()[guard.coordY][guard.coordX+1] == 'H')) {
			 
			 //pass interface game over state, interface will print.
			 System.out.println("");
			 System.out.println("Game Over.");
			 
			 try {
				map.setMap(0, 0, 'E');
				map.setMap(0, 1, 'N');
				map.setMap(0, 2, 'D');
			} catch (IllegalMapChangeException e) {
				// TODO Auto-generated catch block
			}
			 
			 return map.getmap();
		 }
		 
		 //end of a turn of stage 1, by now the map has the current state and hero and guard have both moved and checked for collision.
		 
		 return map.getmap();
		 
		 }
		 
		 //the ogre moves randomly, we're going to have to generate random numbers.
		 
		 else{
			 for (int i = 0;i < ogre.length; i++)
			 { 
				 if (hero_mov == 2 && ogre[i].stun_counter == 0 && hero.getID() == 'A')
				{
					switch(herocommand)
					{
						case 'a':
						{
							if ( (ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX-1) || (ogre[i].coordY == hero.coordY-1 && ogre[i].coordX == hero.coordX) || (ogre[i].coordY == hero.coordY+1 && ogre[i].coordX == hero.coordX-1))
								try {
									ogre[i].stun(map);
								} catch (IllegalMapChangeException e) {
									// TODO Auto-generated catch block
								}
									
							break;
						}
								
						case 'd':
						{
							if ( (ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX+1) || (ogre[i].coordY == hero.coordY+1 && ogre[i].coordX == hero.coordX) || (ogre[i].coordY == hero.coordY-1 && ogre[i].coordX == hero.coordX) )
								try {
									ogre[i].stun(map);
								} catch (IllegalMapChangeException e) {
									// TODO Auto-generated catch block
								}
									
							break;	
						}
							
						case 's':
						{
							if ( (ogre[i].coordY == hero.coordY+1 && ogre[i].coordX == hero.coordX) || (ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX+1) || (ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX-1) )
								try {
									ogre[i].stun(map);
								} catch (IllegalMapChangeException e) {
									// TODO Auto-generated catch block
								}
									
							break;	
						}
							
						case 'w':
						{
							if ( (ogre[i].coordY == hero.coordY-1 && ogre[i].coordX == hero.coordX) || (ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX + 1) || (ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX-1) )
								try {
									ogre[i].stun(map);
								} catch (IllegalMapChangeException e) {
									// TODO Auto-generated catch block
								}
									
							break;	
						}
					}
				}
				
				 
				 rand = randomnumber.nextInt(4);	 

				 int stun = ogre[i].stun_counter;

				 
				 if(stun == 0 && (map.getmap()[ogre[i].coordY-1][ogre[i].coordX] == hero.id || map.getmap()[ogre[i].coordY+1][ogre[i].coordX] == hero.id || map.getmap()[ogre[i].coordY][ogre[i].coordX-1] == hero.id || map.getmap()[ogre[i].coordY][ogre[i].coordX+1] == hero.id) )
				 {
				 
				 System.out.println("Game Over.");
				 
				 try {
					map.setMap(0, 0, 'E');
					map.setMap(0, 1, 'N');
					map.setMap(0, 2, 'D');
				} catch (IllegalMapChangeException e) {
					// TODO Auto-generated catch block
				}
				 
				 return map.getmap();
				 }	
				 
				 if (ogre[i].getBlocker() == false)
				 {
					//ogre moves
					 try {
						ogre[i].move(map,rand, 8);
					} catch (IllegalMapChangeException e) {
						// TODO Auto-generated catch block
					}				 
				 }
				
				 if (club[i].getBlocker() == false) {
					 
					 //club moves
					 clubplacement = randomclub.nextInt(4); 
					 
					 try {
						club[i].move(map,clubplacement, ogre[i], 8);
					} catch (IllegalMapChangeException e) {
						// TODO Auto-generated catch block
					}
				 }
				 
				 if ( (stun == 0 && (map.getmap()[ogre[i].coordY-1][ogre[i].coordX] == hero.id || map.getmap()[ogre[i].coordY+1][ogre[i].coordX] == hero.id || map.getmap()[ogre[i].coordY][ogre[i].coordX-1] == hero.id || map.getmap()[ogre[i].coordY][ogre[i].coordX+1] == hero.id) )
				 || (map.getmap()[club[i].coordY][club[i].coordX+1] == hero.id || map.getmap()[club[i].coordY][club[i].coordX-1] == hero.id || map.getmap()[club[i].coordY-1][club[i].coordX] == hero.id || map.getmap()[club[i].coordY+1][club[i].coordX] == hero.id) )
				 {
					 //interface
					 System.out.println("");
					 System.out.println("Game Over.");
					 
					 try {
						map.setMap(0, 0, 'E');
						map.setMap(0, 1, 'N');
						map.setMap(0, 2, 'D');
					} catch (IllegalMapChangeException e) {
						// TODO Auto-generated catch block
					}
					 
					 return map.getmap();
				 }
				 
				 if (ogre[i].id == '8' && ogre[i].stun_counter == 0)
				 {
						ogre[i].id = 'O';
						try {
							map.setMap(ogre[i].coordY, ogre[i].coordX, ogre[i].id);
						} catch (IllegalMapChangeException e) {
							// TODO Auto-generated catch block
						}
				 }
				 
				 if (map.getmap()[keycoordY][keycoordX] == ' ' && hero.id == 'A')
					try {
						map.setMap(keycoordY, keycoordX, 'k');
					} catch (IllegalMapChangeException e) {
						// TODO Auto-generated catch block
					}
			 } 
				 //by now both the club and the ogre, also hero have moved which concludes a turn in stagee2
				 return map.getmap();
	}
	}
	
	public Hero getHero() {return hero;}
}
