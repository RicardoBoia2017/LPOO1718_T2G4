package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

/**
 * Creates Hat view
 * @author ricar
 *
 */
public class HatView extends EntityView{

	/**
	 * Creates hat view
	 * @param game game
	 */
	public HatView(Monopoly game) {
		super (game);
		sprite = createSprite ();
	}

    public Sprite createSprite() {
        Texture hat = game.getAssetManager().get("Hat.png");

        return new Sprite(hat, hat.getWidth(), hat.getHeight());
    }
}
