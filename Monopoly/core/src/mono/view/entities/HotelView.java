package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

/**
 * Creates Hotel view
 * @author ricar
 *
 */
public class HotelView extends EntityView{

	/**
	 * Creates hotel view
	 * @param game game
	 */
	public HotelView(Monopoly game) {
		super (game);
		sprite = createSprite ();
	}

    public Sprite createSprite() {
        Texture hotel = game.getAssetManager().get("hotel.png");
        return new Sprite(hotel, hotel.getWidth(), hotel.getHeight());
    }
}
