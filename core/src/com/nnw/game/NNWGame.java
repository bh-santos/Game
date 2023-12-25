package com.nnw.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.nnw.game.entities.player.GameUser;
import com.nnw.game.server.GameClient;
import com.nnw.game.ui.MainMenuFirstScreen;
import com.nnw.game.util.UIType;

public class NNWGame extends Game {

	private GameUser gameUser;
	private ShapeRenderer shapeRenderer;


	@Override
	public void create () {
		gameUser = new GameUser();
		shapeRenderer = new ShapeRenderer();

		MainMenuFirstScreen mainMenuFirstScreen = new MainMenuFirstScreen(this, UIType.MAIN_MENU,gameUser);
		setScreen(mainMenuFirstScreen);

		GameClient gameClient = new GameClient(gameUser);

	}

}
