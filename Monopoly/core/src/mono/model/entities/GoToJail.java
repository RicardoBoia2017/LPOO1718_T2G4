package mono.model.entities;

import mono.model.Game;

/**
 * Creates Go To Jail squares and sends to jail that end up on it. Subclass of Square.
 * 
 * @author ricar
 *
 */
public class GoToJail extends Square {

	/**
	 * Creates Go To Jail square
	 */
	public GoToJail() {
		super("GoToJail", 30);
	}

	@Override
	public void doAction (Player p) {
		Game g1 = Game.getInstance();
		g1.movePlayer(-20, false);
		p.setInJail(true);

		p.tellGameModelThePlayerIsInJail();
	} 

	@Override
	public String getType() {
		return "Go To Jail";
	}
	
}
