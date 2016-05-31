package com.prospero.kite.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.prospero.kite.model.StationType;

public class StationTypeSelectionMenu extends Window {

	public final Screen screen;
	public final ClickListener listener = new StationTypeSelectionMenuClickListener();

	public StationTypeSelectionMenu(String title, Skin skin, Screen screen) {
		super(title, skin);
		this.screen = screen;

		defaults().spaceBottom(10);
		//window.row().fill().expandX();
		
		Button button;
		for (StationType type : StationType.values()) {
			if (type != StationType.Unknown) {
				button = new TextButton(type.toString(), skin, "toggle");
				button.addListener(listener);
				button.setUserObject(type);
				//button.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
				add(button).width(Gdx.graphics.getWidth() / 10).height(Gdx.graphics.getHeight() / 10);
				row();
			}
		}

		setPosition(Gdx.graphics.getWidth() - getWidth(), 0);
		pack();
//		/addListener(this);
    }
}
