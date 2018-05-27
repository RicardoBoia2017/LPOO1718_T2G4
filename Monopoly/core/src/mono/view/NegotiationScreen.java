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
import mono.view.AbstractScreen;
import mono.view.entities.PropertyView;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.UIFactory;

public class NegotiationScreen extends AbstractScreen {
	
	Skin skin;
	int currentCard;
	Dialog noMoneyDialog;
	Dialog successfulBuyDialog;
	Player playerWhosePropertiesAreDisplayed;
	Dialog successfulNegotiationDialog;
	Dialog failedNegotiationDialog;

	public NegotiationScreen(Player p1) {
		super();
		skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
		currentCard = 0;
		playerWhosePropertiesAreDisplayed = p1;
		
		loadProperties();
	}
	
	private static void loadProperties()
	{
		System.out.println("Entrou");
		game.getAssetManager().load("Properties/Athens.png", Texture.class);
		game.getAssetManager().load("Properties/Amsterdam.png", Texture.class);
		game.getAssetManager().load("Properties/Berlin.png", Texture.class);
		game.getAssetManager().load("Properties/Brussels.png", Texture.class);
		game.getAssetManager().load("Properties/Buenos Aires.png", Texture.class);
		game.getAssetManager().load("Properties/Cairo.png", Texture.class);
		game.getAssetManager().load("Properties/Cape Town.png", Texture.class);
		game.getAssetManager().load("Properties/Casablanca.png", Texture.class);
		game.getAssetManager().load("Properties/Dubai.png", Texture.class);
		game.getAssetManager().load("Properties/Lisbon.png", Texture.class);
		game.getAssetManager().load("Properties/London.png", Texture.class);
		game.getAssetManager().load("Properties/Madrid.png", Texture.class);
		game.getAssetManager().load("Properties/Mexico City.png", Texture.class);
		game.getAssetManager().load("Properties/Moscow.png", Texture.class);
		game.getAssetManager().load("Properties/New York.png", Texture.class);
		game.getAssetManager().load("Properties/Paris.png", Texture.class);
		game.getAssetManager().load("Properties/Rio de Janeiro.png", Texture.class);
		game.getAssetManager().load("Properties/Rome.png", Texture.class);
		game.getAssetManager().load("Properties/Shanghai.png", Texture.class);
		game.getAssetManager().load("Properties/Singapore.png", Texture.class);
		game.getAssetManager().load("Properties/Sydney.png", Texture.class);
		game.getAssetManager().load("Properties/Tokyo.png", Texture.class);
		
		game.getAssetManager().load("Properties/Dunedin Station.png", Texture.class);
		game.getAssetManager().load("Properties/Liege Guillemins.png", Texture.class);
		game.getAssetManager().load("Properties/Milano Centrale.png", Texture.class);
		game.getAssetManager().load("Properties/Sao Bento.png", Texture.class);
		
		game.getAssetManager().load("Properties/Eletricity.png", Texture.class);
		game.getAssetManager().load("Properties/Water.png", Texture.class);

		game.getAssetManager().load("Mortgaged.png", Texture.class);
		
        game.getAssetManager().finishLoading();
	}

	@Override
	public void buildStage() { 
		addActor(createRightBtn());
		addActor(createLeftBtn());
		addActor(createBackBtn());
		addActor(createBuyBtn());
	}
	
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
	
	private TextButton createBackBtn() {
		
		Player p1 = Game.getInstance().getPlayers().get(Game.getInstance().getCurrentPlayer() - 1);

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
	
	private BuyableSquare getSquareOfCurrentCard(){
		Player p1 = Game.getInstance().getPlayers().get(Game.getInstance().getCurrentPlayer() - 1);
		
		if(p1.getPropertiesOwned().isEmpty()) {
			return null;
		}
		
		return p1.getPropertiesOwned().get(currentCard);
	}
	
	private void drawProperty() {
		
		if (playerWhosePropertiesAreDisplayed.getPropertiesOwned().size() == 0)
			return;
		
		BuyableSquare s1 =  playerWhosePropertiesAreDisplayed.getPropertiesOwned().get(this.currentCard);
		
		String name = s1.getName();
		
		PropertyView property = new PropertyView (game, name);
		
		Sprite sprite = property.getSprite();
				
		sprite.setSize(500, 600);
		sprite.setPosition(250, 300);
		
		sprite.draw(game.getBatch());
		
	}
	
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
	
	private TextButton createBuyBtn() {

		TextButton buyBtn = new TextButton ("Buy", skin);
		buyBtn.setPosition(500, 70); 
		buyBtn.setWidth(100);
		buyBtn.setHeight (80);
		buyBtn.setChecked(false);
		
		buyBtn.addListener( new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
			{
				//1. Send a Request to the other player
				
				//2. Await message from the other side
				
				//3. Message goes into res
				
				//int res = GameController.getInstance().negotiateProperty();
				
				Player buyingPlayer = Game.getInstance().getPlayers().get(Game.getInstance().getCurrentPlayer() - 1);
		        
		        switch (0) //this would be res instead of 0
		        { 
		        case -1: 
		        	createFailDialog(); 
		        	addActor(failedNegotiationDialog); 
		        	break; 
		        case 0:
		        	GameController.getInstance().buyPropertyFromOtherPlayer(playerWhosePropertiesAreDisplayed, buyingPlayer, getSquareOfCurrentCard());
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
