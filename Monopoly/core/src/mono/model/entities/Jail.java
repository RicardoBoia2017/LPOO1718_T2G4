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
		
		if(p.getTurnsWithoutMoving() == 3 && p.getPlayerIsInJail()) {
			freePlayer(p);
			p.tellGameModelThePlayerIsInJail();
			Game.getInstance().movePlayer(p.getCurrentDiceRoll());
		}
		
		if (p.getCurrentDiceRoll() != null && p.getCurrentDiceRoll().getValue1() == p.getCurrentDiceRoll().getValue2() && p.getPlayerIsInJail()) {
			freePlayer(p);
			p.tellGameModelThePlayerIsInJail();
			Game.getInstance().movePlayer(p.getCurrentDiceRoll());
		}
		
		if(playerWillPayFine && p.getPlayerIsInJail()) {
//			System.out.println(p.getMoney());
			
			if(p.getMoney() > fine) {
				p.removeMoney(fine);
				freePlayer(p);
				playerWillPayFine = false;
				p.tellGameModelThePlayerIsInJail();
				Game.getInstance().movePlayer(p.getCurrentDiceRoll());
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
