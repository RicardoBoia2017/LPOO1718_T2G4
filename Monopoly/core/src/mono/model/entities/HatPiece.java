package mono.model.entities;

import java.awt.Point;

/**
 * Class that stores Hat initial coordinates along with its move method
 * 
 * @author ricar
 *
 */
public class HatPiece extends Piece{
	
	/**
	 * Creates pieces and initializes coordinates
	 */
	public HatPiece () 
	{
		super (0, 910);
	}

	@Override
	public String getType() {
		return "Hat";
	}

	@Override
	public Point move(int playerX, int playerY, int playerPosition, int amountToWalk) {
		Point finalPosition = new Point (playerX, playerY);
		int currentPosition = playerPosition;
		int GoSquareGap = 80;
		int StandardSquareGap = 68;
		
		if (amountToWalk < 0)
			amountToWalk += 40;
		
		while (amountToWalk > 0)
		{
			if (currentPosition == 0)
			{
				finalPosition.x += GoSquareGap + StandardSquareGap/2; 
			}
			
			else if (currentPosition < 10  && currentPosition > 0)
			{
				finalPosition.x += StandardSquareGap;
			}
			
			else if (currentPosition == 10)
			{
				finalPosition.y -= GoSquareGap - 5;
			}
			
			else if (currentPosition < 20 && currentPosition > 10)
			{
				finalPosition.y -= StandardSquareGap;
			}
			
			else if (currentPosition == 20)
			{
				finalPosition.x -= GoSquareGap + StandardSquareGap/2;
			}
			
			else if (currentPosition < 30 && currentPosition > 20)
			{
				finalPosition.x -= StandardSquareGap;
			}
			
			else if (currentPosition == 30)
			{
				finalPosition.y += GoSquareGap - 5;
			}
			
			else if (currentPosition < 40 && currentPosition > 30)
			{
				finalPosition.y += StandardSquareGap;
			}
	
			amountToWalk --;
			
			if (currentPosition == 39)
				currentPosition = 0;
			else
				currentPosition++;
		}
		
		return finalPosition;
	}

	
}
