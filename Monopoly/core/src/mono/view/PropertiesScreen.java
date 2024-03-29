package mono.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
import mono.view.entities.PropertyView;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.UIFactory;

/**
 * Creates Property screen
 * @author ricar
 *
 */
public class PropertiesScreen extends AbstractScreen {

	Skin skin;
	int currentCard;
	Dialog noMoneyDialog;
	Dialog successfulBuyDialog;
	Dialog cannotPlaceHouseDialog;
	Dialog doNotOwnAllColorsDialog;
	Dialog mortgagedDialog;
	Dialog noMoreHouses;
	Dialog notEnoughHousesDialog;
	Dialog tooManyHotelsDialog;
	TextButton reBuyBtn;

	/**
	 * Creates property screen
	 */
	public PropertiesScreen()
	{
		super();
		skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
		currentCard = 0;	
	}

	@Override
	public void buildStage() { 
		addActor(createBackBtn());
		addActor(createBuildHouseBtn());
		addActor(createBuildHotelBtn());
		addActor(createMortgageBtn());
		addActor(createRightBtn());
		addActor(createLeftBtn());
	}
	
	/**
	 * Creates Left button
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
	 * Creates Right button
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
	 * Changed property in screen
	 * 
	 * @param value if 1 moves to right, -1 to left
	 */
	private void changeCard(int value)
	{
		Player p1 = Game.getInstance().getCurrentPlayer();

		int size = p1.getPropertiesOwned().size();

		if (value == -1 && currentCard == 0)
			currentCard = size - 1;
		
		else if (value == 1 && currentCard == size - 1)
			currentCard = 0;

		else
			currentCard += value;
			
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
	 * Draws property
	 */
	private void drawProperty() {
		
		Player p1 = Game.getInstance().getCurrentPlayer();
		
		if (p1.getPropertiesOwned().size() == 0)
			return;
		
		BuyableSquare s1 =  p1.getPropertiesOwned().get(this.currentCard);
		
		if (s1.getMortgageStatus())
		{
			drawInMortgate();
			if (reBuyBtn == null)
				addActor(createReBuyBtn());
		}
		
		else if (reBuyBtn != null)
		{
			reBuyBtn.remove();
			reBuyBtn = null;
		}

		
		String name = s1.getName();
		
		PropertyView property = new PropertyView (game, name);
		
		Sprite sprite = property.getSprite();
				
		sprite.setSize(500, 600);
		sprite.setPosition(300, 300);
		
		sprite.draw(game.getBatch());
		
	}

	/**
	 * Creates Re Buy button
	 * 
	 * @return textbutton
	 */
	private TextButton createReBuyBtn() {
	
		reBuyBtn = new TextButton ("Rebuy property", skin);
		reBuyBtn.setPosition(410, 230);
		
		reBuyBtn.addListener( new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
			{
				
				GameController.getInstance().reBuyProperty(currentCard);
				return false;
			}
		});
		
		return reBuyBtn;
		
	}

	/**
	 * Draws in mortgage button
	 */
	private void drawInMortgate() {
		
		Texture texture = game.getAssetManager().get("Mortgaged.png");
		game.getBatch().draw (texture, 435, 925, 200, 50);
	}

	/**
	 * Creates Back button
	 * 
	 * @return textbutton
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
	
	/**
	 * Creates successful buy dialog
	 */
	public void createSuccessfulBuyDialog()
	{
		successfulBuyDialog = new Dialog("Purchase successful", skin){
        protected void result(Object object)
        {
        	if(object.equals(1L)) 
        	{
        		successfulBuyDialog.remove();
        	}
        };
		};
		successfulBuyDialog.setPosition(340f, 600f);
		
		successfulBuyDialog.setWidth(250f);
		successfulBuyDialog.button("EXIT", 1L);
		
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
	 * Creates mortgage dialog
	 */
	private void createMortgagedDialog() {
		mortgagedDialog = new Dialog("Property has been mortgaged", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					mortgagedDialog.remove();

			};
		};
		mortgagedDialog.setPosition(340f, 600f);

		mortgagedDialog.setWidth(380f);
		mortgagedDialog.button("EXIT", 1L);
	}

	/**
	 * Creates dont own all properties dialog
	 */
	private void createDontOwnAllPropertiesDialog() {
		doNotOwnAllColorsDialog = new Dialog("You do not own all properties of this color", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					doNotOwnAllColorsDialog.remove();

			};
		};
		doNotOwnAllColorsDialog.setPosition(340f, 600f);

