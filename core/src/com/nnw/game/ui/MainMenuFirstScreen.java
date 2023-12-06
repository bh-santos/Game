package com.nnw.game.ui;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nnw.game.util.UIType;
import lombok.Data;


import static com.nnw.game.util.AssetNames.Fonts.*;
import static com.nnw.game.util.AssetNames.Sounds.*;
import static com.nnw.game.util.AssetNames.Textures.*;
import static com.nnw.game.util.Settings.GAME_HEIGHT;
import static com.nnw.game.util.Settings.GAME_WIDTH;

@Data
public class MainMenuFirstScreen implements Screen {

    private Game game;
    private UIType uiType;
    private Stage menuStage;
    private Skin uiConfigs;
    private FreeTypeFontGenerator fontGeneratorLogo;
    private FreeTypeFontGenerator fontGeneratorItems;
    private Texture gameLogoTexture;
    private Image gameLogoImage;
    private Animation<TextureRegion> gifAnimation;
    private float stateTime;
    private TweenManager tweenManager;
    private Label logoText;
    private Group animationGroup;
    private AnimatedBackgroundMainMenu animatedBackgroundMainMenu;
    private TextButton startGameTextButton;
    private Sound clickSound;
    private Sound hoverSound;
    private Sound backgroundSound;
    private TextButton.TextButtonStyle startGameButtonTextStyle;
    private BitmapFont startGameFont;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontButtonParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontLogoParameter;
    private BitmapFont fontLogo;
    private Label.LabelStyle titleTextStyle;
    private LoginWindow loginWindow;


