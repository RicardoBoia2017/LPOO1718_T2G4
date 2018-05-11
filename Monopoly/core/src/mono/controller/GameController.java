package mono.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import mono.controller.entities.*;
import mono.model.GameModel;
import mono.model.entities.Player;
import mono.model.entities.Square;

/**
 * Singleton
 * @author luis
 *
 */

public class GameController {

    private static GameController instance;
	
    private BoardModel board;
    
    private List <HouseModel> houses;
    private List <PlayerModel> playerModels = new ArrayList<PlayerModel>();
    
    /**
     * Returns a singleton instance of the game model
     *
     * @return the singleton instance
     */
    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }
    
	private GameController() {
		GameModel g1 = GameModel.getInstance();
		
		Position finalPosition = new Position(0,0);
		
		Player p1 = g1.getPlayers()[0];
		
		Square s1 = g1.getBoard().getBoardArray().get(p1.getPosition());
		
		p1.rollDice();
		
		finalPosition = g1.getCoordFromSquare(s1, p1.getAmountToWalk());
		
		g1.updateGame(p1.getAmountToWalk());
			
		PlayerModel p1Model = new PlayerModel(finalPosition.x, finalPosition.y, 0, p1.getDice1Num(), p1.getDice2Num());
		
		playerModels.add(p1Model);
	}
	
	public List<PlayerModel> getPlayersToDraw() {
		return playerModels;
	}
	
}
