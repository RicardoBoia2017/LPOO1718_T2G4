package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

public class HouseView extends EntityView{

	public HouseView(Monopoly game) {
		super (game);
	}

    public Sprite createSprite(Monopoly game) {
        Texture house = game.getAssetManager().get("House.png");

        return new Sprite(house, house.getWidth(), house.getHeight());
    }
}
