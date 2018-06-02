package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

/**
 * Creates boot view
 * @author ricar
 *
 */
public class BootView extends EntityView {

	/**
	 * Creates boot view
	 * @param game game
	 */
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
