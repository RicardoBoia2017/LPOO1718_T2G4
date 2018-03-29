package dkeep.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Point;

public class OgreLevel implements LevelLogic, Serializable{

	Hero hero;
	ArrayList <Ogre> ogres = new ArrayList <Ogre> ();
	ArrayList <Club> clubs = new ArrayList <Club> ();
	Point keyCoords;
	ArrayList <Point> exitDoors;
	String levelState;
	
	public OgreLevel(int numberOfOgres)
	{
		hero = new Hero (1,7);
		hero.setID('A');
		
		for (int i = 0; i < numberOfOgres; i++)
		{
			ogres.add( new Ogre (4,1) );
			ogres.get(i).setBlocker(true);
		}
		
		for (int i = 0; i < numberOfOgres; i++)
		{
			clubs.add( new Club(3,1) );
			clubs.get(i).setBlocker(true);
		}
		
		this.keyCoords = new Point (7,1);
		
		exitDoors = new ArrayList < Point> ();
		exitDoors.add(new Point (0,1));
		
		levelState = "Running";	
	}

	public OgreLevel(Hero hero, ArrayList<Ogre> ogres, ArrayList<Club> clubs, Point keyCoords, ArrayList<Point> exitDoors)
	{
		this.hero = hero;
		hero.setID('A');
		this.ogres = ogres;
		this.clubs = clubs;
		this.keyCoords = keyCoords;
		this.exitDoors = exitDoors;
		this.levelState = "Running";
	}
	
	@Override
	public void updateGame(char heroMovement, Map map) {
		
		int heroMovementReturn = 0;

		// hero phase
		try {
			heroMovementReturn = hero.move(map, heroMovement, this);
		}

		catch (IllegalMapChangeException e) {
			System.out.println("Excecao mov hero");
		}
		
		if (heroMovementReturn == 1)
		{
			levelState = "Victory";
			return;
		}
		
		for (int i = 0; i < ogres.size(); i++) {
			if (heroMovementReturn == 2 && ogres.get(i).getStunCounter() == 0 && ( hero.getID() == 'A' || hero.getID() == 'K') ) {
				manageOgreStun (heroMovement, ogres.get(i), map);

			}

			int stun = ogres.get(i).getStunCounter();
			
			if (stun == 0 && checkHeroGetsCaughtByOgre(map,ogres.get(i)) ) {

				System.out.println("Game Over.");

				levelState = "Over";

				return;
			}

			if (ogres.get(i).getBlocker() == false) {
				// ogre moves
				try {
					ogres.get(i).move(map);
				} catch (IllegalMapChangeException e) {
				}
			}

			manageKeyVisibility(map);
			
			if (clubs.get(i).getBlocker() == false) {

				try {
					clubs.get(i).move(map, ogres.get(i));
				} catch (IllegalMapChangeException e) {
				}
			}

			if ((stun == 0 && (checkHeroGetsCaughtByOgre(map, ogres.get(i)) )
					|| checkHeroGetsCaughtByClub(map,clubs.get(i)) ) ) {
				System.out.println("");
				System.out.println("Game Over.");

				levelState = "Over";

				return;
			}

			if (ogres.get(i).id == '8' && stun == 0) {
				ogres.get(i).id = 'O';
				map.updateMap(ogres.get(i).coordY, ogres.get(i).coordX, ogres.get(i).id);
			}

			manageKeyVisibility(map);
					
		}
		// by now both the club and the ogre, also hero have moved which
		// concludes a turn in stage2
		return;
	}

	public boolean checkHeroGetsCaughtByOgre(Map map, Ogre ogre) {
		if (ogre.getID() != '8' && (map.getMatrix()[ogre.coordY - 1][ogre.coordX] == hero.id
				|| map.getMatrix()[ogre.coordY + 1][ogre.coordX] == hero.id
				|| map.getMatrix()[ogre.coordY][ogre.coordX - 1] == hero.id
				|| map.getMatrix()[ogre.coordY][ogre.coordX + 1] == hero.id))
			return true;

		return false;
	}
	
	public boolean checkHeroGetsCaughtByClub(Map map, Club club) {
		if ((map.getMatrix()[club.coordY - 1][club.coordX] == hero.id
				|| map.getMatrix()[club.coordY + 1][club.coordX] == hero.id
				|| map.getMatrix()[club.coordY][club.coordX - 1] == hero.id
				|| map.getMatrix()[club.coordY][club.coordX + 1] == hero.id))
			return true;

		return false;
	}
	
	public void manageKeyVisibility (Map map)
	{
		if (map.getMatrix()[(int) keyCoords.getY()][(int) keyCoords.getX()] == ' ' && hero.id == 'A') {
			map.updateMap((int)keyCoords.getY(),(int) keyCoords.getX(), 'k');
		}
	}
	
	public void manageOgreStun (char heroMovement, Ogre ogre, Map map)
	{
		switch (heroMovement) {
		case 'a': {
			if ((ogre.coordY == hero.coordY && ogre.coordX == hero.coordX - 1)
					|| (ogre.coordY == hero.coordY - 1 && ogre.coordX == hero.coordX)
					|| (ogre.coordY == hero.coordY + 1 && ogre.coordX == hero.coordX - 1))
				try {
					ogre.stun(map);
				} catch (IllegalMapChangeException e) {
				}

			break;
		}

		case 'd': {
			if ((ogre.coordY == hero.coordY && ogre.coordX == hero.coordX + 1)
					|| (ogre.coordY == hero.coordY + 1 && ogre.coordX == hero.coordX)
					|| (ogre.coordY == hero.coordY - 1 && ogre.coordX == hero.coordX))
				try {
					ogre.stun(map);
				} catch (IllegalMapChangeException e) {
				}

			break;
		}

		case 's': {
			if ((ogre.coordY == hero.coordY + 1 && ogre.coordX == hero.coordX)
					|| (ogre.coordY == hero.coordY && ogre.coordX == hero.coordX + 1)
					|| (ogre.coordY == hero.coordY && ogre.coordX == hero.coordX - 1))
				try {
					ogre.stun(map);
				} catch (IllegalMapChangeException e) {
				}

			break;
		}

		case 'w': {
			if ((ogre.coordY == hero.coordY - 1 && ogre.coordX == hero.coordX)
					|| (ogre.coordY == hero.coordY && ogre.coordX == hero.coordX + 1)
					|| (ogre.coordY == hero.coordY && ogre.coordX == hero.coordX - 1))
				try {
					System.out.println("Stun");
					ogre.stun(map);
				} catch (IllegalMapChangeException e) {
				}

			break;
		}
		}
	}
	
	@Override
	public void openExitDoor (Map map)
	{
		for (Point elem: exitDoors)
		{
			map.updateMap ( (int)elem.getY(), (int) elem.getX(), 'S');
		}
	}
	
	@Override
	public String getLevelState() {
		return levelState;
	}
	@Override
	public Hero getHero() {
		return hero;
	}
	@Override
	public int getKeyCoordX() {
		return (int) keyCoords.getX();
	}
	@Override
	public int getKeyCoordY() {
		return (int) keyCoords.getY();
	}
	@Override
	public Guard getGuard() {
		return null;
	}
	@Override
	public Ogre getOgre() {
		return ogres.get(0);
	}
	@Override
	public Club getClub() {
		return clubs.get(0);
	}
	@Override
	public String getLevelType() {
		return "Ogre";
	}
	@Override
	public ArrayList<Point> getExitDoors()
	{
		return exitDoors;
	}
}
