package com.prospero.kite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.prospero.kite.screen.MainMenuScreen;

public class Kite extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		setScreen(new MainMenuScreen(this));
	}

	
	@Override
	public void render () {
		super.render();
	}
}
