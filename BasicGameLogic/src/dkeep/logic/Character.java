package dkeep.logic;

import java.io.Serializable;

/**
 * The following class is the super class of all elements at play in each game (the ones that move and have
 * distinguishable actions), or in other words: the Hero, Guard, Ogre and Club.
 * 
 * Note: Yes, the Club counts as "character" as it behaves exactly like one, it has
 * X coordinates, Y coordinates, a character that identifies it and it moves.
 */

public class Character implements Serializable {
	protected int coordX;
	protected int coordY;
	protected char id;
	
	/**
	 * Creates a Character.
	 * @param X X coordinate.
	 * @param Y Y coordinate.
	 * @param iden Identifier, char that represents it.
	 */
	public Character(int X, int Y, char iden) {
		coordX = X;
		coordY = Y;
		id = iden;
	}
	
	/**
	 * Moves the character, abstract method that will be later implemented by each character.
	 * @param map Map to move in.
	 */
	public void move(char [][] map) {
		//abstract movement method
	}
	
	/**
	 * 
	 * @return Character's coordinate X.
	 */
	public int getCoordX() {return coordX;};
	
	/**
	 * 
	 * @return Character's coordinate Y.
	 */
	public int getCoordY() {return coordY;};
	
	/**
	 * 
	 * @return Character's identifier;
	 */
	public int getID() {return id;};
	
	/**
	 * 
	 * @param x X coordinate to set to.
	 */
	public void setcoordX(int x) {coordX = x;};
	
	/**
	 * 
	 * @param y Y coordinate to set to.
	 */
	public void setcoordY(int y) {coordY = y;};
	
	/**
	 * 
	 * @param iden Identifier to set to.
	 */
	public void setID(char iden) {id = iden;};
}
