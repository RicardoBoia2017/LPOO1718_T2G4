package dkeep.logic;

public class Hero extends Character {

	public Hero(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y, 'H');
	}
	
	public int move(Map map, char command, int stage) {
		//abstract movement method
		 switch(command) {
		 
		 case 'a': 
		 {
			 if(map.getmap()[coordY][coordX-1] == ' ') {
				 map.setMap(coordY, coordX-1, id);
				 map.setMap(coordY, coordX, ' ');
				 coordX = coordX-1;
			 } 
			 
			 //Checks if hero steps over the lever
			 else if(map.getmap()[coordY][coordX-1] == 'k' && stage == 1) {
				 
				 map.getmap()[5][0] = 'S';
				 map.getmap()[6][0] = 'S';
				 
				 map.setMap(coordY, coordX-1,id);
				 map.setMap(coordY, coordX, ' ');
				 coordX = coordX-1;
			 } 
			 //to open the door you need to check if the Hero has the key, aka, is in K state.
			 else if(map.getmap()[coordY][coordX-1] == 'I' && stage == 2 && id == 'K') {
				 map.setMap(coordY, coordX-1, 'S');
			 }
			 
			 else if(map.getmap()[coordY][coordX-1] == 'S') {
				 System.out.print("Victory.");
				 return 1;
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
			 
			 //checks if hero grabs key
			 else if (map.getmap()[coordY][coordX+1] == 'k')
			 {
				 id = 'K';
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
			 
			 else if (map.getmap()[coordY-1][coordX] == 'k'){
				 id = 'K';
				 map.setMap(coordY-1, coordX, id);
				 map.setMap(coordY, coordX, ' ');
				 coordY = coordY-1;
			 }
			 break;
		 }
		 
		 }
		 
		 return 0;
	}
}