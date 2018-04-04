package dkeep.logic;

import java.io.Serializable;

/**
 * 
 * Map class, manages all the map related events. Includes default maps, a test map for unit testing, and auxiliary
 * functions for a Personalized Map.
 *
 */

public class Map implements Serializable {
	private char [][] level1 = {
			 {'X','X','X','X','X','X','X','X','X', 'X'}, 
			 {'X','H',' ',' ','I',' ','X',' ','G', 'X'},
			 {'X','X','X',' ','X','X','X',' ',' ', 'X'},
			 {'X',' ','I',' ','I',' ','X',' ',' ', 'X'},
			 {'X','X','X',' ','X','X','X',' ',' ', 'X'},
			 {'I',' ',' ',' ',' ',' ',' ',' ',' ', 'X'},
			 {'I',' ',' ',' ',' ',' ',' ',' ',' ', 'X'},
			 {'X','X','X',' ','X','X','X','X',' ', 'X'},
			 {'X',' ','I',' ','I',' ','X','k',' ', 'X'},
			 {'X','X','X','X','X','X','X','X','X', 'X'}};
	
	private char [][] level2 = {
			{'X','X','X','X','X','X','X','X','X'},
			{'I',' ',' ','*','O',' ',' ','k','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','A',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X'}};
	
	private char [][] testLevel1 = {
			{'X','X','X','X','X'},
			{'X','H',' ','G','X'},
			{'I',' ',' ',' ','X'},
			{'I','k',' ',' ','X'},
			{'X','X','X','X','X'},
	};
		
	private char [][] PersonalizedMap;
	
	private char [][] currentMap;
	
	private int currentLevel;
	
	/**
	 * Creates an empty Map with nothing.
	 */
	public Map() {
		currentLevel = 0;
	}
	
	/**
	 * Creates a map according to the number within test.
	 * @param test If it is 0, creates level1 Map, any other value will be used to create a unit testing map.
	 */
	public Map(int test) {
		if (test == 0) {
			currentLevel = 1;
			currentMap = level1;
		}
		else {
			currentLevel = -1;
			currentMap = testLevel1;
		}
	}
	
	/**
	 * Creates a Personalized map "skeleton" (only the outline with walls).
	 * @param width Width of the map.
	 * @param height Height of the map.
	 */
	public Map (int width, int height)
	{
		PersonalizedMap = new char [height] [width];
		
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				if (i == 0 || i == width -1)
				{
					PersonalizedMap [j][i] = 'X';
				}
				
				else if (j == 0 || j == height - 1)
				{
					PersonalizedMap [j][i] = 'X';
				}
				
				else
				{
					PersonalizedMap [j][i] = ' ';
				}
			}
		}
		
		currentLevel = 3;
		currentMap = PersonalizedMap;
	}
	
	/**
	 * Associates an actual char matrix to the current Map object depending on the level.
	 * @param current The level.
	 */
	public void setMap(int current) {
		currentLevel = current;
		
		if(current == 1) {
			this.currentMap = level1;
		}
		
		if(current == 2) {
			this.currentMap = level2;
		}
		
		if(current == -1) {
			this.currentMap = testLevel1;
		}
		
		if(current == 3) {
			this.currentMap = PersonalizedMap;
		}
	}
	
	/**
	 * @return The char matrix associated with this map.
	 */
	public char [][] getMatrix() {
		char[][] empty = {{' '}};
		
		if (currentMap == null)
			return empty;
		
		return currentMap;
	}
	
	/**
	 * 
	 * @return The current level.
	 */
	public int getCurrentMap() {return currentLevel;};
	
	/**
	 * 	Sets the char matrix of a Personalized map to something else.
	 * @param newMap The char matrix.
	 */
	public void setCustomMapTo(char[][] newMap) {
		PersonalizedMap = newMap;
		currentMap = PersonalizedMap;
	}
	
	/**
	 * Changes the cell inside the char matrix of the Map object.
	 * @param y Y coordinate of that cell in the matrix.
	 * @param x X coordinate.
	 * @param change What you want to change it to.
	 */
	public void updateMap(int y, int x, char change) {
		currentMap [y][x] = change;
	}
}
