package com.nnw.game.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nnw.game.util.UIType;
import lombok.Data;


import static com.nnw.game.util.AssetNames.Fonts.MENU_ITEMS_FONT;
import static com.nnw.game.util.AssetNames.Fonts.MENU_LOGO_FONT;
import static com.nnw.game.util.AssetNames.Textures.*;
import static com.nnw.game.util.Settings.GAME_HEIGHT;
import static com.nnw.game.util.Settings.GAME_WIDTH;

@Data
public class MainMenu implements Screen {

    private Game game;
    private UIType uiType;
    private Stage menuStage;
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Skin uiConfigs;
    private FreeTypeFontGenerator fontGeneratorLogo;
    private FreeTypeFontGenerator fontGeneratorItems;
    private Texture gameLogoTexture;
    private Image gameLogoImage;
    private Animation<TextureRegion> gifAnimation;
    private float stateTime;

    public MainMenu(Game game,UIType type){
        this.game = game;
        this.uiType=type;
    }
    @Override
    public void show() {
        //criando o palco do jogo
        menuStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(menuStage);

        //criando o fundo do palco
        backgroundTexture=new Texture(MAIN_MENU_BACKGROUND_OPTIONAL_IMAGE);
        backgroundImage = new Image(backgroundTexture);

        //criando o logo
        gameLogoTexture = new Texture(NARUTO_LOGO);
        gameLogoImage = new Image(gameLogoTexture);
        gameLogoImage.setScale(0.8f,0.8f);
        gameLogoImage.setPosition((GAME_WIDTH-gameLogoImage.getWidth()*gameLogoImage.getScaleX())/2,((GAME_HEIGHT-gameLogoImage.getHeight()*gameLogoImage.getScaleY())/2)+250);

        //criando a fonte do logo do menu
        fontGeneratorLogo =new FreeTypeFontGenerator(Gdx.files.internal(MENU_LOGO_FONT));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        parameter.color = Color.SKY;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        parameter.borderStraight =true;
        parameter.shadowColor = Color.DARK_GRAY;
        parameter.shadowOffsetX = 8;
        parameter.shadowOffsetY = 8;
        final BitmapFont fontLogo = fontGeneratorLogo.generateFont(parameter);

        Label.LabelStyle titleTextStyle = new Label.LabelStyle();
        titleTextStyle.font=fontLogo;
        //titleTextStyle.fontColor = Color.DARK_GRAY;


        //inicializando o configurador dos itens da UI
        uiConfigs = new Skin();

        uiConfigs.add("title_text",titleTextStyle);

        //criando o texto do logo menu;
        final Label logoText = new Label("Ninja War",uiConfigs,"title_text");

        logoText.setPosition((GAME_WIDTH-logoText.getWidth())/2,((GAME_HEIGHT-logoText.getHeight())/2)+180);

        //trabalhando com gifs

        gifAnimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,Gdx.files.internal(MAIN_MENU_BACKGROUND_OPTIONAL_IMAGE).read());

        //criando a fonte do botão de Start Game
        fontGeneratorItems = new FreeTypeFontGenerator(Gdx.files.internal(MENU_ITEMS_FONT));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 50;
        fontParameter.color = Color.ORANGE;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.borderWidth = 1;
        fontParameter.borderStraight =true;
        fontParameter.shadowColor = Color.DARK_GRAY;
        fontParameter.shadowOffsetX = 8;
        fontParameter.shadowOffsetY = 8;
        final BitmapFont startGameButtonText = fontGeneratorItems.generateFont(fontParameter);

        //ajustando as configurações de estilo do botão
        TextButton.TextButtonStyle startGameButtonTextStyle = new TextButton.TextButtonStyle();
        startGameButtonTextStyle.font = startGameButtonText;


        uiConfigs.add("start_game_text",startGameButtonTextStyle);


        //criando o botão
        final TextButton startGameTextButton = new TextButton("START GAME",uiConfigs,"start_game_text");

        //ajustando o tamanho do botão
        startGameTextButton.setPosition((GAME_WIDTH - startGameTextButton.getWidth())/2,(GAME_HEIGHT - startGameTextButton.getHeight())/2);


        //adicionando os itens ao palco
        //menuStage.addActor(backgroundImage);
        menuStage.addActor(gameLogoImage);
        menuStage.addActor(logoText);
        menuStage.addActor(startGameTextButton);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        menuStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stateTime += delta;

        // Obtenha o frame atual da animação
        menuStage.getBatch().begin();
        menuStage.getBatch().draw(gifAnimation.getKeyFrame(stateTime),0f,0f);
        menuStage.getBatch().end();
        menuStage.draw();


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
        backgroundTexture.dispose();
        fontGeneratorLogo.dispose();
        menuStage.getBatch().dispose();
    }
}
