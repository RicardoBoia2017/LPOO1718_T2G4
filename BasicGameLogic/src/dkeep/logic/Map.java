package dkeep.logic;

import java.io.Serializable;

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
	
	public Map() {
		currentLevel = 0;
	}
	
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
	
	public char [][] getMatrix() {
		char[][] empty = {{' '}};
		
		if (currentMap == null)
			return empty;
		
		return currentMap;
	}
	
	public int getCurrentMap() {return currentLevel;};
	
	public void setCustomMapTo(char[][] newMap) {
		PersonalizedMap = newMap;
		currentMap = PersonalizedMap;
	}
	
	public void updateMap(int y, int x, char change) {
		currentMap [y][x] = change;
	}
}
