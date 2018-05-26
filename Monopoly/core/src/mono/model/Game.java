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
	int moveFromCards;
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
		moveFromCards = 0;
		
		chanceQueue = new LinkedList <Integer>();
		initializeChanceQueue();
		cChestQueue = new LinkedList <Integer>();
		initializeCChestQueue();

	}

	private void initializeChanceQueue()
	{		
		for (int i = 1; i <= 10; i++)
			chanceQueue.add(i);
		
		Collections.shuffle((List<?>) chanceQueue);
		
	}
	
	private void initializeCChestQueue()
	{
		for (int i = 1; i <= 10; i++)
			cChestQueue.add(i);
		
		Collections.shuffle((List<?>) cChestQueue);	
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
//		return new Pair (2,1);
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
		String res = inCardPosition();

		if(this.moveFromCards != 0)
			movePlayerEndTurn ();
		
		changeCardEndTurn(res);

		changePlayer();
		
	}
	
	private void movePlayerEndTurn()
	{
		Player p1 = players.get(currentPlayer - 1);

		String res = inCardPosition().substring(0, 2);

		if (res.substring(0,2).equals("CH") &&
				getFirstChanceCardId()	== 3)
				p1.freeFromJail();
		
		movePlayer(this.moveFromCards);
		
		if (res.substring(0,2).equals("CH") &&
				getFirstChanceCardId()	== 3)
			p1.sendToJail();
		
		this.moveFromCards = 0;
	}

	private void changeCardEndTurn(String res)
	{
		if (res != null) 
		{
			int firstCard;

			if (res.substring(0,2).equals("CH"))
			{
				firstCard = chanceQueue.poll();
				chanceQueue.add (firstCard);
			}
			
			else
			{
				firstCard = cChestQueue.poll();
				cChestQueue.add (firstCard);
			}
		}	
	}

	private void changePlayer()
	{	
		if (currentPlayer == players.size())
			this.currentPlayer = 1;
		
		else
			this.currentPlayer++;
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
//		System.out.println("Telling the controller he is in jail or not");
//		System.out.print("Player ");
//		System.out.print(players.get(currentPlayer).getName());
//		System.out.print("is in jail? ");
//		System.out.print(players.get(currentPlayer).getPlayerIsInJail());
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
	
	public int buyHouse() {
		Player p1 = players.get(currentPlayer - 1);
		Property ps1 = (Property) board.getBoardArray().get(p1.getPosition());

		int res = p1.removeMoney(ps1.getCostOfAHouseByColor());

		if (res == 0) {
			ps1.buyHouse();
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
	
	private boolean checkIfPropertyIsMortgaged (String squarename) {
		BuyableSquare s1 = null;
		
		for(int i = 0; i < board.getBoardArray().size(); i++) {
			if(board.getBoardArray().get(i).getName().equals(squarename)) {
				s1 = (BuyableSquare) board.getBoardArray().get(i);
			}
		}
		
		return s1.getMortgageStatus();
	}
	
	public int checkHouseAvailability() { 
		
	    Player p1 = players.get(currentPlayer - 1);  
	    Square s1 = this.board.getBoardArray().get(p1.getPosition()); 
		     
	    if (!s1.getType().equals("Property")) //trying to place a house in a square other than property 
	      return -1; 
	     
	    else if(checkIfPropertyIsMortgaged(s1.getName())) //trying to place a house in a mortgaged property 
	      return -2; 
		     
	    Property s2 = (Property) s1; //at this point, we can presume it is a valid property 
	     
	    if(!checkIfPlayerOwnsAllPropertiesOfThatColor(p1, s2.getColor())) //trying to place a house without owning all of that color 
	      return -4; 
	    
	    if(s2.getHouses() == 4) //cannot have more than 4 houses
	    	return -5;
	     
	    return 0; 
	  } 
	
	private boolean checkIfPlayerOwnsAllPropertiesOfThatColor(Player p1, String color) {
		
		int numberOfPropertiesOfColor = 0;
		
		numberOfPropertiesOfColor = countPropertiesOfAColor(color);
		
		return checkIfCountingWasValid(color, numberOfPropertiesOfColor);
		
	}
	
	
	public int countPropertiesOfAColor(String color) {
		int countPropertiesOfColor = 0;
		
		for(int i = 0; i < players.size(); i++) {
			for(int j = 0; j < players.get(i).getPropertiesOwned().size(); j++) {
				if(players.get(i).getPropertiesOwned().get(j).getType().equals("Property")) {
					Property s2 = (Property) players.get(i).getPropertiesOwned().get(j);
					
					if(s2.getColor().equals(color)) {
						countPropertiesOfColor++;
					}
				}
			}
		}
		
		return countPropertiesOfColor;
	}
	
	public Boolean checkIfCountingWasValid(String color, int count) {
		if(color.equals("BROWN") || color.equals("DARK_BLUE")) {
			return (count == 2);
		}
		
		else {
			return (count == 3);
		}
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
			res =  "CC " + cChestQueue.peek();
			
		return res;
	}
	
	public void setMoveFromCards (int value) {this.moveFromCards = value;}
	
	public ArrayList <Player> getPlayers() {return players;}
	public Board getBoard() {return board;} 
	public int getCurrentPlayer(){return this.currentPlayer;}
	public int getTaxMoney () {return taxMoney;}
	public int getFirstChanceCardId() {return chanceQueue.peek();}
	public int getFirstCChestCardId() {return cChestQueue.peek();}
	public boolean getplayerIsInJail() {
//		System.out.print("Player ");
//		System.out.print(players.get(currentPlayer).getName());
//		System.out.print("is in jail? ");
//		System.out.print(players.get(currentPlayer).getPlayerIsInJail());
		return players.get(currentPlayer - 1).getPlayerIsInJail();
	}

	public int checkHotelAvailability() {
	    Player p1 = players.get(currentPlayer - 1);  
	    Square s1 = this.board.getBoardArray().get(p1.getPosition()); 
		     
	    if (!s1.getType().equals("Property")) //trying to place a hotel in a square other than property 
	    	return -1; 
	    
	    Property s2 = (Property) s1; //at this point, we can presume it is a valid property 
	    
	    if(s2.getHotels() == 1) //cannot have more than one hotel
	    	return -4;
	    
	    if(s2.getHouses() != 4)  //you cannot have a hotel until you have 4 houses
	    	return -2;
	    
	    return 0;
	}

	public int buyHotel() {
		Player p1 = players.get(currentPlayer - 1);
		Property ps1 = (Property) board.getBoardArray().get(p1.getPosition());

		int res = p1.removeMoney(ps1.getCostOfAHouseByColor());

		if (res == 0) {
			ps1.buyHotel();
			ps1.setHouses(0);
		}

		return res;
	}

	public int checkIfYouCanMortgage() {
		Player p1 = players.get(currentPlayer - 1);
		int res = 0;
		
		return res;
	}

	public int mortgageProperty(int card) {
		Player p1 = players.get(currentPlayer - 1);
		BuyableSquare s1 = p1.getPropertiesOwned().get(card);
		
		s1.setInMortgage(true);
		p1.addMoney(s1.getMortgateValue());
		
		return 0;
	}
}
