package mono.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Align;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.UIFactory;
import singleton.Monopoly;

public class GameScreen extends AbstractScreen {
	
	Monopoly monopoly;
	Integer playerModel;
	
	public GameScreen(Integer player1Model, Monopoly monopoly) {
		super();
		this.monopoly = monopoly;
		playerModel = player1Model;
		loadAssets();
	}	

	private void loadAssets ()
	{
		this.monopoly.getAssetManager().load ("Board.png", Texture.class);
		
		this.monopoly.getAssetManager().load ("Boot.png", Texture.class);
		this.monopoly.getAssetManager().load ("Car.png", Texture.class);
		this.monopoly.getAssetManager().load ("Thimble.png", Texture.class);
		this.monopoly.getAssetManager().load ("Hat.png", Texture.class);
		
        this.monopoly.getAssetManager().finishLoading();

	}
	
	@Override
	public void buildStage() {
		// Adding actors

		Texture board = monopoly.getAssetManager().get("Board.png");
		Image boardImage = new Image (board);
		boardImage.setSize(1000, 1000);
		boardImage.setPosition(getWidth() / 2, getHeight() / 2, Align.center);
		addActor(boardImage);

		Texture player1Model =  monopoly.getAssetManager().get("Boot.png");;
		
		switch (playerModel)
		{
		case 1:
			 player1Model = monopoly.getAssetManager().get("Boot.png");
			 break;
		case 2:	 
			 player1Model = monopoly.getAssetManager().get("Car.png");
			 break;
		case 3:
			 player1Model = monopoly.getAssetManager().get("Hat.png");
			 break;
		case 4:
			 player1Model = monopoly.getAssetManager().get("Thimble.png");
			 break;
		}
		
		Image piece = new Image (player1Model);
		piece.setSize(60, 60);
		piece.setPosition(0, 930);
		addActor(piece);


	}
	

	@Override
	public void dispose() {
		super.dispose();
	}

}
