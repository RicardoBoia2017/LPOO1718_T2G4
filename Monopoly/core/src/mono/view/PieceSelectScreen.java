package mono.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.UIFactory;
//import singleton.Monopoly;

public class PieceSelectScreen extends AbstractScreen {
		
	Skin skin;
	
	public PieceSelectScreen() {
		super();
		skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
		loadPieceTextures();
	}
	
	public void loadPieceTextures() {
		this.game.getAssetManager().load ("Boot.png", Texture.class);
		this.game.getAssetManager().load ("Car.png", Texture.class);
		this.game.getAssetManager().load ("Thimble.png", Texture.class);
		this.game.getAssetManager().load ("Hat.png", Texture.class); 
        this.game.getAssetManager().finishLoading();
	}

	@Override
	public void buildStage() {
		Texture boot = this.game.getAssetManager().get("Boot.png");
		ImageButton btnBoot = UIFactory.createButton(boot);
		btnBoot.setSize(500, 500); 
		btnBoot.setPosition(250.f, 750.f, Align.center);
		addActor(btnBoot);
		
		Texture car = this.game.getAssetManager().get("Car.png");
		ImageButton btnCar = UIFactory.createButton(car);
		btnCar.setSize(500, 500);
		btnCar.setPosition(750.f, 750.f, Align.center);
		addActor(btnCar);
		
		Texture hat = this.game.getAssetManager().get("Hat.png");
		ImageButton btnHat = UIFactory.createButton(hat);
		btnHat.setSize(500, 500);
		btnHat.setPosition(250.f, 250.f, Align.center);
		addActor(btnHat);
		
		Texture thimble = this.game.getAssetManager().get("Thimble.png");
		ImageButton btnThimble = UIFactory.createButton(thimble);
		btnThimble.setSize(500, 500);
		btnThimble.setPosition(750.f, 250.f, Align.center);
		addActor(btnThimble);
		
		TextButton bot = new TextButton ("Bot", skin);
		bot.setPosition(10, 10);
		addActor (bot);
		
		btnBoot.addListener(UIFactory.createListener(ScreenEnum.GAME, "Boot"));
		btnCar.addListener(UIFactory.createListener(ScreenEnum.GAME, "Car"));
		btnHat.addListener(UIFactory.createListener(ScreenEnum.GAME, "Hat"));
		btnThimble.addListener(UIFactory.createListener(ScreenEnum.GAME, "Thimble"));
		bot.addListener(UIFactory.createListener(ScreenEnum.GAME, "Thimble"));
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
