package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.Game;

public class Main {

	public static void main(String[] args) {
		
		Scanner s = new Scanner (System.in);	
		String game_state = "LEVEL1";
		
		Game game = new Game();
		char [][] map = game.getmap(); 
		
		while(game_state != "GAME OVER") {
		 
			for(int i = 0; i < map.length; i++) {
				 System.out.println(map[i]);
			 }
			
			 System.out.println(" ");
			 System.out.println("Options:");
			 System.out.println("(w) - up");
			 System.out.println("(a) - left");
			 System.out.println("(s) - down");
			 System.out.println("(d) - right");
			 System.out.println("(e) - exit");
			 System.out.println("Command: ");		
			
			 char command = s.next().charAt(0);
			 
			 if(command != 'w' && command != 'a' && command != 's' && command != 'd' && command != 'e') {
					 System.out.println("Invalid option");
					 continue;
			 }
			 
			 if(command == 'e') {
				 System.exit(0);
			 }
			 
			 map = game.updateGame(command); //TODO find a way to pass game state from game to here
			 
			 if(map[0][0] == 'E' || map[0][0] == 'W') {
				 game_state = "GAME OVER";
				 for(int i = 0; i < map.length; i++) {
					 System.out.println(map[i]);
				 }
			 }
		}
		
		s.close();
	}
}
