package dkeep.logic;

public class Character {
	protected int coordX;
	protected int coordY;
	protected char id;
	
	public Character(int X, int Y, char iden) {
		coordX = X;
		coordY = Y;
		id = iden;
	}
	
	public void move(char [][] map) {
		//abstract movement method
	}
	
	public int getcoordX() {return coordX;};
	public int getcoordY() {return coordY;};
	public int getID() {return id;};
	public void setcoordX(int x) {coordX = x;};
	public void setcoordY(int y) {coordY = y;};
	public void setID(char iden) {id = iden;};
}
