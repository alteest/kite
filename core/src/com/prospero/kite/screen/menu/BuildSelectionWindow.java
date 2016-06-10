package com.prospero.kite.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.prospero.kite.model.StationType;
import com.prospero.kite.model.factory.AssetFactory;

public class BuildSelectionWindow extends Window {

	public final Screen screen;
	private int buttonsRows = 2;
	
	public BuildSelectionWindow(String title, Skin skin, Screen screen) {
		super(title, skin);
		this.screen = screen;

		defaults().spaceBottom(10);
		//window.row().fill().expandX();
		
		ImageTextButton button;
		BitmapFont font = new BitmapFont();
		int col = (StationType.values().length - 1) / buttonsRows + 1;
		int i = 0;
		for (StationType type : StationType.values()) {
			if (type != StationType.Unknown) {
				//button = new TextButton(type.toString(), skin, "toggle");
				//ImageTextButtonStyle style = new ImageTextButtonStyle(skin.get(ImageTextButtonStyle.class));
				//style.imageUp = new TextureRegionDrawable(new TextureRegion(AssetFactory.getStationImage(type)));
				//style.imageDown = new TextureRegionDrawable(new TextureRegion(AssetFactory.getStationImage(type)));
				ImageTextButtonStyle style = new ImageTextButtonStyle(
						new TextureRegionDrawable(new TextureRegion(AssetFactory.getStationImage(type))),
						new TextureRegionDrawable(new TextureRegion(AssetFactory.getStationImage(type))),
						new TextureRegionDrawable(new TextureRegion(AssetFactory.getStationImage(type))),
						font);
				button = new ImageTextButton(type.toString(), style);
				//button.clear();
				button.clearChildren();
				button.row();
				button.add(button.getImage());
				button.row();
				button.add(button.getLabel());
				button.row();
				button.setUserObject(type);
				//button.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
				//add(button).width(Gdx.graphics.getHeight() / 10).height(Gdx.graphics.getHeight() / 10 + font.getLineHeight());
				add(button).width(Gdx.graphics.getHeight() / 5).height(Gdx.graphics.getHeight() / 5);
				i++;
				if (i > col) {
					row();
				}
			}
		}
		setPosition(Gdx.graphics.getWidth() - getWidth(), 0);
		pack();
	}
}
