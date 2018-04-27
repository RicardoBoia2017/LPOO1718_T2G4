package mono.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mono.game.Monopoly;

public class BoardView extends EntityView{

	public BoardView(Monopoly game) {
		super (game);
	}

    public Sprite createSprite(Monopoly game) {
        Texture board = game.getAssetManager().get("Board.png");

        return new Sprite(board, board.getWidth(), board.getHeight());
    }
}
