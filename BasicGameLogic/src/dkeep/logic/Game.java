package dkeep.logic;

public class Game {
	Hero hero;
	Guard guard;
	Ogre ogre;
	Club club;
	Map map;
	
	
	public Game() {
		// TODO Auto-generated constructor stub
		hero = new Hero(1,1);
		guard = new Guard(8,1);
		map = new Map();
		ogre = new Ogre(4,1);
		club = new Club(3,1);
		//
	}
	
	public void updateGame(char herocommand) {
		int stage = map.getcurrentlevel();
		int a;
		
		//hero phase
		 
		 if(/*move hero returns 1 passing the doors*/) {
			 
			if (stage == 1){
			
			System.out.println(" ");
			System.out.println("Now you went up the stairs, new stage.");
			System.out.println(" ");
			 
			 // you went up the stairs, now a new level must begin.
			 
			//update game stage
			 
			hero.setcoordX(1);
			hero.setcoordY(7);
			 
			stage = 2;
			continue;
			}
			 
			 else if (stage == 2)
				 return;
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
		 
		 //move(matrix, guard, guardposition[a],stage);
		 
		 // parameter for guard a++;
		 
		 if (a == guardposition.length) {
			 a = 0;
		 }
		 
		 if(matrix[guard.coordY-1][guard.coordX] == 'H' || matrix[guard.coordY+1][guard.coordX] == 'H' || matrix[guard.coordY][guard.coordX-1] == 'H' || matrix[guard.coordY][guard.coordX+1] == 'H') {
			 
			 //pass interface game over state, interface will print.
			 
			 System.out.println("Game Over.");
			 
			 return;
		 }
		 
		 }
		 
		 //the ogre moves randomly, we're going to have to generate random numbers.
		 
		 else{
			 rand = randomnumber.nextInt(4);	 

			 if(matrix[ogre.coordY-1][ogre.coordX] == hero.id || matrix[ogre.coordY+1][ogre.coordX] == hero.id || matrix[ogre.coordY][ogre.coordX-1] == hero.id || matrix[ogre.coordY][ogre.coordX+1] == hero.id)
			 {
				 //interface
				 
				 System.out.println("Game Over.");
				 
				 return;
			 }
			 
			 //ogre moves
			 ogremove (matrix,ogre,rand);
			 
			 //club moves
			 clubplacement = randomclub.nextInt(4);
			 
			 clubmove(matrix,ogre,club,clubplacement);
			 
			 if(matrix[ogre.coordY-1][ogre.coordX] == hero.id || matrix[ogre.coordY+1][ogre.coordX] == hero.id || matrix[ogre.coordY][ogre.coordX-1] == hero.id || matrix[ogre.coordY][ogre.coordX+1] == hero.id || matrix[club.coordY][club.coordX+1] == hero.id || matrix[club.coordY][club.coordX-1] == hero.id || matrix[club.coordY-1][club.coordX] == hero.id || matrix[club.coordY+1][club.coordX] == hero.id)
			 {
				 //intercace
				 
				 System.out.println("Game Over.");
				 
				 return;
			 }
			 
			 
			 if (level [1][7] == ' ' && hero.id == 'H')
					level[1][7] = 'k';
		 }
	}
}
