package mono.controller.entities;

public class Money {
	
	double amount;

	public Money() {
		amount = 1500; //initially all players start with $1,500
	}
	
	public double getAmount() {
		return amount;
	}

}
