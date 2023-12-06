package com.nnw.game;

import com.badlogic.gdx.Game;
import com.nnw.game.ui.MainMenuFirstScreen;
import com.nnw.game.util.UIType;

public class NNWGame extends Game {

	@Override
	public void create () {
		MainMenuFirstScreen mainMenuFirstScreen = new MainMenuFirstScreen(this, UIType.MAIN_MENU);
		setScreen(mainMenuFirstScreen);


	}


}
