package mono.model.entities;

import java.awt.Point;

public abstract class Piece {

	//coordinates where piece is drawn
	protected int initialX; 
	protected int initialY;
	
	public Piece ()
	{
		
	}
	
	public abstract Point move (int playerX, int playerY, int playerPosition, int amounToWalk);
	
	public abstract String getType();
	
	public int getInitialX()
	{
		return this.initialX;
	}
	
	public int getInitialY()
	{
		return this.initialY;
	}
}
