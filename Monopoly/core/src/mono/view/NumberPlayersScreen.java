package mono.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import mono.view.swapper.ScreenEnum;
import mono.view.swapper.UIFactory;

public class NumberPlayersScreen extends AbstractScreen{

	Skin skin;
	TextField textField;
	boolean showErrorMsg;
	
	public NumberPlayersScreen ()
	{
		super();
		loadAssets();
		skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
		showErrorMsg = false;
		loadAssets();
	}
	
	private void loadAssets() {
		game.getAssetManager().load ("NumberPlayers.png", Texture.class);
		game.getAssetManager().load ("Numbers/Number1.png", Texture.class);
		game.getAssetManager().load ("Numbers/Number2.png", Texture.class);
		game.getAssetManager().load ("Numbers/Number3.png", Texture.class);
		game.getAssetManager().load ("Numbers/Number4.png", Texture.class);
		
		game.getAssetManager().finishLoading();		
	}

	@Override
	public void buildStage() {
				
		addActor (create1PlayerBtn());
		addActor (create2PlayerBtn());
		addActor (create3PlayerBtn());
		addActor (create4PlayerBtn());
	}

	@Override
	public void render(float delta) {
		// Clear screen
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		drawEntities();
		
		// Calling to Stage methods
		super.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		super.draw();
	}

	private void drawEntities() {
		game.getBatch().begin();
		drawNPlayers();
		game.getBatch().end();		
	}

	private void drawNPlayers() {
		Texture numberPlayers = game.getAssetManager().get ("NumberPlayers.png");
		game.getBatch().draw (numberPlayers, 350, 700, 400, 50);		
	}
	
	
	private ImageButton create1PlayerBtn() {

		Texture texture = game.getAssetManager().get ("Numbers/Number1.png");
		ImageButton n1 = UIFactory.createButton(texture);
		n1.setPosition(300, 500);
		n1.setWidth(100);
		n1.setHeight(100);
		n1.addListener(UIFactory.createListener(ScreenEnum.LEVEL_SELECT, 1));

		
		return n1;
	}
	
	private ImageButton create2PlayerBtn() {

		Texture texture = game.getAssetManager().get ("Numbers/Number2.png");
		ImageButton n2 = UIFactory.createButton(texture);
		n2.setPosition(615, 500);
		n2.setWidth(100);
		n2.setHeight(100);
		n2.addListener(UIFactory.createListener(ScreenEnum.LEVEL_SELECT, 2));
		
		return n2;
	}
	
	private ImageButton create3PlayerBtn() {

		Texture texture = game.getAssetManager().get ("Numbers/Number3.png");
		ImageButton n3 = UIFactory.createButton(texture);
		n3.setPosition(300, 200);
		n3.setWidth(100);
		n3.setHeight(100);
		n3.addListener(UIFactory.createListener(ScreenEnum.LEVEL_SELECT, 3));

		
		return n3;
	}
	
	private ImageButton create4PlayerBtn() {

		Texture texture = game.getAssetManager().get ("Numbers/Number4.png");
		ImageButton n4 = UIFactory.createButton(texture);
		n4.setPosition(615, 200);
		n4.setWidth(100);
		n4.setHeight(100);
		n4.addListener(UIFactory.createListener(ScreenEnum.LEVEL_SELECT, 4));

		
		return n4;
	}
}
