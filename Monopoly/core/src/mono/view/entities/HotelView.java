package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

public class HotelView extends EntityView{

	public HotelView(Monopoly game) {
		super (game);
		sprite = createSprite ();
	}

    public Sprite createSprite() {
        Texture hotel = game.getAssetManager().get("Hotel.png");

        return new Sprite(hotel, hotel.getWidth(), hotel.getHeight());
    }
}
