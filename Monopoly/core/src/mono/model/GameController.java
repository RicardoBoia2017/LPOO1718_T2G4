package mono.model;

import java.util.ArrayList;
import java.util.Arrays;

import mono.controller.entities.Position;
import mono.model.entities.Board;
import mono.model.entities.CommunityChest;
import mono.model.entities.Dice;
import mono.model.entities.HouseSquare;
import mono.model.entities.Player;
import mono.model.entities.Square;
import mono.model.entities.StartSquare;

/**
 * Singleton
 * @author luis
 *
 */
public class GameController {
	private static GameController instance;
	Board board;
	Player[] players = new Player[4];
	String player1Piece;
	
	private GameController()
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
		
		else if(player1Piece.equals("Thimble")) {
			players[1] = new Player("Bot", "Hat");
			players[2] = new Player("Bot", "Car");
			players[3] = new Player("Bot", "Boot");
		}
		
		else if(player1Piece.equals("Boot")) {
			players[1] = new Player("Bot", "Thimble");
			players[2] = new Player("Bot", "Hat");
			players[3] = new Player("Bot", "Car");
		}
		
		else if(player1Piece.equals("Hat")) {
			players[1] = new Player("Bot", "Thimble");
			players[2] = new Player("Bot", "Boot");
			players[3] = new Player("Bot", "Car");
		}
		
		this.player1Piece = player1Piece;
		
		addAllPlayersToGoSquare();
	}
	
	public void addAllPlayersToGoSquare() {
		addPlayerToBoardSquare(0, 0);
		addPlayerToBoardSquare(0, 1);
		addPlayerToBoardSquare(0, 2);
		addPlayerToBoardSquare(0, 3);
	}
	
	public void addPlayerToBoardSquare(int squareIndex, int playerIndex) {
		board.getBoardArray().get(squareIndex).setPlayerOnTopOfSquare(players[playerIndex]);
	}
	
	public void takePlayerFromBoardSquare(int squareIndex, int playerIndex) {
		board.getBoardArray().get(squareIndex).getplayersOnTopOfSquareArray().remove(playerIndex);
	}
	
	public void movePlayer(int playerIndex, int diceSum) {
		takePlayerFromBoardSquare(players[playerIndex].getPosition(), playerIndex);
		players[playerIndex].updatePosition(diceSum);
		addPlayerToBoardSquare(players[playerIndex].getPosition(), playerIndex);
	}

	public static synchronized GameController getInstance()
	{
		if (instance == null)
			instance = new GameController();

		return instance;
	}

	public void updateGame(int diceSum)
	{
		movePlayer(0, diceSum);
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public Position getCoordFromSquare(Square s1, int amountToWalk) {
		Position finalPosition = new Position(0,0);
		
		if(s1.getName().equals("Start")) {
			int GoSquareGap = 60;
			int StandardSquareGap = 68;
			int boardHeight = 930;
			int boardWidth = 750;
			
			if(amountToWalk <= 10) {
				finalPosition.x = GoSquareGap + StandardSquareGap*amountToWalk;
				finalPosition.y = boardHeight;
			}
			
			else {
				finalPosition.x = boardWidth;
				finalPosition.y = StandardSquareGap*(amountToWalk-10);
			}
			
			return finalPosition;
		}
		
		return finalPosition;
	}

}
