package mono.controller;

import java.util.ArrayList;
import java.util.Arrays;

import mono.controller.entities.Board;
import mono.controller.entities.CommunityChest;
import mono.controller.entities.HouseSquare;
import mono.controller.entities.Player;
import mono.controller.entities.Square;
import mono.controller.entities.StartSquare;

/**
 * Singleton
 * @author luis
 *
 */
public class Game {
	private static Game instance;
	Board board;
	Player[] players;
	String player1Piece;
	
	private Game()
	{
		board = new Board();
	}
	
	public void addPlayers(String player1Piece) {
		
		players[0] = new Player("ActualPlayer", player1Piece);
		
		if(player1Piece.equals("Car")) {
			players[1] = new Player("Bot", "Thimble");
			players[2] = new Player("Bot", "Hat");
			players[3] = new Player("Bot", "Boot");
		}
		
		if(player1Piece.equals("Thimble")) {
			players[1] = new Player("Bot", "Hat");
			players[2] = new Player("Bot", "Car");
			players[3] = new Player("Bot", "Boot");
		}
		
		if(player1Piece.equals("Boot")) {
			players[1] = new Player("Bot", "Thimble");
			players[2] = new Player("Bot", "Hat");
			players[3] = new Player("Bot", "Car");
		}
		
		if(player1Piece.equals("Boot")) {
			players[1] = new Player("Bot", "Thimble");
			players[2] = new Player("Bot", "Hat");
			players[3] = new Player("Bot", "Car");
		}
		
		this.player1Piece = player1Piece;
	}

	public static synchronized Game getInstance()
	{
		if (instance == null)
			instance = new Game();

		return instance;
	}

	public void updateGame(int diceSum)
	{
		(players[0]).setAmountToWalk(diceSum);
		board.getBoardArray().get(diceSum).setPlayerOnTopOfSquare(players[0]);
	}
	
	public Player[] getPlayers() {
		return players;
	}

}
