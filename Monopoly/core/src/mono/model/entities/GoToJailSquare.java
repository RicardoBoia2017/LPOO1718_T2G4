package mono.model.entities;

import mono.model.GameModel;

public class GoToJailSquare extends Square {

	public GoToJailSquare() {
		super("GoToJail", 30);
	}

	@Override
	public void doAction (Player p) {
		GameModel g1 = GameModel.getInstance();
		
		p.sendToJail();
		
		g1.movePlayer(20);
	}

}
