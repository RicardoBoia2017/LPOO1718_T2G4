package mono.model.entities;

public class StartSquare extends Square {

	public StartSquare() {
		super("Start", 0);
	}

	@Override
	public void doAction (Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		return "Start";
	}

}
