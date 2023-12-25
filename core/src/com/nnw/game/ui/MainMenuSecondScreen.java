package com.nnw.game.ui;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nnw.game.entities.player.GameUser;
import com.nnw.game.util.UIType;
import lombok.Data;

import static com.nnw.game.util.AssetNames.Fonts.*;
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
    private FreeTypeFontGenerator topButtonsFontGenerator;
    private Skin uiConfigs;
    private TextButton shopButton;
    private TextButton profileButton;
    private TextButton missionsButton;
    private TextButton configurationButton;
    private TextButton playGameButton;
    private Group buttonsGroup;
    private Texture[] charMenuTextures = new Texture[6];
    private Image charMenuImageIdle;
    private Group menuItemsOrganizer;
    private final float charPositionX = 850,charPositionY =120;
    private Sound hoverSound;
    private BitmapFont topButtonsFont;
    private TextButton.TextButtonStyle topButtonsStyle;
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
    private Texture shopButtonTexture;
    private Texture profileButtonTexture;
    private Texture configurationButtonTexture;
    private Texture missionButtonTexture;
    private Texture profileLayoutTexture;
    private Texture profileMoldingTexture;
    private Texture playGameButtonTexture;
    private Texture initButtonTexture;
    private Texture exitButtonTexture;
    private Image shopButtonImage;
    private Image profileButtonImage;
    private Image configurationButtonImage;
    private Image missionButtonImage;
    private Image playGameButtonImage;
    private Image initButtonImage;
    private Image exitButtonImage;
    private Image profileLayoutImage;
    private Image profileMolding;
    private TextButton initButton;
    private TextButton exitButton;
    private TextButton.TextButtonStyle leftButtonsStyle;
    private FreeTypeFontGenerator leftButtonsFontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter leftButtonsFontParameter;
    private BitmapFont leftButtonsFont;
    private Label.LabelStyle profileLayoutStyle;
    private Label nicknameInfo;
    private GameUser gameUser;
    private Label titleInfo;
    private Texture profileIconTexture;
    private Image profileIconImage;
    private FreeTypeFontGenerator generalFontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter generalFontParameter;
    private BitmapFont generalFont;
    private Label levelInfo;
    private Label ryousInfo;
    private Label moonCoinInfo;
    private Group selectModeOrganizer;
    private Animation<TextureRegion> selectModeGifAnimation;
    private AnimatedBackgroundMainMenu animatedSelectModeBackground;
    private Group charOrganizer;
    private SelectModeMiniScreen miniScreen;
    private Group gifBackgroundOrganizer;
    private ParticleEffect particleEffect;
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    private Image backgroundImage;

    public MainMenuSecondScreen(Game game, UIType type,GameUser gameUser){
        this.game = game;
        this.uiType=type;
        this.gameUser=gameUser;
    }
    @Override
    public void show() {
        initStage();
        spriteBatch = new SpriteBatch();
        initUIConfig();
        initOrganizers();
        initAudioSystem();
        initTextures();
        initImages();
        initAnimations();
        initTween();
        configureMenuButtonsFont();
        createMenuItems();
        initButtonsListeners();
        configureMenuButtons();
        configureMenuItemsPositions();
        addActorsToStage();
        startFadeIn();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tweenManager.update(delta);

        menuStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        menuStage.draw();
        moveClouds(delta);

        particleEffect.update(delta);
        spriteBatch.begin();
        // Renderize o efeito de partículas
        particleEffect.draw(spriteBatch, delta);
        particleEffect.start();
        spriteBatch.end();
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
        menuItemsOrganizer = new Group();
        selectModeOrganizer = new Group();
        miniScreen = new SelectModeMiniScreen(uiConfigs);
        charOrganizer= new Group();
        gifBackgroundOrganizer = new Group();
    }
    private void initTextures(){
        backgroundTexture = new Texture(MAIN_MENU_SECOND_SCREEN_BACKGROUND);
        maskTexture = new Texture(Gdx.files.internal(TRANSPARENT_MASK));
        charMenuTextures[0] = new Texture(Gdx.files.internal(MAIN_MENU_CHAR_IDLE));
        charMenuTextures[1] = new Texture(Gdx.files.internal(MAIN_MENU_CHAR_IDLE1));
        charMenuTextures[2] = new Texture(Gdx.files.internal(MAIN_MENU_CHAR_IDLE2));
        charMenuTextures[3] = new Texture(Gdx.files.internal(MAIN_MENU_CHAR_IDLE3));
        charMenuTextures[4] = new Texture(Gdx.files.internal(MAIN_MENU_CHAR_IDLE4));
        charMenuTextures[5] = new Texture(Gdx.files.internal(MAIN_MENU_CHAR_IDLE5));
        gameLogoTexture = new Texture(NARUTO_LOGO);

        //top buttons
        shopButtonTexture = new Texture(GENERAL_TOP_BUTTONS);
        profileButtonTexture = new Texture(GENERAL_TOP_BUTTONS);
        configurationButtonTexture = new Texture(GENERAL_TOP_BUTTONS);
        missionButtonTexture = new Texture(GENERAL_TOP_BUTTONS);

        //leftButtons

        playGameButtonTexture = new Texture(GENERAL_LEFT_BUTTONS);
        initButtonTexture = new Texture(GENERAL_LEFT_BUTTONS);
        exitButtonTexture = new Texture(GENERAL_LEFT_BUTTONS);

        //profileLayout

        profileLayoutTexture = new Texture(GENERAL_PROFILE_LAYOUT);

        //Mold

        profileMoldingTexture = new Texture(GENERAL_PROFILE_MOLD);


        cloudTexture = new Texture(CLOUD_MENU);

        //profile Icon

        profileIconTexture = new Texture(gameUser.getProfileImage());

        //select mode Background



    }
    private void initImages(){
        backgroundImage = new Image(backgroundTexture);

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


        //top buttons images


        shopButtonImage = new Image(shopButtonTexture);
        profileButtonImage = new Image(profileButtonTexture);
        configurationButtonImage = new Image(configurationButtonTexture);
        missionButtonImage = new Image(missionButtonTexture);
        //leftbuttons

        playGameButtonImage = new Image(playGameButtonTexture);
        initButtonImage = new Image(initButtonTexture);
        exitButtonImage = new Image(exitButtonTexture);

        //profile layout

        profileLayoutImage = new Image(profileLayoutTexture);

        //profile mold

        profileMolding = new Image(profileMoldingTexture);

        //profile icon image

        profileIconImage = new Image(profileIconTexture);



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
        /*gifAnimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal(SELECT_MODE_BACKGROUND).read());
        animatedBackgroundMainMenu = new AnimatedBackgroundMainMenu(gifAnimation);*/

        /*selectModeGifAnimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,Gdx.files.internal(SELECT_MODE_BACKGROUND).read());
        animatedSelectModeBackground = new AnimatedBackgroundMainMenu(selectModeGifAnimation);*/

        particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("particles/particle.p"),Gdx.files.internal("particles/imgDir/"));

