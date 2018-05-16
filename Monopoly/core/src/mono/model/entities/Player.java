package mono.model.entities;

import java.awt.Point;
import java.util.Random;

public class Player {
	int position;
	Point coordinates;
	String name; //there can be more than one player
	Piece boardPiece;
	int money;
	Boolean sentToJail;

	public Player(String name, String pieceType) {
		this.name = name;
		position = 0;
		money = 15000; //confirm later
		sentToJail = false;
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
	
	public void move(int diceRoll) {
		
		Point finalPosition = boardPiece.move((int)coordinates.getX(), (int)coordinates.getY(), position, diceRoll);
		
		coordinates = finalPosition;
		
		position = position + diceRoll;

		if(position >= 40) {
			position = position - 40;

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
}
