package mono.model.entities;

import java.awt.Point;
import java.util.Random;

import mono.model.Game;

public class Player {
	int position;
	Point coordinates;
	String name; //there can be more than one player
	Piece boardPiece;
	int money;
	Boolean sentToJail;
	int turnsWithoutMoving;
	Pair currentDiceRoll;

	public Player(String name, String pieceType) {
		this.name = name;
		position = 0;
		money = 1500;
		sentToJail = false;
		turnsWithoutMoving = 0;
		initializePiece(pieceType);
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
		
		if(!sentToJail) {
			Point finalPosition = boardPiece.move((int)coordinates.getX(), (int)coordinates.getY(), position, diceRoll.getValue1()+diceRoll.getValue2());
			
			coordinates = finalPosition;
			
			position = position + (diceRoll.getValue1()+diceRoll.getValue2());
	
			if(position >= 40) {
				position = position - 40;
			}
			
		}
		
		else {
			turnsWithoutMoving++;
		}
		
	}
	
	public void move(int diceRoll) {
		
		if(!sentToJail) {
			Point finalPosition = boardPiece.move((int)coordinates.getX(), (int)coordinates.getY(), position, diceRoll);
			
			coordinates = finalPosition;
			
			position = position + diceRoll;
	
			if(position >= 40) {
				position = position - 40;
			}
			
		}
		
		else {
			turnsWithoutMoving++;
			System.out.println("Telling the model I am in jail");
			tellGameModelThePlayerIsInJail(true);
		}
		
	}

	public void addMoney (int value) {money += value;}
	
	public void removeMoney (int value) {money -= value;}
	
	//not sure if used
	public void setCoordinates (int x, int y)
	{
		this.coordinates.setLocation(x, y);
	}
	
	public int getPosition() {return position;}
	public int getX() {return (int) coordinates.getX();}
	public int getY() {return (int) coordinates.getY();}
	public int getMoney() {return money;}
	
	public Boolean wasSentToJail() {
		return sentToJail;
	}
	
	public void sendToJail() {
		sentToJail = true;
	}
	
	public void freeFromJail() {
		sentToJail = false;
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
	
	public void tellGameModelThePlayerIsInJail(Boolean b) {
		Game.getInstance().tellControllerPlayerIsInJail(b);
	}
}
