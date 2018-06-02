package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

/**
 * Creates Thimble view
 * @author ricar
 *
 */
public class ThimbleView extends EntityView{

	/**
	 * Creates thimble view
	 * @param game game
	 */
	public ThimbleView(Monopoly game) {
		super (game);
		sprite = createSprite ();
	}

    public Sprite createSprite() {
        Texture thimble = game.getAssetManager().get("Thimble.png");

        return new Sprite(thimble, thimble.getWidth(), thimble.getHeight());
    }
}
