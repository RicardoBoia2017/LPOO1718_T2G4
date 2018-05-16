package mono.model.entities;

import java.awt.Point;

public class CarPiece extends Piece {

	public CarPiece ()
	{
		this.initialX = 50;
		this.initialY = 960;
	}
	
	@Override
	public Point move(int playerX, int playerY, int playerPosition, int amountToWalk) {
		Point finalPosition = new Point (playerX, playerY);
		int currentPosition = playerPosition;
		int GoSquareGap = 100;
		int StandardSquareGap = 68;
		int boardHeight = 930;
		int boardWidth = 750;
		
//		System.out.println("Position " + currentPosition + "    ATW " + amountToWalk);
		
		
		while (amountToWalk > 0)
		{
			if (currentPosition == 0)
			{
				finalPosition.x += GoSquareGap - 51 + StandardSquareGap/2; 
			}
			
			else if (currentPosition < 10)
			{
				finalPosition.x += StandardSquareGap;
			}
			
			else if (currentPosition == 10)
			{
				finalPosition.y -= GoSquareGap - 5;
			}
			
			else if (currentPosition < 20)
			{
				finalPosition.y -= StandardSquareGap;
			}
			
			else if (currentPosition == 20)
			{
				finalPosition.x -= GoSquareGap - 51 + StandardSquareGap/2;
			}
			
			else if (currentPosition < 30)
			{
				finalPosition.x -= StandardSquareGap;
			}
			
			else if (currentPosition == 30)
			{
				finalPosition.y += GoSquareGap - 5;
			}
			
			else if (currentPosition < 40)
			{
				finalPosition.y += StandardSquareGap;
			}
			
//			System.out.println(finalPosition.x + "   " + finalPosition.y);
//			System.out.println(currentPosition);
			amountToWalk --;
			
			if (currentPosition == 39)
				currentPosition = 0;
			else
				currentPosition++;
		}
		
//		if(s1.getName().equals("Start")) {
//
//			
//			if(amountToWalk <= 10) {
//				finalPosition.x = GoSquareGap + StandardSquareGap*amountToWalk;
//				finalPosition.y = boardHeight;
//			}
//			
//			else {
//				finalPosition.x = boardWidth;
//				finalPosition.y = 930 - StandardSquareGap*(amountToWalk-10);
//			}
//			
//			return finalPosition;
//		}
		
		return finalPosition;
}
	

	@Override
	public String getType() {
		return "Car";
	}

}
