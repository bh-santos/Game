package com.nnw.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static com.nnw.game.util.AssetNames.Fonts.LOGIN_WINDOW_FONT;
import static com.nnw.game.util.AssetNames.Textures.CLOUD_MENU;

public class LoginWindow extends Table {

    private Label.LabelStyle labelStyle;
    private TextField.TextFieldStyle textFieldStyle;
    private TextureRegionDrawable loginWindowBackground;
    private Texture loginWindowTexture;
    private FreeTypeFontGenerator fontGeneratorLoginWindow;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontLoginTextParameter;
    private BitmapFont loginTextFont;
    private Skin windowUIConfig;
    private Label title;
    private TextField login;
    private TextField password;


    public LoginWindow(){
        initTableConfigurations();
        initWindowBackgroundConfigurations();
        initFontConfigurations();
        initStyleConfigurations();
        initWindowsItems();
        initWindowsItemsConfigurations();
        addItemsToWindow();
    }

    private void initFontConfigurations(){
        //criando a fonte da janela de Login
        fontGeneratorLoginWindow = new FreeTypeFontGenerator(Gdx.files.internal(LOGIN_WINDOW_FONT));
        fontLoginTextParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontLoginTextParameter.size = 35;
        fontLoginTextParameter.color = Color.WHITE;
        fontLoginTextParameter.borderColor = Color.DARK_GRAY;
        fontLoginTextParameter.borderWidth = 0.5f;
        fontLoginTextParameter.borderStraight =false;
        fontLoginTextParameter.shadowColor = Color.DARK_GRAY;
        fontLoginTextParameter.shadowOffsetX = 2;
        fontLoginTextParameter.shadowOffsetY = 2;
        loginTextFont = fontGeneratorLoginWindow.generateFont(fontLoginTextParameter);
    }

    private void initTableConfigurations(){
        setSize(350,180);
        setPosition((Gdx.graphics.getWidth()-getWidth())/2,((Gdx.graphics.getHeight()-getHeight())/2)-210);
        windowUIConfig = new Skin();
        setSkin(windowUIConfig);
    }

    private void initStyleConfigurations(){
        textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = loginTextFont;
        textFieldStyle.fontColor = Color.WHITE;
        windowUIConfig.add("text_field",textFieldStyle);


        labelStyle = new Label.LabelStyle();
        labelStyle.font = loginTextFont;
        windowUIConfig.add("default",labelStyle);
    }

    private void initWindowsItems(){
        title = new Label("Fazer Login",windowUIConfig,"default");
        login = new TextField("",windowUIConfig,"text_field");
        password = new TextField("",windowUIConfig,"text_field");
    }

    private void initWindowsItemsConfigurations(){
        login.setFocusTraversal(true);
        login.setMessageText("digite aqui");
        password.setFocusTraversal(true);
        password.setMessageText("senha");
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');
    }
    private void initWindowBackgroundConfigurations(){
        loginWindowTexture = new Texture(Gdx.files.internal(CLOUD_MENU));
        loginWindowBackground = new TextureRegionDrawable(loginWindowTexture);
        setBackground(loginWindowBackground);
    }

    private void addItemsToWindow(){
        add(title).colspan(2).padBottom(10).center();
        row().padBottom(10);
        add("Login").padRight(10);
        add(login).width(200).padBottom(10);
        row().padBottom(10);
        add("Senha").padRight(10);
        add(password).width(200).padBottom(10);
    }

    public void dispose(){
        loginWindowTexture.dispose();
        fontGeneratorLoginWindow.dispose();
        loginTextFont.dispose();
        windowUIConfig.dispose();

    }
}
