package dkeep.logic;

public class Map {
	private char [][] level1 = {
			 {'X','X','X','X','X','X','X','X','X', 'X'}, 
			 {'X','H',' ',' ','I',' ','X',' ','G', 'X'},
			 {'X','X','X',' ','X','X','X',' ',' ', 'X'},
			 {'X',' ','I',' ','I',' ','X',' ',' ', 'X'},
			 {'X','X','X',' ','X','X','X',' ',' ', 'X'},
			 {'S',' ',' ',' ',' ',' ',' ',' ',' ', 'X'},
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
	
	
	private int currentmap;
	
	public Map(int test) {
		if (test == 0) {
			currentmap = 1;
		}
		else {
			currentmap = -1;
		}
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
		
		return empty;
	}
	
	public int getcurrentlevel() {return currentmap;};
	
	public void setMap(int y, int x, char change) throws IllegalMapChangeException {
		
		if(currentmap == 1) {
			
			if(level1[y][x] == 'X') {
				throw new IllegalMapChangeException();
			}
			
			level1[y][x] = change;
		}
		
		if(currentmap == 2) {
			
			if(level2[y][x] == 'X') {
				throw new IllegalMapChangeException();
			}
			
			level2[y][x] = change;
		}
		
		if(currentmap == -1) {
			
			if(testLevel1[y][x] == 'X') {
				throw new IllegalMapChangeException();
			}
			
			testLevel1[y][x] = change;
		}
	}
}
