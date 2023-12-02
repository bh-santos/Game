package com.nnw.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.nnw.game.ui.MainMenu;
import com.nnw.game.util.AssetNames;

public class NNWGame extends Game {

	@Override
	public void create () {
		MainMenu mainMenu = new MainMenu(this);
		setScreen(mainMenu);


	}


}
