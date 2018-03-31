package dkeep.logic;

import java.util.ArrayList;
import java.awt.Point;

public interface LevelLogic {

	void updateGame (char heroMovement, Map map);
	
	void openExitDoor (Map map);
	
	public void setHero (Hero hero);
	public void setKeyCoords (Point keyCoords);
	public void setExitDoors (ArrayList <Point> exitDoors);
	
	
	public String getLevelType();
	public String getLevelState();
	public Hero getHero();
	public Guard getGuard();
	public Ogre getOgre();
	public Club getClub();
	public int getKeyCoordX();
	public int getKeyCoordY();
	public ArrayList <Point> getExitDoors ();
}
