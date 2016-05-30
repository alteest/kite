package com.prospero.kite;

import com.badlogic.gdx.Game;
import com.prospero.kite.model.User;
import com.prospero.kite.screen.MainMenuScreen;

public class Kite extends Game {
	protected User user;
	
	@Override
	public void create () {
		user = new User();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	public User getUser() {
		return user;
	}
}
