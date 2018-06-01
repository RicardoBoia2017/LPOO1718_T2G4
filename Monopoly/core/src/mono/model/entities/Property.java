package mono.model.entities;

import java.util.ArrayList;

/**
 * Creates property square, calculates rent and builds houses and hotels. Subclass of buyable square
 * 
 * @author ricar
 *
 */
public class Property extends BuyableSquare {
	private String color;
	private int buildingCost; 
	ArrayList <Integer> rents;
	private int nHouses;
	private int nHotels;
	private float coordX;
	private float coordY;

	/**
	 * Creates property square
	 * 
	 * @param name property's name
	 * @param color property's color
	 * @param cost property's cost
	 * @param normalRent property's rent without houses
	 * @param oneRent property's rent with one house
	 * @param twoRent property's rent with two houses
	 * @param threeRent property's rent with three houses
	 * @param fourRent property's rent with four houses
	 * @param hotelRent property's rent with a hotel
	 * @param buildingCost cost for each building
	 * @param position property's position in board
	 * @param coordinX x coordinate (used to draw buildings)
	 * @param coordinY y coordinate (used to draw buildings)
	 */
	public Property(String name, String color, int cost, int normalRent, int oneRent, int twoRent, int threeRent, int fourRent, int hotelRent, int buildingCost, int position, float coordinX, float coordinY) {
		super(name, position, cost);
		this.color = color;
		this.rents = new ArrayList <Integer> (6);
		setRents (normalRent, oneRent, twoRent, threeRent, fourRent, hotelRent);
		this.buildingCost = buildingCost; 
		this.nHouses=0;
		this.nHotels=0;
		coordX = coordinX;
		coordY = coordinY;
	}

	/**
	 * Sets rents 
	 * @param normalRent property's rent without houses
	 * @param oneRent property's rent with one house
	 * @param twoRent property's rent with two houses
	 * @param threeRent property's rent with three houses
	 * @param fourRent property's rent with four houses
	 * @param hotelRent property's rent with a hotel
	 */
	public void setRents(int normalRent, int oneRent, int twoRent, int threeRent, int fourRent, int hotelRent)
	{
		rents.add (normalRent);
		rents.add (oneRent);
		rents.add (twoRent);
		rents.add (threeRent);
		rents.add (fourRent);
		rents.add (hotelRent);
	}
	
	/**
	 * Calculates rent according to buildings
	 * 
	 * @param p1 payer
	 */
	protected void payRent (Player p1)
	{
		int value = 0;
		
		if(nHotels != 0)
			value = rents.get(5) * nHotels; 
		else
			value = rents.get(nHouses*1);
		
		p1.removeMoney(value, true);
		owner.addMoney(value);
	}

	/**
	 * Builds house
	 */
	public void buyHouse ()
	{
		nHouses++;
	}

	/**
	 * Builds hotel
	 */
	public void buyHotel () 
	{
		if (this.nHotels == 0 && this.nHouses == 4) 
			nHotels++;
	}
	
	/**
	 * 
	 * @return property's color
	 */
	public String getColor()
	{
		return color;
	} 
	
	/**
	 * 
	 * @return building cost
	 */
	public int getBuildingCost() {
		return buildingCost;
	}
	
	/**
	 * Morgages property and removes all buildings
	 */
	public void mortgageThisProperty() {
		if(inMortgage) {
			nHouses = 0;
			nHotels = 0;
		}
	}

	@Override
	public String getType() {return "Property";}
	
	/**
	 * 
	 * @return number of houses
	 */
	public int getHouses() {return this.nHouses;}
	
	/**
	 * 
	 * @return numbers of hotels
	 */
	public int getHotels() {return this.nHotels;}

	/**
	 * 
	 * @return x coordinate
	 */
	public float getX() {
		return coordX;
	}

	/**
	 * 
	 * @return y coordinate
	 */
	public float getY() {
		return coordY;
	}
	
	/**
	 * Sets number of houses
	 * @param number number of houses
	 */
	public void setHouses(int number) {
		nHouses = number;
	}
}
