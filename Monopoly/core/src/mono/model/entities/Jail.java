package mono.model.entities;

import mono.model.Game;

/**
 * Creates Jail square and manages everything related with it, like blocking the player, accepting fines, freeing player, etc. Subclass of Square. 
 * 
 * @author ricar
 *
 */
public class Jail extends Square {

	private int fine;
	private Boolean playerWillPayFine;
	
	/**
	 * Creates Jail square
	 */
	public Jail() {
		super("Jail", 10);
		fine = 50;
		playerWillPayFine = false;
	}
	
	/**
	 * Frees player p from jail
	 * 
	 * @param p current player
	 */
	public void freePlayer(Player p) {
		p.setInJail(false);
		p.resetTurnsWithoutMoving(); 
	}

	@Override
	public void doAction(Player p) {
		
		if(p.getTurnsWithoutMoving() == 3 && p.isInJail()) {
			freePlayer(p);
			p.tellGameModelThePlayerIsInJail();
			Game.getInstance().movePlayer(p.getCurrentDiceRoll(), p.getDiceSameValue());
		}
		
		if (p.getCurrentDiceRoll() != 0 && p.getDiceSameValue() && p.isInJail()) {
			freePlayer(p);
			p.tellGameModelThePlayerIsInJail();
			Game.getInstance().movePlayer(p.getCurrentDiceRoll(), p.getDiceSameValue());
		}
		
		if(playerWillPayFine && p.isInJail()) {
			
			if(p.getMoney() > fine) {
				
				int turnsLeft = p.getTurnsWithoutMoving();
				
				p.removeMoney(fine, false);
				freePlayer(p);
				playerWillPayFine = false;
				p.tellGameModelThePlayerIsInJail();
				if (turnsLeft > 0)
					Game.getInstance().movePlayer(p.getCurrentDiceRoll(), p.getDiceSameValue());
			}
			
		}
	}
	
	/**
	 * Sets private boolean to inform square that player wants to pay fine
	 */
	public void aproveFine() {
		playerWillPayFine = true;
	}

	@Override
	public String getType() {
		return "Jail";
	}

}
