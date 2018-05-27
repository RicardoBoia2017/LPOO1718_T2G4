package mono.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import mono.model.entities.Player;
import mono.view.AbstractScreen;

public class NegotiationScreen extends AbstractScreen {
	

	Skin skin;
	int currentCard;
	Dialog noMoneyDialog;
	Dialog successfulBuyDialog;

	public NegotiationScreen(Player p1) {
		super();
		skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
		currentCard = 0;
		
		loadProperties();
	}
	
	private static void loadProperties()
	{
		System.out.println("Entrou");
		game.getAssetManager().load("Properties/Athens.png", Texture.class);
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
		
		game.getAssetManager().load("Properties/Dunedin Station.png", Texture.class);
		game.getAssetManager().load("Properties/Liege Guillemins.png", Texture.class);
		game.getAssetManager().load("Properties/Milano Centrale.png", Texture.class);
		game.getAssetManager().load("Properties/Sao Bento.png", Texture.class);
		
		game.getAssetManager().load("Properties/Eletricity.png", Texture.class);
		game.getAssetManager().load("Properties/Water.png", Texture.class);

		game.getAssetManager().load("Mortgaged.png", Texture.class);
		
        game.getAssetManager().finishLoading();
	}

	@Override
	public void buildStage() {
		// TODO Auto-generated method stub

	}

}
