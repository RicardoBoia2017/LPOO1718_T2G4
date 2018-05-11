package mono.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import mono.controller.GameController;
import mono.controller.entities.DiceModel;
import mono.controller.entities.PlayerModel;
import mono.model.GameModel;
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
	
	String playerModel;
	TextButton rollDiceButton;
	Skin skin;
	Player playerToDraw;
	DiceModel dice1ToDraw;
	DiceModel dice2ToDraw;
	DiceView dice1;
	DiceView dice2;
	
	public GameScreen(String player1Model) {
		super();
		playerModel = player1Model;
		GameModel.getInstance().addPlayers(player1Model);
		playerToDraw = GameModel.getInstance().getPlayers().get(0);
		skin = new Skin(Gdx.files.internal("comic/skin/comic-ui.json"));
		loadAssets();
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
//		// Adding actors
//
//		Texture board = game.getAssetManager().get("Board.png");
//		Image boardImage = new Image (board);
//		boardImage.setSize(800, 800);
//		boardImage.setPosition(1, 190);
////		addActor(boardImage);
//		
//		createRollDiceButton();
//		addActor(rollDiceButton);
//		
}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.getBatch().begin();
		drawBoard();
		
		createRollDiceButton();
		addActor(rollDiceButton);
			
		drawPiece (playerToDraw);
		game.getBatch().end();
		
		super.act();
		super.draw();
	}
	
	private void drawBoard ()
	{
		Texture board = game.getAssetManager().get("Board.png",Texture.class);
		game.getBatch().draw(board, 1, 200, 800, 800);
	}
	
	public void drawDice(DiceModel d1, DiceModel d2) {
		
		dice1 = new DiceView(game, d1.getNumber());
		dice2 = new DiceView(game, d2.getNumber());
		
		Sprite dice_1 = dice1.createSprite();
		Sprite dice_2 = dice2.createSprite();
		
		dice_1.setOrigin(d1.getX(), d1.getY());
		dice_1.setOrigin(d2.getX(), d2.getY());
				
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
		
		hat.setPosition(0, 910);
		hat.setSize (40,40);
		
		hat.draw(game.getBatch());
		
	}
	
	public void drawBoot(Player p1) {
		BootView bootView = new BootView(game);
		
		Sprite boot = bootView.createSprite();
		
		boot.setSize(40, 40);
		boot.setPosition(0, 960);
		
		boot.draw(game.getBatch());
		
	}
	
	public void drawThimble(Player p1) {
		ThimbleView thimbleView = new ThimbleView(game);
		
		Sprite thimble = thimbleView.createSprite();
		
		thimble.setSize(40,40);
		thimble.setPosition(50, 910);
				
		thimble.draw(game.getBatch());
		
	}
	
	public void drawPiece(Player p1) {
		
		if(p1.getBoardPiece().equals("Thimble")) {
			drawThimble(p1);
		}
		
		else if(p1.getBoardPiece().equals("Car")) {
			drawCar(p1);
		}
		
		else if(p1.getBoardPiece().equals("Hat")) {
			drawHat(p1);
		}
		
		else if(p1.getBoardPiece().equals("Boot")) {
			drawBoot(p1);
		}
		
	}
	
	public void drawPieceAndDice() {
		GameModel g1 = GameModel.getInstance();
		playerToDraw = g1.getPlayers().get(0);
//		dice1ToDraw = g1.getPlayers().get(0).getDice1Num();
//		dice2ToDraw = g1.getPlayersToDraw().get(0).getDice2Model();
		drawPiece(playerToDraw);
		drawDice(dice1ToDraw, dice2ToDraw);
	}
	
	public void createRollDiceButton() {
        rollDiceButton = new TextButton("Roll Dice", skin);
        rollDiceButton.setPosition(580, 20);
        rollDiceButton.setWidth(400);
        rollDiceButton.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						GameController.getInstance().movePlayer();
						return false;
					}
				});
	}
	
	
	@Override
	public void dispose() {
		super.dispose();
	}

}
