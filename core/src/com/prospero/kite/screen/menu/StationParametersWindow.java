package com.prospero.kite.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.prospero.kite.model.Station;

public class StationParametersWindow extends Window {

	public final Station station;

	public StationParametersWindow(Skin skin, Station station) {
		super("Station", skin);
		this.station = station;

		defaults().spaceBottom(10);
		row().fill().expandX();

		Table t = new Table();
		t.columnDefaults(0).left();
		t.columnDefaults(1).padLeft(10);
		t.row();
		t.add(new Label("Name : ", skin));
		t.add(new Label(station.getName(), skin));
		t.row();
		t.add(new Label("Type : ", skin));
		t.add(new Label(station.getStationType().toString(), skin));
		add(t);

		setPosition(Gdx.graphics.getWidth() - getWidth(), 0);
		pack();
//		/addListener(this);
    }
}
