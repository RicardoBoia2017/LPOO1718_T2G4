package mono.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import mono.controller.entities.*;
import mono.model.GameModel;
import mono.model.entities.Pair;
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
//		PlayerModel p1Model = new PlayerModel(finalPosition.x, finalPosition.y, 0, p1.getDice1Num(), p1.getDice2Num(), p1.getBoardPiece());
//		
//		playerModels.add(p1Model);
	}
	
	public void doTurn()
	{
		movePlayer();
//		doSquareAction();
		
	}
	
	public Pair movePlayer()
	{	
		Pair diceRoll = GameModel.getInstance().rollDice();
		GameModel.getInstance().movePlayer(diceRoll.getValue1() + diceRoll.getValue2());
		
		return diceRoll;
	}
	
	private void doSquareAction()
	{
		
	}
	
	public List<PlayerModel> getPlayersToDraw() {
		return playerModels;
	}
	
}
