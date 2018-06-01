package mono.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import mono.game.Monopoly;

/**
 * Super class of screen. 
 * @author ricar
 *
 */
public abstract class AbstractScreen extends Stage implements Screen {
	
	static Monopoly game;
	
	/**
	 * Creates abstract screen
	 */
	protected AbstractScreen() {
		super( new StretchViewport(1000.0f, 1000.0f, new OrthographicCamera()) );
	}
	
	/**
	 * Adds actors to stage
	 */
	public abstract void buildStage();
 
	@Override
	public void render(float delta) {
		// Clear screen
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Calling to Stage methods
		super.act(delta);
		super.draw();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void resize(int width, int height) {
		getViewport().update(width, height);
	}

	@Override public void hide() {}
	@Override public void pause() {}
	@Override public void resume() {}
}
