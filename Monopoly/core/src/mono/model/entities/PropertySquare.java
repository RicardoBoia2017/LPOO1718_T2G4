package mono.model.entities;

import java.util.ArrayList;

import mono.controller.GameController;

public class PropertySquare extends Square {
	private String color;
	private int price;
	private int buildingCost; 
	private Player owner;
	ArrayList <Integer> rents;
    private int mortgageValue;
	private int nHouses;
	private int nHotels;
	private boolean inMortgage;

	public PropertySquare(String name, String color, int price, int normalRent, int oneRent, int twoRent, int threeRent, int fourRent, int hotelRent, int buildingCost, int position) {
		super(name, position);
		this.price = price;
		this.color = color;
		this.rents = new ArrayList <Integer> (6);
		setRents (normalRent, oneRent, twoRent, threeRent, fourRent, hotelRent);
		owner = null;
		this.inMortgage=false;
		this.buildingCost = buildingCost; 
		this.mortgageValue = price/2;
		this.nHouses=0;
		this.nHotels=0;
	}

	@Override
	public void doAction(Player p) {
		
		int value = 0;
		
		if (owner != null)
			payRent(p);
		
	}

	private void payRent (Player p)
	{
		int value = 0;
		
		if(nHotels != 0)
			value = rents.get(5) * nHotels; 
		else
			value = rents.get(nHouses*1);
		
		p.removeMoney(value);
		owner.addMoney(value);
	}
	
	private void buyProperty(Player p)
	{
		this.owner = p;
	}
	
	public void setRents(int normalRent, int oneRent, int twoRent, int threeRent, int fourRent, int hotelRent)
	{
		rents.add (normalRent);
		rents.add (oneRent);
		rents.add (twoRent);
		rents.add (threeRent);
		rents.add (fourRent);
		rents.add (hotelRent);
		
	}

	public void setOwner (Player buyer)
	{
		owner = buyer;
	}

	public void buyHouse (int nHouses)
	{
		this.nHouses += nHouses;
	}
	
	public void buyHotel ()
	{
		if (this.nHotels == 0 && this.nHouses == 4)
			nHotels++;
	}

	@Override
	public String getType() {return "Property";	}
	public int getPrice() {return price;}

}
