package mono.model.entities;

import java.util.ArrayList;

/**
 * Super class of all squares that make the board. Store its name and position in the board.
 * 
 * @author ricar
 *
 */
public abstract class Square {
	String name; 
	int position;

	/**
	 * Creates a square
	 * 
	 * @param name square's name
	 * @param position square's position in the board (0-39)
	 */
	public Square(String name, int position) {
		this.name = name;
		this.position = position;
	}
	
	/**
	 * 
	 * @return square's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return square's type
	 */
	public abstract String getType();
	
	/**
	 * Does squares specific action
	 * 
	 * @param p player currently playing
	 */
	public abstract void doAction (Player p);

	/**
	 * 
	 * @return square's position in the board
	 */
	
	public int getPosition() {
		return position;
	}
}
