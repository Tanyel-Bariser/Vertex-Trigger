package com.vertextrigger.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vertextrigger.main.VertexTrigger;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		VertexTrigger vt = new VertexTrigger();
		new LwjglApplication(vt, config);
	}
}
