package com.littlebandit.breakthrough.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.littlebandit.breakthrough.Breakthrough;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Breakthrough.VIRTUAL_WIDTH;
		config.height = Breakthrough.VIRTUAL_HEIGHT;
		config.title = Breakthrough.TITLE;
		new LwjglApplication(new Breakthrough(), config);
	}
}
