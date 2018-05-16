package mono.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import mono.model.entities.Board;
import mono.model.entities.CommunityChest;
import mono.model.entities.HouseSquare;
import mono.model.entities.Player;
import mono.model.entities.Pair;
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
	int currentPlayer;
	int taxMoney; //money in the middle of the board (money payed by players either by stepping in tax squares or with 'lucky' cards (CC and Chance)
	String player1Piece;
	
	private GameModel()
	{
		board = new Board();
		currentPlayer = 1;
		taxMoney = 0;
	}
	
	public static synchronized GameModel getInstance()
	{
		if (instance == null)
			instance = new GameModel();

		return instance;
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
	
	public Pair rollDice() {
		
		Random rand = new Random();
		Pair values = new Pair();
		
		values.setValue1(1+rand.nextInt(6)); //dice roll 1
		values.setValue2(1+rand.nextInt(6)); //dice roll 2
		
		return values;
	}
	
	public void movePlayer(int diceRoll) {
		
		int playerIndex = currentPlayer - 1; //if currentPlayer = 1, the is index in array is 0
		Player p1 = players.get(playerIndex); 
	
		takePlayerFromBoardSquare(p1.getPosition(), playerIndex);
				
		p1.move(diceRoll);
		
		addPlayerToBoardSquare(p1.getPosition(), playerIndex);
	}
	
	public void squareAction ()
	{
		Player p1 = players.get(currentPlayer - 1);
		board.getBoardArray().get(p1.getPosition()).doAction();
	}
	
	public void addTaxMoney (int value) 
	{
		Player p1 = players.get(currentPlayer - 1);
		p1.removeMoney(value);
		taxMoney += value;
	}
	
	public void giveTaxMoney ()
	{
		Player p1 = players.get(currentPlayer - 1);
		p1.addMoney(taxMoney);
		taxMoney = 0;
	}

	public ArrayList <Player> getPlayers() {return players;}
	public Board getBoard() {return board;} 
	public int getCurrentPlayer(){return this.getCurrentPlayer();}
	public int getTaxMoney () {return taxMoney;} 
	
}
