package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

public class ChanceView extends EntityView {

	private int cardId;
	
	public ChanceView(Monopoly game, int cardId) {
		super (game);
		this.cardId = cardId;
		sprite = createSprite ();
	}

	@Override
	public Sprite createSprite() {
	
		Texture chance = null;
		
		if (cardId == 1)
			chance = game.getAssetManager().get("Chance.png");
		
		if (cardId == 2)
			chance = game.getAssetManager().get("Back 3 squares.png");

        return new Sprite(chance, chance.getWidth(), chance.getHeight());
	}

}
