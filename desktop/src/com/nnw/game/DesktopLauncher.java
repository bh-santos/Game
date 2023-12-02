package com.nnw.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static com.nnw.game.util.AssetNames.Textures.ANIMATED_SCREEN_ICON;
import static com.nnw.game.util.Settings.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(FOREGROUND_FPS);
		config.setIdleFPS(IDLE_FPS);
		config.setDecorated(true);
		config.setWindowIcon(ANIMATED_SCREEN_ICON[8]);
		config.setTitle("Naruto Ninja War");
		config.setResizable(false);
		config.setWindowedMode(GAME_WIDTH, GAME_HEIGHT);

		new Lwjgl3Application(new NNWGame(), config);


	}


}
