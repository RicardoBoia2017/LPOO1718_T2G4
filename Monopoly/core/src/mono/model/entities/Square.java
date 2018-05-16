package mono.model.entities;

import java.util.ArrayList;

public abstract class Square {
	String name; 
	int position;
	ArrayList<Player> playersOnTopOfSquare = new ArrayList<Player>();

	public Square(String s, int position) {
		name = s;
		this.position = position;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void doAction (Player p);
	
	public void setPlayerOnTopOfSquare(Player p1) {
		playersOnTopOfSquare.add(p1);
	}
	
	public int getNumPlayersOnTopOfSquare() {
		return playersOnTopOfSquare.size();
	}
	
	public ArrayList<Player> getplayersOnTopOfSquareArray() {
		return playersOnTopOfSquare;
	}
}
