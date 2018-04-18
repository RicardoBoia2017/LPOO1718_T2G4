package mono.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Monopoly extends ApplicationAdapter {
	SpriteBatch batch;
    Stage stage;
	Texture img;
    TextButton newGameButton;
    TextButton exitGameButton;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
	
	@Override
	public void create () {
		stage = new Stage();
		batch = new SpriteBatch();
		img = new Texture("Monopoly.png");
        font = new BitmapFont();
        skin = new Skin(Gdx.files.internal("comic/skin/comic-ui.json"));
        
        newGameButton = new TextButton("New Game", skin);
        exitGameButton = new TextButton("Exit Game", skin);
     
        exitGameButton.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);
        
        newGameButton.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
               
            }
        });
        
        exitGameButton.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
               System.exit(0);
            }
        });
        
        stage.addActor(newGameButton);
        
        stage.addActor(exitGameButton);
        
        Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0, 400, 400, 0, 1, 1, 0);
		batch.end();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
