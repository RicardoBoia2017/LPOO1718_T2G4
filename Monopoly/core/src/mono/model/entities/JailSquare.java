package mono.model.entities;

import mono.model.GameModel;

public class JailSquare extends Square {

	public JailSquare() {
		super("Jail", 10);
	}
	
	public void freePlayer(Player p) {
		p.freeFromJail();
		p.resetTurnsWithoutMoving();
	}

	@Override
	public void doAction(Player p) {
		
		if(p.getTurnsWithoutMoving() == 3) {
			freePlayer(p);
		}
		
		else if (p.getCurrentDiceRoll() != null && p.getCurrentDiceRoll().getValue1() == p.getCurrentDiceRoll().getValue2()) {
			freePlayer(p);
			GameModel g1 = GameModel.getInstance();
			g1.movePlayer(p.getCurrentDiceRoll());
		}
	}

}
