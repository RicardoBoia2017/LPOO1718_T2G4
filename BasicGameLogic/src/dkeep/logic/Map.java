package dkeep.logic;

public class Map {
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
			{'X','H',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X'}};
	
	private int currentmap;
	
	public Map() {
		currentmap = 1;
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
		
		return empty;
	}
	
	public int getcurrentlevel() {return currentmap;};
	
	public void setMap(int y, int x, char change) {
		if(currentmap == 1) {
			level1[y][x] = change;
		}
		
		if(currentmap == 2) {
			level2[y][x] = change;
		}
	}
}
