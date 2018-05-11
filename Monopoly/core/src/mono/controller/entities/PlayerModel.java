package mono.controller.entities;

public class PlayerModel extends EntityModel {
	
	DiceModel dice1;
	DiceModel dice2;
	String boardPiece;

	public PlayerModel(float x, float y, float rotation, int dice1num, int dice2num, String piece) {
		super (x,y,rotation);
		dice1 = new DiceModel(2.f, 2.f, 0, dice1num);
		dice2 = new DiceModel(10.f, 2.f, 0, dice2num);
		boardPiece = piece;
	}
	
	public DiceModel getDice1Model() {
		return dice1;
	}
	
	public DiceModel getDice2Model() {
		return dice2;
	}
	
	public String getBoardPiece() {
		return boardPiece;
	}
}
