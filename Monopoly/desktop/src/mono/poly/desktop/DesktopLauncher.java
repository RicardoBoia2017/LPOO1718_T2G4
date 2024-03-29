package mono.poly.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import mono.game.Monopoly;;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();		
		config.title = "Monopoly";
		config.width = 1100;
		config.height = 1000;
		new LwjglApplication(new Monopoly(), config);
	}
}
