package mono.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import mono.controller.GameController;
import mono.model.Game;
import mono.model.entities.BuyableSquare;
import mono.model.entities.Player;
import mono.view.AbstractScreen;
import mono.view.entities.PropertyView;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.UIFactory;

/**
 * Creates Negotation screen
 * 
 * @author ricar
 *
 */
public class NegotiationScreen extends AbstractScreen {
	
	Skin skin;
	int currentCard;
	Dialog noMoneyDialog;
	Dialog successfulBuyDialog;
	Player playerWhosePropertiesAreDisplayed;
	Dialog successfulNegotiationDialog;
	Dialog failedNegotiationDialog;
	Dialog allowBuyingDialog;
	int buyConditional;
	int firstClick;

	/**
	 * Create Negotiation screen
	 * 
	 * @param p1 Player
	 */
	public NegotiationScreen(Player p1) {
		super();
		skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
		currentCard = 0;
		playerWhosePropertiesAreDisplayed = p1; 
		buyConditional = -1;
		
	}
	
	@Override
	public void buildStage() { 
		addActor(createRightBtn());
		addActor(createLeftBtn());
		addActor(createBackBtn());
		addActor(createBuyBtn());
	}
	
	/**
	 * Creates Left button
	 * 
	 * @return textbutton
	 */
	private TextButton createLeftBtn() {
		
		TextButton leftButton = new TextButton ("Left", skin);
		leftButton.setPosition(30, 600);
		
		leftButton.addListener( new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
			{
				changeCard (-1);
				
				return false;
			}
		});
		
