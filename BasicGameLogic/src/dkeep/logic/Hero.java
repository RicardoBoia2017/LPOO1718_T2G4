package dkeep.logic;

import java.io.Serializable;

public class Hero extends Character {

	public Hero(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y, 'H');
	}
	
	public int move(Map map, char command, String levelType) throws IllegalMapChangeException {
		//abstract movement method
		 switch(command) {
		 
		 case 'a': 
		 {
//			 if(map.getMatrix()[coordY][coordX-1] == ' ') {
//				 map.updateMap(coordY, coordX-1, id);
//				 map.updateMap(coordY, coordX, ' ');
//				 coordX = coordX-1;
//			 } 
//			 
//			 //Checks if hero steps over the lever
//			 else if(map.getMatrix()[coordY][coordX-1] == 'k' && stage == 1) {
//				 
//				 map.updateMap(5, 0, 'S');
//				 map.updateMap(6, 0, 'S');
//				 
//				 map.updateMap(coordY, coordX-1,id);
//				 map.updateMap(coordY, coordX, ' ');
//				 coordX = coordX-1;
//			 }
//			 
//			 else if(map.getMatrix()[coordY][coordX-1] == 'k') {
//				 id = 'K';
//				 map.updateMap(coordY, coordX-1,id);
//				 map.updateMap(coordY, coordX, ' ');
//				 coordX = coordX-1;
//			 }
//			 
//			 //to open the door you need to check if the Hero has the key, aka, is in K state.
//			 else if(map.getMatrix()[coordY][coordX-1] == 'I' && stage == 2 && id == 'K') {
//				 map.updateMap(coordY, coordX-1, 'S');
//			 }
//			 
//			 else if(map.getMatrix()[coordY][coordX-1] == 'S') {
//				 
//				 System.out.println("Victory.");
//				 
//				 return 1;
//			 }
//					
//			 if (coordX > 1){
//				 	if(stage == 2 && (map.getMatrix()[coordY][coordX-1] == 'O' || map.getMatrix()[coordY-1][coordX] == 'O' || map.getMatrix()[coordY+1][coordX] == 'O' ) )  
//					 	return 2;
//			}
			 return moveLeft(map,levelType);
		 }
		 
		 case 'd': 
		 {
			 
			 if(map.getMatrix()[coordY][coordX+1] == ' ') {
				 map.updateMap(coordY, coordX+1, id);
				 map.updateMap(coordY, coordX, ' ');
				 coordX = coordX+1;
			 }
			 
			 //checks if hero grabs key
			 else if (map.getMatrix()[coordY][coordX+1] == 'k')
			 {
				 id = 'K';
				 map.updateMap(coordY, coordX+1, id);
				 map.updateMap(coordY, coordX, ' ');
				 coordX = coordX+1;
			 }
			 
			 else if(map.getMatrix()[coordY][coordX+1] == 'I' && levelType == "Ogre" && id == 'K') {
				 map.updateMap(coordY, coordX+1, 'S');
			 }
			 
			 else if(map.getMatrix()[coordY][coordX+1] == 'S') {
				 
				 System.out.println("Victory.");
				 
				 return 1;
			 }
			 
			 if (coordX <= map.getMatrix().length-2)
			 {
				 if(levelType == "Ogre" && ( map.getMatrix()[coordY][coordX+1] == 'O' || map.getMatrix()[coordY-1][coordX] == 'O' || map.getMatrix()[coordY+1][coordX] == 'O') ) 
					 	return 2;
			 }
			 
			 break;
		 }
		 
		 case 's': 
		 {
			 
			 if(map.getMatrix()[coordY+1][coordX] == ' ') {
				 map.updateMap(coordY+1, coordX, id);
				 map.updateMap(coordY, coordX, ' ');
				 coordY = coordY+1;
			 }
			 
			 else if(map.getMatrix()[coordY+1][coordX] == 'k' && map.getCurrentMap() == -1) {
				 
				 map.updateMap(2, 0, 'S');
				 map.updateMap(3, 0, 'S');
				 
				 map.updateMap(coordY+1, coordX,id);
				 map.updateMap(coordY, coordX, ' ');
				 coordY = coordY+1;
			 }
			 
			 else if(map.getMatrix()[coordY+1][coordX] == 'k') {
				 id = 'K';
				 map.updateMap(coordY+1,coordX,id);
				 map.updateMap(coordY, coordX, ' ');
				 coordY = coordY+1;
			 }
			 
			 else if(map.getMatrix()[coordY+1][coordX] == 'I' && levelType == "Ogre" && id == 'K') {
				 map.updateMap(coordY+1, coordX, 'S');
			 }
			 
			 else if(map.getMatrix()[coordY+1][coordX] == 'S') {
				 
				 System.out.println("Victory.");
				 
				 return 1;
			 }
			 
			 if (coordY <= map.getMatrix().length-2)
			 {
				 if(levelType == "Ogre" && (map.getMatrix()[coordY+1][coordX] == 'O' || map.getMatrix()[coordY][coordX-1] == 'O' || map.getMatrix()[coordY][coordX+1] == 'O') )  
					 	return 2;
			 }
			 
			 break;
		 }
		 
		 case 'w': 
		 {
			 if(map.getMatrix()[coordY-1][coordX] == ' ') {
				 map.updateMap(coordY-1, coordX, id);
				 map.updateMap(coordY, coordX, ' ');
				 coordY = coordY-1;
			 }
			 
			 else if (map.getMatrix()[coordY-1][coordX] == 'k'){
				 id = 'K';
				 map.updateMap(coordY-1, coordX, id);
				 map.updateMap(coordY, coordX, ' ');
				 coordY = coordY-1;
			 }
			 
			 else if(map.getMatrix()[coordY-1][coordX] == 'I' && levelType == "Ogre" && id == 'K') {
				 map.updateMap(coordY-1, coordX, 'S');
			 }
			
			 else if(map.getMatrix()[coordY-1][coordX] == 'S') {
				 
				 System.out.println("Victory.");
				 
				 return 1;
			 }
			 
			 if (coordY >= 1)
			 {
				 if(levelType == "Ogre" && (map.getMatrix()[coordY-1][coordX] == 'O' || map.getMatrix()[coordY][coordX-1] == 'O' || map.getMatrix()[coordY][coordX+1] == 'O') )  
					 	return 2;
			 }
			 
			 break;
		 }
		 
		 }
		 
		 return 0;
	}

	public int moveLeft (Map map, String levelType)
	{
		 if(map.getMatrix()[coordY][coordX-1] == ' ') {
			 moveIntoCell (map, coordY, coordX-1);
		 } 
		 
		 //Checks if hero steps over the lever
		 else if(map.getMatrix()[coordY][coordX-1] == 'k' && levelType == "Guard") {
			 
			 moveIntoCell (map, coordY, coordX-1);

			 map.updateMap(5, 0, 'S');
			 map.updateMap(6, 0, 'S');	 
		 }
		 
		 else if(map.getMatrix()[coordY][coordX-1] == 'k') {
			 id = 'K';
			 moveIntoCell (map, coordY, coordX-1);
		 }
		 
		 //to open the door you need to check if the Hero has the key, aka, is in K state.
		 else if(map.getMatrix()[coordY][coordX-1] == 'I' && levelType == "Ogre" && id == 'K') {
			 map.updateMap(coordY, coordX-1, 'S');
		 }
		 
		 else if(map.getMatrix()[coordY][coordX-1] == 'S') {
			 
			 System.out.println("Victory.");
			 
			 return 1;
		 }
				
		 if (coordX >= 1){
			 	if(levelType == "Ogre"  && (map.getMatrix()[coordY][coordX-1] == 'O' || map.getMatrix()[coordY-1][coordX] == 'O' || map.getMatrix()[coordY+1][coordX] == 'O' ) )  
				 	return 2;
		}
		 
		 return 0;
	}

	public void moveIntoCell (Map map, int newY, int newX)
	{
		 map.updateMap(newY, newX, id);
		 map.updateMap(this.coordY, this.coordX, ' ');
		 this.coordX = newX;
	}
}
