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
public class GameModel {
	private static GameModel instance;
	Board board;
	ArrayList <Player> players = new ArrayList<Player>();
	String player1Piece;
	
	private GameModel()
	{
		board = new Board();
	}
	
	public void addPlayers(String player1Piece) {
		
		players.add(new Player("ActualPlayer", player1Piece));
		
		if(player1Piece.equals("Car")) {
			players.add(new Player("Bot", "Thimble"));
			players.add(new Player("Bot", "Hat"));
			players.add(new Player("Bot", "Boot"));
		}
		
		else if(player1Piece.equals("Thimble")) {
			players.add(new Player("Bot", "Hat"));
			players.add(new Player("Bot", "Car"));
			players.add(new Player("Bot", "Boot"));
		}
		
		else if(player1Piece.equals("Boot")) {
			players.add(new Player("Bot", "Thimble"));
			players.add(new Player("Bot", "Hat"));
			players.add(new Player("Bot", "Car"));
		}
		
		else if(player1Piece.equals("Hat")) {
			players.add(new Player("Bot", "Thimble"));
			players.add(new Player("Bot", "Boot"));
			players.add(new Player("Bot", "Car"));
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
		board.getBoardArray().get(squareIndex).setPlayerOnTopOfSquare(players.get(playerIndex));
	}
	
	public void takePlayerFromBoardSquare(int squareIndex, int playerIndex) {
		board.getBoardArray().get(squareIndex).getplayersOnTopOfSquareArray().remove(playerIndex);
	}
	
	public void movePlayer(int playerIndex, int diceSum) {
		takePlayerFromBoardSquare(players.get(playerIndex).getPosition(), playerIndex);
		
		players.get(playerIndex).updatePosition();
		
		addPlayerToBoardSquare(players.get(playerIndex).getPosition(), playerIndex);
		
		
	}

	public static synchronized GameModel getInstance()
	{
		if (instance == null)
			instance = new GameModel();

		return instance;
	}

	public void updateGame(int diceSum)
	{
		movePlayer(0, diceSum);
	}
	
	public ArrayList <Player> getPlayers() {
		return players;
	}
	
	public Board getBoard() {
		return board;
	} 
	
	public Position getCoordFromSquare(Player p1, Square s1, int amountToWalk) {
		Position finalPosition = new Position(p1.getX(),p1.getY());
		int currentPosition = p1.getPosition();
		int GoSquareGap = 101;
		int StandardSquareGap = 68;
		int boardHeight = 930;
		int boardWidth = 750;
		
//		System.out.println("Position " + currentPosition + "    ATW " + amountToWalk);
		
		
		while (amountToWalk > 0)
		{
			if (currentPosition == 0)
			{
				finalPosition.x += GoSquareGap - 51 + StandardSquareGap/2; 
			}
			
			else if (currentPosition < 10)
			{
				finalPosition.x += StandardSquareGap;
			}
			
			else if (currentPosition == 10)
			{
				finalPosition.y -= GoSquareGap - 5;
			}
			
			else if (currentPosition < 20)
			{
				finalPosition.y -= StandardSquareGap;
			}
			
			else if (currentPosition == 20)
			{
				finalPosition.x -= GoSquareGap - 51 + StandardSquareGap/2;;
			}
			
			else if (currentPosition < 30)
			{
				finalPosition.x -= StandardSquareGap;
			}
			
			else if (currentPosition == 30)
			{
				finalPosition.y += GoSquareGap - 5;
			}
			
			else if (currentPosition < 40)
			{
				finalPosition.y += StandardSquareGap;
			}
			
//			System.out.println(finalPosition.x + "   " + finalPosition.y);
//			System.out.println(currentPosition);
			amountToWalk --;
			
			if (currentPosition == 39)
				currentPosition = 0;
			else
				currentPosition++;
		}
		
//		if(s1.getName().equals("Start")) {
//
//			
//			if(amountToWalk <= 10) {
//				finalPosition.x = GoSquareGap + StandardSquareGap*amountToWalk;
//				finalPosition.y = boardHeight;
//			}
//			
//			else {
//				finalPosition.x = boardWidth;
//				finalPosition.y = 930 - StandardSquareGap*(amountToWalk-10);
//			}
//			
//			return finalPosition;
//		}
		
		return finalPosition;
	}

}
