package mono.view.swapper;

import mono.game.Monopoly;
import mono.model.Game;
import mono.view.AbstractScreen;
import mono.view.GameScreen;
import mono.view.PieceSelectScreen;
import mono.view.PropertiesScreen;
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
			Game.getInstance().addPlayers((String) params[0]);
			return new GameScreen();
		}
	},
	
	GAME_IN_PROGRESS {
		public AbstractScreen getScreen(Object... params) {
			return GameScreen.getInstance();
		}
	},

	PROPERTIES {
		public AbstractScreen getScreen(Object... params) {
			return new PropertiesScreen();
		}
	};
	
	public abstract AbstractScreen getScreen(Object... params);
}
