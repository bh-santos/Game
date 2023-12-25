package com.nnw.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static com.nnw.game.ui.EmailValidator.isValidEmail;
import static com.nnw.game.util.AssetNames.Fonts.LOGIN_WINDOW_FONT;
import static com.nnw.game.util.AssetNames.Textures.CLOUD_MENU;

public class CreateAccountWindow extends Table {

    private Label.LabelStyle labelStyle;
    private TextField.TextFieldStyle textFieldStyle;
    private TextureRegionDrawable createAccountWindowBackground;
    private Texture createAcconuntWindowTexture;
    private FreeTypeFontGenerator fontGeneratorLoginWindow;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontLoginTextParameter;
    private BitmapFont loginTextFont;
    private Skin windowUIConfig;
    private Label title;
    private TextField userName;
    private TextField password;
    private TextField userNickName;
    private TextField confirmPassword;
    private TextField email;


    private TextField confirmEmail;
    private TextField birthday;
    private FreeTypeFontGenerator errorMessageTextFieldGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter errorMessageTextFieldParameter;
    private BitmapFont errorMessageTextFieldFont;
    private Label.LabelStyle errorMessageTextFieldStyle;
    private Label errorMessageTextFieldEmailLabel;

    private Label errorMessageTextFieldConfirmEmailLabel;

    public Label getErrorMessageTextFieldEmailLabel() {
        return errorMessageTextFieldEmailLabel;
    }

    private boolean emailIsValid;

    public TextField getUserName() {
        return userName;
    }

    public TextField getPassword() {
        return password;
    }

    public TextField getUserNickName() {
        return userNickName;
    }

    public TextField getConfirmPassword() {
        return confirmPassword;
    }

    public TextField getEmail() {
        return email;
    }

    public TextField getConfirmEmail() {
        return confirmEmail;
    }

    public TextField getBirthday() {
        return birthday;
    }




    public CreateAccountWindow(Group group){
        initTableConfigurations();
        initWindowBackgroundConfigurations();
        initFontConfigurations();
        initStyleConfigurations();
        initWindowsItems();
        initWindowsItemsConfigurations();
        addListeners(group);
        addItemsToWindow();
    }

    private void initFontConfigurations(){
        //criando a fonte da janela de Login
        fontGeneratorLoginWindow = new FreeTypeFontGenerator(Gdx.files.internal(LOGIN_WINDOW_FONT));
        fontLoginTextParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontLoginTextParameter.size = 25;
        fontLoginTextParameter.color = Color.ORANGE;
        fontLoginTextParameter.borderColor = Color.ORANGE;
        fontLoginTextParameter.borderWidth = 0.5f;
        fontLoginTextParameter.borderStraight =false;
        fontLoginTextParameter.shadowColor = Color.DARK_GRAY;
        fontLoginTextParameter.shadowOffsetX = 2;
        fontLoginTextParameter.shadowOffsetY = 2;
        loginTextFont = fontGeneratorLoginWindow.generateFont(fontLoginTextParameter);


        errorMessageTextFieldGenerator = new FreeTypeFontGenerator(Gdx.files.internal(LOGIN_WINDOW_FONT));
        errorMessageTextFieldParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        errorMessageTextFieldParameter.size = 25;
        errorMessageTextFieldParameter.color = Color.ORANGE;
        errorMessageTextFieldParameter.borderColor = Color.ORANGE;
        errorMessageTextFieldParameter.borderWidth = 0.5f;
        errorMessageTextFieldParameter.borderStraight =false;
        errorMessageTextFieldParameter.shadowColor = Color.DARK_GRAY;
        errorMessageTextFieldParameter.shadowOffsetX = 2;
        errorMessageTextFieldParameter.shadowOffsetY = 2;
        errorMessageTextFieldFont = errorMessageTextFieldGenerator.generateFont(errorMessageTextFieldParameter);
    }

    private void initTableConfigurations(){
        setSize(400,600);
        setPosition((Gdx.graphics.getWidth()-getWidth())/2,((Gdx.graphics.getHeight()-getHeight())/2)-50);
        windowUIConfig = new Skin();
        setSkin(windowUIConfig);
    }

    private void initStyleConfigurations(){
        textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = loginTextFont;
        textFieldStyle.fontColor = Color.WHITE;
        windowUIConfig.add("text_field",textFieldStyle);

        errorMessageTextFieldStyle = new Label.LabelStyle();
        errorMessageTextFieldStyle.font = errorMessageTextFieldFont;
        errorMessageTextFieldStyle.fontColor = Color.RED;
        windowUIConfig.add("error_message",errorMessageTextFieldStyle);


        labelStyle = new Label.LabelStyle();
        labelStyle.font = loginTextFont;
        windowUIConfig.add("default",labelStyle);
    }

    private void initWindowsItems(){
        title = new Label("Criar Conta",windowUIConfig,"default");
        userName = new TextField("",windowUIConfig,"text_field");
        userNickName= new TextField("",windowUIConfig,"text_field");
        email = new TextField("",windowUIConfig,"text_field");
        confirmEmail = new TextField("",windowUIConfig,"text_field");
        password = new TextField("",windowUIConfig,"text_field");
        confirmPassword = new TextField("",windowUIConfig,"text_field");
        birthday = new TextField("",windowUIConfig,"text_field");
        errorMessageTextFieldEmailLabel = new Label("",windowUIConfig,"error_message");
        errorMessageTextFieldConfirmEmailLabel = new Label("", windowUIConfig,"error_message");

    }


