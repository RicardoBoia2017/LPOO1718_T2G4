package mono.model;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Arrays;
import java.util.Random;

import mono.controller.GameController;
import mono.model.entities.Board;
import mono.model.entities.CommunityChest;
import mono.model.entities.PropertySquare;
import mono.model.entities.JailSquare;
import mono.model.entities.Player;
import mono.model.entities.Pair;
import mono.model.entities.Square;
import mono.model.entities.StartSquare;

/**
 * Singleton
 * @author luis
 *
 */
public class Game {
	private static Game instance;
	Board board;
	ArrayList <Player> players = new ArrayList<Player>();
	int currentPlayer;
	int taxMoney; 
	String player1Piece;
	Boolean playerIsInJail;
	
	private Game()
	{
		board = new Board();
		currentPlayer = 1;
		taxMoney = 0;
		playerIsInJail = false;
	}
	
	public void setGameModelInstanceToNull() {
		instance = null;
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
	
	public void movePlayer(Pair diceRoll) {
		
		int playerIndex = currentPlayer - 1; //if currentPlayer = 1, the is index in array is 0
		Player p1 = players.get(playerIndex); 
	
		takePlayerFromBoardSquare(p1.getPosition(), playerIndex);
				
		p1.move(diceRoll);
		
		addPlayerToBoardSquare(p1.getPosition(), playerIndex);
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
		board.getBoardArray().get(p1.getPosition()).doAction(p1);
	}
	
	public void setCurrentPlayer(int playerNumber)
	{
		this.currentPlayer = playerNumber;
	}
	
	public void setTaxMoney (int newValue)
	{
		this.taxMoney = newValue;
	}

	public static synchronized Game getInstance() 
	{ 
		if (instance == null)
			instance = new Game(); 
		
		return instance; 
	}
	
	public void tellJailPlayerWantsToPayFine() {
		JailSquare j1 = (JailSquare) board.getBoardArray().get(10);
		j1.aproveFine();
	}
	
	public void tellControllerPlayerIsInJail(Boolean b) {
		System.out.println("Telling the controller he is in jail or not");
		playerIsInJail = b;
		GameController.getInstance().tellViewToDisplayJailDialog();
	}
	
	public int checkPropertyAvailibility()
	{
		Player p1 = players.get(currentPlayer - 1); 
		Square s1 = this.board.getBoardArray().get(p1.getPosition());
		
		Vector <String> allowedTypes = new Vector <String> (3);
		allowedTypes.add("Property");
		allowedTypes.add("Station");
		allowedTypes.add("Companies");
		
		if (!allowedTypes.contains(s1.getType())) //trying to buy square that can't be bought
			return -1;

		else if (checkIfPropertyIsOwned (s1.getName())) //trying to buy a square that is already owned
			return -2;
		
		return 0; //square can be bought
		
	}

	public int buyProperty()
	{
		Player p1 = players.get(currentPlayer - 1); 
		PropertySquare ps1 = (PropertySquare) board.getBoardArray().get(p1.getPosition());
		
		int res = p1.removeMoney(ps1.getPrice());
		
		
		if (res == 0)	
		{
			p1.addProperty(ps1);
			ps1.setOwner(p1);
		}
		
		return res;
		
	}
	
	private boolean checkIfPropertyIsOwned (String squareName)
	{
		for (Player p: this.players)
		{
			for (Square s: p.getPropertiesOwned())
				
				if (s.getName() == squareName)
					return true;
		}
		
		return false;
	}
	
	public ArrayList <Player> getPlayers() {return players;}
	public Board getBoard() {return board;} 
	public int getCurrentPlayer(){return this.getCurrentPlayer();}
	public int getTaxMoney () {return taxMoney;}
	public boolean getplayerIsInJail() {
		return playerIsInJail;
	}
}
