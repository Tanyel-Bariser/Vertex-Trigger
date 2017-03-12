package com.vertextrigger.desktop;

import com.badlogic.gdx.backends.lwjgl.*;
import com.vertextrigger.main.VertexTrigger;

public class DesktopLauncher {
	public static void main(final String[] arg) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new VertexTrigger(), config);
	}
}