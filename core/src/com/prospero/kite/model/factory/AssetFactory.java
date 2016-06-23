package com.prospero.kite.model.factory;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.prospero.kite.model.Station;
import com.prospero.kite.model.Station1;
import com.prospero.kite.model.Station2;
import com.prospero.kite.model.Station3;
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
		return getTexture(getStationStringImage(type));
	}

	private static String getStationStringImage(StationType type) {
		switch (type) {
		case Station1:
			return Station1.getStringImage();
		case Station2:
			return Station2.getStringImage();
		case Station3:
			return Station3.getStringImage();
		default:
			return Station.getStringImage();
		}
	}
}
