package mono.view;
	
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import mono.controller.GameController;
import mono.controller.entities.DiceModel;
import mono.controller.entities.PlayerModel;
import mono.model.Game;
import mono.model.entities.Pair;
import mono.model.entities.Player;
import mono.view.entities.BoardView;
import mono.view.entities.BootView;
import mono.view.entities.CChestView;
import mono.view.entities.CarView;
import mono.view.entities.ChanceView;
import mono.view.entities.DiceView;
import mono.view.entities.EntityView;
import mono.view.entities.HatView;
import mono.view.entities.ThimbleView;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.ScreenManager;
import mono.view.swapper.UIFactory;

public class GameScreen extends AbstractScreen {
	
    private static GameScreen instance;

	Skin skin;
	Player playerToDraw;
	Pair diceValues;
	Dialog jailDialog;
	Dialog successfulBuyDialog;
	Dialog notBuyableDialog;
	Dialog alreadyOwnedDialog;
	Dialog noMoneyDialog;
	Boolean visibleJail;

	
	static float diceRollTime; 
	Animation diceAnimation;
	
	public GameScreen(String player1Model) {
		super();
		Game.getInstance().addPlayers(player1Model);
		playerToDraw = Game.getInstance().getPlayers().get(0);
		skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
		
		//initialize dice
		diceValues = new Pair();
		
		visibleJail = false;
				
		loadAssets();
		
		drawAnimation();
		
		this.diceRollTime = 100;
	}	

    /**
     * Returns a singleton instance of the game model
     *
     * @return the singleton instance
     */
    public static GameScreen getInstance() {
        if (instance == null) 
            instance = new GameScreen("Hat"); //Change this 
        return instance;
    }
    
	private static void  loadAssets ()
	{
		game.getAssetManager().load ("Board.png", Texture.class);
		loadChanceCards();
		loadDices();
        game.getAssetManager().finishLoading();
	}
	
	private static void loadChanceCards()
	{
		game.getAssetManager().load("ChanceCards/AExam.png",Texture.class);
		game.getAssetManager().load("ChanceCards/Birthday.png",Texture.class);
		game.getAssetManager().load("ChanceCards/CaughtStealing.png",Texture.class);
		game.getAssetManager().load("ChanceCards/FoundMoney.png",Texture.class);
		game.getAssetManager().load("ChanceCards/GoToMoscow.png",Texture.class);
		game.getAssetManager().load("ChanceCards/LostBet.png",Texture.class);
		game.getAssetManager().load("ChanceCards/RealEstateTaxes.png",Texture.class);
		game.getAssetManager().load("ChanceCards/SummerComing.png",Texture.class);
		game.getAssetManager().load("ChanceCards/WrongWay.png",Texture.class);
	}
	
	private static void loadDices()
	{
		game.getAssetManager().load ("Dice/Dice1.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice2.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice3.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice4.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice5.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice6.png", Texture.class);
	}
	
	@Override
	public void buildStage() {
		
		addActor(createRollDiceBtn());
		addActor(createBuyPropertyBtn());
		addActor(createEndTurnBtn());
		
		createSuccessfulBuyDialog();
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
		
		game.getBatch().begin();
		drawBoard();
		drawDice();
		rollDiceAnimation(delta);
		drawPiece (playerToDraw);
		drawCard();
		
		game.getBatch().end();

		//this.setVisibilities();
		
		super.act();
		super.draw();
	}
	
	private void rollDiceAnimation(float delta) {
			
		this.diceRollTime += delta;
		Random rand = new Random();
		Texture current;
		
		if (this.diceRollTime < 1)
		{
			current = (Texture) diceAnimation.getKeyFrames()[rand.nextInt(6)];
			game.getBatch().draw(current, 15.5f, 16f, 151.5f,151.5f);
			
			current = (Texture) diceAnimation.getKeyFrames()[rand.nextInt(6)];
			game.getBatch().draw(current, 225.5f, 16f, 151.5f,151.5f);

		}
	}

