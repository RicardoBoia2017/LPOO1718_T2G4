package mono.controller;

import mono.model.Game;
import mono.model.entities.Bot;
import mono.model.entities.BuyableSquare;
import mono.model.entities.Pair;
import mono.model.entities.Player;
import mono.model.entities.Property;

/**
 * This class is used as a bridge between the view and the model. Handles the inputs received from the view and converts them into commands to the model.
 * 
 * @author ricar
 *
 */

public class GameController {

    private static GameController instance;
    
    /**
     * Returns a singleton instance of the game controller
     *
     * @return the singleton instance
     */
    public static GameController getInstance() {
        if (instance == null)  
            instance = new GameController(); 
        return instance;
    }
    
    /**
     * Constructor
     */
	private GameController() {		
 
	}
	
	/**
	 * Called when Roll Dice or Bot Turn buttons are pressed.
	 * 
	 * @return a pair with the dice values
	 */
	public Pair doTurn()
	{	
		Player p = 	Game.getInstance().getCurrentPlayer();

		p.setInCardPosition(-1); 
		
		Pair diceRoll = Game.getInstance().rollDice(); 
		
		Game.getInstance().movePlayer(diceRoll.getValue1() + diceRoll.getValue2(), diceRoll.sameValue());
		if (Game.getInstance().inCardPosition(true) != null)
		{ 
			p.setInCardPosition(p.getPosition());
		}
 
		Game.getInstance().squareAction();
		
		if (p.isBot())
		{
			Bot bot = (Bot) p;
			bot.botTurn();
		}
		
		return diceRoll;
	}
	
	/**
	 * Called when Buy Property button is pressed
	 * 
	 * @return 0 if property was bought, or a negative value otherwise
	 */
	public int buyProperty() 
	{
		int res = Game.getInstance().checkPropertyAvailibility();
		
		if (res == 0)
			res = Game.getInstance().buyProperty();
			
		return res; 
	}
	
	/**
	 * Called when Buy House button is pressed
	 * 
	 * @param s1 properties where the player wants to build the house
	 * @return 0 if house is bought, or a negative value otherwise
	 */
	public int buyHouse(BuyableSquare s1)
	{
		int res = Game.getInstance().checkHouseAvailability(s1);
		
		if(res == 0) {
			Property s2 = (Property) s1;
			res = Game.getInstance().buyHouse(s2);
		}
		
		return res;
	}
	
	/**
	 * Called when Buy Hotel button is pressed
	 * 
	 * @param s1 properties where the player wants to build the hotel
	 * @return 0 if hotel is bought, or a negative value otherwise
	 */
	public int buyHotel(BuyableSquare s1) {
		int res = Game.getInstance().checkHotelAvailability(s1);
		
		if(res == 0) {
			Property s2 = (Property) s1;
			res = Game.getInstance().buyHotel(s2);
		}
		
		return res;
	}
	
	/**
	 * Adds a player to the game
	 * 
	 * @param piece board piece of the said player
	 */
	public void addPlayer (String piece)
	{
		Game.getInstance().addPlayer(piece);
	}
	
	/**
	 * Called when End Turn or End Bot Turn buttons are pressed
	 */
	public void endTurn()
	{
		Game.getInstance().endTurn();
	}

	/**
	 * Called when player wants to pay fine to get out of jail
	 */
	public void payJail() {
		Game.getInstance().tellJailPlayerWantsToPayFine();
		Game.getInstance().squareAction();
	}

	/**
	 * Tells the view to display the jail dialog, to give player the choice between waiting or pay fine
	 * @return true if player is in jail, false otherwise
	 */
	public boolean tellViewToDisplayJailDialog() {
		return Game.getInstance().getplayerIsInJail();
	}

	/**
	 * Called when Mortgage button is pressed
	 * 
	 * @param currentCard square to be mortgaged
	 */
	public void mortgageProperty(int currentCard) {
		Game.getInstance().mortgageProperty(currentCard);
	}

	/**
	 * Return player with specified name
	 * 
	 * @param name name of player wanted
	 * @return the player if he is found, otherwise returns null
	 */
	public Player getPlayerByName(String name) {
		return Game.getInstance().getPlayerByName(name); 
	}
	
	/**
	 * Called when Re Buy button is pressed. Re buys square that was mortgaged
	 * 
	 * @param currentCard square to be re bought
	 */
	public void reBuyProperty (int currentCard)
	{
		Game.getInstance().reBuyProperty(currentCard);
	}

	/**
	 * Called when buy button from Negotiation screen is pressed
	 * 
	 * @param seller Player selling square
	 * @param buyer Player buying square
	 * @param square square to be negotiated
	 * @return -1 if buyer doesn't have enough, otherwise returns 0
	 */
	public int buyPropertyFromOtherPlayer(Player seller, Player buyer, BuyableSquare square) {
		
		if(buyer.getMoney() < square.getCost()) {
			return -1;
		}
		 
		Game.getInstance().swapPropertiesAround(seller, buyer,square, square.getCost());	 
		return 0;
	}

	/**
	 * Called when buy button from Negotiation screen is pressed and seller is bot
	 * 
	 * @param seller Player selling square
	 * @param buyer Player buying square
	 * @param square square to be negotiated
	 * @return -1 if buyer doesn't have enough, otherwise returns 0
	 */
	public int buyPropertyfromBot (Player buyer, Player seller, BuyableSquare square) {

		int propertyCost = (int) Math.ceil (1.20 * square.getCost());
		
		if (buyer.getMoney() < propertyCost)
			return -1;
		
		Game.getInstance().swapPropertiesAround(seller, buyer, square, propertyCost);
		return 0;
	}
}
