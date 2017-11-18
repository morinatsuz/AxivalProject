package com.axival.game.desktop;

import com.axival.game.CardPlay;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.axival.game.Axival;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.width = 1280;
		config.title = CardPlay.TITLE+" V."+CardPlay.VERSION;
		config.width  = CardPlay.V_WIDTH;
		//config.height = 720;
		config.height  = CardPlay.V_HEIGHT;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		config.resizable = true;
		config.fullscreen = false;
		new LwjglApplication(new CardPlay(), config);
	}
}
