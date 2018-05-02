package mono.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import mono.game.Monopoly;
import mono.model.entities.EntityModel;

public abstract class EntityView {

	Sprite sprite;
	Monopoly game;
	
	public EntityView (Monopoly game) 
	{
		this.game = game;
	}

	public void draw (SpriteBatch batch)
	{
		sprite.draw(batch);
	}
	
    public abstract Sprite createSprite();

    public void update(EntityModel model) {
        sprite.setCenter(model.getX(), model.getY());
        sprite.setRotation((float) Math.toDegrees(model.getRotation()));
    }
}
