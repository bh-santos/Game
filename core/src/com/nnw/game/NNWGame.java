package com.nnw.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.nnw.game.ui.MainMenu;

public class NNWGame extends Game {

	@Override
	public void create () {
		MainMenu mainMenu = new MainMenu(this);
		setScreen(mainMenu);
	}

}
