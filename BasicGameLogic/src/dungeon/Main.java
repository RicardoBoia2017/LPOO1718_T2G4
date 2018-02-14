package dungeon;

public class Main {

	public static void main(String[] args) {
		 char [][] matrix = {{'X','X','X','X','X','X','X','X','X', 'X'}, 
								   {'X','H',' ',' ','I',' ','X',' ','G', 'X'},
								   {'X','X','X',' ','X','X','X',' ',' ', 'X'},
								   {'X',' ','I',' ','I',' ','X',' ',' ', 'X'},
								   {'X','X','X',' ','X','X','X',' ',' ', 'X'},
								   {'I',' ',' ',' ',' ',' ',' ',' ',' ', 'X'},
								   {'I',' ',' ',' ',' ',' ',' ',' ',' ', 'X'},
								   {'X','X','X',' ','X','X','X','X',' ', 'X'},
								   {'X',' ','I',' ','I',' ','X','k',' ', 'X'},
								   {'X','X','X','X','X','X','X','X','X', 'X'}};
		
		 for(int i = 0; i < matrix.length; i++) {
			 System.out.println(matrix[i]);
		 }
	}

}
