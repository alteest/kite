package com.prospero.kite.model;

public class Station2 extends Station {
	
	protected static String texture = "images/images/tiles_15.png";
	
	public Station2(Station station) {
		super(station.name, station.distance, station.direction, station.scale);
	}

	public static String getStringImage() {
		return texture;
	}
}
