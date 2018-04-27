package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

public class HatView extends EntityView{

	public HatView(Monopoly game) {
		super (game);
	}

    public Sprite createSprite(Monopoly game) {
        Texture hat = game.getAssetManager().get("Hat.png");

        return new Sprite(hat, hat.getWidth(), hat.getHeight());
    }
}
