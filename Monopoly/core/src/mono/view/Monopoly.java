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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Monopoly extends ApplicationAdapter {
	SpriteBatch batch;
    Stage stage;
	Texture startScreen;
	Texture board;
	Texture boot;
	Texture car;
	Texture hat;
	Texture thimble;
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
        font = new BitmapFont();
        skin = new Skin(Gdx.files.internal("comic/skin/comic-ui.json"));
        
        startScreen = new Texture("Monopoly.png");
        
//        createNewGameButton();
//        createExitGameButton();
        
        Gdx.input.setInputProcessor(stage);
	}
	
//	public void createNewGameButton() {
//		newGameButton = new TextButton("New Game", skin);
//        newGameButton.setPosition(20, 20);
//        newGameButton.setWidth(400);
//        newGameButton.addListener(new ClickListener(){
//            @Override 
//            public void clicked(InputEvent event, float x, float y){
//            	createSelectPieceScreen();
//            	swapToSelectPieceScreen();
//            }
//        });
//        stage.addActor(newGameButton);
//	}
//	
//	public void createExitGameButton() {
//        exitGameButton = new TextButton("Exit Game", skin);
//        exitGameButton.setPosition(680, 20);
//        exitGameButton.setWidth(200);
//        exitGameButton.addListener(new ClickListener(){
//            @Override 
//            public void clicked(InputEvent event, float x, float y){
//               System.exit(0);
//            }
//        });
//        stage.addActor(exitGameButton);
//	}

//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(startScreen, 0, 0, 700, 700, 0, 1, 1, 0);
//		batch.end();
//		stage.draw();
//	}
	
	public void createSelectPieceScreen() {
		stage = new Stage();
		batch = new SpriteBatch();
		
        loadPieceTextures();
        
        createPieceField();
        createPlayerNumberField();
        
        Gdx.input.setInputProcessor(stage);
	}
	
	public void createPieceField() {
        TextField pieceField = new TextField("", skin);
        pieceField.setPosition(24,73);
        pieceField.setSize(88, 14);
        stage.addActor(pieceField);
	}
	
	public void createPlayerNumberField() {
        TextField playerField = new TextField("", skin);
        playerField.setPosition(24,83);
        playerField.setSize(88, 14);
        stage.addActor(playerField);
	}
	
	public void loadPieceTextures() {
		boot = new Texture ("Boot.png");
		car = new Texture ("Car.png");
		hat = new Texture ("Hat.png");
		thimble = new Texture ("Thimble.png");
	}
	
	
	public void swapToSelectPieceScreen() {
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw (boot, 0, 660, 40, 40, 0 ,1, 1, 0); 
		batch.draw (car, 50, 660, 40, 40, 0 ,1, 1, 0); 
		batch.draw (hat, 0, 610, 40, 40, 0 ,1, 1, 0); 
		batch.draw (thimble, 50, 610, 40, 40, 0 ,1, 1, 0); 
		batch.end();
		stage.draw();
	}
	
	public void createMainGameScreen() {
		stage = new Stage();
		batch = new SpriteBatch();
		
		board = new Texture ("Board.png");
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		startScreen.dispose();
	}
}
