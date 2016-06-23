package com.prospero.kite.model;

public class Station1 extends Station {
	
	protected static String texture = "images/images/tiles_16.png";
	
	public Station1(Station station) {
		super(station.name, station.distance, station.direction, station.scale);
	}

	public static String getStringImage() {
		return texture;
	}
}
