package mono.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import mono.view.swapper.ScreenEnum;
import mono.view.swapper.ScreenManager;

public class Monopoly extends Game {
	
	private static Monopoly instance;

	private SpriteBatch batch;
	private AssetManager assetManager;
	
	/**
	 * Creates the game and initialized batch and asset manager
	 */
	@Override
	public void create () {
		 
		batch = new SpriteBatch();
		assetManager = new AssetManager();

		instance = this;
		
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen( ScreenEnum.MAIN_MENU );
	}
	
    /**
     * Returns a singleton instance
     *
     * @return the singleton instance
     */
	public static Monopoly getInstance() {
		if (instance == null) {
			instance = new Monopoly();
		}
		return instance;
	}
	
    /**
     * Returns the asset manager used to load all textures and sounds.
     *
     * @return the asset manager
     */
	public AssetManager getAssetManager() {
		return assetManager;
	}

    /**
     * Returns the sprite batch used to improve drawing performance.
     *
     * @return the sprite batch
     */
	public SpriteBatch getBatch() {
		return batch;
	}	
}