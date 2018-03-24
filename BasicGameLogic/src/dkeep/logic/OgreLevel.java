package dkeep.logic;

import java.io.Serializable;
import java.util.ArrayList;

public class OgreLevel implements LevelLogic, Serializable{

	Hero hero;
	ArrayList <Ogre> ogres = new ArrayList <Ogre> ();
	ArrayList <Club> clubs = new ArrayList <Club> ();
	int keyCoordX;
	int keyCoordY;
	String levelState;
	
	public OgreLevel(int numberOfOgres)
	{
		hero = new Hero (1,7);
		hero.setID('A');
		
		for (int i = 0; i < numberOfOgres; i++)
		{
			ogres.add( new Ogre (4,1) );
		}
		
		for (int i = 0; i < numberOfOgres; i++)
		{
			clubs.add( new Club(3,1) );
		}
		
		levelState = "Running";	
	}

	public OgreLevel(Hero hero, ArrayList<Ogre> ogres, ArrayList<Club> clubs, int [] keyCoords)
	{
		this.hero = hero;
		hero.setID('A');
		this.ogres = ogres;
		this.clubs = clubs;
		this.keyCoordX = keyCoords[0];
		this.keyCoordY = keyCoords[1];
	}
	
	@Override
	public char[][] updateGame(char heroMovement, Map map) {
		
		int heroMovementReturn = 0;

		// hero phase
		try {
			heroMovementReturn = hero.move(map, heroMovement, 2);
		}

		catch (IllegalMapChangeException e) {
			System.out.println("Excecao mov hero");
		}
		
		for (int i = 0; i < ogres.size(); i++) {
			if (heroMovementReturn == 2 && ogres.get(i).getStunCounter() == 0 && hero.getID() == 'A') {
				switch (heroMovement) {
				case 'a': {
					if ((ogres.get(i).coordY == hero.coordY && ogres.get(i).coordX == hero.coordX - 1)
							|| (ogres.get(i).coordY == hero.coordY - 1 && ogres.get(i).coordX == hero.coordX)
							|| (ogres.get(i).coordY == hero.coordY + 1 && ogres.get(i).coordX == hero.coordX - 1))
						try {
							ogres.get(i).stun(map);
						} catch (IllegalMapChangeException e) {
						}

					break;
				}

				case 'd': {
					if ((ogres.get(i).coordY == hero.coordY && ogres.get(i).coordX == hero.coordX + 1)
							|| (ogres.get(i).coordY == hero.coordY + 1 && ogres.get(i).coordX == hero.coordX)
							|| (ogres.get(i).coordY == hero.coordY - 1 && ogres.get(i).coordX == hero.coordX))
						try {
							ogres.get(i).stun(map);
						} catch (IllegalMapChangeException e) {
						}

					break;
				}

				case 's': {
					if ((ogres.get(i).coordY == hero.coordY + 1 && ogres.get(i).coordX == hero.coordX)
							|| (ogres.get(i).coordY == hero.coordY && ogres.get(i).coordX == hero.coordX + 1)
							|| (ogres.get(i).coordY == hero.coordY && ogres.get(i).coordX == hero.coordX - 1))
						try {
							ogres.get(i).stun(map);
						} catch (IllegalMapChangeException e) {
						}

					break;
				}

				case 'w': {
					if ((ogres.get(i).coordY == hero.coordY - 1 && ogres.get(i).coordX == hero.coordX)
							|| (ogres.get(i).coordY == hero.coordY && ogres.get(i).coordX == hero.coordX + 1)
							|| (ogres.get(i).coordY == hero.coordY && ogres.get(i).coordX == hero.coordX - 1))
						try {
							ogres.get(i).stun(map);
						} catch (IllegalMapChangeException e) {
						}

					break;
				}
				}
			}

			int stun = ogres.get(i).getStunCounter();
			
			if (stun == 0 && checkHeroGetsCaughtByOgre(map,ogres.get(i)) ) {

				System.out.println("Game Over.");

				levelState = "Over";

				return map.getmap();
			}

			if (ogres.get(i).getBlocker() == false) {
				// ogre moves
				try {
					ogres.get(i).move(map);
				} catch (IllegalMapChangeException e) {
				}
			}

			if (clubs.get(i).getBlocker() == false) {

				try {
					clubs.get(i).move(map, ogres.get(i));
				} catch (IllegalMapChangeException e) {
				}
			}

			if ((stun == 0 && (checkHeroGetsCaughtByOgre(map, ogres.get(i)) )
					|| checkHeroGetsCaughtByClub(map,clubs.get(i)) ) ) {
				// interface
				System.out.println("");
				System.out.println("Game Over.");

				levelState = "Over";

				return map.getmap();
			}

			if (ogres.get(i).id == '8' && stun == 0) {
				ogres.get(i).id = 'O';
				map.setMap(ogres.get(i).coordY, ogres.get(i).coordX, ogres.get(i).id);
			}

			if (map.getmap()[keyCoordY][keyCoordX] == ' ' && hero.id == 'H') {
				map.setMap(keyCoordY, keyCoordX, 'k');
			}
					
		}
		// by now both the club and the ogre, also hero have moved which
		// concludes a turn in stage2
		return map.getmap();
	}

	public boolean checkHeroGetsCaughtByOgre(Map map, Ogre ogre) {
		if (ogre.getID() != '8' && (map.getmap()[ogre.coordY - 1][ogre.coordX] == hero.id
				|| map.getmap()[ogre.coordY + 1][ogre.coordX] == hero.id
				|| map.getmap()[ogre.coordY][ogre.coordX - 1] == hero.id
				|| map.getmap()[ogre.coordY][ogre.coordX + 1] == hero.id))
			return true;

		return false;
	}
	
	public boolean checkHeroGetsCaughtByClub(Map map, Club club) {
		if ((map.getmap()[club.coordY - 1][club.coordX] == hero.id
				|| map.getmap()[club.coordY + 1][club.coordX] == hero.id
				|| map.getmap()[club.coordY][club.coordX - 1] == hero.id
				|| map.getmap()[club.coordY][club.coordX + 1] == hero.id))
			return true;

		return false;
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
		return keyCoordX;
	}

	@Override
	public int getKeyCoordY() {
		return keyCoordY;
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
}
