package mono.poly.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import mono.view.Monopoly;
import mono.view.MonopolyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();		
		config.title = "Monopoly";
		config.width = 1000;
		config.height = 1000;
		new LwjglApplication(new MonopolyGame(), config);
	}
}
