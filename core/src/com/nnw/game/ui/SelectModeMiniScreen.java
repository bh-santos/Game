package com.nnw.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.awt.*;

import static com.nnw.game.util.AssetNames.Textures.*;

public class SelectModeMiniScreen extends Group {

    private Texture rankedModeTexture;
    private Texture normalModeTexture;
    private Texture trainingModeTexture;
    private Button rankedButton;

    public Button getRankedButton() {
        return rankedButton;
    }

    public void setRankedButton(Button rankedButton) {
        this.rankedButton = rankedButton;
    }

    public Button getNormalButton() {
        return normalButton;
    }

    public void setNormalButton(Button normalButton) {
        this.normalButton = normalButton;
    }

    public Button getTrainingButton() {
        return trainingButton;
    }

    public void setTrainingButton(Button trainingButton) {
        this.trainingButton = trainingButton;
    }

    private Button normalButton;
    private Button trainingButton;

    public SelectModeMiniScreen(Skin skin){
        initTextures();
        initStyles(skin);
        initButtons(skin);
        addToGroup();
    }

    private void addToGroup() {
        addActor(rankedButton);
        addActor(normalButton);
        addActor(trainingButton);

    }

    private void initButtons(Skin skin) {
        rankedButton = new Button(skin,"ranked");
        normalButton = new Button(skin,"normal");
        trainingButton = new Button(skin,"training");
    }


    private void initTextures() {

        rankedModeTexture = new Texture(RANKED_MODE_ICON);
        normalModeTexture = new Texture(NORMAL_MODE_ICON);
        trainingModeTexture = new Texture(TRAINING_MODE_ICON);


    }

    private void initStyles(Skin skin) {
        Button.ButtonStyle rankedButtonStyle = new Button.ButtonStyle();
        rankedButtonStyle.up = new TextureRegionDrawable(new TextureRegion(rankedModeTexture));

        Button.ButtonStyle normalButtonStyle = new Button.ButtonStyle();
        normalButtonStyle.up = new TextureRegionDrawable(new TextureRegion(normalModeTexture));


        Button.ButtonStyle traininButtonStyle = new Button.ButtonStyle();
        traininButtonStyle.up = new TextureRegionDrawable(new TextureRegion(trainingModeTexture));


        skin.add("ranked",rankedButtonStyle);
        skin.add("normal",normalButtonStyle);
        skin.add("training",traininButtonStyle);
    }



}
