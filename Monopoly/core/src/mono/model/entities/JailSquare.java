package mono.model.entities;

import mono.model.Game;

public class JailSquare extends Square {

	int fine;
	Boolean playerWillPayFine;
	
	public JailSquare() {
		super("Jail", 10);
		fine = 50;
		playerWillPayFine = false;
	}
	
	public void freePlayer(Player p) {
		p.freeFromJail();
		p.resetTurnsWithoutMoving();
	}

	@Override
	public void doAction(Player p) {
		
		if(p.getTurnsWithoutMoving() == 2) {
			//telling the model the player is in Jail ahead of time so it won't display the option to pay on the third turn since that doesn't make much sense (he will get out anyway)
			p.tellGameModelThePlayerIsInJail(false);
		}
		
		if(p.getTurnsWithoutMoving() == 3) {
			freePlayer(p);
		}
		
		else if (p.getCurrentDiceRoll() != null && p.getCurrentDiceRoll().getValue1() == p.getCurrentDiceRoll().getValue2()) {
			freePlayer(p);
			Game g1 = Game.getInstance();
			g1.movePlayer(p.getCurrentDiceRoll());
		}
		
		if(playerWillPayFine) {
			System.out.println(p.getMoney());
			
			if(p.getMoney() > fine) {
				p.removeMoney(fine);
				freePlayer(p);
				playerWillPayFine = false;
			}
			
			System.out.print(p.getMoney());
		}
	}
	
	public void aproveFine() {
		playerWillPayFine = true;
	}

}