		doNotOwnAllColorsDialog.setWidth(500f);
		doNotOwnAllColorsDialog.button("EXIT", 1L);
	}
	
	/**
	 * Creates not placeable dialog 
	 */
	private void createNotPlaceableDialog() {
		cannotPlaceHouseDialog = new Dialog("You cannot place a building here", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					cannotPlaceHouseDialog.remove();

			};
		};
		cannotPlaceHouseDialog.setPosition(340f, 600f);

		cannotPlaceHouseDialog.setWidth(380f);
		cannotPlaceHouseDialog.button("EXIT", 1L);
	}
	
	/**
	 * Create no more houses dialog
	 */
	private void createNoMoreHousesDialog() {
		noMoreHouses = new Dialog("You cannot have more than 4 houses", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					noMoreHouses.remove();

			};
		};
		noMoreHouses.setPosition(340f, 600f);

		noMoreHouses.setWidth(490f);
		noMoreHouses.button("EXIT", 1L);
	}
	
	/**
	 * Get square of current card
	 * 
	 * @return square
	 */
	private BuyableSquare getSquareOfCurrentCard(){
		Player p1 = Game.getInstance().getCurrentPlayer();
		
		if(p1.getPropertiesOwned().isEmpty()) {
			return null;
		}
		
		return p1.getPropertiesOwned().get(currentCard);
	}
	
	/**
	 * Create build house button
	 * 
	 * @return textbutton
	 */
	private TextButton createBuildHouseBtn() {
		TextButton buildHouseBtn = new TextButton ("Build House", skin);
		buildHouseBtn.setPosition(256, 70); 
		buildHouseBtn.setWidth(150);
		buildHouseBtn.setHeight (80);
		buildHouseBtn.setChecked(false);
		
		buildHouseBtn.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						
						BuyableSquare squareOfCurrentlySelectedProperty = getSquareOfCurrentCard();
						
						if(squareOfCurrentlySelectedProperty == null) {
							return false;
						}
						
				        int res = GameController.getInstance().buyHouse(squareOfCurrentlySelectedProperty); 
				        
				        switch (res) 
				        { 
				        case -5:
				      	  createNoMoreHousesDialog();
				      	  addActor(noMoreHouses);
				      	  break;
				        case -4: 
				      	  createDontOwnAllPropertiesDialog(); 
				      	  addActor(doNotOwnAllColorsDialog); 
				      	  break; 
				        case -3:
				      	  createNoMoneyDialog(); 
				      	  addActor(noMoneyDialog); 
				      	  break; 
				        case -2: 
				      	  createMortgagedDialog(); 
				      	  addActor(mortgagedDialog); 
				      	  break; 
				        case -1: 
				      	  createNotPlaceableDialog(); 
				      	  addActor(cannotPlaceHouseDialog); 
				      	  break; 
				        case 0: 
				      	  createSuccessfulBuyDialog(); 
				      	  addActor(successfulBuyDialog); 
				      	  break; 
				        }
						
						return false;
					}
				});
		
		return buildHouseBtn;
	}
	
	/**
	 * Creates too many hotels dialog
	 */
	private void createTooManyHotelsDialog() {
		tooManyHotelsDialog = new Dialog("You cannot have more than 1 hotel", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					tooManyHotelsDialog.remove();

			};
		};
		tooManyHotelsDialog.setPosition(340f, 600f);

		tooManyHotelsDialog.setWidth(490f);
		tooManyHotelsDialog.button("EXIT", 1L);
	}
	
	/**
	 * Creates not enough houses dialog
	 */
	private void createNotEnoughHousesDialog() {
		notEnoughHousesDialog = new Dialog("You do not have 4 houses built", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					notEnoughHousesDialog.remove();

			};
		};
		notEnoughHousesDialog.setPosition(340f, 600f);

		notEnoughHousesDialog.setWidth(400f);
		notEnoughHousesDialog.button("EXIT", 1L);
	}

	/**
	 * Creates Build Hotel button
	 * @return textbutton
	 */
	private TextButton createBuildHotelBtn() {
		TextButton buildHotelBtn = new TextButton ("Build Hotel", skin);
		buildHotelBtn.setPosition(542, 70); 
		buildHotelBtn.setWidth(150);
		buildHotelBtn.setHeight (80);
		buildHotelBtn.setChecked(false);
		
		buildHotelBtn.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						
						BuyableSquare squareOfCurrentlySelectedProperty = getSquareOfCurrentCard();
						
						if(squareOfCurrentlySelectedProperty == null) {
							return false;
						}
						
				        int res = GameController.getInstance().buyHotel(squareOfCurrentlySelectedProperty);
				        
				        switch (res) 
				        { 
				        case -4: 
				      	  createTooManyHotelsDialog(); 
				      	  addActor(tooManyHotelsDialog); 
				      	  break; 
				        case -3:
				      	  createNoMoneyDialog(); 
				      	  addActor(noMoneyDialog); 
				      	  break; 
				        case -2: 
				      	  createNotEnoughHousesDialog(); 
				      	  addActor(notEnoughHousesDialog); 
				      	  break; 
				        case -1: 
				      	  createNotPlaceableDialog(); 
				      	  addActor(cannotPlaceHouseDialog); 
				      	  break; 
				        case 0: 
				      	  createSuccessfulBuyDialog(); 
				      	  addActor(successfulBuyDialog); 
				      	  break; 
				        }
						
						return false;
					}
				});
		
		return buildHotelBtn;
	}
	
	/**
	 * Create Mortgage button
	 * @return textbutton
	 */
	private TextButton createMortgageBtn() {
		TextButton mortgageButton = new TextButton ("Mortgage", skin);
		mortgageButton.setPosition(830, 70); 
		mortgageButton.setWidth(150);
		mortgageButton.setHeight (80);
		mortgageButton.setChecked(false);
		
		mortgageButton.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						
				        GameController.getInstance().mortgageProperty(currentCard);
						
						return false;
					}
				});
		
		return mortgageButton;
	}
}
