package com.prospero.kite.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.prospero.kite.model.factory.ModelFactory;

public class Station1 extends Station {
	
	
	public Station1(Station station) {
		super(station.name, station.distance, station.direction, station.scale);
	}

	/*@Override
	protected void initModel() {
		type = StationType.Station1;
		instance = new ModelInstance(ModelFactory.getStation(type), x, y, z);
        calculateCoordinates();

        orbit = new ModelInstance(ModelFactory.getCircle(distance), 0, 0, 0);
        orbit.materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse, Color.WHITE));
	}*/
}
