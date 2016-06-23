package com.prospero.kite.model;

public class Station3 extends Station {
	
	protected static String texture = "images/images/tiles_14.png";
	
	public Station3(Station station) {
		super(station.name, station.distance, station.direction, station.scale);
	}

	public static String getStringImage() {
		return texture;
	}
}
