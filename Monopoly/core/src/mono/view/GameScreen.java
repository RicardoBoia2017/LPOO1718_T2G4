package mono.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Align;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.UIFactory;

public class GameScreen extends AbstractScreen {
	
	private Texture board;
	
	public GameScreen(Integer player1Model) {
		super();
		board = new Texture ("Board.png");
	}	

	@Override
	public void buildStage() {
		// Adding actors

		Image boardImage = new Image(board);
		boardImage.setSize(1000, 1000);
		boardImage.setPosition(getWidth() / 2, getHeight() / 2, Align.center);
		addActor(boardImage);
		
	}

	@Override
	public void dispose() {
		super.dispose();
		board.dispose();
	}

}
