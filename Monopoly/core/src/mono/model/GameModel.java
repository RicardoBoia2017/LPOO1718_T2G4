package mono.model;

/**
 * Singleton
 * @author luis
 *
 */

public class GameModel {

    private static GameModel instance;
	
    /**
     * Returns a singleton instance of the game model
     *
     * @return the singleton instance
     */
    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }
    
	public GameModel() {
		
	}

}
