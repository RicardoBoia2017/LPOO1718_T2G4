package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

public class ThimbleView extends EntityView{

	public ThimbleView(Monopoly game) {
		super (game);
	}

    public Sprite createSprite(Monopoly game) {
        Texture thimble = game.getAssetManager().get("Thimble.png");

        return new Sprite(thimble, thimble.getWidth(), thimble.getHeight());
    }
}
