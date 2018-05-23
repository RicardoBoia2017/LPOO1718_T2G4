package mono.model;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Queue;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import mono.controller.GameController;
import mono.model.entities.Board;
import mono.model.entities.BuyableSquare;
import mono.model.entities.Chance;
import mono.model.entities.CommunityChest;
import mono.model.entities.Property;
import mono.model.entities.Jail;
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
	Queue <Integer> chanceQueue;
	Queue <Integer> cChestQueue;

	public static synchronized Game getInstance() 
	{ 
		if (instance == null)
			instance = new Game(); 
		
		return instance; 
	}
	
	public void setGameModelInstanceToNull() {
		instance = null;
	}
	
	private Game()
	{
		board = new Board();
		currentPlayer = 1;
		taxMoney = 0;
		
		chanceQueue = new LinkedList <Integer>();
		initializeChanceQueue();
		cChestQueue = new LinkedList <Integer>();

	}
	
	private void initializeChanceQueue()
	{		
		for (int i = 1; i <= 9; i++)
			chanceQueue.add(i);
		
		Collections.shuffle((List<?>) chanceQueue);
		
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
		
		if (inCardPosition() != null) 
		{
			int firstElement = chanceQueue.poll();
			chanceQueue.add (firstElement);
		}
		
		return values;
	}
	
	public void movePlayer(Pair diceRoll) {
		
		int playerIndex = currentPlayer - 1; //if currentPlayer = 1, index in array is 0
		Player p1 = players.get(playerIndex); 
	
		takePlayerFromBoardSquare(p1.getPosition(), playerIndex); //DOES NOT WORK
				
		p1.move(diceRoll);

		if(p1.getPosition() == 0) {
			tellGoSquareItsNotFirstVisit();
		}
		
		addPlayerToBoardSquare(p1.getPosition(), playerIndex);
	}
	
	public void movePlayer(int diceRoll) {
		
		int playerIndex = currentPlayer - 1; //if currentPlayer = 1, index in array is 0
		Player p1 = players.get(playerIndex); 
	
		takePlayerFromBoardSquare(p1.getPosition(), playerIndex);
				
		p1.move(diceRoll);
		
		if(p1.getPosition() == 0) {
			tellGoSquareItsNotFirstVisit();
		}
		addPlayerToBoardSquare(p1.getPosition(), playerIndex);
	}
	
	public void squareAction ()
	{
		Player p1 = players.get(currentPlayer - 1);
		board.getBoardArray().get(p1.getPosition()).doAction(p1);
	}
	
	public void endTurn ()
	{
		
		if (currentPlayer == players.size())
			this.currentPlayer = 1;
		
		else
			this.currentPlayer++;
		
		System.out.println(this.currentPlayer);
	}
	
	public void setTaxMoney (int newValue)
	{
		this.taxMoney = newValue;
	}
	
	public void tellJailPlayerWantsToPayFine() {
		Jail j1 = (Jail) board.getBoardArray().get(10);
		j1.aproveFine();
	}
	
	public void tellGoSquareItsNotFirstVisit() {
		StartSquare s1 = (StartSquare) board.getBoardArray().get(0);
		s1.setFirstVisitToFalse();
	}
	
	public void tellControllerPlayerIsInJail() {
		System.out.println("Telling the controller he is in jail or not");
		System.out.print("Player ");
		System.out.print(players.get(currentPlayer).getName());
		System.out.print("is in jail? ");
		System.out.print(players.get(currentPlayer).getPlayerIsInJail());
		GameController.getInstance().tellViewToDisplayJailDialog();
	}
	
	public int checkPropertyAvailibility()
	{
		Player p1 = players.get(currentPlayer - 1); 
		Square s1 = this.board.getBoardArray().get(p1.getPosition());
		
		Vector <String> allowedTypes = new Vector <String> (3);
		allowedTypes.add("Property");
		allowedTypes.add("Station");
		allowedTypes.add("Company");
		
		if (!allowedTypes.contains(s1.getType())) //trying to buy square that can't be bought
			return -1;

		else if (checkIfPropertyIsOwned (s1.getName())) //trying to buy a square that is already owned
			return -2;
		
		return 0; //square can be bought
		
	}

	public int buyProperty()
	{
		Player p1 = players.get(currentPlayer - 1); 
		BuyableSquare ps1 = (BuyableSquare) board.getBoardArray().get(p1.getPosition());
		
		int res = p1.removeMoney(ps1.getCost());
		
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
	
	public void givePlayer200Money(Player p) {
		System.out.println(p.getMoney());
		p.addMoney(200);
		System.out.println(p.getMoney());
	}
	
	public String inCardPosition()
	{
		String res= null;
		Player p1 = players.get(currentPlayer - 1); 
		Vector <Integer> chancePositions = new Vector<Integer> (3);
		Vector <Integer> cchestPositions = new Vector<Integer> (3);
		
		chancePositions.add(7);
		chancePositions.add(22);
		chancePositions.add(36);
		
		cchestPositions.add(2);
		cchestPositions.add(17);
		cchestPositions.add(33);
		
		if(chancePositions.contains(p1.getPosition()))
			res =  "CH " + chanceQueue.peek();

		
		else if(cchestPositions.contains(p1.getPosition()))
//			res =  "CC " + chanceQueue.peek();
			res =  "CH " + chanceQueue.peek();
			
		return res;
	}
	
	public ArrayList <Player> getPlayers() {return players;}
	public Board getBoard() {return board;} 
	public int getCurrentPlayer(){return this.getCurrentPlayer();}
	public int getTaxMoney () {return taxMoney;}
	public int getFirstChanceCardId() {return chanceQueue.peek();}
	public int getFirstCChestCardId() {return cChestQueue.peek();}
	public boolean getplayerIsInJail() {
		System.out.print("Player ");
		System.out.print(players.get(currentPlayer).getName());
		System.out.print("is in jail? ");
		System.out.print(players.get(currentPlayer).getPlayerIsInJail());
		return players.get(currentPlayer - 1).getPlayerIsInJail();
	}
}
