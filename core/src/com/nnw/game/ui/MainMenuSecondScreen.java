package com.nnw.game.ui;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nnw.game.util.UIType;
import lombok.Data;

import static com.nnw.game.util.AssetNames.Fonts.MENU_ITEMS_FONT;
import static com.nnw.game.util.AssetNames.Sounds.HOVER_ITEM_MENU_SOUND;
import static com.nnw.game.util.AssetNames.Textures.*;
import static com.nnw.game.util.Settings.GAME_HEIGHT;
import static com.nnw.game.util.Settings.GAME_WIDTH;

@Data
public class MainMenuSecondScreen implements Screen {
    private Game game;
    private UIType uiType;
    private Stage menuStage;
    private Texture maskTexture;
    private Image maskImage;
    private TweenManager tweenManager;
    private Group animationOrganizer;
    private Animation<TextureRegion> gifAnimation;
    private AnimatedBackgroundMainMenu animatedBackgroundMainMenu;
    private FileHandle animatedBackgroundFile;
    private FreeTypeFontGenerator fontGeneratorItems;
    private Skin uiConfigs;
    private TextButton playerVsCPUButton;
    private TextButton playerVsPlayerButton;
    private TextButton playerVsPlayerOnlineButton;
    private TextButton playerVsPlayerLocalButton;
    private TextButton trainingButton;
    private Group buttonsGroup;
    private Texture[] charMenuTextures = new Texture[6];
    private Image charMenuImageIdle;
    private Table menuItemsOrganizer;
    private final float charPositionX = 850,charPositionY =120;
    private Sound hoverSound;
    private BitmapFont menuItemsText;
    private TextButton.TextButtonStyle menuItemsStyle;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private Texture gameLogoTexture;
    private Image gameLogoImage;
    private Texture cloudTexture;
    private Image cloudImageBottom1;
    private Image cloudImageBottom2;
    private Image cloudImageBottom3;
    private Image cloudImageBottom4;
    private Image cloudImageBottom5;
    private Image cloudImageTop1;
    private Image cloudImageTop2;
    private Image cloudImageTop3;
    private Image cloudImageBottom6;
    private Image cloudImageTop4;
    private final int SPEED =50;
    private float positionXCloudImageTop1, positionXCloudImageTop3, positionXCloudImageTop4, positionXCloudImageTop2, positionXCloudImageBottom1, positionXCloudImageBottom2, positionXCloudImageBottom3, positionXCloudImageBottom4, positionXCloudImageBottom5, positionXCloudImageBottom6;

    public MainMenuSecondScreen(Game game, UIType type){
        this.game = game;
        this.uiType=type;

    }
    @Override
    public void show() {
        initStage();
        initUIConfig();
        initOrganizers();
        initAudioSystem();
        initTextures();
        initImages();
        initAnimations();
        initTween();
        configureMenuButtonsFont();
        createMenuButtons();
        initButtonsListeners();
        addMenuButtonsToOrganizer();
        configureMenuItemsPositions();
        addActorsToStage();
        startFadeIn();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tweenManager.update(delta);

        menuStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        menuStage.draw();
        moveClouds(delta);

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
        maskTexture.dispose();
        cloudTexture.dispose();
        gameLogoTexture.dispose();
        hoverSound.dispose();
        menuStage.getBatch().dispose();
        for(Texture t:charMenuTextures){
            t.dispose();
        }
    }

