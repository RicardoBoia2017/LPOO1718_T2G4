package dungeon;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) {
		 Scanner keyboard = new Scanner(System.in);
		 
		 char command;
		 
		 int posHeroX = 1;
		 int posHeroY = 1;
		 
		 int posGuardX = 8;
		 int posGuardY = 1;
		 
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
			 
			 }
			 
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
			 
			 if(matrix[posGuardY-1][posGuardX] == 'H' || matrix[posGuardY+1][posGuardX] == 'H' || matrix[posGuardY][posGuardX-1] == 'H' || matrix[posGuardY][posGuardX+1] == 'H') {
				 System.out.println("Game Over.");
				 return;
			 }
		 }
	}

}
