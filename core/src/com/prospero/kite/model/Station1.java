package com.prospero.kite.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.prospero.kite.model3d.ModelFactory;

public class Station1 extends Station {
	
	protected static StationType type = StationType.Station1;
	
	public Station1(Station station) {
		super(station.name, station.distance, station.position, station.r);
		instance = new ModelInstance(ModelFactory.getStation(type));
	}

	@Override
	protected void initModel() {
		instance = new ModelInstance(ModelFactory.getStation(type));
        calculateCoordinates();

        orbit = new ModelInstance(ModelFactory.getCircle(distance), 0, 0, 0);
        orbit.materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse, Color.WHITE));
	}
	
}
