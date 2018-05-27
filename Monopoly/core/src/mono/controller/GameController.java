package mono.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import mono.controller.entities.*;
import mono.model.Game;
import mono.model.entities.BuyableSquare;
import mono.model.entities.Pair;
import mono.model.entities.Player;
import mono.model.entities.Property;
import mono.model.entities.Square;
import mono.view.GameScreen;

/**
 * Singleton
 * @author luis
 *
 */

public class GameController {

    private static GameController instance;
	
//    private BoardModel board;
    
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
		Player p = 	Game.getInstance().getPlayers().get(Game.getInstance().getCurrentPlayer() - 1);

		p.setInCardPosition(-1);
		
		Pair diceRoll = Game.getInstance().rollDice();
		
		Game.getInstance().movePlayer(diceRoll.value1 + diceRoll.value2, diceRoll.sameValue());
		if (Game.getInstance().inCardPosition(true) != null)
		{
			p.setInCardPosition(p.getPosition());
		}

		doSquareAction();
//		System.out.println(p.getInCardPosition());
		
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
	
	public int buyHouse(BuyableSquare s1)
	{
		int res = Game.getInstance().checkHouseAvailability(s1);
		
		if(res == 0) {
			Property s2 = (Property) s1;
			res = Game.getInstance().buyHouse(s2);
		}
		
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

	public int buyHotel(BuyableSquare s1) {
		int res = Game.getInstance().checkHotelAvailability(s1);
		
		if(res == 0) {
			Property s2 = (Property) s1;
			res = Game.getInstance().buyHotel(s2);
		}
		
		return res;
	}

	public int mortgageProperty(int currentCard) {
		int res = Game.getInstance().checkIfYouCanMortgage();
		
//		if(res == 0)
			res = Game.getInstance().mortgageProperty(currentCard);
		
		return res;
	}

	public int negotiateProperty() {
		return 0;
	}

	public Player getPlayerByName(String name) {
		return Game.getInstance().getPlayerInSpecific(name);
	}
	
	public void reBuyProperty (int currentCard)
	{
		int res = Game.getInstance().reBuyProperty(currentCard);
	}

	public int buyPropertyFromOtherPlayer(Player playerThatYouAreBuyingPropertiesFrom, Player playerBuyingTheProperties, BuyableSquare propertyThatIsBeingBought) {
		
		if(playerBuyingTheProperties.getMoney() < propertyThatIsBeingBought.getCost()) {
			return -1;
		}
		
		Game.getInstance().swapPropertiesAround(playerThatYouAreBuyingPropertiesFrom, playerBuyingTheProperties,propertyThatIsBeingBought);	
		return 0;
	}
}
