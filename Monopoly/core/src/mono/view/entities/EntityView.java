package mono.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import mono.controller.entities.EntityModel;
import mono.game.Monopoly;

/**
 * Creates Entity view
 * @author ricar
 *
 */
public abstract class EntityView {

	Sprite sprite;
	Monopoly game;
	
	/**
	 * Creates entity view
	 * @param game game
	 */
	public EntityView (Monopoly game) 
	{
		this.game = game;
	}

	/**
	 * Draws sprite
	 * @param batch game batch
	 */
	public void draw (SpriteBatch batch)
	{
		sprite.draw(batch);
	}
	 
	/**
	 * Creates sprite
	 * 
	 * @return sprite
	 */
    public abstract Sprite createSprite();

    /**
     * 
     * @return sprite
     */
    public Sprite getSprite() {return sprite;}
    
    /**
     * Updates sprite
     * @param model entity model
     */
    public void update(EntityModel model) {
        sprite.setCenter(model.getX(), model.getY());
        sprite.setRotation((float) Math.toDegrees(model.getRotation()));
    }
}
