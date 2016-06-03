package com.prospero.kite.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SpaceSystemBackgroundActor extends Actor {
    private Sprite sprite;

    public SpaceSystemBackgroundActor() {
    	super();
        sprite = new Sprite(new Texture("images/bg2.jpg"));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {
        sprite.draw(batch);
    }
}