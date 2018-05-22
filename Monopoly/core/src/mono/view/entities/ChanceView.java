package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

public class ChanceView extends EntityView {

	public ChanceView(Monopoly game) {
		super (game);
		sprite = createSprite ();

	}

	@Override
	public Sprite createSprite() {
	
        Texture chance = game.getAssetManager().get("Chance.png");

        return new Sprite(chance, chance.getWidth(), chance.getHeight());
	}

}
