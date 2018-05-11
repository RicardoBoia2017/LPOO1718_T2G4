package mono.model.entities;

public class Player {
	int position;
	String name; //there can be more than one player
	String boardPiece;
	Money money;
	Dice dice_1;
	Dice dice_2;
	int diceRoll;
	int dice1Num;
	int dice2Num;

	public Player(String name, String piece) {
		this.name = name;
		position = 0;
		boardPiece = piece;
		money = new Money();
		diceRoll = 0;
		dice_1 = new Dice();
		dice_2 = new Dice();
	}
	
	public void rollDice() {
		dice1Num = dice_1.rollNumber();
		dice2Num = dice_2.rollNumber();
		diceRoll = dice1Num + dice2Num;
	}
	
	public int getAmountToWalk() {
		return diceRoll;
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
	
	public int getDice1Num() {
		return dice1Num;
	}
	
	public int getDice2Num() {
		return dice2Num;
	}
}
