package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

public class BootView extends EntityView {

	public BootView(Monopoly game) {
		super (game);
		sprite = createSprite ();
	}

	@Override
	public Sprite createSprite() {
		Texture boot = game.getAssetManager().get("Boot.png");
		
		return new Sprite (boot, boot.getWidth(), boot.getHeight());
	}

}
