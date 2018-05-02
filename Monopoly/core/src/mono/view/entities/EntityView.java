package mono.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import mono.controller.entities.EntityModel;
import mono.game.Monopoly;

public abstract class EntityView {

	Sprite sprite;
	
	public EntityView (Monopoly game) {
		sprite = createSprite (game);
	}

	public void draw (SpriteBatch batch)
	{
		sprite.draw(batch);
	}
	
    public abstract Sprite createSprite(Monopoly game);

    public void update(EntityModel model) {
        sprite.setCenter(model.getX(), model.getY());
        sprite.setRotation((float) Math.toDegrees(model.getRotation()));
    }
}
