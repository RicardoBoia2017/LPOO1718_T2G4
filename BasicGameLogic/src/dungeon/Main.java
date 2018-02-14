package dungeon;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class Main {
	
	public static void ogremove(char [][] level, Character character, char command) {
		
	}
	
	public static int move(char[][] matrix, Character character, char command) {
		 switch(command) {
		 
		 case 'a': 
		 {
			 if(matrix[character.coordY][character.coordX-1] == ' ') {
				 matrix[character.coordY][character.coordX-1] = character.id;
				 matrix[character.coordY][character.coordX] = ' ';
				 character.coordX = character.coordX-1;
			 } 
			 
			 else if(matrix[character.coordY][character.coordX-1] == 'k') {
				 
				 matrix[5][0] = 'S';
				 matrix[6][0] = 'S';
				 
				 matrix[character.coordY][character.coordX-1] = character.id;
				 matrix[character.coordY][character.coordX] = ' ';
				 character.coordX = character.coordX-1;
			 } 
			 
			 else if(matrix[character.coordY][character.coordX-1] == 'S') {
				 System.out.print("Victory.");
				 return 1;
			 }
			 
			 break;
		 }
		 
		 case 'd': 
		 {
			 
			 if(matrix[character.coordY][character.coordX+1] == ' ') {
				 matrix[character.coordY][character.coordX+1] = character.id;
				 matrix[character.coordY][character.coordX] = ' ';
				 character.coordX = character.coordX+1;
			 }
			 
			 break;
		 }
		 
		 case 's': 
		 {
			 
			 if(matrix[character.coordY+1][character.coordX] == ' ') {
				 matrix[character.coordY+1][character.coordX] = character.id;
				 matrix[character.coordY][character.coordX] = ' ';
				 character.coordY = character.coordY+1;
			 }
			 
			 break;
		 }
		 
		 case 'w': 
		 {
			 if(matrix[character.coordY-1][character.coordX] == ' ') {
				 matrix[character.coordY-1][character.coordX] = character.id;
				 matrix[character.coordY][character.coordX] = ' ';
				 character.coordY = character.coordY-1;
			 }
			 
			 break;
		 }
		 
		 case 'e': 
		 {
			 System.exit(0);
		 }
		 
		 }
		 
		 return 0;
	}

	public static void main(String[] args) {
		 Scanner keyboard = new Scanner(System.in);
		 
		 Random randomnumber = new Random();
		 
		 char command;
		 
		 boolean stage1 = true;
		 
		 int a = 0;
		 
		 int rand;
		 
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
		 
		 char [][] level = {
				   {'X','X','X','X','X','X','X','X','X'},
				   {'I',' ',' ',' ','O',' ',' ','k','X'},
				   {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				   {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				   {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				   {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				   {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				   {'X','H',' ',' ',' ',' ',' ',' ','X'},
				   {'X','X','X','X','X','X','X','X','X'}};
	
		 
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
			 
			 //hero phase
			 
			 if(move(matrix, hero, command) == 1) {
				 System.out.println(" ");
				 
				 System.out.println("Now you went up the stairs, new stage.");
				 
				 System.out.println(" ");
				 
				 // you went up the stairs, now a new level must begin.
				 
				 matrix = level.clone();
				 
				 hero = new Character(1,7,'H');
				 
				 Character ogre = new Character(4,1,'O');
				 
				 stage1 = false;
			 } 
			 
			 //k's position has to be k whenever the hero steps out, same for I.
			 //this is not so true for stage 2 I think. In stage 2 hero picks it up, ogre is the one where it stays.
			
			if(stage1) {
			 if(matrix[8][7] == ' ') {
				 matrix[8][7] = 'k';
			 }
			}
			 
			 //guard phase, he will only move in a given pattern according to the array guardpositon.
			 
			 if(stage1){
			 
			 move(matrix, guard, guardposition[a]);
			 
			 a++;
			 
			 if (a == guardposition.length) {
				 a = 0;
			 }
			 
			 if(matrix[guard.coordY-1][guard.coordX] == 'H' || matrix[guard.coordY+1][guard.coordX] == 'H' || matrix[guard.coordY][guard.coordX-1] == 'H' || matrix[guard.coordY][guard.coordX+1] == 'H') {
				 
				 for(int i = 0; i < matrix.length; i++) {
					 System.out.println(matrix[i]);
				 }
				 
				 System.out.println("Game Over.");
				 
				 return;
			 }
			}
			 
			 //the ogre moves randomly, we're going to have to generate random numbers.
			 
			 rand = randomnumber.nextInt(4);
			 
			 switch(rand) {
			 
			 case 0:
				 command = 'a';
				 break;
				 
			 case 1:
				 command = 'd';
				 break;
			
			 case 2:
				 command = 'w';
				 break;
			
			 case 3:
				 command = 's';
				 break;
			 }
			 
			 //Now all you have to do is call ogremove with variable "command".
		 }
	}
}
