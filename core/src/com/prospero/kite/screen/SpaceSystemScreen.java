package com.prospero.kite.screen;

import com.badlogic.gdx.Gdx;
import com.prospero.kite.Kite;
import com.prospero.kite.model.GO;
import com.prospero.kite.model.SpaceSystem;

public class SpaceSystemScreen extends ObjectScreen {

	public SpaceSystemScreen(Kite game, GO obj) {
		super(game, obj);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		selectedObj = getObject(screenX - Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - screenY);
		if (selectedObj != null) {
			game.setScreen(new PlanetScreen(game, selectedObj));
		}
		return true;
	}

	private GO getObject(int x, int y) {
		System.out.println(Integer.toString(x) + " - " + Integer.toString(y));
		return ((SpaceSystem) object).getObject(x, y);
	}
}
