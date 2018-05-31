package mono.model;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Queue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import mono.controller.GameController;
import mono.model.entities.Board;
import mono.model.entities.Bot;
import mono.model.entities.BuyableSquare;
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
	Player currentPlayer;
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
		taxMoney = 0;
		
		initializeChanceQueue();
		initializeCChestQueue();
	}

	private void initializeChanceQueue()
	{				
		chanceQueue = new LinkedList <Integer>();

		for (int i = 1; i <= 10; i++)
			chanceQueue.add(i);
		
		Collections.shuffle((List<?>) chanceQueue);	
	}
	
	private void initializeCChestQueue()
	{
		cChestQueue = new LinkedList <Integer>();

		for (int i = 1; i <= 10; i++)
			cChestQueue.add(i);
		
		Collections.shuffle((List<?>) cChestQueue);	
	}
	
	public void addPlayer (String piece)
	{
		if (players.size() >= 4)
			return;
		
		if (checkifPieceIsTaken (piece))
			return;

		int gameId = players.size() + 1;
		String name = "Player" + gameId;
		
		players.add(new Player (gameId, name, piece, false));
		currentPlayer = players.get(0);
	}
	
	public void addBots ()
	{
		if (players.size() >= 4)
			return;
		
		ArrayList <String> allPieces = new ArrayList <String> ();
		
		allPieces.add("Boot");
		allPieces.add("Car");
		allPieces.add("Hat");
		allPieces.add("Thimble");
		
		int playersLeft = 4 - players.size();
		for (int i = 1; i <= playersLeft; i++ )
		{
			String botName = "Bot" + i;
			int gameId = players.size() + 1;
			
			for (int j = 0; j < allPieces.size(); j++)
			{
				if (!checkifPieceIsTaken(allPieces.get(j)))
				{
					players.add(new Bot(gameId, botName, allPieces.get(j)));
					break;
				}
			}
		}
	}
	
	private boolean checkifPieceIsTaken(String piece)
	{
		for (Player p : players)
		{
			if (p.getBoardPiece().getType().equals(piece))
				return true;
		}
		
		return false;
	}
	
	public void addPlayers(String player1Piece) {
		
		if (players.size() != 0)
			return;
		
		players.add(new Player(1, "ActualPlayer", player1Piece, false));
		
		if(player1Piece.equals("Car")) {
			players.add(new Bot(2, "Bot1", "Thimble"));
			players.add(new Bot(3, "Bot2", "Hat"));
			players.add(new Bot(4, "Bot3", "Boot"));
		}
		
		else if(player1Piece.equals("Thimble")) {
			players.add(new Bot(2, "Bot1", "Hat"));
			players.add(new Bot(3, "Bot2", "Car"));
			players.add(new Bot(4, "Bot3", "Boot"));
		}
		
		else if(player1Piece.equals("Boot")) { 
			players.add(new Bot(2, "Bot1", "Thimble"));
			players.add(new Bot(3, "Bot2", "Hat"));
			players.add(new Bot(4, "Bot3", "Car"));
		}
		
		else if(player1Piece.equals("Hat")) {
			players.add(new Bot(2, "Bot1", "Thimble"));
			players.add(new Bot(3, "Bot2", "Boot"));
			players.add(new Bot(4, "Bot3", "Car"));
		}
		
		this.player1Piece = player1Piece;
		
		currentPlayer = players.get(0);
	}
	
	public Pair rollDice() {
		
		Random rand = new Random();
		Pair values = new Pair();
		
		values.setValue1(1+rand.nextInt(6));
		values.setValue2(1+rand.nextInt(6)); 
		
		currentPlayer.setCurrentDiceroll(values.getValue1() + values.getValue2()); 
		
//		return values;

		return new Pair (20,10);
	}
	
	public void movePlayer(int diceRoll, boolean sameValue) {
					
		currentPlayer.move(diceRoll, sameValue);
		
		if( currentPlayer.getPosition() == 0) {
			tellGoSquareItsNotFirstVisit();
		}
				
	}
	
	public void squareAction ()
	{
		board.getSquares().get(currentPlayer.getPosition()).doAction(currentPlayer);
	} 
	
	public void endTurn ()
	{	
		String res = inCardPosition(false);
		
		changeCardEndTurn(res);
		
		
		changePlayer(); 
		
	}
	
	public void changeCardEndTurn(String res)
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
			
			currentPlayer.setInCardPosition(0);
		}	
	}

	private void changePlayer()
	{	
		if (currentPlayer.getGameId() == players.size())
			this.currentPlayer = players.get(0);
		
		else
			this.currentPlayer = players.get(currentPlayer.getGameId());
	}
	
	public void setTaxMoney (int newValue)
	{
		this.taxMoney = newValue;
	}
	
	public void tellJailPlayerWantsToPayFine() {
		Jail j1 = (Jail) board.getSquares().get(10);
		j1.aproveFine();
	}
	
	public void tellGoSquareItsNotFirstVisit() {
		StartSquare s1 = (StartSquare) board.getSquares().get(0);
		s1.setFirstVisitToFalse();
	}
	
	public void tellControllerPlayerIsInJail() {
		GameController.getInstance().tellViewToDisplayJailDialog();
	}
	
	public int checkPropertyAvailibility()
	{
		Player p1 = currentPlayer;
		Square s1 = this.board.getSquares().get(p1.getPosition());
		
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
		Player p1 = currentPlayer;
		BuyableSquare ps1 = (BuyableSquare) board.getSquares().get(p1.getPosition());
		
		int res = p1.removeMoney(ps1.getCost(), false);
		
		if (res == 0)	
		{
			p1.addProperty(ps1);
			ps1.setOwner(p1);
		}
		
		return res;
		
	}
	
	public int buyHouse(Property ps1) {
		Player p1 = currentPlayer;

		int res = p1.removeMoney(ps1.getBuildingCost(), false);

		if (res == 0) 
			ps1.buyHouse();
		
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
		
		for(int i = 0; i < board.getSquares().size(); i++) {
			if(board.getSquares().get(i).getName().equals(squarename)) {
				s1 = (BuyableSquare) board.getSquares().get(i);
			}
		}
		
		return s1.getMortgageStatus();
	}
	
	public int checkHouseAvailability(BuyableSquare s1) { 
		
	    Player p1 = currentPlayer; 
		     
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
		p.addMoney(200);
	}
	
	public String inCardPosition(boolean currentPosition)
	{
		String res= null;
		Player p1 = currentPlayer;
		Vector <Integer> chancePositions = new Vector<Integer> (3);
		Vector <Integer> cchestPositions = new Vector<Integer> (3);
		
		int position = p1.getInCardPosition();
		
		if (currentPosition)
			position = p1.getPosition();
			
		chancePositions.add(7);
		chancePositions.add(22);
		chancePositions.add(36);
		
		cchestPositions.add(2);
		cchestPositions.add(17);
		cchestPositions.add(33);
		
		if(chancePositions.contains(position))
			res =  "CH " + chanceQueue.peek();

		
		else if(cchestPositions.contains(position))
			res =  "CC " + cChestQueue.peek();
			
		return res;
	}
		
	public int checkHotelAvailability(BuyableSquare s1) {
//	    Player p1 = currentPlayer;
		     
	    if (!s1.getType().equals("Property")) //trying to place a hotel in a square other than property 
	    	return -1; 
	    
	    Property s2 = (Property) s1; //at this point, we can presume it is a valid property 
	    
	    if(s2.getHotels() == 1) //cannot have more than one hotel
	    	return -4;
	    
	    if(s2.getHouses() != 4)  //you cannot have a hotel until you have 4 houses
	    	return -2;
	    
	    return 0;
	}

	public int buyHotel(Property ps1) {
		Player p1 = currentPlayer;

		int res = p1.removeMoney(ps1.getBuildingCost(), false);

		if (res == 0) {
			ps1.buyHotel();
			ps1.setHouses(0);
		} 

		return res;
	}

	public int mortgageProperty(int card) {
		Player p1 = currentPlayer;
		
		if (card >= p1.getPropertiesOwned().size())
			return -1;
		
		BuyableSquare s1 = p1.getPropertiesOwned().get(card);
		
		s1.setInMortgage(true);
		p1.addMoney(s1.getMortgateValue());
		
		return 0;
	}

	public int reBuyProperty (int card)
	{
		Player p1 = currentPlayer;
		BuyableSquare s1 = p1.getPropertiesOwned().get(card);
		
		s1.setInMortgage(false);
		p1.removeMoney( (int)Math.ceil (s1.getMortgateValue() * 1.10 ), false);
		
		return 0;
	}
	
	public ArrayList <Player> getPlayers() {return players;}
	public Board getBoard() {return board;} 
	public Player getCurrentPlayer() {return this.currentPlayer;}
	public Square getCurrentSquare() {return this.board.getSquares().get(currentPlayer.getPosition());}
	public int getTaxMoney () {return taxMoney;}
	public int getFirstChanceCardId() {return chanceQueue.peek();}
	public int getFirstCChestCardId() {return cChestQueue.peek();}
	
	public boolean getplayerIsInJail() {
		return currentPlayer.getPlayerIsInJail();
	}

	public Player getPlayerByName(String name) {
		
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getName().equals(name)) {
				return players.get(i);
			}
		}
		
		return null;
	}

	public void swapPropertiesAround(Player seller, Player buyer, BuyableSquare property) {
		buyer.removeMoney(property.getCost(), false);
		seller.addMoney(property.getCost());
		property.setOwner(buyer);
		buyer.addProperty(property);
		seller.removeProperty(property.getName());
	}
}
