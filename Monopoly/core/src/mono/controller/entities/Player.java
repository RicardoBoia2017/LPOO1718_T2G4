package mono.controller.entities;

public class Player {
	int position;
	int toWalk;
	String name; //there can be more than one player
	String boardPiece;
	Money money;

	public Player(String name, String piece) {
		this.name = name;
		position = 0;
		boardPiece = piece;
		money = new Money();
	}
	
	public void setAmountToWalk(int amountToWalk) {
		toWalk = amountToWalk;
	}
	
	public int getAmountToWalk() {
		return toWalk;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBoardPiece() {
		return boardPiece;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void updatePosition(int diceSum) {
		position = position + diceSum;
		
		if(position >= 40) {
			position = position - 40;
		}
	}
}
