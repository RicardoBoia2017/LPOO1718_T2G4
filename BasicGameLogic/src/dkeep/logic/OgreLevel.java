package dkeep.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Point;

/**
 * Class responsible for updating ogre level.
 * 
 * Calls hero's, ogres' and clubs' movement functions, checks if hero gets caught and updates the game map
 *
 */

public class OgreLevel implements LevelLogic, Serializable{

	Hero hero;
	ArrayList <Ogre> ogres = new ArrayList <Ogre> ();
	ArrayList <Club> clubs = new ArrayList <Club> ();
	Point keyCoords;
	ArrayList <Point> exitDoors;
	String levelState;
	
	/**
	 * Constructor which creates a level with a specified number of ogres and clubs
	 * 
	 * @param numberOfEnemies number of {@Ogre}s and {@Club}s in the level
	 */
	public OgreLevel(int numberOfEnemies)
	{
		hero = new Hero (1,7);
		hero.setID('A');
		
		for (int i = 0; i < numberOfEnemies; i++)
		{
			ogres.add( new Ogre (4,1) );
		}
		
		for (int i = 0; i < numberOfEnemies; i++)
		{
			clubs.add( new Club(3,1) );
		}
		
		this.keyCoords = new Point (7,1);
		
		exitDoors = new ArrayList < Point> ();
		exitDoors.add(new Point (0,1));
		
		levelState = "Running";	
	}
	
	/**
	 * Constructor which created a game with the given ogres and clubs
	 * 
	 * @param ogres ArrayList of ogres
	 * @param clubs ArrayList of clubs
	 */
	public OgreLevel(ArrayList<Ogre> ogres, ArrayList<Club> clubs)
	{
		this.ogres = ogres;
		this.clubs = clubs;
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
				manageOgreStun (ogres.get(i), map);

			}

			int stun = ogres.get(i).getStunCounter();
			
			if (stun == 0 && checkHeroGetsCaughtByOgre(map,ogres.get(i)) ) {
				levelState = "Over";

				return;
			}

			if (ogres.get(i).getBlocker() == false) {
				try {
					ogres.get(i).move(map,this);
				} catch (IllegalMapChangeException e) {
				}
			}

			manageKeyVisibility(map);
			
			if (clubs.get(i).getBlocker() == false) {

				try {
					clubs.get(i).move(map, ogres.get(i),this);
				} catch (IllegalMapChangeException e) {
				}
			}

			if ((stun == 0 && (checkHeroGetsCaughtByOgre(map, ogres.get(i)) )
					|| checkHeroGetsCaughtByClub(map,clubs.get(i)) ) ) {

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

	/**
	 * Checks if hero gets caught by a specific ogre
	 * 
	 * @param map current {@link Map}
	 * @param ogre {@link Ogre}
	 * @return true if hero gets caught, otherwise return false
	 */
	public boolean checkHeroGetsCaughtByOgre(Map map, Ogre ogre) {
		if (ogre.getID() != '8' && (map.getMatrix()[ogre.coordY - 1][ogre.coordX] == hero.id
				|| map.getMatrix()[ogre.coordY + 1][ogre.coordX] == hero.id
				|| map.getMatrix()[ogre.coordY][ogre.coordX - 1] == hero.id
				|| map.getMatrix()[ogre.coordY][ogre.coordX + 1] == hero.id))
			return true;

		return false;
	}
	
	/**
	 * Checks if hero gets caught by a specific club
	 * 
	 * @param map current {@link Map}
	 * @param club {@link Club}
	 * @return true if hero gets caught, otherwise returns false
	 */
	public boolean checkHeroGetsCaughtByClub(Map map, Club club) {
		if ((map.getMatrix()[club.coordY - 1][club.coordX] == hero.id
				|| map.getMatrix()[club.coordY + 1][club.coordX] == hero.id
				|| map.getMatrix()[club.coordY][club.coordX - 1] == hero.id
				|| map.getMatrix()[club.coordY][club.coordX + 1] == hero.id))
			return true;

		return false;
	}
	
	/**
	 * Makes key visible when no enemy is on top of it and hero doesn't have it
	 * 
	 * @param map current {@link Map}
	 */
	private void manageKeyVisibility (Map map)
	{
		if (map.getMatrix()[(int) keyCoords.getY()][(int) keyCoords.getX()] == ' ' && hero.id == 'A') {
			map.updateMap((int)keyCoords.getY(),(int) keyCoords.getX(), 'k');
		}
	}
	
	/**
	 * Checks if ogre needs to be stunned and if affirmative, calls function
	 * 
	 * @param ogre {@link Ogre}
	 * @param map current {@link Map}
	 */
	private void manageOgreStun (Ogre ogre, Map map)
	{
		
		if ( (ogre.getCoordY() == hero.getCoordY() && ogre.getCoordX() == hero.getCoordX() + 1) || 
			 (ogre.getCoordY() == hero.getCoordY() && ogre.getCoordX() == hero.getCoordX() - 1) ||
			 (ogre.getCoordY() == hero.getCoordY() - 1 && ogre.getCoordX() == hero.getCoordX() ) ||
			 (ogre.getCoordY() == hero.getCoordY() + 1 && ogre.getCoordX() == hero.getCoordX() ) )
			
		try {
			ogre.stun(map);
		}
		catch (IllegalMapChangeException e)
		{	
		
		}
	}
	
	@Override
	public void openExitDoor (Map map)
	{
		switch (hero.getLastMovement()) {
		case 'a':
			map.updateMap(hero.getCoordY(), hero.getCoordX() - 1, 'S');
			break;
		case 'd':
			map.updateMap(hero.getCoordY(), hero.getCoordX() + 1, 'S');
			break;
		case 's':
			map.updateMap(hero.getCoordY() + 1, hero.getCoordX(), 'S');
			break;
		case 'w':
			map.updateMap(hero.getCoordY() - 1, hero.getCoordX(), 'S');
			break;
		}
	}
	
	@Override
	public void setHero(Hero hero) {
		this.hero = hero;
	}

	@Override
	public void setKeyCoords(Point keyCoords) {	
		this.keyCoords = keyCoords;
	}

	@Override
	public void setExitDoors(ArrayList<Point> exitDoors) {
		this.exitDoors = exitDoors;
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
