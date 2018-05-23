package mono.model.entities;

import mono.model.Game;

public class Jail extends Square {

	int fine;
	Boolean playerWillPayFine;
	
	public Jail() {
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
			p.tellGameModelThePlayerIsInJail();
		}
		
		else if (p.getCurrentDiceRoll() != null && p.getCurrentDiceRoll().getValue1() == p.getCurrentDiceRoll().getValue2()) {
			freePlayer(p);
			Game g1 = Game.getInstance();
			g1.movePlayer(p.getCurrentDiceRoll());
			p.tellGameModelThePlayerIsInJail();
		}
		
		if(playerWillPayFine) {
//			System.out.println(p.getMoney());
			
			if(p.getMoney() > fine) {
				p.removeMoney(fine);
				freePlayer(p);
				playerWillPayFine = false;
				p.tellGameModelThePlayerIsInJail();
				Game g1 = Game.getInstance();
				g1.movePlayer(p.getCurrentDiceRoll());
			}
			
//			System.out.print(p.getMoney());
		}
	}
	
	public void aproveFine() {
		playerWillPayFine = true;
	}

	@Override
	public String getType() {
		return "Jail";
	}

}
