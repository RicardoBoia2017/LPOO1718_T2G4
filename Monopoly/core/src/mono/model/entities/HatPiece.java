package mono.model.entities;

import java.awt.Point;

public class HatPiece extends Piece{
	
	public HatPiece () 
	{
		
	}

	@Override
	public String getType() {
		return "Hat";
	}

	@Override
	public Point move(int playerX, int playerY, int playerPosition, int amounToWalk) {

		return null;
	}

	
}
