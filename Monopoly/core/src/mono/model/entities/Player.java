package mono.model.entities;

import java.awt.Point;
import java.util.ArrayList;
//import java.util.Random;

import mono.model.Game;

public class Player {
	int position;
	int gameId;
	boolean isBot;
	Point coordinates;
	String name; //there can be more than one player
	Piece boardPiece;
	int money;
	Boolean inJail; 
	Boolean isBankrupt;
	int turnsWithoutMoving;
	int currentDiceRoll;
	Boolean diceSameValue;
	ArrayList <BuyableSquare> propertiesOwned =  new ArrayList <BuyableSquare> ();
	Boolean hasPassedGoSquareOnce;
	int inCardPosition;

	public Player(int gameId, String name, String pieceType, boolean bot) {
		this.gameId = gameId;
		this.name = name;
		this.isBot = bot;
		position = 0;
		money = 1500;
		inJail = false;
		turnsWithoutMoving = 0;
		initializePiece(pieceType);
		hasPassedGoSquareOnce = true;
		currentDiceRoll = 0;
		inCardPosition = -1;
		isBankrupt = false;
	}
	
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

	public String getName() {
		return name;
	}
	
	public Piece getBoardPiece() {
		return boardPiece;
	}
		
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

	public void addProperty (BuyableSquare property)
	{
		this.propertiesOwned.add(property);
	}
	
	public void addMoney (int value)  
	{
		money += value;
	}
	
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
	
	//not sure if used
	public void setCoordinates (int x, int y)
	{
		this.coordinates.setLocation(x, y);
	}
	
	public int getGameId() {return gameId;}
	public boolean isBot () {return isBot;}
	public int getPosition() {return position;}
	public int getX() {return (int) coordinates.getX();}
	public int getY() {return (int) coordinates.getY();}
	public int getMoney() {return money;}
	public ArrayList <BuyableSquare> getPropertiesOwned () {return this.propertiesOwned;}
	
	public Boolean isInJail() {
		return inJail;
	}
	
	public void sendToJail() {
		inJail = true;
	}
	
	public void freeFromJail() {
		inJail = false;
	}
	
	public void resetTurnsWithoutMoving() {
		turnsWithoutMoving = 0;
	}
	
	public int getTurnsWithoutMoving() {
		return turnsWithoutMoving;
	}
	
	public int getCurrentDiceRoll() {
		return currentDiceRoll;
	}
	
	public boolean getDiceSameValue () { return this.diceSameValue;}
		
	public void tellGameModelThePlayerIsInJail() {
		System.out.print("My flag is ");
		System.out.print(inJail);
		Game.getInstance().tellControllerPlayerIsInJail(); 
	}
	
	public void setCurrentDiceroll(int dice) {
		currentDiceRoll = dice;
	}
	
	public void setInCardPosition (int value)
	{
		this.inCardPosition = value;
	}
	
	public int getInCardPosition() {return this.inCardPosition;}
	
	public void tellGameModelThePlayerPassedByGoSquare() {
		Game.getInstance().givePlayer200Money(this);
	}
	
	public int getAdditiveDiceRoll() {
		return currentDiceRoll;
	}
	
	public Boolean getPlayerIsInJail() {
		return inJail;
	}
	
	public void removeProperty(String name) {
		for(int i = 0; i < propertiesOwned.size(); i++) {
			if(propertiesOwned.get(i).getName().equals(name)) {
				propertiesOwned.remove(propertiesOwned.get(i));
			}
		}
	}
	
	public Boolean getBankrupcyState() {
		return isBankrupt;
	}
	
	public void setBankrupcyState(Boolean state) {
		isBankrupt = state;
	}
}
