package mono.model.entities;

import mono.model.Game;

public class GoToJail extends Square {

	public GoToJail() {
		super("GoToJail", 30);
	}

	@Override
	public void doAction (Player p) {
		Game g1 = Game.getInstance();
		g1.movePlayer(-20, false);
		p.sendToJail();

		p.tellGameModelThePlayerIsInJail();
	} 

	@Override
	public String getType() {
		return "Go To Jail";
	}
	
}
