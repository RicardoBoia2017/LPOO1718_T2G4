package mono.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import mono.game.Monopoly;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.UIFactory;

/**
 * Main menu screen
 * 
 * @author ricar
 *
 */
public class MainMenuScreen extends AbstractScreen {
	
    TextButton newGameButton;
    TextButton exitGameButton;
    Skin skin;
	
    /**
     * Creates main menu
     */
	public MainMenuScreen() {
		super();
		game = Monopoly.getInstance();
		skin = new Skin(Gdx.files.internal("comic/skin/comic-ui.json"));
		
		loadAssets();
	}

	/**
	 * Loads assets to AssetManager
	 */
	private static void loadAssets()
	{
		game.getAssetManager().load ("Monopoly.png", Texture.class);
		game.getAssetManager().load ("Facebook.png", Texture.class);
		game.getAssetManager().finishLoading();
	}
	
	@Override
	public void buildStage() {
		// Adding actors
		Texture monopolyLogo = game.getAssetManager().get("Monopoly.png");
		Image bg = new Image(monopolyLogo);
		bg.setSize(1000, 1000);
		bg.setPosition(0, 0);
		addActor(bg);
		
		createNewGameButton();
		createExitGameButton();
		
		addActor(createFacebookButton());
		addActor(newGameButton);
		addActor(exitGameButton);
	}
	
	/**
	 * Creates New Game button
	 */
	public void createNewGameButton() {
		newGameButton = new TextButton("New Game", skin);
		newGameButton.setPosition(20, 20);
	    newGameButton.setWidth(400);
        newGameButton.addListener(UIFactory.createListener(ScreenEnum.NUMBER_PLAYERS));
	}
	
	/**
	 * Creates Exit Game button
	 */
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

	public ImageButton createFacebookButton() {
		
		Texture texture = game.getAssetManager().get("Facebook.png");
        ImageButton facebookButton = UIFactory.createButton(texture);
        facebookButton.setPosition(300, 100);
        facebookButton.setWidth(400);
        facebookButton.addListener(
				new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						
						Facebook login = new Facebook ();
						login.login();
						
						return false;
					}
				});
        
        return facebookButton; 
	}
	
	@Override
	public void dispose() {
		super.dispose();
		skin.dispose();
	}
}
