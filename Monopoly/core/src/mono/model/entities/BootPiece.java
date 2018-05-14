package mono.model.entities;

import java.awt.Point;

public class BootPiece extends Piece {
	
	public BootPiece ()
	{
		
	}

	public String getType()
	{
		return "Boot";
	}

	@Override
	public Point move(int playerX, int playerY, int playerPosition, int amounToWalk) {

		return null;
	}
}