    //Métodos de construção do menu.
    private void initStage(){
        menuStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(menuStage);
    }
    private void initUIConfig(){
        uiConfigs = new Skin();
    }
    private void initOrganizers(){
        animationOrganizer = new Group();
        menuItemsOrganizer = new Table();
    }
    private void initTextures(){
        maskTexture = new Texture(Gdx.files.internal(TRANSPARENT_MASK));
        charMenuTextures[0] = new Texture(Gdx.files.internal(MAIN_MENU_CHAR_IDLE));
        charMenuTextures[1] = new Texture(Gdx.files.internal(MAIN_MENU_CHAR_IDLE1));
        charMenuTextures[2] = new Texture(Gdx.files.internal(MAIN_MENU_CHAR_IDLE2));
        charMenuTextures[3] = new Texture(Gdx.files.internal(MAIN_MENU_CHAR_IDLE3));
        charMenuTextures[4] = new Texture(Gdx.files.internal(MAIN_MENU_CHAR_IDLE4));
        charMenuTextures[5] = new Texture(Gdx.files.internal(MAIN_MENU_CHAR_IDLE5));
        gameLogoTexture = new Texture(NARUTO_LOGO);
        cloudTexture = new Texture(CLOUD_MENU);

    }
    private void initImages(){
        maskImage = new Image(maskTexture);
        charMenuImageIdle = new Image(charMenuTextures[0]);
        gameLogoImage = new Image(gameLogoTexture);
        cloudImageBottom1 = new Image(cloudTexture);
        cloudImageBottom2 = new Image(cloudTexture);
        cloudImageBottom3 = new Image(cloudTexture);
        cloudImageBottom4 = new Image(cloudTexture);
        cloudImageBottom5 = new Image(cloudTexture);
        cloudImageTop1 = new Image(cloudTexture);
        cloudImageTop2 = new Image(cloudTexture);
        cloudImageTop3 = new Image(cloudTexture);
        cloudImageBottom6 = new Image(cloudTexture);
        cloudImageTop4 = new Image(cloudTexture);
    }
    public void moveClouds(float delta){
        if(cloudImageTop1.getX()<1280) {
            positionXCloudImageTop1 = cloudImageTop1.getX();
        }else{
            positionXCloudImageTop1 = -417;
        }
        cloudImageTop1.setPosition((positionXCloudImageTop1 += SPEED * delta), 520);

        if(cloudImageTop3.getX()<1280) {
            positionXCloudImageTop3 = cloudImageTop3.getX();
        }else{
            positionXCloudImageTop3 = -417;
        }
        cloudImageTop3.setPosition((positionXCloudImageTop3 += SPEED * delta), 520);

        if(cloudImageTop4.getX()<1280) {
            positionXCloudImageTop4 = cloudImageTop4.getX();
        }else{
            positionXCloudImageTop4 = -417;
        }
        cloudImageTop4.setPosition((positionXCloudImageTop4 += SPEED * delta), 520);

        if(cloudImageTop2.getX()<1280) {
            positionXCloudImageTop2 = cloudImageTop2.getX();
        }else{
            positionXCloudImageTop2 = -417;
        }
        cloudImageTop2.setPosition((positionXCloudImageTop2 += SPEED * delta), 520);



        if(cloudImageBottom1.getX()<1280) {
            positionXCloudImageBottom1 = cloudImageBottom1.getX();
        }else{
            positionXCloudImageBottom1 = -417;
        }
        cloudImageBottom1.setPosition((positionXCloudImageBottom1 += SPEED * delta), 0);

        if(cloudImageBottom2.getX()<1280) {
            positionXCloudImageBottom2 = cloudImageBottom2.getX();
        }else{
            positionXCloudImageBottom2 = -417;
        }
        cloudImageBottom2.setPosition((positionXCloudImageBottom2 += SPEED * delta), 0);

        if(cloudImageBottom3.getX()<1280) {
            positionXCloudImageBottom3 = cloudImageBottom3.getX();
        }else{
            positionXCloudImageBottom3 = -417;
        }
        cloudImageBottom3.setPosition((positionXCloudImageBottom3 += SPEED * delta), 0);

        if(cloudImageBottom4.getX()<1280) {
            positionXCloudImageBottom4 = cloudImageBottom4.getX();
        }else{
            positionXCloudImageBottom4 = -417;
        }
        cloudImageBottom4.setPosition((positionXCloudImageBottom4 += SPEED * delta), 0);

        if(cloudImageBottom5.getX()<1280) {
            positionXCloudImageBottom5 = cloudImageBottom5.getX();
        }else{
            positionXCloudImageBottom5 = -417;
        }
        cloudImageBottom5.setPosition((positionXCloudImageBottom5 += SPEED * delta), 0);

        if(cloudImageBottom6.getX()<1280) {
            positionXCloudImageBottom6 = cloudImageBottom6.getX();
        }else{
            positionXCloudImageBottom6 = -417;
        }
        cloudImageBottom6.setPosition((positionXCloudImageBottom6 += SPEED * delta), 0);
    }
    private void initAnimations(){
        gifAnimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal(SECONDARY_MENU_BACKGROUND_IMAGE).read());
        animatedBackgroundMainMenu = new AnimatedBackgroundMainMenu(gifAnimation);
    }
    private void initTween(){
        tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class,new ActorAcessor());
    }
    private void initAudioSystem(){
        hoverSound = Gdx.audio.newSound(Gdx.files.internal(HOVER_ITEM_MENU_SOUND));
        hoverSound.play(0.0f);
        hoverSound.stop();
    }
    private void createMenuButtons(){
        playerVsCPUButton = new TextButton("INICIAR",uiConfigs,"menu_items");
        playerVsPlayerButton = new TextButton("OPCOES",uiConfigs,"menu_items");
        playerVsPlayerOnlineButton  = new TextButton("CREDITOS",uiConfigs,"menu_items");
        playerVsPlayerLocalButton = new TextButton("PERSONALIZAR",uiConfigs,"menu_items");
        trainingButton = new TextButton("SAIR",uiConfigs,"menu_items");
    }
    private void configureMenuButtonsFont(){
        fontGeneratorItems = new FreeTypeFontGenerator(Gdx.files.internal(MENU_ITEMS_FONT));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 45;
        fontParameter.color = Color.WHITE;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.borderWidth = 1;
        fontParameter.borderStraight =true;
        fontParameter.shadowColor = Color.DARK_GRAY;
        fontParameter.shadowOffsetX = 4;
        fontParameter.shadowOffsetY = 4;
        menuItemsText = fontGeneratorItems.generateFont(fontParameter);
        menuItemsStyle = new TextButton.TextButtonStyle();
        menuItemsStyle.font = menuItemsText;
        menuItemsStyle.overFontColor = Color.ORANGE;
        menuItemsStyle.downFontColor = Color.RED;
        uiConfigs.add("menu_items",menuItemsStyle);
    }
    private void addMenuButtonsToOrganizer(){
        menuItemsOrganizer.add(playerVsCPUButton).left();
        menuItemsOrganizer.row();
        menuItemsOrganizer.add(playerVsPlayerButton).left();
        menuItemsOrganizer.row();
        menuItemsOrganizer.add(playerVsPlayerOnlineButton).left();
        menuItemsOrganizer.row();
        menuItemsOrganizer.add(playerVsPlayerLocalButton).left();
        menuItemsOrganizer.row();
        menuItemsOrganizer.add(trainingButton).left();
    }
    private void initButtonsListeners(){
        playerVsCPUButton.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.play();
                animationOrganizer.removeActor(charMenuImageIdle);
                charMenuImageIdle = new Image(charMenuTextures[1]);
                charMenuImageIdle.setPosition(charPositionX,charPositionY);
                animationOrganizer.addActor(charMenuImageIdle);
            }
        });

        playerVsPlayerButton.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.play();
                animationOrganizer.removeActor(charMenuImageIdle);
                charMenuImageIdle = new Image(charMenuTextures[2]);
                charMenuImageIdle.setPosition(charPositionX,charPositionY);
                animationOrganizer.addActor(charMenuImageIdle);

            }
        });

        playerVsPlayerOnlineButton.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.play();
                animationOrganizer.removeActor(charMenuImageIdle);
                charMenuImageIdle = new Image(charMenuTextures[3]);
                charMenuImageIdle.setPosition(charPositionX,charPositionY);
                animationOrganizer.addActor(charMenuImageIdle);
            }
        });

        playerVsPlayerLocalButton.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.play();
                animationOrganizer.removeActor(charMenuImageIdle);
                charMenuImageIdle = new Image(charMenuTextures[4]);
                charMenuImageIdle.setPosition(charPositionX,charPositionY);
                animationOrganizer.addActor(charMenuImageIdle);
            }
        });

        trainingButton.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.play();
                animationOrganizer.removeActor(charMenuImageIdle);
                charMenuImageIdle = new Image(charMenuTextures[5]);
                charMenuImageIdle.setPosition(charPositionX,charPositionY);
                animationOrganizer.addActor(charMenuImageIdle);
            }
        });


    }
    private void configureMenuItemsPositions(){
        menuItemsOrganizer.setPosition(300,360);
        charMenuImageIdle.setPosition(850,120);

        gameLogoImage.setScale(0.4f,0.4f);
        gameLogoImage.setPosition((GAME_WIDTH-gameLogoImage.getWidth()*gameLogoImage.getScaleX())/2,((GAME_HEIGHT-gameLogoImage.getHeight()*gameLogoImage.getScaleY())/2)+290);

        cloudImageTop1.setPosition(-50,520);
        cloudImageTop2.setPosition(380,520);
        cloudImageTop3.setPosition(780,520);
        cloudImageTop4.setPosition(1200,520);

        cloudImageBottom1.setPosition(50,0);
        cloudImageBottom2.setPosition(480,0);
        cloudImageBottom3.setPosition(880,0);
        cloudImageBottom4.setPosition(1280,0);
        cloudImageBottom5.setPosition(600,0);
        cloudImageBottom6.setPosition(1000,0);


    }
    private void addActorsToStage(){
        animationOrganizer.addActor(maskImage);
        animationOrganizer.addActor(animatedBackgroundMainMenu);
        animationOrganizer.addActor(cloudImageBottom1);
        animationOrganizer.addActor(cloudImageBottom2);
        animationOrganizer.addActor(cloudImageBottom3);
        animationOrganizer.addActor(cloudImageBottom4);
        animationOrganizer.addActor(cloudImageBottom5);
        animationOrganizer.addActor(cloudImageBottom6);
        animationOrganizer.addActor(cloudImageTop1);
        animationOrganizer.addActor(cloudImageTop2);
        animationOrganizer.addActor(cloudImageTop3);
        animationOrganizer.addActor(cloudImageTop4);
        animationOrganizer.addActor(gameLogoImage);
        animationOrganizer.addActor(charMenuImageIdle);
        animationOrganizer.addActor(menuItemsOrganizer);

        menuStage.addActor(animationOrganizer);
    }
    public void startFadeIn(){
        float duration = 3.0f;
        Tween.from(animationOrganizer, ActorAcessor.ALPHA, duration)
                .target(0)
                .ease(TweenEquations.easeInOutQuad)
                .start(tweenManager);
    }
}
