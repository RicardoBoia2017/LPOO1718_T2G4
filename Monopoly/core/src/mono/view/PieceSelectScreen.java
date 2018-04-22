package mono.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Align;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.UIFactory;

public class PieceSelectScreen extends AbstractScreen {
	
	Texture boot;
	Texture car;
	Texture hat;
	Texture thimble;
	
	public PieceSelectScreen() {
		super();
		loadPieceTextures();
	}
	
	public void loadPieceTextures() {
		boot = new Texture ("Boot.png");
		car = new Texture ("Car.png");
		hat = new Texture ("Hat.png");
		thimble = new Texture ("Thimble.png");
	}

	@Override
	public void buildStage() {
		ImageButton btnBoot = UIFactory.createButton(boot);
		btnBoot.setSize(500, 500);
		btnBoot.setPosition(250.f, 750.f, Align.center);
		addActor(btnBoot);
		
		ImageButton btnCar = UIFactory.createButton(car);
		btnCar.setSize(500, 500);
		btnCar.setPosition(750.f, 750.f, Align.center);
		addActor(btnCar);
		
		ImageButton btnHat = UIFactory.createButton(hat);
		btnHat.setSize(500, 500);
		btnHat.setPosition(250.f, 250.f, Align.center);
		addActor(btnHat);
		
		ImageButton btnThimble = UIFactory.createButton(thimble);
		btnThimble.setSize(500, 500);
		btnThimble.setPosition(750.f, 250.f, Align.center);
		addActor(btnThimble);
		
		btnBoot.addListener(UIFactory.createListener(ScreenEnum.GAME, 1));
		btnCar.addListener(UIFactory.createListener(ScreenEnum.GAME, 2));
		btnHat.addListener(UIFactory.createListener(ScreenEnum.GAME, 3));
		btnThimble.addListener(UIFactory.createListener(ScreenEnum.GAME, 4));
	}

	@Override
	public void dispose() {
		super.dispose();
		boot.dispose();
		car.dispose();
		hat.dispose();
		thimble.dispose();
	}
}
