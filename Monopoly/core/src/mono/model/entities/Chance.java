package mono.model.entities;

public class Chance extends Square {

	int id;
	
	public Chance(int position) {
		super("Chance", position);
	}

	@Override
	public void doAction(Player p) {

		
	}

	@Override
	public String getType() {

		return "Chance";
	}

}
