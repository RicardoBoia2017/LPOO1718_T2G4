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
 * Main class of the model. Stores board, players, chance and community chest queues and tax money.
 * 
 * @author ricar
 *
 */
public class Game {
	private static Game instance;
	Board board;
	ArrayList <Player> players = new ArrayList<Player>();
	Player currentPlayer;
	int taxMoney; 
	Queue <Integer> chanceQueue;
	Queue <Integer> cChestQueue; 
	private int currentSocketUsed = 22222;
	
	public int getCurrentSocketUsed() {
		return currentSocketUsed;
	}
	
	public void incrementCurrentSocket() {
		currentSocketUsed++;
	}
	
	/**
	 * 
	 * @return singleton instance
	 */
	public static synchronized Game getInstance() 
	{ 
		if (instance == null)
			instance = new Game(); 
		
		return instance;  
	}
	
	/**
	 * Sets instance to null (used in unit tests)
	 */
	public void setGameModelInstanceToNull() {
		instance = null;
	}
	
	/**
	 * Creates game
	 */
	private Game()
	{
		board = new Board();
		taxMoney = 0;
		
		initializeChanceQueue();
		initializeCChestQueue();
	}

	/**
	 * Initializes chance queue by adding all cards and shuffling them
	 */
	private void initializeChanceQueue()
	{				
		chanceQueue = new LinkedList <Integer>();

		for (int i = 1; i <= 10; i++)
			chanceQueue.add(i);
		
		Collections.shuffle((List<?>) chanceQueue);	
	}
	
	/**
	 * Initializes community chest queue by adding all cards and shuffling them
	 */
	private void initializeCChestQueue()
	{
		cChestQueue = new LinkedList <Integer>();

		for (int i = 1; i <= 10; i++)
			cChestQueue.add(i);
		
		Collections.shuffle((List<?>) cChestQueue);	
	}
	
	/**
	 * If possible, adds player to games
	 * @param piece player's piece
	 */	
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
	
	/**
	 * If needed, adds bots to game
	 */
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
	
