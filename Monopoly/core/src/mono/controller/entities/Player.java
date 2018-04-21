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
}
