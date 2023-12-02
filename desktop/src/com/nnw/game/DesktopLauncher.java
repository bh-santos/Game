package com.nnw.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.nnw.game.NNWGame;
import com.nnw.game.util.Settings;

import static com.nnw.game.util.Settings.*;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Naruto Ninja War");
		config.setResizable(false);
		config.setWindowedMode(GAME_WIDTH, GAME_HEIGHT);

		new Lwjgl3Application(new NNWGame(), config);
	}
}
