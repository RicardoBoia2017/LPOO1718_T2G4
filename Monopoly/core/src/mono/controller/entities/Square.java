package mono.controller.entities;

public class Square {
	String name; //name of the square
	int position;
	Player[] playersOnTopOfSquare;
	int numPlayersOnTopOfSquare;

	public Square(String s, int position) {
		name = s;
		this.position = position;
		numPlayersOnTopOfSquare = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPlayerOnTopOfSquare(Player p1) {
		playersOnTopOfSquare[numPlayersOnTopOfSquare] = p1;
		numPlayersOnTopOfSquare++;
	}
}
