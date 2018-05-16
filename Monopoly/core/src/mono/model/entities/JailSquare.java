package mono.model.entities;

public class JailSquare extends Square {

	public JailSquare() {
		super("Jail", 10);
	}

	@Override
	public void doAction(Player p) {
		if(p.getTurnsWithoutMoving() == 3) {
			p.freeFromJail();
			p.resetTurnsWithoutMoving();
		}
	}

}
