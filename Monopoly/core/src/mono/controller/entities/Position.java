package mono.controller.entities;

public class Position {
	public int x;
	public int y;
	
	public Position(int xCoord,int yCoord){
		x = xCoord;
		y = yCoord;
	}
	
	int getCoordX() {return x;}
	int getCoordY() {return y;}
}
