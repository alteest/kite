package com.prospero.kite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.prospero.kite.model.User;
import com.prospero.kite.model3d.TextureFactory;
import com.prospero.kite.screen.MainMenuScreen;

public class Kite extends Game {
	protected User user;
	public final static int multi = 100;
	private Skin skin = null;
	
	public Kite() {
		super();
	}
	
	@Override
	public void create () {
		TextureFactory.init();
		user = new User();
		//Station station = new Station1(new Station("TestStation1", 0f, 0f, 0f));
		//setScreen(new StationScreen(this, station));
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void dispose() {
		if (skin != null) {
			skin.dispose();
		}
	}
	
	@Override
	public void render () {
		super.render();
	}

	public User getUser() {
		return user;
	}
	
	public Skin getSkin() {
		if (skin == null) {
			skin = new Skin();
			FileHandle fileHandle = Gdx.files.internal("data/uiskin.json");
			FileHandle atlasFile = fileHandle.sibling("uiskin.atlas");

			if (atlasFile.exists()) {
			    skin.addRegions(new TextureAtlas(atlasFile));
			}

			skin.load(fileHandle);		
		}
		return skin;
	}
}
