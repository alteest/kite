package com.prospero.kite.model.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class TextureFactory {

	private static Map<String, Texture> textures = new HashMap<String, Texture>();
	
	public static void init() {
		if (textures.size() == 0) {
			/*textures.put("Ako", new Texture("images/planet_7_d.png"));
			textures.put("Spot", new Texture("images/planet_6_d.png"));
			textures.put("Rim", new Texture("images/planet_5_d.png"));
			textures.put("Dust", new Texture("images/planet_4_d.png"));
			textures.put("Dante", new Texture("images/planet_3_d.png"));
			textures.put("Reststop", new Texture("images/planet_2_d.png"));
			textures.put("Down", new Texture("images/planet_1_d.png"));

			textures.put("Avalon", new Texture("images/planet_Avalon_1600.jpg"));
			textures.put("Blink", new Texture("images/planet_blink_1600.jpg"));
			textures.put("Cerca Trova", new Texture("images/planet_Cerca_Trova_1600.jpg"));
			textures.put("Miners Moon", new Texture("images/planet_Miners_Moon_1600.jpg"));
			textures.put("Serendip", new Texture(Gdx.files.internal("images/planet_serendip_1600.jpg"), true));
			textures.put("Song", new Texture("images/planet_Song_1600.jpg"));
			textures.put("Tao Seti", new Texture("images/planet_Tao_Seti_1600.jpg"));
			textures.put("Wight", new Texture("images/planet_Wight_1600.jpg"));*/
		}
	}
	
	public static Texture getTexture(String name) {
		if (textures.containsKey(name)) {
			return textures.get(name);
		} else {
			if (!textures.containsKey("default")) {
				textures.put("default", new Texture("images/planet_Wight_1600.jpg"));
			}
			return getTexture("default");
		}
	}
}
