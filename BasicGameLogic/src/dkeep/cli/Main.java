package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.Game;
import dkeep.logic.Map;

public class Main {

	public static void main(String[] args) {
		
		Scanner s = new Scanner (System.in);	
		String game_state = "LEVEL1";
		
		Game game = new Game(0);
		Map map = game.getMap();
		
		while(game_state != "GAME OVER") {
			
			for(int i = 0; i < map.getMatrix().length; i++) {
				 System.out.println(map.getMatrix()[i]);
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
			 
			 if(game.getLevelLogic().getLevelState() == "Over" || game.getLevelLogic().getLevelState() == "Victory") {
				 game_state = "GAME OVER";
				 
				 for(int i = 0; i < map.getMatrix().length; i++) {
					 System.out.println(map.getMatrix()[i]);
				 }
			 }
		}
		
		s.close();
	}
}
