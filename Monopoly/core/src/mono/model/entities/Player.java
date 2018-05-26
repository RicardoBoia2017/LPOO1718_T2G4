package mono.model.entities;

import java.awt.Point;
import java.util.ArrayList;
//import java.util.Random;

import mono.model.Game;

public class Player {
	int position;
	Point coordinates;
	String name; //there can be more than one player
	Piece boardPiece;
	int money;
	Boolean inJail; 
	int turnsWithoutMoving;
	Pair currentDiceRoll;
	ArrayList <BuyableSquare> propertiesOwned =  new ArrayList <BuyableSquare> ();
	Boolean hasPassedGoSquareOnce;

	public Player(String name, String pieceType) {
		this.name = name;
		position = 0;
		money = 1500;
		inJail = false;
		turnsWithoutMoving = 0;
		initializePiece(pieceType);
		hasPassedGoSquareOnce = true;
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
	
	public void move(Pair diceRoll) {
		
		currentDiceRoll = diceRoll;
		
		if(!inJail) {
			Point finalPosition = boardPiece.move((int)coordinates.getX(), (int)coordinates.getY(), position, diceRoll.getValue1()+diceRoll.getValue2());
			
			coordinates = finalPosition;
			
			position = position + (diceRoll.getValue1()+diceRoll.getValue2());
	
			if(position >= 40) {
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
	
	public void move(int diceRoll) {
		
		if(!inJail) {
			Point finalPosition = boardPiece.move((int)coordinates.getX(), (int)coordinates.getY(), position, diceRoll);
			
			coordinates = finalPosition;
			
			position = position + diceRoll;
	
			if(position >= 40) 
				position = position - 40;
			
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
	
	public int removeMoney (int value)
	{
		if (value > money)
			return -3;
		
		money -= value;
	
		return 0;
	}
	
	//not sure if used
	public void setCoordinates (int x, int y)
	{
		this.coordinates.setLocation(x, y);
	}
	
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
	
	public Pair getCurrentDiceRoll() {
		return currentDiceRoll;
	}
	
	public void tellGameModelThePlayerIsInJail() {
		System.out.print("My flag is ");
		System.out.print(inJail);
		Game.getInstance().tellControllerPlayerIsInJail(); 
	}
	
	public void setCurrentDiceroll(Pair dice) {
		currentDiceRoll = dice;
	}
	
	public void tellGameModelThePlayerPassedByGoSquare() {
		Game.getInstance().givePlayer200Money(this);
	}
	
	public int getAdditiveDiceRoll() {
		return currentDiceRoll.getValue1()+currentDiceRoll.getValue2();
	}
	
	public Boolean getPlayerIsInJail() {
		return inJail;
	}
}
