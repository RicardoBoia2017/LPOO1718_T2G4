package mono.view;

import com.badlogic.gdx.Game;
import mono.view.swapper.ScreenEnum;
import mono.view.swapper.ScreenManager;

public class MonopolyGame extends Game {
	
	@Override
	public void create () {
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen( ScreenEnum.MAIN_MENU );
	}
}