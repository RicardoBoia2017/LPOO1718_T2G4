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
import mono.view.entities.DiceView;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.ScreenManager;
import mono.view.swapper.UIFactory;

public class GameScreen extends AbstractScreen {
	
	Integer playerModel;
	TextButton rollDiceButton;
	Skin skin;
	PlayerModel playerToDraw;
	DiceModel dice1ToDraw;
	DiceModel dice2ToDraw;
	DiceView dice1;
	DiceView dice2;
	
	Float pieceCoordX = 0.f;
	Float pieceCoordY = 930.f;
	
	public GameScreen(Integer player1Model) {
		super();
		playerModel = player1Model;
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
		// Adding actors

		Texture board = game.getAssetManager().get("Board.png");
		Image boardImage = new Image (board);
		boardImage.setSize(800, 800);
		boardImage.setPosition(1, 190);
		addActor(boardImage);
		
		createRollDiceButton();
		addActor(rollDiceButton);

		Texture player1Model =  game.getAssetManager().get("Boot.png");;
		
		switch (playerModel)
		{
		case 1:
			 player1Model = game.getAssetManager().get("Boot.png");
			 break;
		case 2:	 
			 player1Model = game.getAssetManager().get("Car.png");
			 break;
		case 3:
			 player1Model = game.getAssetManager().get("Hat.png");
			 break;
		case 4:
			 player1Model = game.getAssetManager().get("Thimble.png");
			 break;
		}
		
		Image piece = new Image (player1Model);
		piece.setSize(50, 50);
		piece.setPosition(pieceCoordX, pieceCoordY);
		addActor(piece);
	}
	
	public void drawDice(DiceModel d1, DiceModel d2) {
		dice1ToDraw = d1;
		dice2ToDraw = d2;
		
		dice1 = new DiceView(game, dice1ToDraw.getNumber());
		dice2 = new DiceView(game, dice2ToDraw.getNumber());
		
		Sprite dice_1 = dice1.createSprite();
		Sprite dice_2 = dice2.createSprite();
		
		dice_1.setOrigin(2.f, 2.f);
		dice_1.setOrigin(10.f, 2.f);
		
		game.getBatch().begin();
		
		dice_1.draw(game.getBatch());
		dice_2.draw(game.getBatch());
		
		game.getBatch().end();
	}
	
	public void redrawPieceAndDice() {
		GameController g1 = GameController.getInstance();
		/*
		playerToDraw = g1.getPlayersToDraw().get(0);
		pieceCoordX = playerToDraw.getX();
		pieceCoordY = playerToDraw.getY();
		buildStage();*/
		drawDice(g1.getPlayersToDraw().get(0).getDice1Model(), g1.getPlayersToDraw().get(0).getDice2Model());
	}
	
	public void createRollDiceButton() {
        rollDiceButton = new TextButton("Roll Dice", skin);
        rollDiceButton.setPosition(580, 20);
        rollDiceButton.setWidth(400);
        rollDiceButton.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						redrawPieceAndDice();
						return false;
					}
				});
	}
	
	
	@Override
	public void dispose() {
		super.dispose();
	}

}
