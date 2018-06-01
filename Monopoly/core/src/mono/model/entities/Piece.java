package mono.model.entities;

import java.awt.Point;

/**
 * Super class for all board pieces. Stores initial coordinates and has "getters" to this variables.
 * 
 * @author ricar
 *
 */
public abstract class Piece {

	//coordinates where piece is drawn
	protected int initialX; 
	protected int initialY;
	
	public Piece (int x, int y)
	{
		this.initialX = x;
		this.initialY = y;
	}
	
	/**
	 * 
	 * 
	 * @param playerX player's initial X.
	 * @param playerY player's initial Y.
	 * @param playerPosition player's initial position
	 * @param amounToWalk value from the dice roll
	 * @return new coordinates after movement is complete
	 */
	public abstract Point move (int playerX, int playerY, int playerPosition, int amounToWalk);
	
	/**
	 * 
	 * @return piece type
	 */
	public abstract String getType();
	
	/**
	 * 
	 * @return initial x coordinate
	 */
	public int getInitialX()
	{
		return this.initialX;
	}
	
	/**
	 * 
	 * @return initial y coordinate
	 */
	public int getInitialY()
	{
		return this.initialY;
	}
}
