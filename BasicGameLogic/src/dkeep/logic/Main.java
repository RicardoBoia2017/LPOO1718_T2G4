package dkeep.logic;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class Main {
	
	public static void clubmove(char [][] level, Character character, Character club, int clubplace) {
		
		Random randomnum = new Random();
		
		int rand;
		
		switch(clubplace) {
		
		//appears at the left adjacent cell, in relation to our character - the ogre.
		case 0:
		{
			if(level[character.coordY][character.coordX-1] == ' '){
				level[character.coordY][character.coordX-1] = club.id;
				level[club.coordY][club.coordX] = ' ';
				club.coordX = character.coordX-1;
				club.coordY = character.coordY;
			} else {
				rand = randomnum.nextInt(4);
				clubmove(level, character, club, rand); //recursion to avoid cases where ogre has an X in the position the club wants to move to.
			}
			
			break;
		}
		
		//.. right ...
		case 1:
		{
			 if(level[character.coordY][character.coordX+1] == ' ') {
				 level[character.coordY][character.coordX+1] = club.id;
				 level[club.coordY][club.coordX] = ' ';
				 club.coordX = character.coordX+1;
				 club.coordY = character.coordY;
			 }
			 
			 else if(level[character.coordY][character.coordX+1] == 'k') {
				 club.id = '$';
				 level[character.coordY][character.coordX+1] = club.id;
				 level[club.coordY][club.coordX] = ' ';
				 club.coordX = character.coordX+1;
				 club.coordY = character.coordY;
			 } else {
					rand = randomnum.nextInt(4);
					clubmove(level, character, club, rand);
				}
			 
			 break;
		}
		
		//down
		case 2:
		{
			 if(level[character.coordY+1][character.coordX] == ' ') {
				 level[character.coordY+1][character.coordX] = club.id;
				 level[club.coordY][club.coordX] = ' ';
				 club.coordY = character.coordY+1;
				 club.coordX = character.coordX;
			 } else {
					rand = randomnum.nextInt(4);
					clubmove(level, character, club, rand); 
				}
			 
			 break;
		}
		
		//up
		case 3:
		{
			 if(level[character.coordY-1][character.coordX] == ' ') {
				 level[character.coordY-1][character.coordX] = club.id;
				 level[club.coordY][club.coordX] = ' ';
				 club.coordY = character.coordY-1;
				 club.coordX = character.coordX;
			 }
			 
			 else if(level[character.coordY-1][character.coordX] == 'k') {
				 club.id = '$';
				 level[character.coordY-1][character.coordX] = club.id;
				 level[club.coordY][club.coordX] = ' ';
				 club.coordY = character.coordY-1;
				 club.coordX = character.coordX;
			 } else {
					rand = randomnum.nextInt(4);
					clubmove(level, character, club, rand); 
				}
			 
			 break;
		}
		
		}
	}
	
	public static void ogremove(char [][] level, Character character, int command) {
		
		switch (command){
		
		// left
		case 0:
		{
			if(level[character.coordY][character.coordX-1] == ' '){
				level[character.coordY][character.coordX-1] = character.id;
				level[character.coordY][character.coordX] = ' ';
				character.coordX = character.coordX-1;
			}
			
			break;
		}
		
		//right
		case 1:
		{
			 if(level[character.coordY][character.coordX+1] == ' ') {
				 level[character.coordY][character.coordX+1] = character.id;
				 level[character.coordY][character.coordX] = ' ';
				 character.coordX = character.coordX+1;
			 }
			 
			 else if(level[character.coordY][character.coordX+1] == 'k') {
				 level[character.coordY][character.coordX+1] = '$';
				 level[character.coordY][character.coordX] = ' ';
				 character.coordX = character.coordX+1;
			 }
			 
			 break;
		}
		
		//down
		case 2:
		{
			 if(level[character.coordY+1][character.coordX] == ' ') {
				 level[character.coordY+1][character.coordX] = character.id;
				 level[character.coordY][character.coordX] = ' ';
				 character.coordY = character.coordY+1;
			 }
			 
			 break;
		}
		
		//up
		case 3:
		{
			 if(level[character.coordY-1][character.coordX] == ' ') {
				 level[character.coordY-1][character.coordX] = character.id;
				 level[character.coordY][character.coordX] = ' ';
				 character.coordY = character.coordY-1;
			 }
			 
			 else if(level[character.coordY-1][character.coordX] == 'k') {
				 level[character.coordY-1][character.coordX] = '$';
				 level[character.coordY][character.coordX] = ' ';
				 character.coordY = character.coordY-1;
			 }
			 
			 break;
		}
		}
				 
	}
	
	public static int move(char[][] matrix, Character character, char command, int stage) {
		 switch(command) {
		 
		 case 'a': 
		 {
			 if(matrix[character.coordY][character.coordX-1] == ' ') {
				 matrix[character.coordY][character.coordX-1] = character.id;
				 matrix[character.coordY][character.coordX] = ' ';
				 character.coordX = character.coordX-1;
			 } 
			 
			 //Checks if hero steps over the lever
			 else if(matrix[character.coordY][character.coordX-1] == 'k' && stage == 1) {
				 
				 matrix[5][0] = 'S';
				 matrix[6][0] = 'S';
				 
				 matrix[character.coordY][character.coordX-1] = character.id;
				 matrix[character.coordY][character.coordX] = ' ';
				 character.coordX = character.coordX-1;
			 } 
			 //to open the door you need to check if the Hero has the key, aka, is in K state.
			 else if(matrix[character.coordY][character.coordX-1] == 'I' && stage == 2 && character.id == 'K') {
				 matrix[character.coordY][character.coordX-1] = 'S';
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
			 
			 //checks if hero grabs key
			 else if (matrix[character.coordY][character.coordX+1] == 'k')
			 {
				 character.id = 'K';
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
			 
			 else if (matrix[character.coordY-1][character.coordX] == 'k'){
				 character.id = 'K';
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
		/* 
		 Random randomnumber = new Random();
		 
		 Random randomclub = new Random();
		 
		 char command;
		 
		 int stage = 1;
		 
		 int a = 0;
		 
		 int rand;
		 
		 int clubplacement;
		 
		 Character hero = new Character(1,1,'H');
		 
		 Character guard = new Character(8,1,'G');
		 
		 Character ogre = new Character (4,1,'O');
		 
		 //the club "behaves" like a character
		 
		 Character club = new Character(3,1,'*');
		 		 
		 char [] guardposition = {'a', 's', 's','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
		
		*/
		
		 /* 
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
				   {'I',' ',' ','*','O',' ',' ','k','X'},
				   {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				   {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				   {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				   {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				   {'X',' ',' ',' ',' ',' ',' ',' ','X'},
				   {'X','H',' ',' ',' ',' ',' ',' ','X'},
				   {'X','X','X','X','X','X','X','X','X'}};*/
	
			 //hero phase
			 
			 if(/*move hero returns 1 passing the doors*/) {
				 
				if (stage == 1){
				
				System.out.println(" ");
				System.out.println("Now you went up the stairs, new stage.");
				System.out.println(" ");
				 
				 // you went up the stairs, now a new level must begin.
				 
				//update game stage
				 
				hero.coordX = 1;
				hero.coordY = 7;
				 
				stage = 2;
				continue;
				}
				 
				 else if (stage == 2)
					 return;
			 } 
			 
			 //k's position has to be k whenever the hero steps out, same for I.
			 //this is not so true for stage 2 I think. In stage 2 hero picks it up, ogre is the one where it stays.
			
			if(stage == 1) {
			 if(matrix[8][7] == ' ') {
				 matrix[8][7] = 'k';
			 }
			}
			 
			 //guard phase, he will only move in a given pattern according to the array guardpositon.
			 
			 if(stage == 1){
			 
			 move(matrix, guard, guardposition[a],stage);
			 
			 a++;
			 
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
		 
		// keyboard.close();
}
