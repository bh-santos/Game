package com.nnw.game.ui;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nnw.game.entities.player.GameUser;
import com.nnw.game.server.GeneralCallback;
import com.nnw.game.server.LoginCallback;
import com.nnw.game.util.UIType;
import lombok.Data;


import static com.nnw.game.server.GameClient.*;

import static com.nnw.game.ui.TextFieldListener.dateIsValid;
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
    private Group menuItemsOrganizer;
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
    private TextButton loginButton;
    private TextButton.TextButtonStyle enterButtonStyle;
    private FreeTypeFontGenerator fontGeneratorEnterButton;
    private FreeTypeFontGenerator.FreeTypeFontParameter enterButtonFontParameter;
    private BitmapFont enterButtonFont;
    private TextButton createAccountButton;
    private CreateAccountWindow createAccountWindow;
    private FreeTypeFontGenerator fontGeneratorButtons;
    private FreeTypeFontGenerator.FreeTypeFontParameter buttonsFontParameter;
    private BitmapFont buttonsFont;
    private TextButton.TextButtonStyle buttonsStyle;
    private TextButton createButton;
    private TextButton backButton;
    private Label createAccountTextSuccess;
    private GameUser gameUser;
    private String loginCode;
    private FreeTypeFontGenerator fontGeneratorLoginMessage;
    private FreeTypeFontGenerator.FreeTypeFontParameter loginMessageFontParameter;
    private BitmapFont loginMessageFont;
    private Label.LabelStyle errorMessageStyle;
    private Label errorMessageLabel;
    private ShapeRenderer shapeRenderer;


    public MainMenuFirstScreen(Game game, UIType type, GameUser gameUser){
        this.game = game;
        this.uiType=type;
        this.gameUser=gameUser;
    }
    @Override
    public void show() {
        initStage();
        initAudioSystem();
        initUIConfig();
        initOrganizers();
        initTextures();
        initImages();
        initAnimations();
        generateFonts();
        initStyleConfigurations();
        createMenuItems();
        configureMenuItems();
        initMenuButtonsListeners();

        addActorsToStage();
        initTween();

    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Configura a cor da linha (vermelha no exemplo)
        shapeRenderer.setColor(Color.RED);

        // Desenha a linha ao redor da tela
        shapeRenderer.line(0, 0, Gdx.graphics.getWidth(), 0); // Linha superior
        shapeRenderer.line(Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Linha direita
        shapeRenderer.line(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0, Gdx.graphics.getHeight()); // Linha inferior
        shapeRenderer.line(0, Gdx.graphics.getHeight(), 0, 0); // Linha esquerda

        // Encerra o desenho das linhas
        shapeRenderer.end();


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
        createAccountWindow.dispose();

    }

//Configuração de todos os itens do menu.
    private void initStage(){
        menuStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(menuStage);
    }
    private void initOrganizers(){
        menuItemsOrganizer = new Group();
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
        shapeRenderer=new ShapeRenderer();
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


        fontGeneratorButtons = new FreeTypeFontGenerator(Gdx.files.internal(LOGIN_WINDOW_FONT));
        buttonsFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        buttonsFontParameter.size = 30;
        buttonsFontParameter.color = Color.WHITE;
        buttonsFontParameter.borderColor = Color.BLACK;
        buttonsFontParameter.borderWidth = 0.3f;
        buttonsFontParameter.shadowColor = Color.DARK_GRAY;
        buttonsFontParameter.shadowOffsetX = 2;
        buttonsFontParameter.shadowOffsetY = 2;
        buttonsFont = fontGeneratorButtons.generateFont(buttonsFontParameter);

        fontGeneratorLoginMessage = new FreeTypeFontGenerator(Gdx.files.internal(LOGIN_WINDOW_FONT));
        loginMessageFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        loginMessageFontParameter.size = 30;
        loginMessageFontParameter.color = Color.RED;
        loginMessageFontParameter.borderColor = Color.RED;
        loginMessageFontParameter.borderWidth = 0.3f;
        loginMessageFontParameter.shadowColor = Color.BLACK;
        loginMessageFontParameter.shadowOffsetX = 1;
        loginMessageFontParameter.shadowOffsetY = 1;
        loginMessageFont = fontGeneratorLoginMessage.generateFont(loginMessageFontParameter);


    }
    private void createMenuItems(){
        //criando o texto do logo menu;
        logoText = new Label("Ninja War",uiConfigs,"title_text");
        //criando o botão
        startGameTextButton = new TextButton("START GAME",uiConfigs,"start_game_text");
        //criando tela de login
        loginWindow = new LoginWindow();
        createAccountWindow = new CreateAccountWindow(menuItemsOrganizer);
        loginButton = new TextButton("Entrar",uiConfigs,"enter_button");
        createAccountButton = new TextButton("Criar conta", uiConfigs, "enter_button");
        createButton = new TextButton("Criar", uiConfigs, "enter_button");
        backButton = new TextButton("Voltar",uiConfigs,"enter_button");
        createAccountTextSuccess = new Label("CONTA CRIADA COM SUCESSO", uiConfigs,"title_text");
        errorMessageLabel = new Label("Usuário ou senha incorreta.",uiConfigs,"error_message");
    }

    private void initStyleConfigurations(){
        titleTextStyle = new Label.LabelStyle();
        titleTextStyle.font=fontLogo;
        uiConfigs.add("title_text",titleTextStyle);

        //ajustando as configurações de estilo do botão
        startGameButtonTextStyle = new TextButton.TextButtonStyle();
        startGameButtonTextStyle.font = startGameFont;
        startGameButtonTextStyle.overFontColor = Color.DARK_GRAY;
        startGameButtonTextStyle.downFontColor = Color.RED;
        uiConfigs.add("start_game_text",startGameButtonTextStyle);


        buttonsStyle = new TextButton.TextButtonStyle();
        buttonsStyle.font = buttonsFont;
        buttonsStyle.overFontColor = Color.DARK_GRAY;
        uiConfigs.add("enter_button",buttonsStyle);

        errorMessageStyle = new Label.LabelStyle();
        errorMessageStyle.font = loginMessageFont;
        uiConfigs.add("error_message", errorMessageStyle);
    }
    private void configureMenuItems(){
        gameLogoImage.setScale(0.8f,0.8f);
        gameLogoImage.setPosition((GAME_WIDTH-gameLogoImage.getWidth()*gameLogoImage.getScaleX())/2,((GAME_HEIGHT-gameLogoImage.getHeight()*gameLogoImage.getScaleY())/2)+250);

        logoText.setPosition((GAME_WIDTH-logoText.getWidth())/2,((GAME_HEIGHT-logoText.getHeight())/2)+180);
        startGameTextButton.setPosition((GAME_WIDTH - startGameTextButton.getWidth())/2,(GAME_HEIGHT - startGameTextButton.getHeight())/2);
        loginButton.setPosition(640-120,85);
        createAccountButton.setPosition(640+10,85);
        createButton.setPosition(640-120,85);
        backButton.setPosition(640+10,85);
        createAccountTextSuccess.setPosition((GAME_WIDTH- createAccountTextSuccess.getWidth())/2,(GAME_HEIGHT- createAccountTextSuccess.getHeight())/2);

    }
    private void initMenuButtonsListeners(){
        startGameTextButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menuItemsOrganizer.removeActor(startGameTextButton);
                menuItemsOrganizer.addActor(loginWindow);
                menuItemsOrganizer.addActor(loginButton);
                menuItemsOrganizer.addActor(createAccountButton);
                clickSound.play();
                return true;
            }


            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                if (!(Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {
                    // Se o botão esquerdo do mouse não estiver pressionado, então é um hover
                     hoverSound.play();
                    // Coloque qualquer outra lógica relacionada ao hover aqui
                }

            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        loginButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clickSound.play();
                menuItemsOrganizer.removeActor(errorMessageLabel);
                if(loginWindow.getLogin().getText().equals("") || loginWindow.getPassword().getText().equals("")){
                    errorMessageLabel.setText("Nenhum campo pode estar vazio.");
                    errorMessageLabel.setPosition((float) (1280 - errorMessageLabel.getText().length * 13) /2,50);
                    menuItemsOrganizer.addActor(errorMessageLabel);
                }else {
                    login(loginWindow.getLogin().getText(), loginWindow.getPassword().getText(), new LoginCallback() {

                        @Override
                        public void onLoginSuccess() {

                            requestProfile(new GeneralCallback() {
                                @Override
                                public void onRequisitionSuccess() {
                                    System.out.println("Sucesso.");
                                    gameUser.viewAllInformation();
                                }

                                @Override
                                public void onRequisitionFailed(String message) {

                                }
                            });

                            startFadeOutAnimation();
                        }

                        @Override
                        public void onLoginFailure(String errorMessage) {
                            errorMessageLabel.setText("Usuário ou senha Incorreta");
                            errorMessageLabel.setPosition((float) (1280 - errorMessageLabel.getText().length * 11) /2,50);
                            menuItemsOrganizer.addActor(errorMessageLabel);
                        }
                    });

                }
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            hoverSound.play();
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }

        });

        createAccountButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menuItemsOrganizer.removeActor(loginWindow);
                menuItemsOrganizer.removeActor(errorMessageLabel);
                menuItemsOrganizer.removeActor(loginButton);
                menuItemsOrganizer.removeActor(createAccountButton);
                menuItemsOrganizer.addActor(createAccountWindow);
                menuItemsOrganizer.addActor(createButton);
                menuItemsOrganizer.addActor(backButton);
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });

        createButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(createAccountWindow.getConfirmEmail().getText().equalsIgnoreCase(createAccountWindow.getEmail().getText())){
                    if(createAccountWindow.getConfirmPassword().getText().equals(createAccountWindow.getPassword().getText())){
                        if(!createAccountWindow.verifyIfTextFieldsIsEmpty()){
                            if(createAccountWindow.isEmailIsValid()){
                                if(dateIsValid){
                                    String date = formatDate(createAccountWindow.getBirthday().getText());

                                    createAccount(
                                            createAccountWindow.getUserNickName().getText(),
                                            createAccountWindow.getUserName().getText(),
                                            createAccountWindow.getPassword().getText(),
                                            date,
                                            createAccountWindow.getEmail().getText(),
                                            new GeneralCallback() {
                                                @Override
                                                public void onRequisitionSuccess() {
                                                    menuItemsOrganizer.removeActor(createAccountWindow);
                                                    menuItemsOrganizer.removeActor(createButton);
                                                    menuItemsOrganizer.addActor(createAccountTextSuccess);
                                                    backButton.setPosition((1280-backButton.getWidth())/2,85);
                                                }

                                                @Override
                                                public void onRequisitionFailed(String message) {
                                                    errorMessageLabel.setText(message);
                                                    errorMessageLabel.setPosition((float) (1280 - errorMessageLabel.getText().length * 13) /2,50);
                                                    menuItemsOrganizer.addActor(errorMessageLabel);
                                                }
                                            }
                                    );
                                }else{
                                    errorMessageLabel.setText("Data de nascimento inválida");
                                    errorMessageLabel.setPosition((float) (1280 - errorMessageLabel.getText().length * 13) /2,50);
                                    menuItemsOrganizer.addActor(errorMessageLabel);
                                }
                            }else{
                                errorMessageLabel.setText("Formato de email inválido.");
                                errorMessageLabel.setPosition((float) (1280 - errorMessageLabel.getText().length * 13) /2,50);
                                menuItemsOrganizer.addActor(errorMessageLabel);
                            }

                        }else{
                            errorMessageLabel.setText("Nenhum campo pode estar vazio.");
                            errorMessageLabel.setPosition((float) (1280 - errorMessageLabel.getText().length * 13) /2,50);
                            menuItemsOrganizer.addActor(errorMessageLabel);
                        }
                    }else {
                        errorMessageLabel.setText("As senhas digitadas não correspondem.");
                        errorMessageLabel.setPosition((float) (1280 - errorMessageLabel.getText().length * 13) /2,50);
                        menuItemsOrganizer.addActor(errorMessageLabel);
                    }

                }else {
                    errorMessageLabel.setText("Os emails digitados não correspondem.");
                    errorMessageLabel.setPosition((float) (1280 - errorMessageLabel.getText().length * 13) /2,50);
                    menuItemsOrganizer.addActor(errorMessageLabel);
                }

                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });

        backButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                createAccountWindow.clearAllTextFields();
                menuItemsOrganizer.removeActor(createAccountWindow);
                menuItemsOrganizer.removeActor(createButton);
                menuItemsOrganizer.removeActor(createAccountWindow.getErrorMessageTextFieldEmailLabel());
                menuItemsOrganizer.removeActor(createAccountWindow.getErrorMessageTextFieldConfirmEmailLabel());
                menuItemsOrganizer.removeActor(backButton);
                menuItemsOrganizer.removeActor(errorMessageLabel);
                menuItemsOrganizer.addActor(loginWindow);
                menuItemsOrganizer.addActor(loginButton);
                menuItemsOrganizer.addActor(createAccountButton);
                backButton.setPosition(640+10,85);
                if(menuItemsOrganizer.getChildren().contains(createAccountTextSuccess,true)) {
                    menuItemsOrganizer.removeActor(createAccountTextSuccess);
                }
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
            }
        });

    }

    private void addActorsToStage(){

        menuItemsOrganizer.addActor(animatedBackgroundMainMenu);
        menuItemsOrganizer.addActor(gameLogoImage);
        menuItemsOrganizer.addActor(logoText);
        menuItemsOrganizer.addActor(startGameTextButton);
        menuStage.addActor(menuItemsOrganizer);

    }
    private void initTween(){
        tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class, new ActorAcessor());
    }

    private void startFadeOutAnimation() {
        float duration = 2.0f;  // Duração da animação em segundos

        // Crie os tweens para os atores (imagem e label)
        Tween.to(menuItemsOrganizer, ActorAcessor.ALPHA, duration)
                .target(0)
                .ease(TweenEquations.easeInOutQuad)
                .setCallback(new TweenCallback() {
                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        // Este método será chamado quando a animação terminar
                        if (type == TweenCallback.COMPLETE) {
                            // Execute o código que você quer após a animação
                            game.setScreen(new MainMenuSecondScreen(game, UIType.MAIN_MENU,gameUser));
                        }
                    }
                })
                .start(tweenManager);

    }

    private String formatDate(String inputDate) {
        // O padrão yyyy-mm-dd
        String[] parts = inputDate.split("/");
        if (parts.length == 3) {
            // Reorganize as partes para o padrão dd/mm/yyyy
            return parts[2] + "-" + parts[1] + "-" + parts[0];
        } else {
            // A data não está no formato esperado, retorne a entrada original
            return inputDate;
        }
    }
}
