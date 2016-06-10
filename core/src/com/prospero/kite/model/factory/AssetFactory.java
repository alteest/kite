package com.prospero.kite.model.factory;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.prospero.kite.model.StationType;

public class AssetFactory {

	private static AssetManager assets = new AssetManager();
	
	public static Texture getTexture(String name) {
		if (!assets.isLoaded(name, Texture.class)) {
			assets.load(name, Texture.class);
			assets.finishLoadingAsset(name);
		}
		return assets.get(name, Texture.class);
	}

	public static Texture getStationImage(StationType type) {
		return getTexture("images/ui/" + type.toString() + ".png");
	}
	
}
