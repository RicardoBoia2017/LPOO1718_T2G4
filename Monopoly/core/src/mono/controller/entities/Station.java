package mono.controller.entities;

public class Station extends Square {
	private int price;
	private boolean owned;
	private Player owner;
	private int rent;
	private boolean mortgage;
	private double mortgageValue;

	public Station(int price, int position) {
		super("Station", position);
		this.price=price;
		this.owned=false;
		this.owner=null;
		this.rent=25;
		this.mortgage=false;
		this.mortgageValue=price/2;
	}

}
