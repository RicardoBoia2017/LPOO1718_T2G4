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
	
	private int currentmap;
	
	public Map() {
		currentmap = 0;
	}
	
	public Map(int test) {
		if (test == 0) {
			currentmap = 1;
		}
		else {
			currentmap = -1;
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
		
		currentmap = 3;
	}
	
	public void setmap(int current) {
		currentmap = current;
	}
	
	public char [][] getmap() {
		char[][] empty = {};
		
		if(currentmap == 1) {
			return level1;
		}
		
		if(currentmap == 2) {
			return level2;
		}
		
		if(currentmap == -1) {
			return testLevel1;
		}
		
		if (currentmap == 3)
		{
			return PersonalizedMap;
		}
		
		return empty;
	}
	
	public int getcurrentlevel() {return currentmap;};
	
	public void setCustomMapTo(char[][] newmap) {
		PersonalizedMap = newmap;
	}
	
	public void setMap(int y, int x, char change) {
		//TODO: Make this generic
		
		if(currentmap == 1) {
			level1[y][x] = change;
		}
		
		if(currentmap == 2) {
			level2[y][x] = change;
		}
		
		if(currentmap == -1) {
			testLevel1[y][x] = change;
		}
		
		if(currentmap == 3) {
			PersonalizedMap[y][x] = change;
		}
	}
}