	/**
	 * Used when adding bots. Checks if board piece is taken
	 * @param piece piece
	 * @return true if piece is taken, otherwise return false
	 */
	private boolean checkifPieceIsTaken(String piece)
	{
		for (Player p : players)
		{
			if (p.getBoardPiece().getType().equals(piece))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Adds players to be used test.
	 * @param player1Piece player's piece
	 */
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
				
		currentPlayer = players.get(0);
	}
	
	/**
	 * Rolls dices by getting two random numbers between 1 and 6
	 * @return Pair with both values
	 */
	public Pair rollDice() {
		
		Random rand = new Random();
		Pair values = new Pair();
		
		values.setValue1(1+rand.nextInt(6));
		values.setValue2(1+rand.nextInt(6)); 
		
		currentPlayer.setCurrentDiceroll(values.getValue1() + values.getValue2()); 
		
		return values;
	}
	
	/**
	 * Moves player
	 * 
	 * @param diceRoll sum of dice values
	 * @param sameValue true if dice values are equal, false otherwise
	 */
	public void movePlayer(int diceRoll, boolean sameValue) {
					
		currentPlayer.move(diceRoll, sameValue);
		
		if( currentPlayer.getPosition() == 0) {
			tellGoSquareItsNotFirstVisit();
		}
				
	}
	
	/**
	 * Call action from current player's current square.
	 */
	public void squareAction ()
	{
		board.getSquares().get(currentPlayer.getPosition()).doAction(currentPlayer);
	} 
	
	/**
	 * Ends player's turn by changing current player and, if needed, changing cards queues.
	 */
	public void endTurn ()
	{	
		String res = inCardPosition(false);
		
		changeCardEndTurn(res);
		
		changePlayer(); 
		
	}
	
	/**
	 * If player is in card (Chance or Community Chest) position, removes first card from queue and adds it to the end
	 * 
	 * @param res result from inCardPosition method
	 */
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

	/**
	 * Changes current player to next one.
	 */
	private void changePlayer()
	{	
		if (currentPlayer.getGameId() == players.size())
			this.currentPlayer = players.get(0);
		
		else
			this.currentPlayer = players.get(currentPlayer.getGameId());
	}
	
	/**
	 * Sets tax money to current value
	 * 
	 * @param newValue new value
	 */
	public void setTaxMoney (int newValue)
	{
		this.taxMoney = newValue;
	}
	
	/**
	 * Tells Jail Square that player wants to pay fine
	 */
	public void tellJailPlayerWantsToPayFine() {
		Jail j1 = (Jail) board.getSquares().get(10);
		j1.aproveFine();
	}
	
	/**
	 * Tells Go Square that player is not visiting it for the first time
	 */
	public void tellGoSquareItsNotFirstVisit() {
		StartSquare s1 = (StartSquare) board.getSquares().get(0);
		s1.setFirstVisitToFalse();
	}
	
	/**
	 * Tells controller that player is in jail
	 */
	public void tellControllerPlayerIsInJail() {
		GameController.getInstance().tellViewToDisplayJailDialog();
	}
	
	/**
	 * Check if it is possible to buy square
	 * 
	 * @return 0 if it is possible to buy square, or a negative value otherwise
	 */
	public int checkPropertyAvailibility()
	{
		Player p1 = currentPlayer;
		Square s1 = this.board.getSquares().get(p1.getPosition());
		
		Vector <String> allowedTypes = new Vector <String> (3);
		allowedTypes.add("Property");
		allowedTypes.add("Station");
		allowedTypes.add("Company");
		
		if (!allowedTypes.contains(s1.getType())) 
			return -1;

		else if (checkIfPropertyIsOwned (s1.getName())) 
			return -2;
		
		return 0; 
		
	}

	/**
	 * Checks if a property is owned
	 * 
	 * @param squareName property's name
	 * 
	 * @return true if property is owned, otherwise returns false
	 */
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
	
	
	/**
	 * If player has enough money, buys property
	 * 
	 * @return 0 if porperty was bought, otherwise return negative value
	 */
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
	
	/**
	 * Builds house in ps1
	 * 
	 * @param ps1 property where is going to be built
	 * 
	 * @return 0 if house was build, otherwise returns negative value
	 */
	public int buyHouse(Property ps1) {
		Player p1 = currentPlayer;

		int res = p1.removeMoney(ps1.getBuildingCost(), false);

		if (res == 0) 
			ps1.buyHouse();
		
		return res;
	}

	/**
	 * Check is property is mortgaged
	 * 
	 * @param squarename property's name
	 * 
	 * @return true if property is mortgaged, otherwise returns false
	 */
	private boolean checkIfPropertyIsMortgaged (String squarename) {
		BuyableSquare s1 = null;
		
		for(int i = 0; i < board.getSquares().size(); i++) {
			if(board.getSquares().get(i).getName().equals(squarename)) {
				s1 = (BuyableSquare) board.getSquares().get(i);
			}
		}
		
		return s1.getMortgageStatus();
	}
	
	/**
	 * Checks if it is possible to build an house
	 * 
	 * @param s1 property where house if going to be built
	 * 
	 * @return 0 if it is possible to build house, negative value othewise
	 */
	public int checkHouseAvailability(BuyableSquare s1) { 
		
	    Player p1 = currentPlayer; 
		     
	    if (!s1.getType().equals("Property")) 
	      return -1;  
	     
	    else if(checkIfPropertyIsMortgaged(s1.getName())) 
	      return -2; 
		     
	    Property s2 = (Property) s1; 
	     
	    if(!checkIfPlayerOwnsAllPropertiesOfThatColor(p1, s2.getColor()))
	      return -4; 
	    
	    if(s2.getHouses() == 4) 
	    	return -5;
	     
	    return 0; 
	  } 
	
	/**
	 * Checks if player owns all properties of a certain color
	 * 
	 * @param p1 player
	 * @param color color 
	 * 
	 * @return true if he has all properties of color, false otherwise
	 */
	private boolean checkIfPlayerOwnsAllPropertiesOfThatColor(Player p1, String color) {
		
		int numberOfPropertiesOfColor = 0;
		
		numberOfPropertiesOfColor = countPropertiesOfAColor(color);
		
		return checkIfCountingWasValid(color, numberOfPropertiesOfColor);
		
	}
	
	/**
	 * Counts properties of color
	 * 
	 * @param color color
	 * @return number of properties from color
	 */
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
	
	/**
	 * Checks if count was valid
	 * 
	 * @param color color
	 * @param count count
	 * 
	 * @return true if was valid, false otherwise
	 */
	public Boolean checkIfCountingWasValid(String color, int count) {
		if(color.equals("BROWN") || color.equals("DARK_BLUE")) {
			return (count == 2);
		}
		
		else {
			return (count == 3);
		}
	}
	
	/**
	 * Gives player p 200�
	 * 
	 * @param p player
	 */
	public void givePlayer200Money(Player p) {
		p.addMoney(200);
	}
	
	/**
	 * Checks if current player in a card (Chance or Community Chest) position
	 * 
	 * @param currentPosition current player's position
	 * 
	 * @return null if he isn't, or a code if he is
	 */
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
	
	/**
	 * Checks if it is possible to build an hotel
	 * 
	 * @param s1 property where hotel if going to be built
	 * 
	 * @return 0 if it is possible to build hotel, negative value othewise
	 */
	public int checkHotelAvailability(BuyableSquare s1) {
		     
	    if (!s1.getType().equals("Property")) //trying to place a hotel in a square other than property 
	    	return -1; 
	    
	    Property s2 = (Property) s1; //at this point, we can presume it is a valid property 
	    
	    if(s2.getHotels() == 1) //cannot have more than one hotel
	    	return -4;
	    
	    if(s2.getHouses() != 4)  //you cannot have a hotel until you have 4 houses
	    	return -2;
	    
	    return 0;
	}

	/**
	 * Builds hotel in ps1
	 * 
	 * @param ps1 property where hotel is gonna be built
	 * @return 0 if hotel was build, negative value otherwise
	 */
	public int buyHotel(Property ps1) {
		Player p1 = currentPlayer;

		int res = p1.removeMoney(ps1.getBuildingCost(), false);

		if (res == 0) {
			ps1.buyHotel();
			ps1.setHouses(0);
		} 

		return res;
	}

	/**
	 * Mortgages property
	 *  
	 * @param position property's position
	 * 
	 * @return 0 if property was mortgaged, negative value otherwise
	 */
	public int mortgageProperty(int position) {
		Player p1 = currentPlayer;
		
		if (position  >= p1.getPropertiesOwned().size())
			return -1;
		
		BuyableSquare s1 = p1.getPropertiesOwned().get(position );
		
		s1.setInMortgage(true);
		p1.addMoney(s1.getMortgateValue());
		
		return 0;
	}

	/**
	 * Re buys mortgaged property
	 * 
	 * @param position property's position
	 * @return 0 if property was re bought, negative value otherwise
	 */
	public int reBuyProperty (int position)
	{
		Player p1 = currentPlayer;
		BuyableSquare s1 = p1.getPropertiesOwned().get(position);
		
		s1.setInMortgage(false);
		p1.removeMoney( (int)Math.ceil (s1.getMortgateValue() * 1.10 ), false);
		
		return 0;
	}
	
	/**
	 * 
	 * @return player in game
	 */
	public ArrayList <Player> getPlayers() {return players;}
	
	/**
	 * 
	 * @return board
	 */
	public Board getBoard() {return board;} 
	
	/**
	 * 
	 * @return current player
	 */
	public Player getCurrentPlayer() {return this.currentPlayer;}
	
	/**
	 * 
	 * @return current square
	 */
	public Square getCurrentSquare() {return this.board.getSquares().get(currentPlayer.getPosition());}
	
	/**
	 * 
	 * @return tax money
	 */
	public int getTaxMoney () {return taxMoney;}
	
	/**
	 * 
	 * @return chance queue first card
	 */
	public int getFirstChanceCardId() {return chanceQueue.peek();}
	
	/**
	 * 
	 * @return community chest first card
	 */
	public int getFirstCChestCardId() {return cChestQueue.peek();}
	
	/**
	 * 
	 * @return true if current player is in jail, otherwise returns false
	 */
	public boolean getplayerIsInJail() {
		return currentPlayer.isInJail();
	}

	/**
	 * Gets player with name
	 * 
	 * @param name player's name
	 * 
	 * @return if player is found, he is returned, otherwise returns null
	 */
	public Player getPlayerByName(String name) {
		
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getName().equals(name)) {
				return players.get(i);
			}
		}
		 
		return null;
	}

	/**
	 * Swap property from seller to buyer
	 * 
	 * @param seller player selling
	 * @param buyer player buying
	 * @param property property being swapped
	 * @param cost cost of the sell
	 */
	public void swapPropertiesAround(Player seller, Player buyer, BuyableSquare property, int cost) {
		buyer.removeMoney(cost, false);
		seller.addMoney (cost);   
		
		property.setOwner(buyer); 
		buyer.addProperty(property);
		seller.removeProperty(property.getName());
	}
}
