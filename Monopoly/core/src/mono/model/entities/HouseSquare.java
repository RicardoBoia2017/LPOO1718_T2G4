package mono.model.entities;

public class HouseSquare extends Square {
	private String color;
	private int price;
	private int apart_price;
	private boolean owned;
	private Player owner;
	private int normalRent;
	private int OneRent;
	private int TwoRent;
	private int ThreeRent;
	private int FourRent;
	private int HotelRent;
	private int n_apart;
	private int n_hotel;
	private boolean mortgage;
    private double mortgageValue;

	public HouseSquare(String name, String color, int price, int normalRent, int OneRent, int TwoRent, int ThreeRent, int FourRent, int HotelRent, int apart_price, int position) {
		super(name, position);
		this.price = price;
		this.color = color;
		this.normalRent=normalRent;
		this.OneRent=OneRent;
		this.TwoRent=TwoRent;
		this.ThreeRent=ThreeRent;
		this.FourRent=FourRent;
		this.HotelRent=HotelRent;
		this.mortgage=false;
		this.apart_price = apart_price;
		this.mortgageValue=price/2;
		this.n_apart=0;
		this.n_hotel=0;
		this.owned = false;
	}

}