	private void drawAnimation() {
			
		Array <Texture> array = new Array <Texture> (6);
		array.add((Texture) game.getAssetManager().get("Dice/Dice1.png"));
		array.add((Texture) game.getAssetManager().get("Dice/Dice2.png"));
		array.add((Texture) game.getAssetManager().get("Dice/Dice3.png"));
		array.add((Texture) game.getAssetManager().get("Dice/Dice4.png"));
		array.add((Texture) game.getAssetManager().get("Dice/Dice5.png"));
		array.add((Texture) game.getAssetManager().get("Dice/Dice6.png"));
		
		diceAnimation = new Animation<>(0.1f, array);
		
	}

	private void setVisibilities() {
		this.setJailVisibility();
	}

	private void setJailVisibility()
	{
		if(!visibleJail) {
			createJailDialog();
			addActor(jailDialog);
			jailDialog.setVisible(visibleJail);
		}
		
		else {
			jailDialog.setVisible(visibleJail);
		}
	}
	
	private void drawBoard ()
	{
		Texture board = game.getAssetManager().get("Board.png",Texture.class);
		game.getBatch().draw(board, 1, 197, 803, 803);
	}
	
	public void drawDice() {
		Game g1 = Game.getInstance();
		DiceView dice1 = new DiceView(game, diceValues.getValue1());
		DiceView dice2 = new DiceView(game, diceValues.getValue2());
		
		Sprite dice_1 = dice1.createSprite();
		Sprite dice_2 = dice2.createSprite();
		
		dice_1.scale(-0.5f);
		dice_2.scale(-0.5f);
		dice_1.setPosition(-60, -60);
		dice_2.setPosition(150, -60);
				
		dice_1.draw(game.getBatch());
		dice_2.draw(game.getBatch());	
	}
	
	public void drawCar(Player p1) {
		CarView carView = new CarView(game);
		
		Sprite car = carView.createSprite();
		
		car.setPosition(p1.getX(), p1.getY());
		car.setSize (40,40);
				
		car.draw(game.getBatch());
	}
	
	public void drawHat(Player p1) {
		HatView hatView = new HatView(game);
		
		Sprite hat = hatView.createSprite();
		
		hat.setPosition(p1.getX(), p1.getY());
		hat.setSize (40,40);
		
		hat.draw(game.getBatch());
	}
	
	public void drawBoot(Player p1) {
		BootView bootView = new BootView(game);
		
		Sprite boot = bootView.createSprite();
		
		boot.setSize(40, 40);
		boot.setPosition(p1.getX(), p1.getY());
		
		boot.draw(game.getBatch());
	}
	
	public void drawThimble(Player p1) {
		ThimbleView thimbleView = new ThimbleView(game);
		
		Sprite thimble = thimbleView.createSprite();
		
		thimble.setSize(40,40);
		thimble.setPosition(p1.getX(), p1.getY());
				
		thimble.draw(game.getBatch());
	}
	
	private void drawCard()
	{
		String res = Game.getInstance().inCardPosition();
		String cardType;
		int cardId;
		
		if (res != null)
		{
			EntityView cardView = null;
			cardType = res.substring(0,2);
			cardId = Integer.valueOf(res.substring(3));
			
			if (cardType.equals("CH"))
				cardView = new ChanceView(game,cardId);
			
			else if (cardType.equals("CC"))
				cardView = new ChanceView(game,cardId);

			Sprite card = cardView.createSprite();
			
			card.setSize(550, 400);
			card.setPosition(125, 400);
			
			card.draw(game.getBatch());
		}
	}
	
