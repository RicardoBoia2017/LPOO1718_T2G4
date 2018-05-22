package mono.model.entities;

public class StartSquare extends Square {

	Boolean firstVisit;
	
	public StartSquare() {
		super("Start", 0);
		firstVisit = true;
	}

	@Override
	public void doAction (Player p) {
		if(!firstVisit) {
			System.out.println(p.getMoney());
			p.addMoney(400);
			System.out.println(p.getMoney());
		}
	}

	@Override
	public String getType() {
		return "Start";
	}
	
	public void setFirstVisitToFalse() {
		firstVisit = false;
	}

}