    public MainMenuFirstScreen(Game game, UIType type){
        this.game = game;
        this.uiType=type;
    }
    @Override
    public void show() {
        initStage();
        initAudioSystem();
        initUIConfig();
        initTextures();
        initImages();
        initAnimations();
        generateFonts();
        createMenuItems();
        configureMenuItems();
        initMenuButtonsListeners();
        initOrganizers();
        addActorsToStage();
        initTween();

    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        stateTime += delta;


        // Obtenha o frame atual da animação
        /*menuStage.getBatch().begin();
        menuStage.getBatch().draw(gifAnimation.getKeyFrame(stateTime),0f,0f);
        menuStage.getBatch().end();*/
        tweenManager.update(delta);
        menuStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
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
        uiConfigs.dispose();
        startGameFont.dispose();
        fontGeneratorLogo.dispose();
        menuStage.getBatch().dispose();
        hoverSound.dispose();
        clickSound.dispose();
        backgroundSound.dispose();
        fontLogo.dispose();
        gameLogoTexture.dispose();
        fontGeneratorItems.dispose();
        loginWindow.dispose();

    }

//Configuração de todos os itens do menu.
    private void initStage(){
        menuStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(menuStage);
    }
    private void initOrganizers(){
        animationGroup = new Group();
    }
    public void initAudioSystem(){
        clickSound = Gdx.audio.newSound(Gdx.files.internal(SELECT_ITEM_MENU_SOUND));
        clickSound.play(0.0f);
        clickSound.stop();

        hoverSound = Gdx.audio.newSound(Gdx.files.internal(HOVER_ITEM_MENU_SOUND));
        hoverSound.play(0.0f);
        clickSound.stop();

        backgroundSound = Gdx.audio.newSound(Gdx.files.internal(MENU_BACKGROUND_SOUND));
        backgroundSound.play();
        backgroundSound.loop();
    }
    public void initTextures(){
        gameLogoTexture = new Texture(NARUTO_LOGO);
    }
    public void initAnimations(){
        gifAnimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,Gdx.files.internal(MAIN_MENU_FIRST_SCREEN_BACKGROUND).read());
        animatedBackgroundMainMenu = new AnimatedBackgroundMainMenu(gifAnimation);
    }
    public void initImages(){
        gameLogoImage = new Image(gameLogoTexture);
    }
    private void initUIConfig(){
        uiConfigs = new Skin();
    }
    private void generateFonts(){
        //criando a fonte do logo do menu
        fontGeneratorLogo =new FreeTypeFontGenerator(Gdx.files.internal(MENU_LOGO_FONT));
        fontLogoParameter =new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontLogoParameter.size = 50;
        fontLogoParameter.color = Color.FIREBRICK;
        fontLogoParameter.borderColor = Color.BLACK;
        fontLogoParameter.borderWidth = 1;
        fontLogoParameter.borderStraight =true;
        fontLogoParameter.shadowColor = Color.DARK_GRAY;
        fontLogoParameter.shadowOffsetX = 8;
        fontLogoParameter.shadowOffsetY = 8;
        fontLogo = fontGeneratorLogo.generateFont(fontLogoParameter);
        titleTextStyle = new Label.LabelStyle();
        titleTextStyle.font=fontLogo;
        uiConfigs.add("title_text",titleTextStyle);

        //criando a fonte do botão de Start Game
        fontGeneratorItems = new FreeTypeFontGenerator(Gdx.files.internal(MENU_ITEMS_FONT));
        fontButtonParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontButtonParameter.size = 50;
        fontButtonParameter.color = Color.ORANGE;
        fontButtonParameter.borderColor = Color.BLACK;
        fontButtonParameter.borderWidth = 1;
        fontButtonParameter.borderStraight =true;
        fontButtonParameter.shadowColor = Color.DARK_GRAY;
        fontButtonParameter.shadowOffsetX = 4;
        fontButtonParameter.shadowOffsetY = 4;
        startGameFont = fontGeneratorItems.generateFont(fontButtonParameter);

        //ajustando as configurações de estilo do botão
        startGameButtonTextStyle = new TextButton.TextButtonStyle();
        startGameButtonTextStyle.font = startGameFont;
        startGameButtonTextStyle.overFontColor = Color.DARK_GRAY;
        startGameButtonTextStyle.downFontColor = Color.RED;
        uiConfigs.add("start_game_text",startGameButtonTextStyle);

    }
    private void createMenuItems(){
        //criando o texto do logo menu;
        logoText = new Label("Ninja War",uiConfigs,"title_text");
        //criando o botão
        startGameTextButton = new TextButton("START GAME",uiConfigs,"start_game_text");
        //criando tela de login
        loginWindow = new LoginWindow();
    }
    private void configureMenuItems(){
        gameLogoImage.setScale(0.8f,0.8f);
        gameLogoImage.setPosition((GAME_WIDTH-gameLogoImage.getWidth()*gameLogoImage.getScaleX())/2,((GAME_HEIGHT-gameLogoImage.getHeight()*gameLogoImage.getScaleY())/2)+250);

        logoText.setPosition((GAME_WIDTH-logoText.getWidth())/2,((GAME_HEIGHT-logoText.getHeight())/2)+180);

        startGameTextButton.setPosition((GAME_WIDTH - startGameTextButton.getWidth())/2,(GAME_HEIGHT - startGameTextButton.getHeight())/2);

    }
    private void initMenuButtonsListeners(){
        startGameTextButton.addListener(new ClickListener() {


            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //startFadeOutAnimation();
                animationGroup.removeActor(startGameTextButton);
                animationGroup.addActor(loginWindow);
                clickSound.play();
                return true;
            }


            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

                if (!(Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {
                    // Se o botão esquerdo do mouse não estiver pressionado, então é um hover
                     hoverSound.play();
                    // Coloque qualquer outra lógica relacionada ao hover aqui
                }

            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });
    }
    private void addActorsToStage(){

        animationGroup.addActor(animatedBackgroundMainMenu);
        animationGroup.addActor(gameLogoImage);
        animationGroup.addActor(logoText);
        animationGroup.addActor(startGameTextButton);
        menuStage.addActor(animationGroup);

    }
    private void initTween(){
        tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class, new ActorAcessor());
    }

    private void startFadeOutAnimation() {
        float duration = 2.0f;  // Duração da animação em segundos

        // Crie os tweens para os atores (imagem e label)
        Tween.to(startGameTextButton, ActorAcessor.ALPHA, duration)
                .target(0)
                .ease(TweenEquations.easeInOutQuad)
                .setCallback(new TweenCallback() {
                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        // Este método será chamado quando a animação terminar
                        if (type == TweenCallback.COMPLETE) {
                            // Execute o código que você quer após a animação
                            game.setScreen(new MainMenuSecondScreen(game, UIType.MAIN_MENU));
                        }
                    }
                })
                .start(tweenManager);

        Tween.to(logoText, ActorAcessor.ALPHA, duration)
                .target(0)
                .ease(TweenEquations.easeInOutQuad)
                .start(tweenManager);

        Tween.to(gameLogoImage,ActorAcessor.ALPHA,duration)
                .target(0)
                .ease(TweenEquations.easeInOutQuad)
                .start(tweenManager);


    }
}