	public void drawPiece(Player p1) {
		
		if(p1.getBoardPiece().getType() == "Thimble") {
			drawThimble(p1);
		}
		
		else if(p1.getBoardPiece().getType() == "Car") {
			drawCar(p1);
		}
		
		else if(p1.getBoardPiece().getType() == "Hat") {
			drawHat(p1);
		}
		
		else if(p1.getBoardPiece().getType() == "Boot") {
			drawBoot(p1);
		}
		
	}
	
	
	private TextButton createRollDiceBtn() { 
       TextButton rollDiceButton = new TextButton("Roll Dice", skin);
        rollDiceButton.setPosition(780, 20);
        rollDiceButton.setWidth(200);
        rollDiceButton.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				
						diceRollTime = 0;
						
						if(GameController.getInstance().tellViewToDisplayJailDialog()) {
							
							if(jailDialog != null) {
								if(jailDialog.isVisible() == false) {
									jailDialog.setVisible(true);
								}
							} 
							
							else {
								createJailDialog();
								addActor(jailDialog);
							}
							
						} else {
							
							if(jailDialog != null) {
								jailDialog.remove();
							}
							
						}
								
						diceValues = GameController.getInstance().doTurn();
							
						return false;
					}
				});
        
        return rollDiceButton;
	}
	
	
	private TextButton createBuyPropertyBtn() {

        TextButton buyPropertyButton = new TextButton("Buy", skin);
        buyPropertyButton.setPosition(500, 20);
        buyPropertyButton.setWidth(200);
        buyPropertyButton.setChecked(false);
        
        buyPropertyButton.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						
						int res = GameController.getInstance().buyProperty();
						
						switch (res)
						{
						case -3:
							createNoMoneyDialog();
							addActor(noMoneyDialog);
							break;
						case -2:
							createAlreadyOwnedDialog();
							addActor(alreadyOwnedDialog);
							break;
						case -1:
							createNotBuyableDialog();
							addActor(notBuyableDialog);
							break;
						case 0:
							createSuccessfulBuyDialog();
							addActor(successfulBuyDialog);
							break;
						
						}
						
						return false;
					}
				});
        
        return buyPropertyButton;
	}
	
	private TextButton createEndTurnBtn()
	{
	       TextButton endTurnBtn = new TextButton("End Turn", skin);
	        endTurnBtn.setPosition(780, 110);
	        endTurnBtn.setWidth(200);
	        
	        endTurnBtn.addListener(
					new InputListener() {
						@Override
						public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
											
							GameController.getInstance().endTurn();
							
							return false;
						}
					});
	        
	        return endTurnBtn;
	}
	
	
	public void createJailDialog() {
		
		jailDialog = new Dialog("Pay the fine or wait?", skin) {
            protected void result(Object object)
            {
            	
            	if(object.equals(1L)) {
            		GameController.getInstance().payJail();
            	}
            	
            	if(object.equals(2L)) {
            		jailDialog.setVisible(false);
            	}
            	
            };
		};
		
		jailDialog.setPosition(340f, 600f);
		
		jailDialog.setMovable(false);
		
		jailDialog.setWidth(250f);
		jailDialog.button("PAY", 1L);
		jailDialog.button("WAIT", 2L);
	}
	
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
		
	public void createNotBuyableDialog()
	{
		notBuyableDialog = new Dialog("This square is not buyable", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					notBuyableDialog.remove();

			};
		};
		notBuyableDialog.setPosition(340f, 600f);

		notBuyableDialog.setWidth(300f);
		notBuyableDialog.button("EXIT", 1L);
	}

	public void createAlreadyOwnedDialog()
	{
		alreadyOwnedDialog = new Dialog("Property is already owned", skin) {
			protected void result(Object object) {
				if (object.equals(1L))
					alreadyOwnedDialog.remove();

			};
		};
		alreadyOwnedDialog.setPosition(340f, 600f);

		alreadyOwnedDialog.setWidth(320f);
		alreadyOwnedDialog.button("EXIT", 1L);
	}
		
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
	
	@Override
	public void dispose() {
		super.dispose();
	}
	
}
