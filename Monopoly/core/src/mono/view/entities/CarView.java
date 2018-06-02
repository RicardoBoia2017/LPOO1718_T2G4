package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

/**
 * Creates car view
 * 
 * @author ricar
 *
 */
public class CarView extends EntityView{

	/**
	 * Creates car view
	 * @param game game
	 */
	public CarView(Monopoly game) {
		super (game);
		sprite = createSprite ();
	}

    public Sprite createSprite() {
        Texture car = game.getAssetManager().get("Car.png");

        return new Sprite(car, car.getWidth(), car.getHeight());
    }
}
