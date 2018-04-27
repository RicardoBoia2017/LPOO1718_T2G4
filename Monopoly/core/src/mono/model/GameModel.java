package mono.model;

import java.util.List;

import mono.model.entities.*;

/**
 * Singleton
 * @author luis
 *
 */

public class GameModel {

    private static GameModel instance;
	
    private BoardModel board;
    
    private List <HouseModel> houses;
    
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
