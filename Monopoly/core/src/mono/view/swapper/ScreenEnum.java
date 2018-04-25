package mono.view.swapper;

import mono.view.AbstractScreen;
import mono.view.GameScreen;
import mono.view.PieceSelectScreen;
import singleton.Monopoly;
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
			return new GameScreen((Integer) params[0], Monopoly.getInstance());
		}
	};
	
	public abstract AbstractScreen getScreen(Object... params);
}
