package dkeep.logic;

public class GuardLevel implements LevelLogic{

	Hero hero;
	Guard guard;
//	Map map;
	int keycoordX;
	int keycoordY;
	
	public GuardLevel(String guardPersonality)
	{
		hero = new Hero(1,1);
		guard = new Guard (8,1,guardPersonality);
		
	}
	
	@Override
	public char[][] updateGame(char heroMovement, Map map) {
		
		int heroMovementReturn = 0;

		// hero phase
		try {
			heroMovementReturn = hero.move(map, heroMovement, 1);
		}

		catch (IllegalMapChangeException e) {
			System.out.println("Excecao mov hero");
		}
		
		if (heroMovementReturn == 1) {

//				System.out.println(" ");
//				System.out.println("Now you went up the stairs, new stage.");
//				System.out.println(ogre.length + " ogre(s).");
//				System.out.println(" ");

				// you went up the stairs, now a new level must begin.

				// update game stage
//				hero.setcoordY(7);
//				hero.setid('A');
//				keycoordX = 7;
//				keycoordY = 1;
//
//				stage = 2;
//				map.setmap(2); // change to second map
//
//				return map.getmap();
		}
		if (map.getmap()[keycoordY][keycoordX] == ' ') {
			
			try {
				map.setMap(keycoordY, keycoordX, 'k');
			} 
			
			catch (IllegalMapChangeException e) {}
		}

		// guard phase, he will only move in a given pattern according to
		// the array guardpositon.

		if (guard.getMovementBlocker() == false) {

			if (checkIfGameOver(map)) {

				// pass interface game over state, interface will print.

				System.out.println("Game Over.");

//				gameState = "Over";

				return map.getmap();
			}

			switch (guard.personality) {

			case "Rookie":
				try {
					guard.rookieMove(map);
				} catch (IllegalMapChangeException e) {
				}
				break;

			case "Drunken":
				try {
					guard.drunkenMove(map);
				} catch (IllegalMapChangeException e) {
				}
				break;

			case "Suspicious":
				try {
					guard.suspiciousMove(map);
				} catch (IllegalMapChangeException e) {
				}
				break;
			}

			if (checkIfGameOver(map)) {

				// pass interface game over state, interface will print.
				System.out.println("");
				System.out.println("Game Over.");

//				gameState = "Over";

				return map.getmap();
			}

			// end of a turn of stage 1, by now the map has the current
			// state
			// and hero and guard have both moved and checked for collision.
		}
		return map.getmap();		
	}
	
	public boolean checkIfGameOver(Map map)
	{
		if (guard.getID() != 'g' && (map.getmap()[guard.coordY - 1][guard.coordX] == hero.id
				|| map.getmap()[guard.coordY + 1][guard.coordX] == hero.id
				|| map.getmap()[guard.coordY][guard.coordX - 1] == hero.id
				|| map.getmap()[guard.coordY][guard.coordX + 1] == hero.id))
			return true;

		return false;
	}

}
