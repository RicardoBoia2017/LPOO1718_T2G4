package dkeep.logic;

import java.util.Random;

public class Game {
	Hero hero;
	Guard guard;
	Ogre [] ogre;
	Club [] club;
	Map map;
	
	public Game() {
		// TODO Auto-generated constructor stub
		hero = new Hero(1,1);
		guard = new Guard(8,1,"Suspicious");
		map = new Map();
		ogre = new Ogre[3];
		ogre[0] = new Ogre (4,1);
		//ogre[1] = new Ogre (4,1);
		club = new Club[3];
		club[0] = new Club(3,1);
		club[1] = new Club(3,1);
	}
	
	public char[][] getmap(){return map.getmap();};	
	
	public char[][] updateGame(char herocommand) {
		int stage = map.getcurrentlevel();
		int  rand;
		int clubplacement;
		
		Random randomnumber = new Random();
		Random randomclub = new Random();
		
		//hero phase
		 
		int hero_mov = hero.move(map, herocommand, stage);
		if(hero_mov == 1) {
			 
			if (stage == 1){
			
			System.out.println(" ");
			System.out.println("Now you went up the stairs, new stage.");
			System.out.println(" ");
			 
			// you went up the stairs, now a new level must begin.
			 
			//update game stage
			 
			hero.setcoordX(1);
			hero.setcoordY(7);
			hero.setid('A');

			 
			stage = 2;
			map.setmap(2); //change to second map
			} 
			else {
				//he achieved the S victory door in stage 2, the game is over.
				 char[][] gameovermap = map.getmap();
				 
				 gameovermap[0][0] = 'W';
				 gameovermap[0][1] = 'I';
				 gameovermap[0][2] = 'N';
				 
				 return gameovermap;
			}
			
		 }
		 
		 //k's position has to be k whenever the hero steps out, same for I.
		 //this is not so true for stage 2 I think. In stage 2 hero picks it up, ogre is the one where it stays.
		
		if(stage == 1) {
		 if(map.getmap()[8][7] == ' ') {
			 map.setMap(8, 7, 'k');
		 }
		}
		
		 //guard phase, he will only move in a given pattern according to the array guardpositon.
		 
		 if(stage == 1){
	
			 if(guard.id == 'G' && (map.getmap()[guard.coordY-1][guard.coordX] == 'H' || map.getmap()[guard.coordY+1][guard.coordX] == 'H' || map.getmap()[guard.coordY][guard.coordX-1] == 'H' || map.getmap()[guard.coordY][guard.coordX+1] == 'H')) {
				 
				 //pass interface game over state, interface will print.
				 
				 System.out.println("Game Over.");
				 
				 char[][] gameovermap = map.getmap();
				 
				 gameovermap[0][0] = 'E';
				 gameovermap[0][1] = 'N';
				 gameovermap[0][2] = 'D';
				 
				 return gameovermap;
			 }
			 
		 switch(guard.personality) {
		 
		 case "Rookie":
			 guard.rookieMove(map);
			 break;
		
		 case "Drunken":
			 guard.drunkenMove(map);
			 break;
			 
		 case "Suspicious":
			 guard.suspiciousMove(map);
			 break;
		 }
		 
		 
		 if(guard.id == 'G' && (map.getmap()[guard.coordY-1][guard.coordX] == 'H' || map.getmap()[guard.coordY+1][guard.coordX] == 'H' || map.getmap()[guard.coordY][guard.coordX-1] == 'H' || map.getmap()[guard.coordY][guard.coordX+1] == 'H')) {
			 
			 //pass interface game over state, interface will print.
			 System.out.println("");
			 System.out.println("Game Over.");
			 
			 char[][] gameovermap = map.getmap();
			 
			 gameovermap[0][0] = 'E';
			 gameovermap[0][1] = 'N';
			 gameovermap[0][2] = 'D';
			 
			 return gameovermap;
		 }
		 
		 //end of a turn of stage 1, by now the map has the current state and hero and guard have both moved and checked for collision.
		 
		 return map.getmap();
		 
		 }
		 
		 //the ogre moves randomly, we're going to have to generate random numbers.
		 
		 else{
			 for (int i = 0;i < 1; i++)
			 { 
				 if (hero_mov == 2)
				{
					switch(herocommand)
					{
						case 'a':
						{
							if (ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX-1)
								ogre[i].stun();
									
							break;
						}
								
						case 'd':
						{
							if (ogre[i].coordY == hero.coordY && ogre[i].coordX == hero.coordX+1)
								ogre[i].stun();
									
							break;	
						}
							
						case 's':
						{
							if (ogre[i].coordY == hero.coordY+1 && ogre[i].coordX == hero.coordX)
								ogre[i].stun();
									
							break;	
						}
							
						case 'w':
						{
							if (ogre[i].coordY == hero.coordY-1 && ogre[i].coordX == hero.coordX)
								ogre[i].stun();
									
							break;	
						}
					}
				}
				
				 
				 rand = randomnumber.nextInt(4);	 

				 int stun = ogre[i].stun_counter;

				 
				 if(stun == 0 && (map.getmap()[ogre[i].coordY-1][ogre[i].coordX] == hero.id || map.getmap()[ogre[i].coordY+1][ogre[i].coordX] == hero.id || map.getmap()[ogre[i].coordY][ogre[i].coordX-1] == hero.id || map.getmap()[ogre[i].coordY][ogre[i].coordX+1] == hero.id) )
				 {
				 
				 System.out.println("Game Over.");
				 
				 char[][] gameovermap = map.getmap();
				 
				 gameovermap[0][0] = 'E';
				 gameovermap[0][1] = 'N';
				 gameovermap[0][2] = 'D';
				 
				 return gameovermap;
				 }	
				 
				 //ogre moves
				 ogre[i].move(map,rand);
				 
				 if(stun == 0 && (map.getmap()[ogre[i].coordY-1][ogre[i].coordX] == hero.id || map.getmap()[ogre[i].coordY+1][ogre[i].coordX] == hero.id || map.getmap()[ogre[i].coordY][ogre[i].coordX-1] == hero.id || map.getmap()[ogre[i].coordY][ogre[i].coordX+1] == hero.id) ) 
				 {
					 //interface
					 System.out.println("");
					 System.out.println("Game Over.");
							 
					 char[][] gameovermap = map.getmap();
							 
					 gameovermap[0][0] = 'E';
					 gameovermap[0][1] = 'N';
					 gameovermap[0][2] = 'D';
							 
					 return gameovermap;
				 }
				 
				 //club moves
				 clubplacement = randomclub.nextInt(4);
				 
				 club[i].move(map,clubplacement, ogre[i]);
				 
				 if (map.getmap()[club[i].coordY][club[i].coordX+1] == hero.id || map.getmap()[club[i].coordY][club[i].coordX-1] == hero.id || map.getmap()[club[i].coordY-1][club[i].coordX] == hero.id || map.getmap()[club[i].coordY+1][club[i].coordX] == hero.id)
				 {
					 //interface
					 System.out.println("");
					 System.out.println("Game Over.");
					 
					 char[][] gameovermap = map.getmap();
					 
					 gameovermap[0][0] = 'E';
					 gameovermap[0][1] = 'N';
					 gameovermap[0][2] = 'D';
					 
					 return gameovermap;
				 }
			 } 
				 
				 if (map.getmap()[1][7] == ' ' && hero.id == 'H')
						map.setMap(1, 7, 'k');
				 
				 //by now both the club and the ogre, also hero have moved which concludes a turn in stagee2
				 return map.getmap();
	}
	}

	//public int g () {return ogre.length;}
}
