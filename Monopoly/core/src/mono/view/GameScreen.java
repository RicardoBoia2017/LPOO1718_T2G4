package mono.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import mono.controller.GameController;
import mono.controller.entities.PlayerModel;
import mono.model.GameModel;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.ScreenManager;
import mono.view.swapper.UIFactory;

public class GameScreen extends AbstractScreen {
	
	Integer playerModel;
	TextButton rollDiceButton;
	Skin skin;
	PlayerModel playerToDraw;
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
		
		createRollDiceButton();
		addActor(rollDiceButton);
	}
	
	public void createRollDiceButton() {
        rollDiceButton = new TextButton("Roll Dice", skin);
        rollDiceButton.setPosition(580, 20);
        rollDiceButton.setWidth(400);
        rollDiceButton.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						GameController g1 = GameController.getInstance();
						playerToDraw = g1.getPlayersToDraw().get(0);
						pieceCoordX = playerToDraw.getX();
						pieceCoordY = playerToDraw.getY();
						buildStage();
						return false;
					}
				});
	}
	
	
	@Override
	public void dispose() {
		super.dispose();
	}

}
