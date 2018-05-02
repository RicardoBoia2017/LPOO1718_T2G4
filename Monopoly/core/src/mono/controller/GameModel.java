package mono.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import mono.controller.entities.*;
import mono.model.GameController;
import mono.model.entities.Player;
import mono.model.entities.Square;

/**
 * Singleton
 * @author luis
 *
 */

public class GameModel {

    private static GameModel instance;
	
    private BoardModel board;
    
    private List <HouseModel> houses;
    private List <PlayerModel> playerModels;
    
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
    
	private GameModel() {
		GameController g1 = GameController.getInstance();
		Position finalPosition = new Position(0,0);
		
		Player p1 = g1.getPlayers()[0];
		
		Square s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		
		p1.RollDice();
		
		finalPosition = g1.getCoordFromSquare(s1, p1.getAmountToWalk());
		
		g1.updateGame(p1.getAmountToWalk());
			
			//^
		//how to know the position from the current square??
			//v
			
		PlayerModel p1Model = new PlayerModel(finalPosition.x, finalPosition.y, 0);
			
		playerModels.add(p1Model);
	}
	
}
