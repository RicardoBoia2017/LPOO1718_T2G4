package mono.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import mono.model.Game;
import mono.model.entities.Player;
import mono.view.entities.PropertyView;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.UIFactory;

public class PropertiesScreen extends AbstractScreen {

	Skin skin;
	int currentCard;

	public PropertiesScreen()
	{
		super();
		skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
		currentCard = 0;
		
		loadProperties();
	}
	
	private static void loadProperties()
	{
		System.out.println("Entrou");
		game.getAssetManager().load("C://Users//ricar//git//LPOO1718_T2G4//Monopoly//core//assets//Properties//Athens.png", Texture.class);
		game.getAssetManager().load("Properties/Berlin.png", Texture.class);
		game.getAssetManager().load("Properties/Brussels.png", Texture.class);
		game.getAssetManager().load("Properties/Buenos Aires.png", Texture.class);
		game.getAssetManager().load("Properties/Cairo.png", Texture.class);
		game.getAssetManager().load("Properties/Cape Town.png", Texture.class);
		game.getAssetManager().load("Properties/Casablanca.png", Texture.class);
		game.getAssetManager().load("Properties/Dubai.png", Texture.class);
		game.getAssetManager().load("Properties/Lisbon.png", Texture.class);
		game.getAssetManager().load("Properties/London.png", Texture.class);
		game.getAssetManager().load("Properties/Madrid.png", Texture.class);
		game.getAssetManager().load("Properties/Mexico City.png", Texture.class);
		game.getAssetManager().load("Properties/Moscow.png", Texture.class);
		game.getAssetManager().load("Properties/New York.png", Texture.class);
		game.getAssetManager().load("Properties/Paris.png", Texture.class);
		game.getAssetManager().load("Properties/Rio de Janeiro.png", Texture.class);
		game.getAssetManager().load("Properties/Rome.png", Texture.class);
		game.getAssetManager().load("Properties/Shanghai.png", Texture.class);
		game.getAssetManager().load("Properties/Singapore.png", Texture.class);
		game.getAssetManager().load("Properties/Sydney.png", Texture.class);
		game.getAssetManager().load("Properties/Tokyo.png", Texture.class);
        game.getAssetManager().finishLoading();
	}

	@Override
	public void buildStage() {
		 
		addActor(createBackBtn());
	}
	
	@Override
	public void render (float delta)
	{
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.getBatch().begin();
		drawProperty();
		game.getBatch().end();
		
		super.act();
		super.draw();
	}

	private void drawProperty() {

		Player p1 = Game.getInstance().getPlayers().get(Game.getInstance().getCurrentPlayer() - 1);
		
		if (p1.getPropertiesOwned().size() == 0)
			return;
		
		String name = p1.getPropertiesOwned().get(this.currentCard).getName();
		
		PropertyView property = new PropertyView (game, name);
		
		//property.draw(game.getBatch());
		Sprite sprite = property.getSprite();
		
		sprite.setSize(500, 600);
		sprite.setPosition(250, 350);
		sprite.draw(game.getBatch());
		
	}

	private TextButton createBackBtn() {

		TextButton backBtn = new TextButton ("Back", skin);
        backBtn.setPosition(70, 70); 
        backBtn.setWidth(180);
        backBtn.setHeight (120);
        backBtn.setChecked(false);
        
        backBtn.addListener(UIFactory.createListener(ScreenEnum.GAME_IN_PROGRESS));
        
		return backBtn;
	}
}
