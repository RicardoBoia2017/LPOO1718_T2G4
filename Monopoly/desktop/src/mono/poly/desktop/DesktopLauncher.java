package mono.poly.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import mono.poly.Monopoly;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();		
		config.title = "Monopoly";
		config.useGL30=false;
		config.width = 400;
		config.height = 400;
		new LwjglApplication(new Monopoly(), config);
	}
}
