package mono.poly.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import mono.view.Monopoly;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();		
		config.title = "Monopoly";
		config.width = 400;
		config.height = 400;
		new LwjglApplication(new Monopoly(), config);
	}
}