    private void initWindowsItemsConfigurations(){
        userName.setFocusTraversal(true);
        userName.setMessageText("nome de usuário");
        userNickName.setFocusTraversal(true);
        userNickName.setMessageText("nome no jogo");
        email.setFocusTraversal(true);
        email.setMessageText("email");
        confirmEmail.setFocusTraversal(true);
        confirmEmail.setMessageText("confirmar email");
        password.setFocusTraversal(true);
        password.setMessageText("senha");
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');
        confirmPassword.setFocusTraversal(true);
        confirmPassword.setMessageText("confirmar senha");
        confirmPassword.setPasswordMode(true);
        confirmPassword.setPasswordCharacter('*');
        birthday.setFocusTraversal(true);
        birthday.setMessageText("yyyy-mm-dd");
        birthday.setTextFieldListener(new TextFieldListener());

    }
    private void initWindowBackgroundConfigurations(){
        createAcconuntWindowTexture = new Texture(Gdx.files.internal(CLOUD_MENU));
        createAccountWindowBackground = new TextureRegionDrawable(createAcconuntWindowTexture);
        //setBackground(createAccountWindowBackground);
    }

    private void addItemsToWindow(){
        add(title).colspan(2).padBottom(10).center();
        row().padBottom(10);
        add("Nome de usuário:").padRight(10).left();
        add(userName).width(200).padBottom(10);
        row().padBottom(10);


        add("Nome no jogo:").padRight(10).left();
        add(userNickName).width(200).padBottom(10);
        row().padBottom(10);

        add("Email:").padRight(10).left();
        add(email).width(200).padBottom(10);
        row().padBottom(10);

        add("Confirmar email:").padRight(10).left();
        add(confirmEmail).width(200).padBottom(10);
        row().padBottom(10);


        add("Senha:").padRight(10).left();
        add(password).width(200).padBottom(10);
        row().padBottom(10);

        add("Confirmar Senha:").padRight(10).left();
        add(confirmPassword).width(200).padBottom(10);
        row().padBottom(10);

        add("Data de nascimento:").padRight(10).left();
        add(birthday).width(200).padBottom(10);
        row().padBottom(10);


    }

    private void addListeners(final Group group){
        userName.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Ibeam);
            }


            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        password.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Ibeam);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        confirmPassword.addListener(new InputListener(){
            @Override
            public boolean keyTyped(InputEvent event, char character) {
                if (!(password.getText().equals(confirmPassword.getText()))){
                    System.out.println("DIFERENTES");
                }else{
                    System.out.println("iguais");
                }
                return false;
            }
        });



        email.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if (!focused) {
                    // Quando o foco é perdido, valide o texto
                    if (!isValidEmail(email.getText())) {
                        errorMessageTextFieldEmailLabel.setText("Formato de email inválido.");
                        errorMessageTextFieldEmailLabel.setPosition(email.getX()+email.getWidth()+450, email.getY()+25);
                        group.addActor(errorMessageTextFieldEmailLabel);
                        emailIsValid = false;
                    }else{
                        group.removeActor(errorMessageTextFieldEmailLabel);
                        emailIsValid = true;
                    }
                }
            }
        });

        confirmEmail.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if (!focused) {
                    // Quando o foco é perdido, valide o texto
                    if (!isValidEmail(confirmEmail.getText())) {
                        errorMessageTextFieldConfirmEmailLabel.setText("Formato de email inválido.");
                        errorMessageTextFieldConfirmEmailLabel.setPosition(confirmEmail.getX()+confirmEmail.getWidth()+450, confirmEmail.getY()+25);
                        group.addActor(errorMessageTextFieldConfirmEmailLabel);
                        emailIsValid = false;
                    }else{
                        group.removeActor(errorMessageTextFieldConfirmEmailLabel);
                        emailIsValid = true;
                    }
                }
            }
        });

    }


    public boolean verifyIfTextFieldsIsEmpty(){
        return userName.getText().isEmpty() ||
                userNickName.getText().isEmpty() ||
                email.getText().isEmpty() ||
                confirmEmail.getText().isEmpty() ||
                password.getText().isEmpty() ||
                confirmPassword.getText().isEmpty() ||
                birthday.getText().isEmpty();

    }

    public void clearAllTextFields(){
        userName.setText("");
        userNickName.setText("");
        email.setText("");
        confirmEmail.setText("");
        password.setText("");
        confirmPassword.setText("");
        birthday.setText("");
    }

    public void dispose(){
        createAcconuntWindowTexture.dispose();
        fontGeneratorLoginWindow.dispose();
        loginTextFont.dispose();
        windowUIConfig.dispose();

    }

    public boolean isEmailIsValid() {
        return emailIsValid;
    }

    public Label getErrorMessageTextFieldConfirmEmailLabel() {
        return errorMessageTextFieldConfirmEmailLabel;
    }
}