// Adicione o ParticleEmitter ao ParticleEffect

// Inicie o efeito de partículas
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
    private void createMenuItems(){
        shopButton = new TextButton("LOJA",uiConfigs,"top_button");
        profileButton = new TextButton("PERFIL",uiConfigs,"top_button");
        missionsButton = new TextButton("MISSOES",uiConfigs,"top_button");
        configurationButton = new TextButton("CONFIG.",uiConfigs,"top_button");
        playGameButton = new TextButton("JOGAR",uiConfigs,"left_button");
        initButton = new TextButton("INICIO",uiConfigs,"left_button");
        exitButton = new TextButton("SAIR",uiConfigs,"left_button");

        levelInfo = new Label("Level: "+gameUser.getLevel(),uiConfigs,"info_style");
        nicknameInfo = new Label("Nickname: "+gameUser.getNickname(),uiConfigs,"info_style");
        titleInfo = new Label ("Titulo: " +gameUser.getPlayerTitle(),uiConfigs,"info_style");
        ryousInfo = new Label("Ryous: "+gameUser.getRyous(),uiConfigs,"info_style");
        moonCoinInfo = new Label("Moon Coin: " + gameUser.getMoonCoin(),uiConfigs,"info_style");


    }
    private void configureMenuButtonsFont(){
        topButtonsFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(GENERAL_FONT_2));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 20;
        fontParameter.color = Color.WHITE;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.borderWidth = 1;
        fontParameter.borderStraight =true;
        fontParameter.shadowColor = Color.BLACK;
        fontParameter.shadowOffsetX = 1;
        fontParameter.shadowOffsetY = 1;
        topButtonsFont = topButtonsFontGenerator.generateFont(fontParameter);

        leftButtonsFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(GENERAL_FONT_2));
        leftButtonsFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        leftButtonsFontParameter.size = 20;
        leftButtonsFontParameter.color = Color.WHITE;
        leftButtonsFontParameter.borderColor = Color.BLACK;
        leftButtonsFontParameter.borderWidth = 1;
        leftButtonsFontParameter.borderStraight =true;
        leftButtonsFontParameter.shadowColor = Color.BLACK;
        leftButtonsFontParameter.shadowOffsetX = 1;
        leftButtonsFontParameter.shadowOffsetY = 1;
        leftButtonsFont = leftButtonsFontGenerator.generateFont(leftButtonsFontParameter);


        generalFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(GENERAL_FONT_2));
        generalFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        generalFontParameter.size = 20;
        generalFontParameter.color = Color.WHITE;
        generalFontParameter.borderColor = Color.BLACK;
        generalFontParameter.borderWidth = 1;
        generalFontParameter.borderStraight =true;
        generalFontParameter.shadowColor = Color.BLACK;
        generalFontParameter.shadowOffsetX = 1;
        generalFontParameter.shadowOffsetY = 1;
        generalFont = generalFontGenerator.generateFont(generalFontParameter);




        topButtonsStyle = new TextButton.TextButtonStyle();
        topButtonsStyle.font = topButtonsFont;
        topButtonsStyle.overFontColor = Color.ORANGE;
        topButtonsStyle.downFontColor = Color.RED;
        topButtonsStyle.up = new TextureRegionDrawable(new TextureRegion(shopButtonTexture));
        uiConfigs.add("top_button", topButtonsStyle);

        leftButtonsStyle = new TextButton.TextButtonStyle();
        leftButtonsStyle.font = leftButtonsFont;
        leftButtonsStyle.overFontColor = Color.RED;
        leftButtonsStyle.downFontColor = Color.WHITE;
        leftButtonsStyle.up = new TextureRegionDrawable(new TextureRegion(playGameButtonTexture)).tint(Color.WHITE);
        uiConfigs.add("left_button",leftButtonsStyle);

        profileLayoutStyle = new Label.LabelStyle();
        profileLayoutStyle.font = generalFont;
        uiConfigs.add("info_style",profileLayoutStyle);

    }
    private void configureMenuButtons(){
        menuItemsOrganizer.addActor(profileButton);
        menuItemsOrganizer.addActor(missionsButton);
        menuItemsOrganizer.addActor(shopButton);
        menuItemsOrganizer.addActor(configurationButton);
        menuItemsOrganizer.addActor(profileLayoutImage);
        menuItemsOrganizer.addActor(playGameButton);
        menuItemsOrganizer.addActor(initButton);
        menuItemsOrganizer.addActor(exitButton);
        menuItemsOrganizer.addActor(profileIconImage);
        menuItemsOrganizer.addActor(profileMolding);
        menuItemsOrganizer.addActor(nicknameInfo);
        menuItemsOrganizer.addActor(titleInfo);
        menuItemsOrganizer.addActor(levelInfo);
        menuItemsOrganizer.addActor(ryousInfo);
        menuItemsOrganizer.addActor(moonCoinInfo);

    }
    private void initButtonsListeners(){
        shopButton.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.play();
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                charOrganizer.removeActor(charMenuImageIdle);
                charMenuImageIdle = new Image(charMenuTextures[1]);
                charMenuImageIdle.setPosition(charPositionX,charPositionY);
                charOrganizer.addActor(charMenuImageIdle);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        profileButton.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.play();
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                charOrganizer.removeActor(charMenuImageIdle);
                charMenuImageIdle = new Image(charMenuTextures[2]);
                charMenuImageIdle.setPosition(charPositionX,charPositionY);
                charOrganizer.addActor(charMenuImageIdle);

            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        missionsButton.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.play();
                charOrganizer.removeActor(charMenuImageIdle);
                charMenuImageIdle = new Image(charMenuTextures[3]);
                charMenuImageIdle.setPosition(charPositionX,charPositionY);
                charOrganizer.addActor(charMenuImageIdle);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        configurationButton.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.play();
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                charOrganizer.removeActor(charMenuImageIdle);
                charMenuImageIdle = new Image(charMenuTextures[4]);
                charMenuImageIdle.setPosition(charPositionX,charPositionY);
                charOrganizer.addActor(charMenuImageIdle);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        playGameButton.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                selectModeOrganizer.addActor(miniScreen);
                animationOrganizer.removeActor(charOrganizer);
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.play();
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                charOrganizer.removeActor(charMenuImageIdle);
                charMenuImageIdle = new Image(charMenuTextures[5]);
                charMenuImageIdle.setPosition(charPositionX,charPositionY);
                charOrganizer.addActor(charMenuImageIdle);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        initButton.addListener(new InputListener(){

        });

        exitButton.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });

        miniScreen.getRankedButton().addListener(new InputListener(){

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                miniScreen.getRankedButton().setWidth((float) (miniScreen.getRankedButton().getWidth()+30));
                miniScreen.getRankedButton().setHeight((float) (miniScreen.getRankedButton().getHeight()+30));
                miniScreen.getRankedButton().setColor(Color.GOLD);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                miniScreen.getRankedButton().setWidth((float) (miniScreen.getRankedButton().getWidth()-30));
                miniScreen.getRankedButton().setHeight((float) (miniScreen.getRankedButton().getHeight()-30));
                miniScreen.getRankedButton().setColor(Color.WHITE);
            }
        });

        miniScreen.getNormalButton().addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                miniScreen.getNormalButton().setWidth((float) (miniScreen.getNormalButton().getWidth()+30));
                miniScreen.getNormalButton().setHeight((float) (miniScreen.getNormalButton().getHeight()+30));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                miniScreen.getNormalButton().setWidth((float) (miniScreen.getNormalButton().getWidth()-30));
                miniScreen.getNormalButton().setHeight((float) (miniScreen.getNormalButton().getHeight()-30));
            }
        });


        miniScreen.getTrainingButton().addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                miniScreen.getTrainingButton().setWidth((float) (miniScreen.getTrainingButton().getWidth()+30));
                miniScreen.getTrainingButton().setHeight((float) (miniScreen.getTrainingButton().getHeight()+30));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                miniScreen.getTrainingButton().setWidth((float) (miniScreen.getTrainingButton().getWidth()-30));
                miniScreen.getTrainingButton().setHeight((float) (miniScreen.getTrainingButton().getHeight()-30));
            }
        });


    }
    private void configureMenuItemsPositions(){
        charMenuImageIdle.setPosition(850,120);


        cloudImageTop1.setPosition(-50,520);
        cloudImageTop2.setPosition(380,520);
        cloudImageTop3.setPosition(780,520);
        cloudImageTop4.setPosition(1200,520);

        cloudImageBottom1.setPosition(50+300,0);
        cloudImageBottom2.setPosition(480+300,0);
        cloudImageBottom3.setPosition(880+300,0);
        cloudImageBottom4.setPosition(100,0);
        cloudImageBottom5.setPosition(600+300,0);
        cloudImageBottom6.setPosition(1280,0);


        profileButton.setPosition((float) (GAME_WIDTH*0.425), (GAME_HEIGHT-profileButton.getHeight())-20);
        missionsButton.setPosition((float) (GAME_WIDTH*0.425)+180, (GAME_HEIGHT-missionsButton.getHeight())-20);
        shopButton.setPosition((float) (GAME_WIDTH*0.425)+180*2, (GAME_HEIGHT-shopButton.getHeight())-20);
        configurationButton.setPosition((float) (GAME_WIDTH*0.425)+180*3, (GAME_HEIGHT-configurationButton.getHeight())-20);
        profileLayoutImage.setPosition(0,720-150-20+9);

        playGameButton.setPosition(0,GAME_HEIGHT-playGameButton.getHeight()-20-profileLayoutImage.getHeight()-20);
        initButton.setPosition(0,GAME_HEIGHT-initButton.getHeight()-20-profileLayoutImage.getHeight() - playGameButton.getHeight()-20-20);
        exitButton.setPosition(0,GAME_HEIGHT-exitButton.getHeight()-20-profileLayoutImage.getHeight()-playGameButton.getHeight()-initButton.getHeight()-20-20-20);

        gameLogoImage.setScale(0.35f,0.35f);
        gameLogoImage.setPosition(-17,((GAME_HEIGHT-gameLogoImage.getHeight()*gameLogoImage.getScaleY()))-20-profileLayoutImage.getHeight()-playGameButton.getHeight()-initButton.getHeight()-20-exitButton.getHeight()-20-20-150+100);


        profileMolding.setPosition(21,GAME_HEIGHT-profileMolding.getHeight()-21+9);

        levelInfo.setPosition(21+profileMolding.getWidth()+10,GAME_HEIGHT-levelInfo.getHeight()-30+6);
        levelInfo.setColor(Color.RED);
        nicknameInfo.setPosition(levelInfo.getX(),levelInfo.getY()-23);
        titleInfo.setPosition(nicknameInfo.getX(),nicknameInfo.getY()-23);
        ryousInfo.setPosition(titleInfo.getX(),titleInfo.getY()-23);
        moonCoinInfo.setPosition(ryousInfo.getX(),ryousInfo.getY()-23);


        profileIconImage.setPosition(profileMolding.getX()-8,profileMolding.getY()-5);
        profileIconImage.setScale(1.1f);

        miniScreen.getRankedButton().setPosition(301,187);
        miniScreen.getNormalButton().setPosition(630,175);
        miniScreen.getTrainingButton().setPosition(920,187);


    }
    private void addActorsToStage(){
        animationOrganizer.addActor(maskImage);
        animationOrganizer.addActor(backgroundImage);
        animationOrganizer.addActor(gifBackgroundOrganizer);
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
        charOrganizer.addActor(charMenuImageIdle);
        animationOrganizer.addActor(charOrganizer);
        animationOrganizer.addActor(menuItemsOrganizer);
        animationOrganizer.addActor(selectModeOrganizer);

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
