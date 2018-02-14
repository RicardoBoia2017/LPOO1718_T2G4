package dungeon;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
	
	public static void move(char[][] matrix, int coordX, int coordY, char command, char id) {
		 switch(command) {
		 
		 case 'a': 
		 {
			 if(matrix[coordY][coordX-1] == ' ') {
				 matrix[coordY][coordX-1] = id;
				 matrix[coordY][coordX] = ' ';
				 coordX = coordX-1;
			 } 
			 
			 else if(matrix[coordY][coordX-1] == 'k') {
				 
				 matrix[5][0] = 'S';
				 matrix[6][0] = 'S';
				 
				 matrix[coordY][coordX-1] = id;
				 matrix[coordY][coordX] = ' ';
				 coordX = coordX-1;
			 } 
			 
			 else if(matrix[coordY][coordX-1] == 'S') {
				 System.out.print("Victory.");
				 return;
			 }
			 
			 break;
		 }
		 
		 case 'd': 
		 {
			 
			 if(matrix[coordY][coordX+1] == ' ') {
				 matrix[coordY][coordX+1] = id;
				 matrix[coordY][coordX] = ' ';
				 coordX = coordX+1;
			 }
			 
			 break;
		 }
		 
		 case 's': 
		 {
			 
			 if(matrix[coordY+1][coordX] == ' ') {
				 matrix[coordY+1][coordX] = id;
				 matrix[coordY][coordX] = ' ';
				 coordY = coordY+1;
			 }
			 
			 break;
		 }
		 
		 case 'w': 
		 {
			 if(matrix[coordY-1][coordX] == ' ') {
				 matrix[coordY-1][coordX] = id;
				 matrix[coordY][coordX] = ' ';
				 coordY = coordY-1;
			 }
			 
			 break;
		 }
		 
		 case 'e': 
		 {
			 return;
		 }
		 
		 }
	}

	public static void main(String[] args) {
		 Scanner keyboard = new Scanner(System.in);
		 
		 char command;
		 
		 Character hero = new Character(1,1,'H');
		 
		 Character guard = new Character(8,1,'G');
		 
		 char [] guardposition = {'a', 's', 's','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
		 
		 char [][] matrix = {{'X','X','X','X','X','X','X','X','X', 'X'}, 
								   {'X','H',' ',' ','I',' ','X',' ','G', 'X'},
								   {'X','X','X',' ','X','X','X',' ',' ', 'X'},
								   {'X',' ','I',' ','I',' ','X',' ',' ', 'X'},
								   {'X','X','X',' ','X','X','X',' ',' ', 'X'},
								   {'I',' ',' ',' ',' ',' ',' ',' ',' ', 'X'},
								   {'I',' ',' ',' ',' ',' ',' ',' ',' ', 'X'},
								   {'X','X','X',' ','X','X','X','X',' ', 'X'},
								   {'X',' ','I',' ','I',' ','X','k',' ', 'X'},
								   {'X','X','X','X','X','X','X','X','X', 'X'}};
	
		 
		 while(true) {
			 
			 for(int i = 0; i < matrix.length; i++) {
				 System.out.println(matrix[i]);
			 }
			 
			 System.out.println(" ");
			 System.out.println("Options:");
			 System.out.println("(w) - up");
			 System.out.println("(a) - left");
			 System.out.println("(s) - down");
			 System.out.println("(d) - right");
			 System.out.println("(e) - exit");
			 System.out.println("Command: ");			 
			 
			 command = keyboard.next().charAt(0);
			 
			 if(command != 'w' && command != 'a' && command != 's' && command != 'd' && command != 'e') {
				 System.out.println("Invalid option");
				 continue;
			 }
			 
			 move(matrix, hero.coordX, hero.coordY, command, hero.id);
			 
			 /*
			 switch(command) {
			 
			 case 'a': 
			 {
				 if(matrix[posHeroY][posHeroX-1] == ' ') {
					 matrix[posHeroY][posHeroX-1] = 'H';
					 matrix[posHeroY][posHeroX] = ' ';
					 posHeroX = posHeroX-1;
				 } 
				 
				 else if(matrix[posHeroY][posHeroX-1] == 'k') {
					 
					 matrix[5][0] = 'S';
					 matrix[6][0] = 'S';
					 
					 matrix[posHeroY][posHeroX-1] = 'H';
					 matrix[posHeroY][posHeroX] = ' ';
					 posHeroX = posHeroX-1;
				 } 
				 
				 else if(matrix[posHeroY][posHeroX-1] == 'S') {
					 System.out.print("Victory.");
					 return;
				 }
				 
				 break;
			 }
			 
			 case 'd': 
			 {
				 
				 if(matrix[posHeroY][posHeroX+1] == ' ') {
					 matrix[posHeroY][posHeroX+1] = 'H';
					 matrix[posHeroY][posHeroX] = ' ';
					 posHeroX = posHeroX+1;
				 }
				 
				 break;
			 }
			 
			 case 's': 
			 {
				 
				 if(matrix[posHeroY+1][posHeroX] == ' ') {
					 matrix[posHeroY+1][posHeroX] = 'H';
					 matrix[posHeroY][posHeroX] = ' ';
					 posHeroY = posHeroY+1;
				 }
				 
				 break;
			 }
			 
			 case 'w': 
			 {
				 if(matrix[posHeroY-1][posHeroX] == ' ') {
					 matrix[posHeroY-1][posHeroX] = 'H';
					 matrix[posHeroY][posHeroX] = ' ';
					 posHeroY = posHeroY-1;
				 }
				 
				 break;
			 }
			 
			 case 'e': 
			 {
				 return;
			 }
			 
			 }*/
			 
			 //k's position has to be k whenever the hero steps out, same for I.
			 if(matrix[8][7] == ' ') {
				 matrix[8][7] = 'k';
			 }
			 
			 /*
			 if(matrix[5][0] == ' ') {
				 matrix[5][0] = 'S';
			 }
			 
			 if(matrix[6][0] == ' ') {
				 matrix[6][0] = 'S';
			 }*/
			 
			 if(matrix[guard.coordY-1][guard.coordX] == 'H' || matrix[guard.coordY+1][guard.coordX] == 'H' || matrix[guard.coordY][guard.coordX-1] == 'H' || matrix[guard.coordY][guard.coordX+1] == 'H') {
				 System.out.println("Game Over.");
				 return;
			 }
			 
		 }
	}

}
