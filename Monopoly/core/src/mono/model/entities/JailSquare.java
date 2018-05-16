package mono.model.entities;

import mono.model.GameModel;

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
		
		if(p.getTurnsWithoutMoving() == 3) {
			freePlayer(p);
		}
		
		else if (p.getCurrentDiceRoll() != null && p.getCurrentDiceRoll().getValue1() == p.getCurrentDiceRoll().getValue2()) {
			freePlayer(p);
			GameModel g1 = GameModel.getInstance();
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
