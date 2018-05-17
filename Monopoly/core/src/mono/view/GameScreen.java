package mono.view;
	
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import mono.controller.GameController;
import mono.controller.entities.DiceModel;
import mono.controller.entities.PlayerModel;
import mono.model.Game;
import mono.model.entities.Pair;
import mono.model.entities.Player;
import mono.view.entities.BoardView;
import mono.view.entities.BootView;
import mono.view.entities.CarView;
import mono.view.entities.DiceView;
import mono.view.entities.HatView;
import mono.view.entities.ThimbleView;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.ScreenManager;
import mono.view.swapper.UIFactory;

public class GameScreen extends AbstractScreen {
	
    private static GameScreen instance;

	TextButton rollDiceButton;
	Skin skin;
	Player playerToDraw;
	Pair diceValues;
	Dialog jailDialog;
	Dialog buyPropertyDialog;
	
	public GameScreen(String player1Model) {
		super();
		Game.getInstance().addPlayers(player1Model);
		playerToDraw = Game.getInstance().getPlayers().get(0);
		skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
		
		//initialize dice
		diceValues = new Pair();
				
		loadAssets();
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
		game.getAssetManager().load ("Dice/Dice1.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice2.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice3.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice4.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice5.png", Texture.class);
		game.getAssetManager().load ("Dice/Dice6.png", Texture.class);
        game.getAssetManager().finishLoading();
	}
	
	@Override
	public void buildStage() {
		
		createRollDiceButton();
		addActor(rollDiceButton);
		
//		createJailDialog();
//		addActor(jailDialog);
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
		game.getBatch().begin();
		drawBoard();
			
		drawPiece (playerToDraw);
		drawDice();
		game.getBatch().end();
		
		super.act();
		super.draw();
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
	
/*	public void drawPieceAndDice() {
		GameModel g1 = GameModel.getInstance();
		playerToDraw = g1.getPlayers().get(0);
//		dice1ToDraw = g1.getPlayers().get(0).getDice1Num();
//		dice2ToDraw = g1.getPlayersToDraw().get(0).getDice2Model();
		drawPiece(playerToDraw);
		drawDice(dice1ToDraw, dice2ToDraw);
	}
*/
	
	public void createRollDiceButton() {
        rollDiceButton = new TextButton("Roll Dice", skin);
        rollDiceButton.setPosition(580, 20);
        rollDiceButton.setWidth(400);
        rollDiceButton.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				
						if(GameController.getInstance().tellViewToDisplayJailDialog()) {
	//						System.out.println(hideJailDialog);
			//				showJailDialog();
	//						System.out.println(hideJailDialog);
						}
												
						diceValues = GameController.getInstance().movePlayer();
							
						return false;
					}
				});
	}
	
	public void createJailDialog() {
		
		jailDialog = new Dialog("Pay the fine or wait?", skin) {
            protected void result(Object object)
            {
            	
            	if(object.equals(1L)) {
            		GameController.getInstance().payJail();
            	}
            	
            	if(object.equals(2L)) {
            	}
            	
            	jailDialog.remove();
            };
		};
		
		jailDialog.setPosition(340f, 600f);
		
		jailDialog.setMovable(false);
		jailDialog.setVisible(true);
		
		jailDialog.setWidth(250f);
		jailDialog.button("PAY", 1L);
		jailDialog.button("WAIT", 2L);
		addActor(jailDialog);
	}
	
	public void createBuyPropertyDialog (String propertyName)
	{
		String dialog = "Buy " + propertyName;
		
		buyPropertyDialog = new Dialog(dialog, skin) {
            protected void result(Object object)
            {
            	
            	if(object.equals(1L)) {
            		GameController.getInstance().buyPropertyResponse(true);
            	}
            	
            	if(object.equals(2L)) {
            		GameController.getInstance().buyPropertyResponse(false);
            	}
            	
            	buyPropertyDialog.remove();
            };
		};
		 
		buyPropertyDialog.setPosition(340f, 600f);
		
		buyPropertyDialog.setMovable(false);
		buyPropertyDialog.setVisible(false);
		
		buyPropertyDialog.setWidth(250f);
		buyPropertyDialog.button("YES", 1L);
		buyPropertyDialog.button("NO", 2L);
		addActor(buyPropertyDialog);
//		while (true)
	//		;
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}

}
