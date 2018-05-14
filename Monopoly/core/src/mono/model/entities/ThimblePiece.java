package mono.model.entities;

import java.awt.Point;

public class ThimblePiece extends Piece {

	
	public ThimblePiece ()
	{
		
	}

	@Override
	public String getType() {
		return "Thimble";
	}

	@Override
	public Point move(int playerX, int playerY, int playerPosition, int amounToWalk) {

		return null;
	}
	
	
}
