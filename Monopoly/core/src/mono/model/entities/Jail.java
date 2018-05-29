package mono.model.entities;

import mono.model.Game;

public class Jail extends Square {

	private int fine;
	private Boolean playerWillPayFine;
	
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
			Game.getInstance().movePlayer(p.getCurrentDiceRoll(), p.getDiceSameValue());
		}
		
		if (p.getCurrentDiceRoll() != 0 && p.getDiceSameValue() && p.getPlayerIsInJail()) {
			freePlayer(p);
			p.tellGameModelThePlayerIsInJail();
			Game.getInstance().movePlayer(p.getCurrentDiceRoll(), p.getDiceSameValue());
		}
		
		if(playerWillPayFine && p.getPlayerIsInJail()) {
//			System.out.println(p.getMoney());
			
			if(p.getMoney() > fine) {
				p.removeMoney(fine, false);
				freePlayer(p);
				playerWillPayFine = false;
				p.tellGameModelThePlayerIsInJail();
				Game.getInstance().movePlayer(p.getCurrentDiceRoll(), p.getDiceSameValue());
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
