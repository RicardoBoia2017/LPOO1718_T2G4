package mono.model.entities;

import java.awt.Point;
import java.util.Random;

public class Player {
	int position;
	Point coordinates;
	String name; //there can be more than one player
	Piece boardPiece;
	Money money;
//	int diceRoll;
//	int dice1Num;
//	int dice2Num;

	public Player(String name, String pieceType) {
		this.name = name;
		position = 0;
		money = new Money();
	//	diceRoll = 0;
		initializePiece(pieceType);
	//	dice1Num = 1;	
	//	dice2Num = 1;
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

//	public int rollDice() {
//		Random rand = new Random();
//
//		int dice1Num = 1+rand.nextInt(6);
//		int dice2Num = 1+rand.nextInt(6);
//		
//		return dice1Num + dice2Num;
//	}
	
//	public int getAmountToWalk() {
//		return diceRoll;
//	}
	
	public String getName() {
		return name;
	}
	
	public Piece getBoardPiece() {
		return boardPiece;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void move(int diceRoll) {

		//rollDice ();
		
		Point finalPosition = boardPiece.move((int)coordinates.getX(), (int)coordinates.getY(), position, diceRoll);

		coordinates = finalPosition;
		
		position = position + diceRoll;

		if(position >= 40) {
			position = position - 40;
		}
	}
	
//	public int getDice1Num() {
//		return dice1Num;
//	}
//	
//	public int getDice2Num() {
//		return dice2Num;
//	}

	public void setCoordinates (int x, int y)
	{
		this.coordinates.setLocation(x, y);
	}
	
	public int getX() {return (int) coordinates.getX();}
	
	public int getY() {return (int) coordinates.getY();}
}
