package dkeep.logic;

import java.io.Serializable;

public class Hero extends Character {

	public Hero(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y, 'H');
	}
	
	public int move(Map map, char command, int stage) throws IllegalMapChangeException {
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
				 
				 map.setMap(5, 0, 'S');
				 map.setMap(6, 0, 'S');
				 
				 map.setMap(coordY, coordX-1,id);
				 map.setMap(coordY, coordX, ' ');
				 coordX = coordX-1;
			 }
			 
			 else if(map.getmap()[coordY][coordX-1] == 'k') {
				 id = 'K';
				 map.setMap(coordY, coordX-1,id);
				 map.setMap(coordY, coordX, ' ');
				 coordX = coordX-1;
			 }
			 
			 //to open the door you need to check if the Hero has the key, aka, is in K state.
			 else if(map.getmap()[coordY][coordX-1] == 'I' && stage == 2 && id == 'K') {
				 map.setMap(coordY, coordX-1, 'S');
			 }
			 
			 else if(map.getmap()[coordY][coordX-1] == 'S') {
				 
				 System.out.println("Victory.");
				 
				 return 1;
			 }
					
			 if (coordX > 1){
				 	if(stage == 2 && (map.getmap()[coordY][coordX-1] == 'O' || map.getmap()[coordY-1][coordX] == 'O' || map.getmap()[coordY+1][coordX] == 'O' ) )  
					 	return 2;
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
			 
			 else if(map.getmap()[coordY][coordX+1] == 'I' && stage == 2 && id == 'K') {
				 map.setMap(coordY, coordX+1, 'S');
			 }
			 
			 else if(map.getmap()[coordY][coordX+1] == 'S') {
				 
				 System.out.println("Victory.");
				 
				 return 1;
			 }
			 
			 if (coordX < 7)
			 {
				 if(stage == 2 && ( map.getmap()[coordY][coordX+1] == 'O' || map.getmap()[coordY-1][coordX] == 'O' || map.getmap()[coordY+1][coordX] == 'O') ) 
					 	return 2;
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
			 
			 else if(map.getmap()[coordY+1][coordX] == 'k' && stage == -1) {
				 
				 map.setMap(2, 0, 'S');
				 map.setMap(3, 0, 'S');
				 
				 map.setMap(coordY+1, coordX,id);
				 map.setMap(coordY, coordX, ' ');
				 coordY = coordY+1;
			 }
			 
			 else if(map.getmap()[coordY+1][coordX] == 'k') {
				 id = 'K';
				 map.setMap(coordY+1,coordX,id);
				 map.setMap(coordY, coordX, ' ');
				 coordY = coordY+1;
			 }
			 
			 else if(map.getmap()[coordY+1][coordX] == 'I' && stage == 2 && id == 'K') {
				 map.setMap(coordY+1, coordX, 'S');
			 }
			 
			 else if(map.getmap()[coordY+1][coordX] == 'S') {
				 
				 System.out.println("Victory.");
				 
				 return 1;
			 }
			 
			 if (coordY < 7)
			 {
				 if(stage == 2 && (map.getmap()[coordY+1][coordX] == 'O' || map.getmap()[coordY][coordX-1] == 'O' || map.getmap()[coordY][coordX+1] == 'O') )  
					 	return 2;
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
			 
			 else if(map.getmap()[coordY-1][coordX] == 'I' && stage == 2 && id == 'K') {
				 map.setMap(coordY-1, coordX, 'S');
			 }
			
			 else if(map.getmap()[coordY-1][coordX] == 'S') {
				 
				 System.out.println("Victory.");
				 
				 return 1;
			 }
			 
			 if (coordY > 1)
			 {
				 if(stage == 2 && (map.getmap()[coordY-1][coordX] == 'O' || map.getmap()[coordY][coordX-1] == 'O' || map.getmap()[coordY][coordX+1] == 'O') )  
					 	return 2;
			 }
			 
			 break;
		 }
		 
		 }
		 
		 return 0;
	}
	
	public int getCoordX () {return coordX;}
	public int getCoordY () {return coordY;}
}
