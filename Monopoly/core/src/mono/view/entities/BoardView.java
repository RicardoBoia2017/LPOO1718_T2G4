package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

/**
 * Creates board view
 * @author ricar
 *
 */
public class BoardView extends EntityView{

	/**
	 * Creates board view
	 * @param game game
	 */
	public BoardView(Monopoly game) {
		super (game);
		sprite = createSprite ();
	}

    public Sprite createSprite() {
        Texture board = game.getAssetManager().get("Board.png");
        return new Sprite(board, board.getWidth(), board.getHeight());
    }
}
