package com.prospero.kite.model3d;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetFactory {

	private static AssetManager assets = new AssetManager();
	
	public static Texture getTexture(String name) {
		if (!assets.isLoaded(name, Texture.class)) {
			assets.load(name, Texture.class);
			assets.finishLoadingAsset(name);
		}
		return assets.get(name, Texture.class);
	}
}
