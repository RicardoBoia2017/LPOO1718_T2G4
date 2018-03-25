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
	
	private int currentMap;
	
	public Map() {
		currentMap = 0;
	}
	
	public Map(int test) {
		if (test == 0) {
			currentMap = 1;
		}
		else {
			currentMap = -1;
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
		
		currentMap = 3;
	}
	
	public void setMap(int current) {
		currentMap = current;
	}
	
	public char [][] getMatrix() {
		char[][] empty = {};
		
		if(currentMap == 1) {
			return level1;
		}
		
		if(currentMap == 2) {
			return level2;
		}
		
		if(currentMap == -1) {
			return testLevel1;
		}
		
		if (currentMap == 3)
		{
			return PersonalizedMap;
		}
		
		return empty;
	}
	
	public int getCurrentMap() {return currentMap;};
	
	public void setCustomMapTo(char[][] newmap) {
		PersonalizedMap = newmap;
	}
	
	public void updateMap(int y, int x, char change) {
		//TODO: Make this generic
		
		if(currentMap == 1) {
			level1[y][x] = change;
		}
		
		if(currentMap == 2) {
			level2[y][x] = change;
		}
		
		if(currentMap == -1) {
			testLevel1[y][x] = change;
		}
		
		if(currentMap == 3) {
			PersonalizedMap[y][x] = change;
		}
	}
}
