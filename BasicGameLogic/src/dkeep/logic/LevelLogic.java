package dkeep.logic;

public interface LevelLogic {

	void updateGame (char heroMovement, Map map);
	
	public String getLevelState();
	
	public Hero getHero();
	
	public Guard getGuard();
	public Ogre getOgre();
	public Club getClub();
	
	public int getKeyCoordX();
	
	public int getKeyCoordY();
	
}
