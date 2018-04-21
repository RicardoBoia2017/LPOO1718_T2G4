package mono.controller.entities;

import java.util.Random;

public class Dice {

	int number;
	
	public Dice() {
		number = 0;
	}
	
	public int getNumber() {
		Random rand = new Random();
		
		number = 1+rand.nextInt(6);
		
		return number;
	}
}
