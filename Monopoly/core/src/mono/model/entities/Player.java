package mono.model.entities;

import java.awt.Point;
import java.util.ArrayList;
//import java.util.Random;

import mono.model.Game;

/**
 * Creates a player and is reponsible for moving him, adding and removing money, managing is stay in jail, add properties, etc.
 * @author ricar
 *
 */
public class Player {
	
	final int gameId;
	final String name; 
	final boolean isBot;
	
	Piece boardPiece;
	int position;
	Point coordinates;
	int money;
	Boolean inJail; 
	Boolean isBankrupt;
	int turnsWithoutMoving;
	int currentDiceRoll;
	Boolean diceSameValue;
	ArrayList <BuyableSquare> propertiesOwned =  new ArrayList <BuyableSquare> ();
	Boolean hasPassedGoSquareOnce;
	int inCardPosition;

	/**
	 * Creates player and sets final variables
	 * 
	 * @param gameId player's id in game
	 * @param name player's name 
	 * @param pieceType player's piece
	 * @param bot if the player is a bot or not
	 */
	public Player(int gameId, String name, String pieceType, boolean bot) {
		this.gameId = gameId;
		this.name = name;
		this.isBot = bot;
		initVariables();
		initializePiece(pieceType);
	}
	
	/**
	 * Initializes non final variables
	 */
	private void initVariables() {
		position = 0;
		money = 1500;
		inJail = false;
		turnsWithoutMoving = 0;
		hasPassedGoSquareOnce = true;
		currentDiceRoll = 0;
		inCardPosition = -1;
		isBankrupt = false;
	}

	/**
	 * Initializes player's piece according to variable
	 *  
	 * @param pieceType player's piece
	 */
	private void initializePiece(String pieceType) {
		
		switch (pieceType)
		{
			case "Boot":
				boardPiece = new BootPiece();
				break;
				
			case "Car":
				boardPiece = new CarPiece ();
				break;
				
			case "Hat":
				boardPiece = new HatPiece ();
				break;
			
			case "Thimble":
				boardPiece = new ThimblePiece ();
				break;
		}
		
		coordinates = new Point (boardPiece.getInitialX(), boardPiece.getInitialY());
	}
	
	/**
	 * Changed player position and coordinates according to diceValue
	 * 
	 * @param diceRoll sum of dice values
	 * @param sameValue true if dice values are equals, false otherwise
	 */
	public void move(int diceRoll, boolean sameValue) {
		
		currentDiceRoll = diceRoll;
		diceSameValue = sameValue;
			
		if(!inJail) {
			Point finalPosition = boardPiece.move((int)coordinates.getX(), (int)coordinates.getY(), position, diceRoll);
			
			coordinates = finalPosition;
			
			position = position + diceRoll;
	
			if(position >= 40) 
			{
				position = position - 40;
				
				if(position > 0) {
					tellGameModelThePlayerPassedByGoSquare();
					hasPassedGoSquareOnce = true;
				}
			}
			
			else if (position < 0)
				position = position + 40; 
			
		}
		
		else {
			turnsWithoutMoving++;
		}
		
	}

	/**
	 * Add property to properties owned ArrayList
	 * 
	 * @param property property to be owned
	 */
	public void addProperty (BuyableSquare property)
	{
		this.propertiesOwned.add(property);
	}
	
	/**
	 * Adds value to player money
	 * 
	 * @param value value to be added
	 */
	public void addMoney (int value)  
	{
		money += value;
	}
	
	/**
	 * If possible, removes value from player
	 * 
	 * @param value value to be removed
	 * @param obligatory if the operation is obligatory or not (player is forced to pay rent, but not forced to buy properties)
	 * 
	 * @return true if money was removed or false if player doesn't have enough money
	 */
	public int removeMoney (int value, boolean obligatory)
	{
		if (value > money && obligatory == false)
			return -3;
		
		if (value > money && obligatory == true) {
			isBankrupt = true;
		}
		
		money -= value;
	
		return 0;
	}
	
	/**
	 * 
	 * @return player's id
	 */
	public int getGameId() {return gameId;}
	
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return board piece
	 */
	public Piece getBoardPiece() {
		return boardPiece;
	}
	
	/**
	 * 
	 * 
	 * @return true if player is a bot, otherwise returns false
	 */
	public boolean isBot () {return isBot;}
	
	/**
	 * 
	 * @return players position (0-39)
	 */
	public int getPosition() {return position;}
	
	/**
	 * 
	 * @return player's X
	 */
	public int getX() {return (int) coordinates.getX();}
	
	/**
	 * 
	 * @return player's Y
	 */
	public int getY() {return (int) coordinates.getY();}
	
	/**
	 * 
	 * @return player'smoney
	 */
	public int getMoney() {return money;}
	
	/**
	 * 
	 * @return properties owned by player
	 */
	public ArrayList <BuyableSquare> getPropertiesOwned () {return this.propertiesOwned;}
	
	/**
	 * 
	 * @return whether or not player is arrested
	 */
	public Boolean isInJail() {
		return inJail;
	}
	
	/**
	 * Sends or frees player from jail according to value
	 * 
	 * @param value value
	 */
	public void setInJail (boolean value)
	{
		inJail = value;
	}

	/**
	 * Resets turns without moving
	 */
	public void resetTurnsWithoutMoving() {
		turnsWithoutMoving = 0;
	}
	
	/**
	 * 
	 * @return turns without moving
	 */
	public int getTurnsWithoutMoving() {
		return turnsWithoutMoving;
	}
	
	/**
	 * 
	 * @return player's last dice roll (used when freeing him from jail)
	 */
	public int getCurrentDiceRoll() {
		return currentDiceRoll;
	}
	
	/**
	 * 
	 * @return whether or not player's last dice roll had the same value or not (used when freeing player from jail)
	 */
	public boolean getDiceSameValue () { return this.diceSameValue;}
		
	/**
	 * Tells Game that player is in jail
	 */
	public void tellGameModelThePlayerIsInJail() {
		if (!isBot)
			Game.getInstance().tellControllerPlayerIsInJail(); 
	}
	
	/**
	 * Sets dice roll
	 * 
	 * @param dice dice roll value
	 */
	public void setCurrentDiceroll(int dice) {
		currentDiceRoll = dice;
	}
	
	/**
	 * If player is in Chance or Community Chest value, sets inCardPosition to his currentPosition
	 * 
	 * @param value value
	 */
	public void setInCardPosition (int value)
	{
		this.inCardPosition = value;
	}
	
	/**
	 * 
	 * @return inCardPosition
	 */
	public int getInCardPosition() {return this.inCardPosition;}
	
	/**
	 * Informs Game that player passed GO square
	 */
	public void tellGameModelThePlayerPassedByGoSquare() {
		Game.getInstance().givePlayer200Money(this);
	}
	
	/**
	 * Removes property with specified name from properties owned
	 * @param name name of the property to be removed
	 */
	public void removeProperty(String name) {
		for(int i = 0; i < propertiesOwned.size(); i++) {
			if(propertiesOwned.get(i).getName().equals(name)) {
				propertiesOwned.remove(propertiesOwned.get(i));
			}
		}
	}
	
	/**
	 * 
	 * @return bankruptcy state
	 */
	public Boolean getBankruptcyState() {
		return isBankrupt;
	}
	
	/**
	 * Sets bankruptcy state
	 * 
	 * @param state state
	 */
	public void setBankruptcyState(Boolean state) {
		isBankrupt = state;
	}
}
