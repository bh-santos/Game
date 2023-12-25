package com.nnw.game;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl3.*;
import org.lwjgl.glfw.GLFW;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static com.nnw.game.util.AssetNames.Textures.ANIMATED_SCREEN_ICON;
import static com.nnw.game.util.Settings.*;


public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		/*DisplayMode[] mode = Lwjgl3ApplicationConfiguration.getDisplayModes(Lwjgl3ApplicationConfiguration.getPrimaryMonitor());
		for(Graphics.DisplayMode modes:mode){
			if(modes.width == 1280 && modes.height == 720){
				config.setFullscreenMode(modes);
			}
		}*/

		config.setForegroundFPS(FOREGROUND_FPS);
		config.setIdleFPS(IDLE_FPS);
		config.setWindowIcon(ANIMATED_SCREEN_ICON[8]);
		config.setTitle("Naruto Ninja War");
		config.setResizable(false);


		config.setWindowedMode(1280, 720);

		NNWGame game =new NNWGame();

		new Lwjgl3Application(game, config);

	}


}
