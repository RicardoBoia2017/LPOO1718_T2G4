package mono.view.swapper;

import mono.model.Game;
import mono.model.entities.Player;
import mono.view.AbstractScreen;
import mono.view.GameScreen;
import mono.view.PieceSelectScreen;
import mono.view.PropertiesScreen;
import mono.view.MainMenuScreen;
import mono.view.NegotiationScreen;
import mono.view.NetworkScreen;
import mono.view.NumberPlayersScreen;

public enum ScreenEnum {
	
	MAIN_MENU {
		public AbstractScreen getScreen(Object... params) {
			return new MainMenuScreen();
		}
	},
	
	NUMBER_PLAYERS {
		public AbstractScreen getScreen(Object... params) {
			return new NumberPlayersScreen();
		}
	},
	
	LEVEL_SELECT {
		public AbstractScreen getScreen(Object... params) {
			return new PieceSelectScreen((int) params [0]);
		}
	},
	
	GAME {
		public AbstractScreen getScreen(Object... params) {
			Game.getInstance().addPlayer((String) params[0]);
			Game.getInstance().addBots();
			return new GameScreen();
		}
	},

	PROPERTIES {
		public AbstractScreen getScreen(Object... params) { 
			return new PropertiesScreen();
		}
	}, 
	
	NEGOTIATION{
		public AbstractScreen getScreen(Object... params) {
			return new NegotiationScreen((Player) params[0]);
		}
	},
		
	NETWORKING{
		public AbstractScreen getScreen(Object... params) {
			return new NetworkScreen();
		}
	
	};
	
	public abstract AbstractScreen getScreen(Object... params);
}
