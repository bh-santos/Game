package com.nnw.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Timer;
import com.nnw.game.server.PingCallback;
import com.nnw.game.server.PingManager;
import com.nnw.game.ui.MainMenuFirstScreen;
import com.nnw.game.ui.MainMenuSecondScreen;
import com.nnw.game.util.UIType;

import static com.nnw.game.server.PingManager.isConnected;

public class NNWGame extends Game {

	@Override
	public void create () {
		MainMenuFirstScreen mainMenuFirstScreen = new MainMenuFirstScreen(this, UIType.MAIN_MENU);
		setScreen(mainMenuFirstScreen);






	}


}
