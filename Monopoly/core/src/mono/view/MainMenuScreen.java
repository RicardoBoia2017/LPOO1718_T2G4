package mono.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.UIFactory;

public class MainMenuScreen extends AbstractScreen {
	
    TextButton newGameButton;
    TextButton exitGameButton;
    Skin skin;
	
	public MainMenuScreen() {
		super();
		this.game = this.game.getInstance();
		skin = new Skin(Gdx.files.internal("comic/skin/comic-ui.json"));
		loadAssets();
	}

	private static void loadAssets()
	{
		game.getAssetManager().load ("Monopoly.png", Texture.class);
		game.getAssetManager().load ("house.png", Texture.class);
		
		game.getAssetManager().finishLoading();
	}
	
	@Override
	public void buildStage() {
		// Adding actors
		Texture monopolyLogo = this.game.getAssetManager().get("Monopoly.png");
		Image bg = new Image(monopolyLogo);
		bg.setSize(1000, 1000);
		bg.setPosition(0, 0);
		addActor(bg);
		
		createNewGameButton();
		createExitGameButton();
		
		addActor(newGameButton);
		addActor(exitGameButton);
	}
	
	public void createNewGameButton() {
		newGameButton = new TextButton("New Game", skin);
		newGameButton.setPosition(20, 20);
	    newGameButton.setWidth(400);
        newGameButton.addListener(UIFactory.createListener(ScreenEnum.LEVEL_SELECT));
	}
	
	public void createExitGameButton() {
        exitGameButton = new TextButton("Exit Game", skin);
        exitGameButton.setPosition(580, 20);
        exitGameButton.setWidth(400);
        exitGameButton.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						Gdx.app.exit();
						return false;
					}
				});
	}

	@Override
	public void dispose() {
		super.dispose();
		skin.dispose();
	}
}
