package mono.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import mono.controller.GameController;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.UIFactory;

/**
 * Piece Selection screen
 * @author ricar
 *
 */
public class PieceSelectScreen extends AbstractScreen {
		
	Skin skin;
	int nPlayers;
	int currentPlayer;
	ImageButton btnBoot;
	ImageButton btnCar;
	ImageButton btnHat;
	ImageButton btnThimble;
	InputListener bootListener;
	InputListener carListener;
	InputListener hatListener;
	InputListener thimbleListener;

	/**
	 * Creates piece selection screen
	 * 
	 * @param nPlayers number of players in game
	 */
	public PieceSelectScreen (int nPlayers) {
		super();
		skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
		this.nPlayers = nPlayers;
		this.currentPlayer = 1;
		loadAssets();
	}
	
	/**
	 * Loads assets to AssetManager
	 */
	public void loadAssets() {
		game.getAssetManager().load ("Boot.png", Texture.class);
		game.getAssetManager().load ("Car.png", Texture.class);
		game.getAssetManager().load ("Thimble.png", Texture.class);
		game.getAssetManager().load ("Hat.png", Texture.class); 
		
		game.getAssetManager().load ("Players/Player 1.png", Texture.class); 
		game.getAssetManager().load ("Players/Player 2.png", Texture.class); 
		game.getAssetManager().load ("Players/Player 3.png", Texture.class); 
		game.getAssetManager().load ("Players/Player 4.png", Texture.class); 

        game.getAssetManager().finishLoading();
	}

	@Override
	public void buildStage() {
		Texture boot = this.game.getAssetManager().get("Boot.png");
		btnBoot = UIFactory.createButton(boot);
		btnBoot.setSize(400, 400); 
		btnBoot.setPosition(250.f, 650.f, Align.center);
		addActor(btnBoot);
		
		Texture car = this.game.getAssetManager().get("Car.png");
		btnCar = UIFactory.createButton(car);
		btnCar.setSize(400, 400);
		btnCar.setPosition(750.f, 650.f, Align.center);
		addActor(btnCar);
		
		Texture hat = this.game.getAssetManager().get("Hat.png");
		btnHat = UIFactory.createButton(hat);
		btnHat.setSize(400, 400);
		btnHat.setPosition(250.f, 250.f, Align.center);
		addActor(btnHat);
		
		Texture thimble = this.game.getAssetManager().get("Thimble.png");
		btnThimble = UIFactory.createButton(thimble);
		btnThimble.setSize(400, 400);
		btnThimble.setPosition(750.f, 250.f, Align.center);
		addActor(btnThimble);
		
		manageListeners();

}

	/**
	 * Manages listeners from buttons
	 */
	private void manageListeners() {

	if (this.currentPlayer == this.nPlayers)
	{
		if (nPlayers > 1)
		{
			btnBoot.removeListener (bootListener);
			btnCar.removeListener(carListener);
			btnHat.removeListener(hatListener);
			btnThimble.removeListener(thimbleListener);
		}
		
		
		btnBoot.addListener(UIFactory.createListener(ScreenEnum.GAME, "Boot"));
		btnCar.addListener(UIFactory.createListener(ScreenEnum.GAME, "Car"));
		btnHat.addListener(UIFactory.createListener(ScreenEnum.GAME, "Hat"));
		btnThimble.addListener(UIFactory.createListener(ScreenEnum.GAME, "Thimble")); 
	}
		
	else if (currentPlayer == 1)	
	{
		btnBoot.addListener( bootListener = new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				GameController.getInstance().addPlayer("Boot");
				currentPlayer++;
				manageListeners();
				btnBoot.remove();
				
				return false;
			}
		});
		
		btnCar.addListener( carListener = new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				GameController.getInstance().addPlayer("Car");
				currentPlayer++;
				manageListeners();
				btnCar.remove();
				
				return false;
			}
		});
		
		btnHat.addListener( hatListener = new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				GameController.getInstance().addPlayer("Hat");
				currentPlayer++;
				manageListeners();
				btnHat.remove();
				
				return false;
			}
		});
		
		btnThimble.addListener( thimbleListener = new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				GameController.getInstance().addPlayer("Thimble");
				currentPlayer++;
				manageListeners();
				btnThimble.remove();
				
				return false;
			}
		});
	}
	
	}

	@Override
	public void render (float delta)
	{
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.getBatch().begin();
		drawPlayer();
		game.getBatch().end();
		
		super.act();
		super.draw();
	}
	
	/**
	 * Draw Player # label
	 */
	private void drawPlayer() {
		String fileName = "Players/Player " + currentPlayer + ".png"; 
		Texture texture = game.getAssetManager().get(fileName);
		game.getBatch().draw(texture, 450, 900, 200, 50);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
