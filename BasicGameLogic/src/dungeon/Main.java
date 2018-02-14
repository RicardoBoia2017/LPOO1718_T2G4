package dungeon;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) {
		 Scanner keyboard = new Scanner(System.in);
		 
		 char command;
		 
		 int posHeroX = 1;
		 int posHeroY = 1;
		 
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
			 
		 }
	}

}
