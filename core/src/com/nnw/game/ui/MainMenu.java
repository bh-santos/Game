package com.nnw.game.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nnw.game.util.AssetNames;
import com.nnw.game.util.UIType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static com.nnw.game.util.AssetNames.Textures.MAIN_MENU_BACKGROUND_IMAGE;

@Data
public class MainMenu implements Screen {

    private Game game;
    private UIType uiType;
    private Texture backgroundTexture;
    private Image backgroundImage;

    public MainMenu(Game game,UIType type){
        this.game = game;
        this.uiType=type;
    }
    @Override
    public void show() {
        //criando o palco do jogo
        Stage menuStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(menuStage);

        //adicionando o fundo do palco
        backgroundTexture=new Texture(MAIN_MENU_BACKGROUND_IMAGE);
        backgroundImage = new Image(backgroundTexture);



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
