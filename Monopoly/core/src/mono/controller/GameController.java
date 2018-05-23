package mono.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import mono.controller.entities.*;
import mono.model.Game;
import mono.model.entities.Pair;
import mono.model.entities.Player;
import mono.model.entities.Square;
import mono.view.GameScreen;

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

	}
	
	public Pair doTurn()
	{	
		Pair diceRoll = Game.getInstance().rollDice();
		Game.getInstance().movePlayer(diceRoll);
		doSquareAction();

		return diceRoll;
	}
	
	private void doSquareAction()
	{
		Game.getInstance().squareAction();
	}
	
	public int buyProperty()
	{
		int res = Game.getInstance().checkPropertyAvailibility();
		
		if (res == 0)
			res = Game.getInstance().buyProperty();
			
		return res;
	}
	
	public void endTurn()
	{
		Game.getInstance().endTurn();
	}
	
	public List<PlayerModel> getPlayersToDraw() {
		return playerModels;
	}

	public void payJail() {
		Game.getInstance().tellJailPlayerWantsToPayFine();
		doSquareAction();
	}

	public boolean tellViewToDisplayJailDialog() {
		System.out.println(Game.getInstance().getplayerIsInJail());
		return Game.getInstance().getplayerIsInJail();
	}
	
}
