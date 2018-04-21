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
    Texture startScreen;
    Skin skin;
	
	public MainMenuScreen() {
		super();
		skin = new Skin(Gdx.files.internal("comic/skin/comic-ui.json"));
        startScreen = new Texture("Monopoly.png");
	}

	@Override
	public void buildStage() {
		// Adding actors
		Image bg = new Image(startScreen);
		bg.setSize(200, 200);
		bg.setPosition(150.f, 150.f, Align.center);
		addActor(bg);
		
		createNewGameButton();
		createExitGameButton();
		
		addActor(newGameButton);
		addActor(exitGameButton);
	}
	
	public void createNewGameButton() {
		newGameButton = new TextButton("New Game", skin);
        newGameButton.setPosition(20, 20);
        newGameButton.addListener(UIFactory.createListener(ScreenEnum.LEVEL_SELECT));
	}
	
	public void createExitGameButton() {
        exitGameButton = new TextButton("Exit Game", skin);
        exitGameButton.setPosition(40, 20);
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
		startScreen.dispose();
		skin.dispose();
	}
}
