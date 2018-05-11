package mono.view.swapper;

import mono.game.Monopoly;
import mono.view.AbstractScreen;
import mono.view.GameScreen;
import mono.view.PieceSelectScreen;
import mono.view.MainMenuScreen;

public enum ScreenEnum {
	
	MAIN_MENU {
		public AbstractScreen getScreen(Object... params) {
			return new MainMenuScreen();
		}
	},
	
	LEVEL_SELECT {
		public AbstractScreen getScreen(Object... params) {
			return new PieceSelectScreen();
		}
	},
	
	GAME {
		public AbstractScreen getScreen(Object... params) {
			return new GameScreen((String) params[0]);
		}
	};
	
	public abstract AbstractScreen getScreen(Object... params);
}
