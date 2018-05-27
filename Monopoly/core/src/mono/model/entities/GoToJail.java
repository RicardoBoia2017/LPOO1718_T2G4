package mono.model.entities;

import mono.model.Game;

public class GoToJail extends Square {

	public GoToJail() {
		super("GoToJail", 30);
	}

	@Override
	public void doAction (Player p) {
		Game g1 = Game.getInstance();
		g1.movePlayer(20, false);
		p.sendToJail();
		
		System.out.print("Inside Do Action Got to Jail, player is in jail?: ");
		System.out.print(p.getPlayerIsInJail());
		
		p.tellGameModelThePlayerIsInJail();
	}

	@Override
	public String getType() {
		return "Go To Jail";
	}
	
}
