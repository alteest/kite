package com.prospero.kite.screen.old3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SpaceSystemBackgroundActor extends Actor {
    private Texture backgroundTexture;
    private Sprite backgroundSprite;

    public SpaceSystemBackgroundActor() {
        backgroundTexture = new Texture("images/sky.jpg");
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {
        backgroundSprite.draw(batch);
    }
}