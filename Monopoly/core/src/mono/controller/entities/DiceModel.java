package mono.controller.entities;

public class DiceModel extends EntityModel {

	int number;
	
	public DiceModel(float x, float y, float rotation, int num) {
		super (x,y,rotation);
		number = num;
	}
	
	public int getNumber() {
		return number;
	}
}
