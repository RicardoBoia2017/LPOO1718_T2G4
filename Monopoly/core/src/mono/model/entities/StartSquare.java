package mono.model.entities;

/**
 * Creates start square and pay 400€ to players if the end up in it. Subclass of Square
 * @author ricar
 *
 */
public class StartSquare extends Square {

	Boolean firstVisit;
	
	/**
	 * Creates start square 
	 */
	public StartSquare() {
		super("Start", 0);
		firstVisit = true;
	}

	@Override
	public void doAction (Player p) {
		if(!firstVisit) {
			p.addMoney(400);
		}
	}

	@Override
	public String getType() {
		return "Start";
	}
	
	/**
	 * Sets first visit variable to false
	 */
	public void setFirstVisitToFalse() {
		firstVisit = false;
	}

}
