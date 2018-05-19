package mono.model.entities;

import mono.model.Game;

public class GoToJailSquare extends Square {

	public GoToJailSquare() {
		super("GoToJail", 30);
	}

	@Override
	public void doAction (Player p) {
		Game g1 = Game.getInstance();
		g1.movePlayer(20);
		p.sendToJail();
		p.tellGameModelThePlayerIsInJail(true);
	}

}
