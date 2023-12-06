package com.nnw.game.ui;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AnimatedBackgroundMainMenu extends Actor {
    private Animation<TextureRegion> animation;
    private float stateTime;

    public AnimatedBackgroundMainMenu(Animation<TextureRegion> animation) {
        this.animation = animation;
        this.stateTime = 0f;

        // Define o tamanho do ator com base no tamanho do primeiro quadro da animação
        setWidth(animation.getKeyFrame(0).getRegionWidth());
        setHeight(animation.getKeyFrame(0).getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        // Obtém o quadro atual da animação
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);

        // Desenha o quadro atual
        batch.draw(currentFrame, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