		return leftButton;
	}

	/**
	 * Creates right button
	 * 
	 * @return textbutton
	 */
	private TextButton createRightBtn() {
		
		TextButton leftButton = new TextButton ("Right", skin);
		leftButton.setPosition(900, 600);
		
		leftButton.addListener( new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
			{
				changeCard (1);
				
				return false;
			}
		});
		
		return leftButton;	
	}
	
	/**
	 * Creates no money dialog
	 */
	public void createNoMoneyDialog()
	{
		noMoneyDialog = new Dialog("You don't have enough money", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					noMoneyDialog.remove();
			};
		};
		noMoneyDialog.setPosition(340f, 600f);

		noMoneyDialog.setWidth(350f);
		noMoneyDialog.button("EXIT", 1L);
	}
	
	/**
	 * Changes card in the screen
	 * 
	 * @param value 1 if moves right, -1 if moves -1
	 */
	private void changeCard(int value)
	{

		int size = playerWhosePropertiesAreDisplayed.getPropertiesOwned().size();

		if (value == -1 && currentCard == 0)
			currentCard = size - 1;
		
		else if (value == 1 && currentCard == size - 1)
			currentCard = 0;

		else
			currentCard += value;
			
	}
	
	/**
	 * Create back button
	 * 
	 * @return text button
	 */
	private TextButton createBackBtn() {
		
		Player p1 = Game.getInstance().getCurrentPlayer(); 

		TextButton backBtn = new TextButton ("Back", skin);
        backBtn.setPosition(20, 70); 
        backBtn.setWidth(100);
        backBtn.setHeight (80);
        backBtn.setChecked(false);
        
        backBtn.addListener(UIFactory.createListener(ScreenEnum.GAME, p1.getBoardPiece().getType()));
        
		return backBtn;
	}
	
	@Override
	public void render (float delta)
	{
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.getBatch().begin();
		drawProperty();
		game.getBatch().end();
		
		super.act();
		super.draw();
	}
	
	/**
	 * 
	 * @return square of current card
	 */
	private BuyableSquare getSquareOfCurrentCard(){
		
		if(playerWhosePropertiesAreDisplayed.getPropertiesOwned().isEmpty()) {
			return null;
		}
		
		return playerWhosePropertiesAreDisplayed.getPropertiesOwned().get(currentCard);
	}
	
	/**
	 * Draws property card
	 */
	private void drawProperty() {
		
		if (playerWhosePropertiesAreDisplayed.getPropertiesOwned().size() == 0)
			return;
		
		BuyableSquare s1 =  playerWhosePropertiesAreDisplayed.getPropertiesOwned().get(currentCard);
		
		String name = s1.getName();
		
		PropertyView property = new PropertyView (game, name);
		
		Sprite sprite = property.getSprite();
				
		sprite.setSize(500, 600);
		sprite.setPosition(250, 300);
		
		sprite.draw(game.getBatch());
		
	}
	
	/**
	 * Creates fail dialog
	 */
	private void createFailDialog() {
		failedNegotiationDialog = new Dialog("Negotiation failed", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					failedNegotiationDialog.remove();

			};
		};
		failedNegotiationDialog.setPosition(340f, 600f);

		failedNegotiationDialog.setWidth(220f);
		failedNegotiationDialog.button("EXIT", 1L);
	}
	 
	/**
	 * Creates success dialog
	 */
	private void createSucessDialog() {
		successfulNegotiationDialog = new Dialog("Negotiation done", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					successfulNegotiationDialog.remove();

			};
		};
		successfulNegotiationDialog.setPosition(340f, 600f);

		successfulNegotiationDialog.setWidth(210f);
		successfulNegotiationDialog.button("EXIT", 1L);
	}
	
	/**
	 * Create allow buying dialog
	 */
	private void createAllowBuyingDialog() {
		allowBuyingDialog = new Dialog("Allow buy?", skin) {
			protected void result(Object object) {
				if (object.equals(2L)) {
					buyConditional = 0;
					allowBuyingDialog.remove();
				}
				
				if (object.equals(1L)) {
					buyConditional = -1;
					allowBuyingDialog.remove();
				}
				
			};
		};
		allowBuyingDialog.setPosition(340f, 600f);

		allowBuyingDialog.setWidth(210f);
		allowBuyingDialog.button("NO", 1L);
		allowBuyingDialog.button("YES", 2L);
	}
	
	/**
	 * Creates Buy button
	 * 
	 * @return textbutton
	 */
	private TextButton createBuyBtn() {

		TextButton buyBtn = new TextButton ("Buy", skin);
		buyBtn.setPosition(500, 70); 
		buyBtn.setWidth(100);
		buyBtn.setHeight (80);
		buyBtn.setChecked(false);
		
		buyBtn.addListener( new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
			{

				Player buyingPlayer = Game.getInstance().getCurrentPlayer();
				BuyableSquare propertyBeingBought = getSquareOfCurrentCard();
				
				if(firstClick == 0) {
					if (!playerWhosePropertiesAreDisplayed.isBot())
					{
						createAllowBuyingDialog();
						addActor(allowBuyingDialog);
					}
					
					else
					{
						if (GameController.getInstance().buyPropertyfromBot(buyingPlayer, playerWhosePropertiesAreDisplayed, propertyBeingBought) == -1)
						{
				        	createFailDialog(); 
			        		addActor(failedNegotiationDialog); 
						}
					}
					
					firstClick++;
					return false;
				}
				
				else {
					firstClick = 0;
				}
				

		        
		        switch (buyConditional) //this would be res instead of 0
		        { 
		        
		        case -1: 
		        	createFailDialog(); 
		        	addActor(failedNegotiationDialog); 
		        	break; 
		        
		        case 0:
		        	if(GameController.getInstance().buyPropertyFromOtherPlayer(playerWhosePropertiesAreDisplayed, buyingPlayer, propertyBeingBought) == -1) {
		        		createNoMoneyDialog();
		        		addActor(noMoneyDialog);
		        	};
		        	
		        	if(currentCard == 0 && playerWhosePropertiesAreDisplayed.getPropertiesOwned().size() != 1) 
		        		changeCard(1);
		        	
		        	else 
		        		changeCard(-1);
		        	
		        	createSucessDialog(); 
		        	addActor(successfulNegotiationDialog); 
		        	break; 
		        }
				
				return false;
			}
		});
        
		return buyBtn;
	}

}
